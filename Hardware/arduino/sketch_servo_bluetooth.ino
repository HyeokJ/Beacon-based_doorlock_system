#include <SoftwareSerial.h>
#include <Servo.h>

int Tx = 0;
int Rx = 1;

SoftwareSerial BTSerial(Tx, Rx); // Software Serial (Tx, Rx)

int servoPin = 7; // 1번 Servo Moter Pin 번호

Servo servo;

void setup() {
  Serial.begin(9600);
  delay(100);
  BTSerial.begin(9600);
  servo.attach(servoPin);
  servo.write(0); // 문 초기화
}

void loop() {
  
  if(BTSerial.available()) 
  {
    char toSend = (char)BTSerial.read();

    if (toSend != -5)
    {
      switch(toSend)
      {
        case 'o' : // 문이 열릴 경우
        servo.write(0); 
        delay(10);
        break;

        case 'c': // 문이 닫힐 경우
        servo.write(90); 
        delay(10);
        break;
      }
    }
  }
}
