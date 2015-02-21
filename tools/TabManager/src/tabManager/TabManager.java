/**
 * 
 * TabManager v2.0.0
 * 
 * PDE-Tabs are listed vertically in a separate window.
 * 
 * 
 * 
 *   (c) 2014    Thomas Diewald
 *               http://thomasdiewald.com
 *   
 *   last built: 06/29/2014
 *   
 *   download:   http://thomasdiewald.com/processing/tools/TabManager/
 *   source:     https://github.com/diwi/ 
 *   
 *   tested OS:  osx,windows
 *   processing: 1.x, 2.x.x
 *
 *
 *
 *
 * This source is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * A copy of the GNU General Public License is available on the World
 * Wide Web at <http://www.gnu.org/copyleft/gpl.html>. You can also
 * obtain it by writing to the Free Software Foundation,
 * Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */



package tabManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import javax.swing.border.Border;

import processing.app.Editor;
import processing.app.Sketch;
import processing.app.SketchCode;
import processing.app.tools.Tool;


/**
 * 
 * @author Thomas Diewald, 2014
 *
 */
public class TabManager implements Tool {
  
  static{
    Style.init();
  }

  protected Editor editor;
  protected JFrame frame;

  private String sketchname;
  private String tm_name;
  
     private static final String TOOL_AUTHOR   = "Thomas Diewald, http://thomasdiewald.com";
     private static final String TOOL_NAME     = "TabManager";
     private static final String TOOL_VERSION  = "2.0.0 (2)";
//  private static final String TOOL_AUTHOR   = "Thomas Diewald, www.thomasdiewald.com";
//  private static final String TOOL_NAME     = "TabManager_WIP";
//  private static final String TOOL_VERSION  = "2.0.0 (2)";
  
  protected DockTool2Base tooldock; // docks the TabManager to the PDE
  
  private Thread sync_task; // Thread: for snychronizing PDE and TabManager

  private HashMap<String, JToggleButton> map_tab_jtb;
  private TabSetting[] tab_settings = new TabSetting[0];
  private boolean autoupdate = false;
  
  
//  public TabManager_WIP(){
//  }

  @Override
  public String getMenuTitle() {
    return TOOL_NAME;
  }
  
  @Override
  public void init(Editor p5_editor) {
    this.editor = p5_editor;
  }
  
  @Override
  public void run() {
    sketchname = editor.getSketch().getName();
    System.out.printf("%s %s by %s\n", TOOL_NAME, TOOL_VERSION, TOOL_AUTHOR);

    if( frame == null ){
      createAndShowGUI();
      synchronizeTabs();
    } else {
      tooldock.dock();
      frame.setVisible(true);
      frame.toFront();
      frame.setExtendedState(Editor.NORMAL);
    }

    
    if( sync_task == null ){
      sync_task = new TabThread();
      sync_task.start();
    }
 
  }


  public void quit(){
    removeListeners();
   
    if( sync_task != null ){
      sync_task.interrupt();
      try {
        sync_task.join();
      } catch (InterruptedException e) {
      }
      sync_task = null;
    }
    
    frame.setVisible(false); 
    frame.dispose();
    frame = null;
    tab_settings = new TabSetting[0];
    map_tab_jtb = null;
    tooldock = null;

    System.gc();
    System.gc();  
  }

  
  private class TabThread extends Thread {

    @Override
    public void run() {
//      System.out.println("TabThread start");
      while(!Thread.currentThread().isInterrupted()){
        try {
          sleep(200);
          synchronizeTabs();
        } catch(InterruptedException e) {
          interrupt();
        }
      }

    }
  }



  
  private void synchronizeTabs(){

    Sketch       sketch = editor.getSketch();
    SketchCode   sc_cur = sketch.getCurrentCode();
    SketchCode[] sc_all = sketch.getCode();
    

    ___REBUILD_UI___:
    {
      
      //--------------------------------------------------------------------------
      // ... tab-number has changed
      if( tab_settings == null || sc_all.length != tab_settings.length){
        buildTabButtons(null);
        break ___REBUILD_UI___;
      }
      
      //--------------------------------------------------------------------------
      // .. tab-name has changed
      for(int i = 0; i < tab_settings.length; i++){
        String sc_name = sketch.getCode(tab_settings[i].sketch_code_index).getFileName();
        if( !tab_settings[i].sketch_name.equals(sc_name) ){
          buildTabButtons(null);
          break ___REBUILD_UI___;
        }
      }

      //--------------------------------------------------------------------------
      // ... tab/sketch was modified AND auto-update is enabled
      if( autoupdate && sketch.isModified()){
        
        TabSetting[] tab_settings_tmp = TabSetting.extractTabSettings(editor.getSketch());
        sortTabs(tab_settings_tmp);
        
        boolean allthesame = true;
        for(int i = 0; i < tab_settings.length; i++ ){
          if( !tab_settings[i].compareSettings(tab_settings_tmp[i]) ){
            allthesame = false;
            break;
          }
        }
        if( !allthesame ) {
          buildTabButtons(tab_settings_tmp);
          break ___REBUILD_UI___;
        }
      }
    }


    // make sure the same tab is selected as in the IDE
    JToggleButton tb = map_tab_jtb.get(sc_cur.getFileName());
    if( tb != null && !tb.isSelected()){
      tb.setSelected(true);
      tb.requestFocusInWindow();
    }
  }

  
  
  
  private void sortTabs(TabSetting[] ts){
    // correct tabs without an ID
    
    // find max ID
    float max_ID = Float.NEGATIVE_INFINITY;
    for(int i = 0; i < ts.length; i++){
      if( !Float.isNaN( ts[i].tag_value_ID ) ){
        max_ID = Math.max(max_ID, ts[i].tag_value_ID);
      }
    }

    max_ID = (int)(max_ID+10);

    // assign tabs, with NaN-ID the max ID
    for(int i = 0; i < ts.length; i++){
      if( Float.isNaN( ts[i].tag_value_ID ) ){
        ts[i].tag_value_ID = max_ID;
      }
    }

    Arrays.sort(ts);
  }



  
  

