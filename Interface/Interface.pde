/**
 
 */
import g4p_controls.*;
import processing.serial.*;
import org.gicentre.utils.stat.*;        // For chart classes.
import peasy.*;
import toxi.geom.*;
import toxi.geom.mesh.*;
import toxi.processing.*;

XYChart SenChart1;        // sensing data chart
// The serial port

Serial myPort;
String value;
IntList sl1, sl2, slh, sr1, sr2, srh, s1, s2;

// for simulation 
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

  sl1 = new IntList();
  sl2 = new IntList();
  slh = new IntList();
  sr1 = new IntList();
  sr2 = new IntList();
  srh = new IntList();
  s1 = new IntList();
  s2 = new IntList();

  // Print a list of the serial ports, for debugging purposes:
  print(Serial.list()); 
  myPort = new Serial(this, Serial.list()[2], 9600);
  myPort.bufferUntil('\n');  // initialise the lists
}

void draw() {
  background(255);
  if (value != null) 
  {
    println(value);
    value = trim(value);
    parseData(value);

    // update data for graph
  }
}

void parseData(String data)
{
  int[] tempArray= int(split(data, ",")); 
  sl1.append(tempArray[0]);
  sl2.append(tempArray[1]);
  slh.append(tempArray[2]);
  sr1.append(tempArray[3]);
  sr2.append(tempArray[4]);
  srh.append(tempArray[5]);
  s1.append(tempArray[6]);
  s2.append(tempArray[7]);
}

void serialEvent (Serial myPort) 
{
  // start to read the data when it meets new line mark
  value = myPort.readStringUntil('\n');
}
