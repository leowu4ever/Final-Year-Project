/**
 
 */

GButton btnManual;
GButton btnChart;
GButton btnSmltion;

GButton btnGrasp;
GButton btnRelease;

GWindow wndManual;
GWindow wndChart;
GWindow wndSmltion;
int btnWidth = 200;
int btnHeight = 30;

public void createMainMenu() {
  btnManual = new GButton (this, 0, 0, btnWidth, btnHeight, "Manual Control");
  btnChart = new GButton (this, 0, btnHeight, btnWidth, btnHeight, "Sensing Data Chart");
  btnSmltion = new GButton (this, 0, btnHeight*2, btnWidth, btnHeight, "Simulation");
}

void handleButtonEvents(GButton button, GEvent event) {
  if (button == btnManual && event == GEvent.CLICKED && wndManual == null) {
    wndManual = new GWindow(this, "Manual Control", 0, 0, 300, 300, false, JAVA2D);
    wndManual.setBackground(255);
    btnGrasp = new GButton (wndManual.papplet, 0, 0, btnWidth, btnHeight, "Grasp");
    btnRelease = new GButton (wndManual.papplet, 0, btnHeight, btnWidth, btnHeight, "Release");
    wndManual.setOnTop(false);
    wndManual.setActionOnClose(GWindow.CLOSE_WINDOW);
    btnManual.setEnabled(false);
    wndManual.addDrawHandler(this, "wndManualDraw");
  }

  if (button == btnChart && event == GEvent.CLICKED && wndChart == null) {
    wndChart = new GWindow(this, "Sensing Data Chart", 0, 0, 600, 400, false, JAVA2D);
    wndChart.setBackground(255);
    wndChart.setOnTop(false);
    wndChart.setActionOnClose(GWindow.CLOSE_WINDOW);
    btnChart.setEnabled(false);
    // init chart object 
    SenChart1 = new XYChart(wndChart.papplet);
    // update data in draw method

    // add drawHandler after init the chart to avoid nullPointerException
    wndChart.addDrawHandler(this, "wndChartDraw");
    // default setting for chart
  }

  if (button == btnSmltion && event == GEvent.CLICKED && wndSmltion == null) {
    wndSmltion = new GWindow(this, "Simulation", 0, 0, 600, 600, false, P3D);
    wndSmltion.setBackground(0);
    wndSmltion.setOnTop(false);
    wndSmltion.setActionOnClose(GWindow.CLOSE_WINDOW);
    btnSmltion.setEnabled(false);

    // import 3d mesh
    cam = new PeasyCam(wndSmltion.papplet, 100);
    cam.setMinimumDistance(50);
    cam.setMaximumDistance(500);
    mesh=(TriangleMesh)new STLReader().loadBinary(sketchPath("bumps_deformed.STL"), STLReader.TRIANGLEMESH);
    gfx=new ToxiclibsSupport(wndSmltion.papplet);

    wndSmltion.addDrawHandler(this, "wndSmltionDraw");
  }

  if (button == btnGrasp && event == GEvent.CLICKED) {
    println ("Grasp");
    myPort.write("G");
  }

  if (button == btnRelease && event == GEvent.CLICKED) {
    println ("Release");
    myPort.write("R");
  }
}

