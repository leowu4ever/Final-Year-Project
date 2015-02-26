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
FloatList sl1, sl2, slh, sr1, sr2, srh, s1, s2;        
// sensing data chart
BarChart SenChart1, SenChart2, SenChart3, SenChart4, SenChart5, SenChart6;        

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
  slh.append(tempArray[2]);
  sr1.append(tempArray[3]);
  sr2.append(tempArray[4]);
  srh.append(tempArray[5]);
  s1.append(tempArray[6]);
  s2.append(tempArray[7]);
}

// init all the intlists
void initFloatList() {
  sl1 = new FloatList();
  sl2 = new FloatList();
  slh = new FloatList();
  sr1 = new FloatList();
  sr2 = new FloatList();
  srh = new FloatList();
  s1 = new FloatList();
  s2 = new FloatList();
}

void importMesh() {
  cam = new PeasyCam(wndSmltion.papplet, 100);
  cam.setMinimumDistance(50);
  cam.setMaximumDistance(500);
  mesh=(TriangleMesh)new STLReader().loadBinary(sketchPath("bumps_deformed.STL"), STLReader.TRIANGLEMESH);
  gfx=new ToxiclibsSupport(wndSmltion.papplet);
  wndSmltion.addDrawHandler(this, "wndSmltionDraw");
}

void initSenCharts() {
  // init chart object 
  SenChart1 = new BarChart(wndChart.papplet);
  SenChart2 = new BarChart(wndChart.papplet);
  SenChart3 = new BarChart(wndChart.papplet);
  SenChart4 = new BarChart(wndChart.papplet);
  SenChart5 = new BarChart(wndChart.papplet);
  SenChart6 = new BarChart(wndChart.papplet);
  // default setting for chart
  setUpCharts(SenChart1);
  setUpCharts(SenChart2);
  setUpCharts(SenChart3);
  setUpCharts(SenChart4);
  setUpCharts(SenChart5);
  setUpCharts(SenChart6);

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
  SenChart3.setData(slh.array());
  SenChart4.setData(sr1.array());
  SenChart5.setData(sr2.array());
  SenChart6.setData(srh.array());
}

void updateSenFloList() {
  checkDeleteSenData (sl1);
  checkDeleteSenData (sl2);
  checkDeleteSenData (slh);
  checkDeleteSenData (sr1);
  checkDeleteSenData (sr2);
  checkDeleteSenData (srh);
}

void checkDeleteSenData(FloatList SenFloList ) {
  if (SenFloList.size() == 1000) {
    SenFloList.clear();
  }
}

