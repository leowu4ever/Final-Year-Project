import g4p_controls.*;
import processing.serial.*;

Serial myPort;
String value;

// Int List for storing the dynamic sensing data for each sensor
IntList firstSenData;
IntList secondSenData;

// UI components 
int windowWidth =700;
int windowHeight =500;
GButton btnStart;
GTextArea txaFirstSen;
GTextArea txaSecondSen;

// for graph
int xPos = 1;         // horizontal position of the graph


void setup() 
{
  print(Serial.list()); 
  myPort = new Serial(this, Serial.list()[3], 9600);
  myPort.bufferUntil('\n');  // initialise the lists
  firstSenData = new IntList();
  secondSenData = new IntList();
  size(windowWidth,windowHeight);
  createUI();
}

void draw() 
{
  // set the background color to white in case display problem
  background(255);
  fill(227, 230, 255);
  noStroke();
 // rect(width - 190, 0, 200, height);
  if (value != null) 
  {
      // get rid of the white space created by println
      value = trim(value);
      parseData(value);
      // update the value to the UI component
      txaFirstSen.appendText(str(firstSenData.get(firstSenData.size()-1)) + "  ");
      txaSecondSen.appendText(str(secondSenData.get(secondSenData.size()-1)) + "  ");
      println(value);
   } 
   drawGraph(firstSenData);
   drawGraph(secondSenData);

}

void handleButtonEvents(GButton button, GEvent event)
{
  if (button == btnStart)
  {
    println("clicked");
  }
}

void serialEvent (Serial myPort) 
{
  // start to read the data when it meets new line mark
  value = myPort.readStringUntil('\n');
}

// parse data and store data to corresponding int list 
void parseData(String data)
{
  int[] tempArray= int(split(data, ",")); 
  firstSenData.append(tempArray[0]);
  secondSenData.append(tempArray[1]);
}

void createUI() 
{
  // set the global color to green
  frame.setTitle("Robotic Finger Sensing Interface");
  btnStart = new GButton (this, 0, 0, 150, 30, "Start Connection");
  txaFirstSen = new GTextArea (this, 0, 30, 150, 200, G4P.SCROLLBARS_VERTICAL_ONLY | G4P.SCROLLBARS_AUTOHIDE);
  txaSecondSen = new GTextArea (this, 0, 230, 150, 200, G4P.SCROLLBARS_VERTICAL_ONLY | G4P.SCROLLBARS_AUTOHIDE);
}

void drawGraph(IntList senData)
{
  float inByte = (float)senData.get(senData.size()-1);
  inByte = map(inByte, 0, 1023, 0, height);
  stroke(127,34,255);
  line(xPos, height, xPos, height - inByte);
  
  if (xPos >= width) {
    xPos = 0;
    background(0);    
  } 
  else {
  // increment the horizontal position:
    xPos++;
  }
}


