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

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;


/**
 * 
 * @author Thomas Diewald, 2014
 *
 */
public class AbsolutLayout implements LayoutManager {

  private int w_min = 200;
  private int h_min = 40;
  
  private int w_max = 0; 
  private int h_max = 0;

  public AbsolutLayout() {
  }
  
  private void setSizes(Container parent) {
    w_max = 0;
    h_max = 0;
    int num_comps = parent.getComponentCount();
    Rectangle bounds = new Rectangle();

    for (int i = 0; i < num_comps; i++) {
      Component comp = parent.getComponent(i);
      if (comp.isVisible()) {
        comp.getBounds(bounds);
        int x_max_comp = bounds.x+bounds.width;
        int y_max_comp = bounds.y+bounds.height;

        w_max = Math.max(w_max, x_max_comp);
        h_max = Math.max(h_max, y_max_comp);
      }
    }
    
    w_max += 5;
    h_max += 5;
    
    w_max = Math.max(w_min, w_max);
    h_max = Math.max(h_min, h_max);
  }

  @Override
  public void addLayoutComponent(String name, Component comp) {
  }

  @Override
  public void removeLayoutComponent(Component comp) {
  }

  @Override
  public Dimension preferredLayoutSize(Container parent) {
    setSizes(parent);

    Insets insets = parent.getInsets();
    Dimension dim = new Dimension(0, 0);
    dim.width  = w_max  + insets.left + insets.right;
    dim.height = h_max + insets.top  + insets.bottom;
    return dim;
  }

  @Override
  public Dimension minimumLayoutSize(Container parent) {
    setSizes(parent);

    Insets insets = parent.getInsets();
    Dimension dim = new Dimension(0, 0);
    dim.width  = w_max  + insets.left + insets.right;
    dim.height = h_max + insets.top  + insets.bottom;
    return dim;
  }

  @Override
  public void layoutContainer(Container parent) {
  }

  @Override
  public String toString() {
    return getClass().getName();
  }
  
}
