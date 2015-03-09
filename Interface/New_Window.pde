// draw servo charts
void wndManualDraw(GWinApplet appc, GWinData data) {
}

// draw sensing data charts
void wndChartDraw(GWinApplet appc, GWinData data) {
  // draw grids 
  appc.rect(0, 0, appc.width/2, appc.height/2);
  appc.rect(appc.width/2, 0, appc.width/2, appc.height/2);
  appc.rect(0, appc.height/2, appc.width/2, appc.height/2);
  appc.rect(appc.width/2, appc.height/2, appc.width/2, appc.height/2);

  setChartData();
  // draw charts 
  SenChart1.draw(0, 0, appc.width/2, appc.height/2);
  SenChart2.draw(appc.width/2, 0, appc.width/2, appc.height/2);
  SenChart3.draw(0, appc.height/2, appc.width/2, appc.height/2);
  SenChart4.draw(appc.width/2, appc.height/2, appc.width/2, appc.height/2);
}

