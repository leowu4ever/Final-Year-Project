#include <Servo.h>

int sl1, sl2, slh, sr1, sr2, srh, s1, s2;
int inByte = 0;         // incoming serial byte
Servo servo1, servo2;
String data;

void setup()
{
  Serial.begin(9600);

  servo1.attach(8);
  servo2.attach(9);
  // start serial port at 9600 bps:
  // establishContact();  // send a byte to establish contact until Processing responds 
  servo1.write(0);
  servo2.write(0);
}

void loop()
{
  // if we get a valid byte, read analog ins:
  if (Serial.available() > 0) {
    // get incoming byte:
    data = Serial.readString();
    Serial.print(data);
  }

  // rea4d first analog input, divide by 4 to make the range 0-255:
  sl1 = analogRead(A0);
  // delay 10ms to let the ADC recover:
  delay(10);
  // read second analog input, divide by 4 to make the range 0-255:
  sl2 = analogRead(A1);
  delay(10);
  slh = analogRead(A2);
  delay(10);
  s1 = servo1.read();
  delay(10);
  sr1 = analogRead(A3);
  delay(10);
  sr2 = analogRead(A4);
  delay(10);
  srh = analogRead(A5);
  delay(10);
  s2 = servo2.read();

  Serial.print(sl1);
  Serial.print(",");

  Serial.print(sl2);
  Serial.print(",");

  Serial.print(slh);
  Serial.print(",");

  Serial.print(s1);
  Serial.print(",");

  Serial.print(sr1);
  Serial.print(",");

  Serial.print(sr2);
  Serial.print(",");

  Serial.print(srh);
  Serial.print(",");

  Serial.println(s2);
  delay(50);
  //println("sl1: " + sl1 + "sl2: " + sl2 + "slh: " + slh + "sr1: " + sr1 + "sr2: " + sr2 + "srh: " + srh + "s1: " + s1 + "s2: " + s2);
  // }
  if (data == "R") {
    servo1.write(0);
    servo2.write(0);


  }
  if (data == "G") {
    servo1.write(95);
    servo2.write(150);
  }
}



