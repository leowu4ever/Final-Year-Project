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

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import processing.app.Sketch;
import processing.app.SketchCode;



/**
 * 
 * @author Thomas Diewald, 2014
 *
 */
public final class TabSetting implements Comparable<TabSetting>{
  
  protected static final String STYLE_TEMPLATE_STRING = "// TabManager.config: id=1.0; margin=0,0,0; height=25; col=200,200,200;";
  
  private static final String tag_id_TOOLNAME = "TabManager";
  private static final String tag_id_TOOLJOB  = "config";

  private static final String tag_value_name_ID      = "id";
  private static final String tag_value_name_MARGIN  = "margin";
  private static final String tag_value_name_HEIGHT  = "height";
  private static final String tag_value_name_COLOR   = "col";
  
  
  protected final static float Default_ID     = Float.NaN;
  protected final static int[] Default_MARGIN = new int[]{10,-2,0}; 
  protected final static int   Default_HEIGHT = 25;
  protected final static int   Default_COLOR  = new Color(230,230,230).getRGB();

  protected float tag_value_ID    ;
  protected int[] tag_value_MARGIN = new int[3];
  protected int   tag_value_HEIGHT;
  protected int   tag_value_COLOR ;
  
  protected String sketch_name = "";
  protected int    sketch_code_index  = 0;
  protected SketchCode   sketch_code = null;
  
  
  public void unstyledDefaults(){
    sketch_name = "";
    sketch_code_index = 0;
    sketch_code = null;
    
    tag_value_ID     = Default_ID;
    tag_value_MARGIN[0] = Default_MARGIN[0];
    tag_value_MARGIN[1] = Default_MARGIN[1];
    tag_value_MARGIN[2] = Default_MARGIN[2];
    tag_value_HEIGHT = Default_HEIGHT;
    tag_value_COLOR  = Default_COLOR;
  }
  
  public boolean compareSettings(TabSetting ts){
    
    if( !sketch_name.equals(ts.sketch_name )            ) { return false; }
    if( sketch_code_index != ts.sketch_code_index       ) { return false; }
    if( tag_value_ID      != ts.tag_value_ID            ) { return false; }
    for(int i = 0; i < tag_value_MARGIN.length; i++ ){
      if( tag_value_MARGIN[i] != ts.tag_value_MARGIN[i] ) { return false; }
    }
    if( tag_value_HEIGHT      != ts.tag_value_HEIGHT    ) { return false; }
    if( tag_value_COLOR       != ts.tag_value_COLOR     ) { return false; }
    
    return true;
  }

 
  protected TabSetting(){
    unstyledDefaults();
  }
  
  protected final void printValues(){     
    System.out.println( "SKETCHCODE        = "+ sketch_code.getFileName()  );
    System.out.println( "tag_value_ID_     = "+ tag_value_ID    );
    System.out.println( "tag_value_MARGIN_ = "+ tag_value_MARGIN[0] +", "+tag_value_MARGIN[1]+", "+tag_value_MARGIN[2] );
    System.out.println( "tag_value_HEIGHT_ = "+ tag_value_HEIGHT);
    System.out.println( "tag_value_COLOR   = "+ tag_value_COLOR  );
  }
  

