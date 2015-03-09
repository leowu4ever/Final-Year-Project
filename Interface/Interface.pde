/**
 
 */
import g4p_controls.*;
import processing.serial.*;
import org.gicentre.utils.stat.*;        // For chart classes.
import peasy.*;
import toxi.geom.*;
import toxi.geom.mesh.*;
import toxi.processing.*;

Serial myPort;       
String value;        

// data for each components
FloatList sl1, sl2, sr1, sr2, s1, s2;        
// sensing data chart
BarChart SenChart1, SenChart2, SenChart3, SenChart4; 

// for simulation 
PeasyCam cam;
TriangleMesh mesh;
ToxiclibsSupport gfx;
float scale = 2.2;

void setup() {
  size(200, 200, P3D);  
  smooth();
  createMainMenu();
  initFloatList();

  // Print a list of the serial ports, for debugging purposes
  print(Serial.list());  
  myPort = new Serial(this, Serial.list()[2], 9600);
  myPort.bufferUntil('\n');
}

void draw() {
  background(255);
  if (value != null) 
  {
    println(value);     
    // get rid of whitespace  
    value = trim(value);   
    updateSenFloList();
    parseData(value);
    // update data for graph
  }
}

void serialEvent (Serial myPort) {
  // start to read the data when it meets new line mark
  value = myPort.readStringUntil('\n');
}

void parseData(String data) {
  //split data by comma 
  int[] tempArray= int(split(data, ","));        
  // add individual data to corresponding intlist
  sl1.append(tempArray[0]);
  sl2.append(tempArray[1]);
  sr1.append(tempArray[2]);
  sr2.append(tempArray[3]);
  s1.append(tempArray[4]);
  s2.append(tempArray[5]);
}

// init all the intlists
void initFloatList() {
  sl1 = new FloatList();
  sl2 = new FloatList();
  sr1 = new FloatList();
  sr2 = new FloatList();
  s1 = new FloatList();
  s2 = new FloatList();
}

void importMesh() {
  cam = new PeasyCam(wndSmltion.papplet, 500);
  cam.setMinimumDistance(250);
  cam.setMaximumDistance(350);
  mesh=(TriangleMesh)new STLReader().loadBinary(sketchPath("bumps_deformed.STL"), STLReader.TRIANGLEMESH);
  gfx=new ToxiclibsSupport(wndSmltion.papplet);
}

void initSenCharts() {
  // init chart object 
  SenChart1 = new BarChart(wndChart.papplet);
  SenChart2 = new BarChart(wndChart.papplet);
  SenChart3 = new BarChart(wndChart.papplet);
  SenChart4 = new BarChart(wndChart.papplet);

  // default setting for chart
  setUpCharts(SenChart1);
  setUpCharts(SenChart2);
  setUpCharts(SenChart3);
  setUpCharts(SenChart4);
 

  // update data in draw method
}

void setUpCharts(BarChart chart) {
  chart.showValueAxis(true);
  chart.showCategoryAxis(true);
  chart.setMinValue(0);
}

void setChartData() {
  SenChart1.setData(sl1.array());
  SenChart2.setData(sl2.array());
  SenChart3.setData(sr1.array());
  SenChart4.setData(sr2.array());
}

void updateSenFloList() {
  checkDeleteSenData (sl1);
  checkDeleteSenData (sl2);
  checkDeleteSenData (sr1);
  checkDeleteSenData (sr2);
}

void checkDeleteSenData(FloatList SenFloList ) {
  if (SenFloList.size() == 1000) {
    SenFloList.clear();
  }
}

