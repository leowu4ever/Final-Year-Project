/**
 
 */

// main menu
GButton btnManual;
GButton btnChart;

// Manual Control 
GButton btnGrasp;
GButton btnRelease;

// Windows
GWindow wndManual;
GWindow wndChart;

int btnWidth = 100;
int btnHeight = 30;

void createMainMenu() {
  GLabel Title = new GLabel(this, 0, 0, 300, 100, "Robotic Fingers Interface\n\nFinal Year Project\n(Liqun Wu)");
  // btnManual = new GButton (this, 0, 0, btnWidth, btnHeight, "Manual Control");
  btnChart = new GButton (this, 100, 100, btnWidth, btnHeight, "Start");
}

void handleButtonEvents(GButton button, GEvent event) {
  // create manual control window
  if (button == btnManual && event == GEvent.CLICKED && wndManual == null) {
    wndManual = new GWindow(this, "Manual Control", 0, 0, 300, 300, false, JAVA2D);
    wndManual.setBackground(255);

    //  btnGrasp = new GButton (wndManual.papplet, 0, 0, btnWidth, btnHeight, "Grasp");
    //  btnRelease = new GButton (wndManual.papplet, 0, btnHeight, btnWidth, btnHeight, "Release");
    wndManual.setOnTop(false);
    wndManual.setActionOnClose(GWindow.CLOSE_WINDOW);
    btnManual.setEnabled(false);
    wndManual.addDrawHandler(this, "wndManualDraw");
  }

  // create sensing data charts window 
  if (button == btnChart && event == GEvent.CLICKED && wndChart == null) {
    wndChart = new GWindow(this, "Interface", 0, 0, 600, 580, false, JAVA2D);
    wndChart.setBackground(255);
    GLabel Title = new GLabel(wndChart.papplet, 150, 0, 300, 40, "Tactile Sensation Data & Manual Control");

    btnGrasp = new GButton (wndChart.papplet, 195, 545, btnWidth, btnHeight, "Grasp");
    btnRelease = new GButton (wndChart.papplet, 305, 545, btnWidth, btnHeight, "Release");
    wndChart.setOnTop(false);
    wndChart.setActionOnClose(GWindow.CLOSE_WINDOW);
    btnChart.setEnabled(false);
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

