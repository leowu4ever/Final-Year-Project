// draw servo charts
void wndManualDraw(GWinApplet appc, GWinData data) {
}

// draw sensing data charts
void wndChartDraw(GWinApplet appc, GWinData data) {
  float xMergin = appc.width*0.01;
  float yMergin = appc.height*0.015; 
  float chartWidth = appc.width/2 -xMergin;
  float chartHeight = (appc.height-80)/2 -yMergin;  

  // draw new grids from top to bottom
  appc.line(0, 40, appc.width, 40);
  appc.line(0, 290, appc.width, 290);
  appc.line(0, 540, appc.width, 540);
  appc.line(appc.width/2, 40, appc.width/2, 540);

  // draw grids 
  //  appc.rect(0, 0, appc.width/2, appc.height/2);
  //  appc.rect(appc.width/2, 0, appc.width/2, appc.height/2);
  //  appc.rect(0, appc.height/2, appc.width/2, appc.height/2);
  //  appc.rect(appc.width/2, appc.height/2, appc.width/2, appc.height/2);

  appc.textSize(10);

  setChartData();
  // draw charts 
  SenChart1.draw(0+xMergin, 40, chartWidth, chartHeight);
  SenChart2.draw(appc.width/2+xMergin, 40, chartWidth, chartHeight);
  SenChart3.draw(0+xMergin, appc.height/2, chartWidth, chartHeight);
  SenChart4.draw(appc.width/2+xMergin, appc.height/2, chartWidth, chartHeight);
}