  /**
   * 
   * creates for each tab in the PDE a togglebutton in the tabmanager
   * 
   */
  private void buildTabButtons(TabSetting[] newtabs){
    
    //--------------------------------------------------------------------------
    Style.activateLAF();

    //--------------------------------------------------------------------------
    // load/parse settings
    tab_settings = newtabs;
    if( newtabs == null ){
      tab_settings = TabSetting.extractTabSettings(editor.getSketch());
      sortTabs(tab_settings);
    }
    
    //--------------------------------------------------------------------------
    // associate buttons with tab-names for "programatic" selection
    map_tab_jtb = new HashMap<String, JToggleButton>();

    //--------------------------------------------------------------------------
    // PANEL CENTER: rebuild layout/buttons
    panel_center.removeAll();
    panel_center.validate();
    
    ButtonGroup group = new ButtonGroup();
    JToggleButton[] tab_buttons = new JToggleButton[tab_settings.length];

    for(int i = 0; i < tab_settings.length; i++){
      
      final TabSetting tab_setting = tab_settings[i];
      final JToggleButton tab_button = new JToggleButton(tab_setting.sketch_name);

      int x = tab_setting.tag_value_MARGIN[0] + 5;
      int y = tab_setting.tag_value_MARGIN[1];
      int w = tab_button.getPreferredSize().width;
      int h = tab_setting.tag_value_HEIGHT;
      
      if( i == 0 ){
        y += 10;
      } else {
        Rectangle bounds = tab_buttons[i-1].getBounds();
        //        x += tab_buttons[i-1].getBounds().x;
        y += bounds.y;
        y += bounds.height;
        y += tab_settings[i-1].tag_value_MARGIN[2];
      } 

      x = Math.max(x, 5);
      y = Math.max(y, 10);
      w = Math.max(w, 4);
      h = Math.max(h, 2);
      
      
      tab_button.setBounds(x, y, w, h);
      tab_button.setForeground( new Color(tab_setting.tag_value_COLOR));

      tab_button.addActionListener( new ActionListener() {
        @Override public void actionPerformed( ActionEvent e ) {
          JToggleButton tBtn = (JToggleButton)e.getSource();
          if( tBtn.isSelected() ){
            editor.getSketch().setCurrentCode( tab_setting.sketch_code_index );
          }
        }
      });
      
      
      group.add( tab_button );
      panel_center.add( tab_button);
      map_tab_jtb.put(tab_setting.sketch_name, tab_button);
      tab_buttons[i] = tab_button;
    }

    //--------------------------------------------------------------------------
    panel_center.repaint();
    panel_center.validate();
    frame.validate();
    
    //--------------------------------------------------------------------------
    Style.resetLAF();
  }
  
  
  
  

  private JPanel panel_north ; 
  private JPanel panel_center ;
  private JPanel panel_south  ;
  
