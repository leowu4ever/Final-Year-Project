void wndManualDraw(GWinApplet appc, GWinData data) {
}

void wndChartDraw(GWinApplet appc, GWinData data) {
  appc.rect(0, 0, appc.width/3, appc.height/2);
  appc.rect(appc.width/3, 0, appc.width/3, appc.height/2);
  appc.rect(appc.width/3 *2, 0, appc.width/3, appc.height/2);
  appc.rect(0, appc.height/2, appc.width/3, appc.height/2);
  appc.rect(appc.width/3, appc.height/2, appc.width/3, appc.height/2);
  appc.rect(appc.width/3 *2, appc.height/2, appc.width/3, appc.height/2);

  // update chart data X axis and Y axis 
  SenChart1.setData(new float[] {
    1900, 1910, 1920, 1930, 1940, 1950, 
    1960, 1970, 1980, 1990, 2000
  }
  , 
  new float[] { 
    6322, 6489, 6401, 7657, 9649, 9767, 
    12167, 15154, 18200, 23124, 28645
  }
  );
  SenChart1.draw(0, 0, appc.width/3, appc.height/2);
}

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
