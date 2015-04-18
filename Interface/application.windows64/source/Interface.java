import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import g4p_controls.*; 
import processing.serial.*; 
import org.gicentre.utils.stat.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Interface extends PApplet {



      

Serial myPort;       
String value;        

FloatList sl1, sl2, sr1, sr2, s1, s2;  // store incoming data    
BarChart SenChart1, SenChart2, SenChart3, SenChart4; 

public void setup() {
  size(300, 160, P2D);  
  smooth();
  createMainWnd();
  initFloatList();
  print(Serial.list());  // print all ports
  myPort = new Serial(this, Serial.list()[2], 9600);
  //  start to read data when meets a line break
  myPort.bufferUntil('\n');  
}

public void draw() {
  background(255);
  if (value != null) 
  {
    println(value);     
    value = trim(value);      // get rid of whitespace
    updateSenFloList();
    parseData(value);
  }
}

public void serialEvent (Serial myPort) {
  // start to read the data when it meets new line mark
  value = myPort.readStringUntil('\n');
}

public void parseData(String data) {
  //split data by comma 
  int[] tempArray= PApplet.parseInt(split(data, ","));        
  // add individual data to corresponding intlist
  sl1.append(tempArray[0]);
  sl2.append(tempArray[1]);
  sr1.append(tempArray[2]);
  sr2.append(tempArray[3]);
  s1.append(tempArray[4]);
  s2.append(tempArray[5]);
}

// initialise all the float lists
public void initFloatList() {
  sl1 = new FloatList();
  sl2 = new FloatList();
  sr1 = new FloatList();
  sr2 = new FloatList();
  s1 = new FloatList();
  s2 = new FloatList();
}

// initialise chart objects 
public void initSenCharts() {
  SenChart1 = new BarChart(wndChart.papplet);
  SenChart2 = new BarChart(wndChart.papplet);
  SenChart3 = new BarChart(wndChart.papplet);
  SenChart4 = new BarChart(wndChart.papplet);

  setUpCharts(SenChart1);
  setUpCharts(SenChart2);
  setUpCharts(SenChart3);
  setUpCharts(SenChart4);
}

// default setting for chart
public void setUpCharts(BarChart chart) {
  //chart.setBarGap(3);
  chart.showValueAxis(true);
  chart.setMinValue(0);
  chart.setMaxValue(600);
}

public void setChartData() {
  SenChart1.setData(sl1.array());
  SenChart2.setData(sl2.array());
  SenChart3.setData(sr1.array());
  SenChart4.setData(sr2.array());
}

public void updateSenFloList() {
  checkDeleteSenData(sl1);
  checkDeleteSenData(sl2);
  checkDeleteSenData(sr1);
  checkDeleteSenData(sr2);
}

public void checkDeleteSenData(FloatList SenFloList) {
  if (SenFloList.size() == 2000) {
    SenFloList.clear();
  }
}
GButton btnStart;
GButton btnGrasp;
GButton btnRelease;
GWindow wndManual;
GWindow wndChart;

int btnWidth = 100;
int btnHeight = 30;

// create UI for main window
public void createMainWnd() {
  GLabel Title = new GLabel(this, 0, 0, 300, 100, "Robotic Fingers Interface\n\nFinal Year Project\n(Liqun Wu)");
  btnStart = new GButton (this, 100, 100, btnWidth, btnHeight, "Start");
}

// handle button events
public void handleButtonEvents(GButton button, GEvent event) {
  
  if (button == btnStart && event == GEvent.CLICKED && wndChart == null) {
    wndChart = new GWindow(this, "Interface", 0, 0, 600, 580, false, JAVA2D);      // create window 
    wndChart.setBackground(255);
    GLabel Title = new GLabel(wndChart.papplet, 150, 0, 300, 40, "Tactile Sensation Data & Manual Control");
    btnGrasp = new GButton (wndChart.papplet, 195, 545, btnWidth, btnHeight, "Grasp");
    btnRelease = new GButton (wndChart.papplet, 305, 545, btnWidth, btnHeight, "Release");
    wndChart.setActionOnClose(GWindow.CLOSE_WINDOW);
    initSenCharts();
    // add drawHandler after init the chart to avoid nullPointerException
    wndChart.addDrawHandler(this, "wndChartDraw");
  }

  // for grasp button 
  if (button == btnGrasp && event == GEvent.CLICKED) {
    println ("Grasp");
    myPort.write("G");        // do serial write to control Arduino
  }

  // for release button 
  if (button == btnRelease && event == GEvent.CLICKED) {
    println ("Release");
    myPort.write("R");        // do serial write to control Arduino
  }
}

// draw sensing data charts
public void wndChartDraw(GWinApplet appc, GWinData data) {
  // set mergins
  float xMergin = appc.width*0.01f;
  float yMergin = appc.height*0.015f; 
  float chartWidth = appc.width/2 -xMergin;
  float chartHeight = (appc.height-80)/2 -yMergin;  

  // draw new grids from top to bottom
  appc.line(0, 40, appc.width, 40);
  appc.line(0, 290, appc.width, 290);
  appc.line(0, 540, appc.width, 540);
  appc.line(appc.width/2, 40, appc.width/2, 540);
  appc.textSize(10);

  setChartData();
  // draw charts 
  SenChart1.draw(0+xMergin, 40, chartWidth, chartHeight);
  SenChart2.draw(appc.width/2+xMergin, 40, chartWidth, chartHeight);
  SenChart3.draw(0+xMergin, appc.height/2, chartWidth, chartHeight);
  SenChart4.draw(appc.width/2+xMergin, appc.height/2, chartWidth, chartHeight);
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Interface" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
