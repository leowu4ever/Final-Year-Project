// Main window GUI components
GButton btnStart;
GButton btnGrasp;
GButton btnRelease;
GWindow wndChart;

// button size
int btnWidth = 100;
int btnHeight = 30;

// create UI for main window
void createMainWnd() {
  GLabel Title = new GLabel(this, 0, 0, 300, 100, "Robotic Fingers Interface\n\nFinal Year Project\n(Liqun Wu)");
  btnStart = new GButton (this, 100, 100, btnWidth, btnHeight, "Start");
}

// handle button events
void handleButtonEvents(GButton button, GEvent event) {
  
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
void wndChartDraw(GWinApplet appc, GWinData data) {
  // set mergins
  float xMergin = appc.width*0.01;
  float yMergin = appc.height*0.015; 
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

