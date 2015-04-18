/**
 * Final year project
 *
 * Interface which controls robotic hand and displays
 * tactile sensing data
 *
 * The public libraries used are G4P, giCentre and Processing serial
 * G4P (http://www.lagers.org.uk/g4p/download.html)
 * giCentre (http://www.gicentre.net/software/#/utils/)
 * Processing (https://www.processing.org/download/?processing)
 
 * @author Liqun Wu (1242421)
 */
 
import g4p_controls.*;
import processing.serial.*;
import org.gicentre.utils.stat.*;      

Serial myPort;       
String value;        

FloatList sl1, sl2, sr1, sr2, s1, s2;  // store incoming data    
BarChart SenChart1, SenChart2, SenChart3, SenChart4; 

void setup() {
  size(300, 160, P2D);  
  smooth();
  createMainWnd();
  initFloatList();
  print(Serial.list());  // print all ports
  myPort = new Serial(this, Serial.list()[2], 9600);
  //  start to read data when meets a line break
  myPort.bufferUntil('\n');  
}

void draw() {
  background(255);
  if (value != null) 
  {
    println(value);     
    value = trim(value);  // get rid of whitespace
    updateSenFloList();
    parseData(value);
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

// initialise all the float lists
void initFloatList() {
  sl1 = new FloatList();
  sl2 = new FloatList();
  sr1 = new FloatList();
  sr2 = new FloatList();
  s1 = new FloatList();
  s2 = new FloatList();
}

// initialise chart objects 
void initSenCharts() {
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
void setUpCharts(BarChart chart) {
  //chart.setBarGap(3);
  chart.showValueAxis(true);
  chart.setMinValue(0);
  chart.setMaxValue(600);
}

// set the datat to the charts 
void setChartData() {
  SenChart1.setData(sl1.array());
  SenChart2.setData(sl2.array());
  SenChart3.setData(sr1.array());
  SenChart4.setData(sr2.array());
}

// update the sensing data 
void updateSenFloList() {
  checkDeleteSenData(sl1);
  checkDeleteSenData(sl2);
  checkDeleteSenData(sr1);
  checkDeleteSenData(sr2);
}

// wipe the sensing data when the size is 2000
void checkDeleteSenData(FloatList SenFloList) {
  if (SenFloList.size() == 2000) {
    SenFloList.clear();
  }
}
