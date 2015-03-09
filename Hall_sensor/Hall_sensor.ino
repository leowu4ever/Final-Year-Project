#include <Servo.h>

int pressure;
int counter = 0;
boolean finish = false;
void setup () 
{
  Serial.begin (9600);
}

void loop () 
{
  if(!finish) {
    pressure  = analogRead (A0);
    Serial.println(pressure);
    counter++;
    delay (50);
  }
  if(counter == 100) {
    finish = true; 
  }
}

