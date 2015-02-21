/**
 * This sketch is to build the basic UI 
 *
 * @autho Liqun Wu
 */
 
import g4p_controls.*;

GButton bStart;
GDropList dlNumOfSen;
GToggleControl tcMode;

public void setup() {
  size(300, 160);
  G4P.setGlobalColorScheme(6);
  bStart = new GButton(this, 0, 0, 100, 30, "Start");  
  dlNumOfSen = new GDropList (this, 100, 30, 100, 30);
}

public void draw() {
  background(255, 255, 255);
}