  public final static TabSetting[] extractTabSettings(Sketch sketch){
    SketchCode sketch_codes[] = sketch.getCode();
    TabSetting[] tab_settings = new TabSetting[sketch_codes.length];

    for(int i = 0; i < sketch_codes.length; i++){
      tab_settings[i] = new TabSetting();

      tab_settings[i].sketch_code = sketch_codes[i];
      tab_settings[i].sketch_code_index = i;
      tab_settings[i].sketch_name = sketch_codes[i].getFileName();
      String text = sketch_codes[i].getProgram();
      
      Document d = sketch_codes[i].getDocument();
      try {
        if( d != null){
          text = d.getText(0, d.getLength());
        }
      } catch (BadLocationException e) {
  //      e.printStackTrace();
      }
      
      String line = text;
      int end_index = text.indexOf("\n");
      if( end_index >= 0)
        line = text.substring(0, end_index);
       
      parseValues( tab_settings[i], line);
    }
    return tab_settings;
  }


  
  private final static void parseValues(TabSetting tab_setting, String line){  
    if ( line != null && line.contains( tag_id_TOOLNAME )) {
      String parts_1[] = line.split(":");
      if ( parts_1 != null && parts_1.length > 1) {
        
        String tag_id     = parts_1[0];
        String tag_values = parts_1[1];
        
        tag_id = tag_id.replaceAll("/", "").trim();
        String tag_id_parts[] = tag_id.split("[.]");
 
        if ( tag_id_parts != null && 
             tag_id_parts.length == 2 &&
             tag_id_parts[0].equals( tag_id_TOOLNAME ) &&
             tag_id_parts[1].equals( tag_id_TOOLJOB  ) )
        {

          String tag_values_parts[] = tag_values.split(";");
          
          for (int i = 0; i < tag_values_parts.length; i++) {
            String value = tag_values_parts[i].trim();
            String value_parts[] = value.split("=");
            
            if ( value_parts!= null && value_parts.length == 2) {
              String tag_value_name = value_parts[0].trim();
              String tag_value      = value_parts[1].trim();
              
              if (tag_value_name.equals(tag_value_name_ID     ))  tab_setting.tag_value_ID     = extractTagValue_ID     (tag_value);
              if (tag_value_name.equals(tag_value_name_MARGIN ))  tab_setting.tag_value_MARGIN = extractTagValue_MARGIN (tag_value, tab_setting.tag_value_MARGIN);              
              if (tag_value_name.equals(tag_value_name_HEIGHT ))  tab_setting.tag_value_HEIGHT = extractTagValue_HEIGHT (tag_value);
              if (tag_value_name.equals(tag_value_name_COLOR  ))  tab_setting.tag_value_COLOR  = extractTagValue_COLOR  (tag_value);
            }
          }
        }
      }
    }
  }
  
  
  private static final float extractTagValue_ID(String tag_value) {
    try{
      return Float.valueOf(tag_value);
    } catch (NumberFormatException e){
      return Default_ID;
    }
  }

  private static final int[] extractTagValue_MARGIN(String tag_value, int margin[]) {
    String[] values_as_string = tag_value.split(",");
    
    int length = margin.length > values_as_string.length ? values_as_string.length  : margin.length;
    for(int i = 0; i < length; i++){
      try{
        margin[i] = Integer.valueOf(values_as_string[i].trim());
//        if (i > 0 && margin[i] < 0) {
//          margin[i] = 0;
//        }
      } catch (NumberFormatException e){
//        margin[0] = Default_MARGIN[0];
//        margin[1] = Default_MARGIN[1];
//        margin[2] = Default_MARGIN[2];
      }
    }
    return margin;
  }

  private static final int extractTagValue_HEIGHT(String tag_value) {
    try{
      return Integer.valueOf(tag_value).intValue();
    } catch (NumberFormatException e){
      return Default_HEIGHT;
    }
  }


  private static final int extractTagValue_COLOR(String tag_value) {
    
    if(  tag_value.charAt(0) == '#' ) {
      if( tag_value.length() == 7){
        tag_value = tag_value.substring(1, 7);
        try{
          return Integer.valueOf(tag_value, 16);
        } catch (NumberFormatException e){
          return Default_COLOR;
        }
      }
    } 
    
    String[] parts = tag_value.split(",");
    if( parts.length == 3 ){
      try {
        int r = Integer.parseInt( parts[0].trim() );
        int g = Integer.parseInt( parts[1].trim() );
        int b = Integer.parseInt( parts[2].trim() );
        
        if( r < 0 ) r = 0; else if( r > 255) r = 255;
        if( g < 0 ) g = 0; else if( g > 255) g = 255;
        if( b < 0 ) b = 0; else if( b > 255) b = 255;
        
        return 0xFF000000 | r<<16 | g<<8 | b;

      } catch (NumberFormatException e){
      }
    }

    return Default_COLOR;
  }


  @Override
  public int compareTo(TabSetting o) {
    if(  Float.isNaN(o.tag_value_ID ) ){
      return -1;
    }
    if( tag_value_ID  > o.tag_value_ID ){ 
       return 1;
    }
    if( tag_value_ID  < o.tag_value_ID){
      return -1;
    }
    
    return 0;
  }

  
}
