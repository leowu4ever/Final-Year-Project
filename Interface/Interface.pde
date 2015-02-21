/**
 
 */
import g4p_controls.*;
import processing.serial.*;
import org.gicentre.utils.stat.*;        // For chart classes.
import peasy.*;
import toxi.geom.*;
import toxi.geom.mesh.*;
import toxi.processing.*;

XYChart SenChart1;

PeasyCam cam;
TriangleMesh mesh;
ToxiclibsSupport gfx;
float scale = 2;
float rX = mouseY*0.005;
float rY = mouseX*0.005;


void setup() {
  size(200, 200, P3D);  
  smooth();
  createMainMenu();
}

public void draw() {
  background(255);
}