  /**
   * 
   * creates the basic UI for the TabManager.
   * 
   */
  private void createAndShowGUI() {
    //--------------------------------------------------------------------------
    Style.activateLAF();
    
    //--------------------------------------------------------------------------
    // JFRAME
    frame = new JFrame();


//    KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
//    Action escapeAction = new AbstractAction() {
//      private static final long serialVersionUID = 1L;
//      public void actionPerformed(ActionEvent e) {
//        frame.dispose();
//      }
//    }; 
//    frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
//    frame.getRootPane().getActionMap().put("ESCAPE", escapeAction);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    

    //--------------------------------------------------------------------------
    // MAIN_PANEL: north/center/south
    JPanel main_panel   = new JPanel(new BorderLayout());
    
    panel_north  = new JPanel(new GridLayout(1,3));
    panel_center = new JPanel(new AbsolutLayout());
    panel_south  = new JPanel(new GridLayout(2,2));
    JScrollPane panel_center_scroll = new JScrollPane(panel_center);


    Border border1 = BorderFactory.createRaisedBevelBorder();
    Border border2 = BorderFactory.createLoweredBevelBorder();

//    panel_north.setBorder(border1);
    panel_center.setBorder(border2);
//    panel_center_scroll.setBorder(border2);
//    panel_south.setBorder(border1);
    main_panel.setBorder(border1);

    main_panel.add(panel_south,         BorderLayout.NORTH);
    main_panel.add(panel_center_scroll, BorderLayout.CENTER);
    main_panel.add(panel_north,         BorderLayout.SOUTH);





    //--------------------------------------------------------------------------
    // PANEL NORTH
    final JToggleButton tb_update_auto   = new JToggleButton("auto"); 
    final JButton b_update_manual = new JButton("update"); 
    final JButton b_style_example = new JButton("style"); 
    final JButton b_colorscheme = new JButton("colors"); 

    tb_update_auto.addActionListener( new ActionListener() {
      @Override public void actionPerformed( ActionEvent e ) {
        autoupdate = tb_update_auto.isSelected();
      }
    });

    b_update_manual.addActionListener( new ActionListener() {
      @Override public void actionPerformed( ActionEvent e ) {
        buildTabButtons(null);
      }
    });

    b_style_example.addActionListener( new ActionListener() {
      @Override public void actionPerformed( ActionEvent e ) {
        System.out.println( "TabManager: style template > ");
        System.out.println( TabSetting.STYLE_TEMPLATE_STRING);
      }
    });

    panel_north.add(tb_update_auto);
    panel_north.add(b_update_manual);
    panel_north.add(b_style_example);

    
    
    
    //--------------------------------------------------------------------------
    // PANEL SOUTH
    JButton b_add    = new JButton("add");
    JButton b_new    = new JButton("new");
    JButton b_delete = new JButton("delete");
    JButton b_rename = new JButton("rename");

    b_add.addActionListener( new ActionListener() {
      @Override public void actionPerformed( ActionEvent e ) {
        editor.getSketch().handleAddFile();
      }
    });

    b_new.addActionListener( new ActionListener() {
      @Override public void actionPerformed( ActionEvent e ) {
        editor.getSketch().handleNewCode();
      }
    });

    b_delete.addActionListener( new ActionListener() {
      @Override public void actionPerformed( ActionEvent e ) {
        editor.getSketch().handleDeleteCode();
      }
    });

    b_rename.addActionListener( new ActionListener() {
      @Override public void actionPerformed( ActionEvent e ) {
        editor.getSketch().handleRenameCode();
      }
    });


    panel_south.add(b_add   );
    panel_south.add(b_new   );
    panel_south.add(b_delete);
    panel_south.add(b_rename);




    //--------------------------------------------------------------------------
    // finish frame
    frame.add(main_panel, BorderLayout.CENTER);
    
    
    tooldock = new DockTool2Base(this);
    addListeners();
    
    
    frame.setFocusable(true);
    frame.setIconImage(Style.PROG_ICON);
    frame.setTitle(" "+sketchname+" | "+TOOL_NAME);
    frame.setPreferredSize( new Dimension(250, 500));
    frame.pack();
    tooldock.dock();
    frame.setVisible(true);
    frame.toFront();
    frame.setExtendedState(Editor.NORMAL);

    
    //--------------------------------------------------------------------------
    Style.resetLAF();
  }
  
  
  private ToolListener.EditorListener editor_listener;
  private ToolListener.FrameListener  frame_listener;
  
  private void addListeners(){
    if( editor_listener != null && frame_listener != null){
      return;
    }
    editor_listener = new ToolListener.EditorListener(this);
    frame_listener  = new ToolListener.FrameListener(this);
    
    if( editor != null ){
      editor.addWindowListener   ( editor_listener );
      editor.addComponentListener( editor_listener );
    }
    frame.addWindowListener    ( frame_listener  );
    frame.addComponentListener ( frame_listener  );
  }
  

  private void removeListeners(){
    if( editor_listener == null && frame_listener == null){
      return;
    }
    
    if( editor != null ){
      editor.removeWindowListener   (editor_listener);
      editor.removeComponentListener(editor_listener);
    }
    frame.removeWindowListener    (frame_listener );
    frame.removeComponentListener (frame_listener );

    editor_listener = null;
    frame_listener  = null;
  }
  
  

  public static void main(String[] args){

    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new TabManager().createAndShowGUI();
      }
    });
    
  }

}
