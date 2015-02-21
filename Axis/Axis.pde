import processing.opengl.*;
float origonX, origonY;
void setup() 
{
  size(500, 500, P3D);
  //  origonX = width/2;
  //  origonY = height/2;
}

void draw() 
{ 
  background(255, 255, 255);
  origonX = mouseX;
  origonY = mouseY;
  fill (0, 0, 0);
  line (origonX, origonY, 0, origonX, 0, 0);
  line (origonX, origonY, 0, width, origonY, 0);
  line (origonX, origonY, 0, 0, height*0.9, 0);
}

