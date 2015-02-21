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

import java.awt.Frame;

import processing.app.Editor;


/**
 * 
 * @author Thomas Diewald, 2014
 *
 */
public class DockTool2Base {
  
  private final int MAX_GAP = 20;
  private int docking_border = 0;
  private int dock_on_editor_y_offset = 0;
  private int dock_on_editor_x_offset = 0;
  
  private TabManager tool;
  
  ////////////////////////  
  //     ____2____      //
  //    |         |     //
  //    |         |     //
  //  0 | editor  | 1   //
  //    |         |     //
  //    |_________|     //
  //         3          //
  ////////////////////////
  

  public DockTool2Base(TabManager tool){
    this.tool = tool;
  }
 
  public void reset(){
    dock_on_editor_y_offset = 0;
    dock_on_editor_x_offset = 0;
    docking_border = 0;
  }
  
  public boolean isDocked(){
    return (docking_border != -1);
  }
  
  public void tryDocking(){
 
    Editor editor = tool.editor;
    Frame  frame  = tool.frame;
    
    if( editor == null ){
      return;
    }
    
    int ex = editor.getX();
    int ey = editor.getY();
    int ew = editor.getWidth();
    int eh = editor.getHeight();

    int fx = frame.getX();
    int fy = frame.getY();
    int fw = frame.getWidth();
    int fh = frame.getHeight();
    

    if( ((fy    > ey) && (fy    < ey+eh)) ||
        ((fy+fh > ey) && (fy+fh < ey+eh))
    ){
      int gap_l = Math.abs( ex-(fx+fw) );
      int gap_r = Math.abs( (ex+ew)-fx );
      
      if( gap_l < MAX_GAP  || gap_r < MAX_GAP  ){
        docking_border = (gap_l < gap_r ) ? 0 : 1;
        dock_on_editor_y_offset = fy-ey;
        dock();
        return;
      }
    }
    
    
    if( ((fx    > ex) && (fx    < ex+ew)) ||
        ((fx+fw > ey) && (fx+fw < ex+ew))
    ){
      int gap_t = Math.abs( ey-(fy+fh) );
      int gap_b = Math.abs( (ey+eh)-fy );
      
      if( gap_t < MAX_GAP  || gap_b < MAX_GAP  ){
        docking_border = ( gap_t < gap_b  ) ? 2 : 3;
        dock_on_editor_x_offset = fx-ex;
        dock();
        return;
      }
    }
    
  }
  
  
  public void dock(){

    Editor editor = tool.editor;
    Frame  frame  = tool.frame;
    
    if( editor == null ){
      return;
    }
    
    int ex = editor.getX();
    int ey = editor.getY();
    int ew = editor.getWidth();
    int eh = editor.getHeight();
    
    int fw = frame.getWidth();
    int fh = frame.getHeight();
    
    switch(docking_border){
      case -1: return;
      case  0: frame.setLocation( ex-fw, ey+dock_on_editor_y_offset );  break;
      case  1: frame.setLocation( ex+ew, ey+dock_on_editor_y_offset );  break;
      case  2: frame.setLocation( ex+dock_on_editor_x_offset, ey-fh );  break;
      case  3: frame.setLocation( ex+dock_on_editor_x_offset, ey+eh );  break;
    }
  }
  
 
}
