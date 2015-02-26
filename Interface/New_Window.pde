// draw servo charts
void wndManualDraw(GWinApplet appc, GWinData data) {
}

// draw sensing data charts
void wndChartDraw(GWinApplet appc, GWinData data) {
  // draw grids 
  appc.rect(0, 0, appc.width/3, appc.height/2);
  appc.rect(appc.width/3, 0, appc.width/3, appc.height/2);
  appc.rect(appc.width/3 *2, 0, appc.width/3, appc.height/2);
  appc.rect(0, appc.height/2, appc.width/3, appc.height/2);
  appc.rect(appc.width/3, appc.height/2, appc.width/3, appc.height/2);
  appc.rect(appc.width/3 *2, appc.height/2, appc.width/3, appc.height/2);

  setChartData();
  // draw charts 
  SenChart1.draw(0, 0, appc.width/3, appc.height/2);
  SenChart2.draw(appc.width/3, 0, appc.width/3, appc.height/2);
  SenChart3.draw(appc.width/3*2, 0, appc.width/3, appc.height/2);
  SenChart4.draw(0, appc.height/2, appc.width/3, appc.height/2);
  SenChart5.draw(appc.width/3, appc.height/2, appc.width/3, appc.height/2);
  SenChart6.draw(appc.width/3*2, appc.height/2, appc.width/3, appc.height/2);
}

// draw simulation 
void wndSmltionDraw(GWinApplet appc, GWinData data) {
  appc.lights();
  //    cam = new PeasyCam(appc, 100);
  //    cam.setMinimumDistance(50);
  //    cam.setMaximumDistance(500);
  //    mesh=(TriangleMesh)new STLReader().loadBinary(sketchPath("bumps_deformed.STL"), STLReader.TRIANGLEMESH);
  //    gfx=new ToxiclibsSupport(appc);
  appc.translate(width, height);
  appc.strokeWeight(0.05);
  gfx.scale(new Vec3D(scale, scale, scale));
  gfx.mesh(mesh, false, 0);
}

