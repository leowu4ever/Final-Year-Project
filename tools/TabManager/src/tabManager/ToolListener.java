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

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import processing.app.Editor;



/**
 * 
 * @author Thomas Diewald, 2014
 *
 */
public class ToolListener {

  static public class EditorListener implements WindowListener, ComponentListener{

    TabManager tool;
    
    public EditorListener(TabManager parent){
      this.tool = parent;
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
      tool.quit();
    }

    @Override
    public void windowClosed(WindowEvent e) {
      tool.quit();
    }

    @Override
    public void windowIconified(WindowEvent e) {
      tool.frame.setExtendedState(Frame.ICONIFIED);
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
      tool.frame.setExtendedState(Frame.NORMAL);
    }


    @Override
    public void windowActivated(WindowEvent e) {
      if( e.getOppositeWindow() != tool.frame){
//        tool.editor.setVisible(true);
        tool.frame.setVisible(true);
//        tool.frame.requestFocus();
//        tool.editor.requestFocus();
      }
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
      if(  tool.tooldock.isDocked() ){
        tool.tooldock.dock();
      } else {
        tool.tooldock.tryDocking();
      }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
      if(  tool.tooldock.isDocked()){
        tool.tooldock.dock();
      } else {
        tool.tooldock.tryDocking();
      }   
    }  
    
    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
    
    
  }
  
  
  
  
  
  
  
  
  
  
  
  static public class FrameListener implements WindowListener, ComponentListener{
    
    TabManager tool;

    public FrameListener(TabManager parent){
      this.tool = parent;
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
      tool.quit();
    }

    @Override
    public void windowClosed(WindowEvent e) {
      tool.quit();
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
      tool.editor.setExtendedState(Editor.NORMAL);
    }

    @Override
    public void windowActivated(WindowEvent e) {
      if( e.getOppositeWindow() != tool.editor){
//        tool.editor.setVisible(true);
//        tool.frame.setVisible(true);
        tool.editor.requestFocus();
//        tool.frame.requestFocus();
      }
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
      tool.tooldock.tryDocking();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
      tool.tooldock.tryDocking();
    }  
   
    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
  }
  
  
}
