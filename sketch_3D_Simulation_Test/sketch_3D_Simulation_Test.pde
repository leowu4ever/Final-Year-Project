/**
 * <p>Tiny demo showing usage of STLReader class to load binary STL files
 * and display them (with face normals). There're 2 example meshes provided
 * with one of them having been exported using a flipped Y axis. The TriangleMesh
 * class has a convenient method to reorient all faces.</p>
 */

/* 
 * Copyright (c) 2010 Karsten Schmidt
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * http://creativecommons.org/licenses/LGPL/2.1/
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import toxi.geom.*;
import toxi.geom.mesh.*;
import toxi.processing.*;
import peasy.*;
PeasyCam cam;

TriangleMesh mesh;
ToxiclibsSupport gfx;
float scale = 2;


void setup() {
  size(600, 600, P3D);
  smooth();

  cam = new PeasyCam(this, 100);
  cam.setMinimumDistance(300);
  cam.setMaximumDistance(400);
  //mesh=(TriangleMesh)new STLReader().loadBinary(sketchPath("bumps_deformed.STL"), STLReader.TRIANGLEMESH);
  mesh=(TriangleMesh)new STLReader().loadBinary(sketchPath("bumps_deformed.STL"),STLReader.TRIANGLEMESH).flipYAxis();
  gfx=new ToxiclibsSupport(this);
}

void draw() {
  background(0);
  lights();

 
  //  rotateX(45);

//  rotateX(rX);
//  rotateY(rY);

  strokeWeight(0.05);
  // gfx.origin(new Vec3D(), 200);

  gfx.scale(new Vec3D(scale, scale, scale));
  //noStroke();  
  gfx.mesh(mesh, false, 0);
}


//void mouseWheel(MouseEvent event) {
//  float e = event.getAmount();
//  if (e>0 && scale>=0) {
//    scale++;
//  } else if (e<0 && scale>0){
//    scale--;
//  }
//}
//
//void mouseDragged() 
//{
//  rX = rX+mouseX*0.001;
//  rY = rY+mouseY*0.001;
//}

