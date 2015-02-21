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



import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.LookAndFeel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;



/**
 * 
 * @author Thomas Diewald, 2014
 *
 */
public class Style {
  
  public static Image PROG_ICON;
  
  public static final Font font_mono_10 = new Font(Font.MONOSPACED, Font.PLAIN, 10);
  public static final Font font_mono_11 = new Font(Font.MONOSPACED, Font.PLAIN, 11);
  
  public static final Color GRAY_250 = new Color(250, 250, 250);
  public static final Color GRAY_240 = new Color(240, 240, 240);
  public static final Color GRAY_230 = new Color(230, 230, 230);
  public static final Color GRAY_220 = new Color(220, 220, 220);
  public static final Color GRAY_210 = new Color(210, 210, 210);
  public static final Color GRAY_200 = new Color(200, 200, 200);
  public static final Color GRAY_190 = new Color(190, 190, 190);
  public static final Color GRAY_180 = new Color(180, 180, 180);
  public static final Color GRAY_160 = new Color(160, 160, 160);
  public static final Color GRAY_140 = new Color(140, 140, 140);
  public static final Color GRAY_120 = new Color(120, 120, 120);
  public static final Color GRAY_110 = new Color(110, 110, 110);
  public static final Color GRAY_100 = new Color(100, 100, 100);
  public static final Color GRAY_80  = new Color( 80,  80, 80);
  public static final Color GRAY_50  = new Color( 50,  50, 50);
  public static final Color GRAY_40  = new Color( 40,  40, 40);
  public static final Color GRAY_30  = new Color( 30,  30, 30);
  public static final Color GRAY_20  = new Color( 20,  20, 20);
  public static final Color GRAY_15  = new Color( 15,  15, 15);
  public static final Color GRAY_10  = new Color( 10,  10, 10);
  public static final Color GRAY_5   = new Color(  5,   5,  5);
  public static final Color GRAY_0   = new Color(  0,   0,  0);
  
  public static final Color PANEL_BG_COL = GRAY_50;

  static LookAndFeel laf_orig;
  static LookAndFeel laf_new;
  
  public static void init(){
//    PROG_ICON = Toolkit.getDefaultToolkit().getImage("data/TabManager_icon.png");
    
    try{
      PROG_ICON = new ImageIcon(Style.class.getClassLoader().getResource("data/TabManager_icon.png")).getImage();
    } catch(Exception e){
      e.printStackTrace();
    }
    
    laf_orig = UIManager.getLookAndFeel();

    try
    {
      LookAndFeelInfo[] laf_info = UIManager.getInstalledLookAndFeels();
      
  //    System.out.println("number of lafs = "+laf_info.length);
      for (LookAndFeelInfo info : laf_info) {
  //      System.out.println(info);
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
  //      if ("Metal".equals(info.getName())) {
  //        UIManager.setLookAndFeel(info.getClassName());
  //        break;
  //      }
      }
      
  //    UIManager.setLookAndFeel(laf_info[0].getClassName());
  
      laf_new = UIManager.getLookAndFeel();

      UIDefaults laf_def = laf_new.getDefaults();
  //------------------------------------------------------------------------------
  //http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/_nimbusDefaults.html
  //------------------------------------------------------------------------------
  //    laf_def = null;
      if( laf_def != null ){
        laf_def.put("defaultFont"      , new Font(Font.SANS_SERIF, 0, 12));
        
        // dark
        laf_def.put("textForeground"   , GRAY_200);
        laf_def.put("background"       , GRAY_50);

        // bright
//        laf_def.put("textForeground"   , GRAY_20);
//        laf_def.put("background"       , GRAY_200);
        
        
        laf_def.put("nimbusBase"       , GRAY_20);
        laf_def.put("nimbusBlueGrey"   , GRAY_100);
        laf_def.put("nimbusBorder"     , GRAY_100);
//        laf_def.put("text"             , GRAY_140);
//        laf_def.put("Button.background", new Color(255,0,0));
        laf_def.put("nimbusFocus"      , new Color(255,100,0));
//        laf_def.put("control"          , GRAY_100);
//        laf_def.put("scrollbar"        , GRAY_0);
//        laf_def.put("controlShadow"    , GRAY_200);            
      }
  
      resetLAF();
  
  
    } catch ( Exception e ){
      e.printStackTrace();
    }
    
  }
  

  
  public static void activateLAF(){
    try {
      UIManager.setLookAndFeel( laf_new );
    } catch (UnsupportedLookAndFeelException e) {
//      e.printStackTrace();
    }
  }
  
  // reset LAF the processing-ide style gets changed too.
  public static void resetLAF(){
    try {
      UIManager.setLookAndFeel( laf_orig );
    } catch (UnsupportedLookAndFeelException e) {
//      e.printStackTrace();
    }
  }
  

}
