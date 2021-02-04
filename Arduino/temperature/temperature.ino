// select the analogic input A0
const int sensorPin = A0;

void setup() {
  // open a serial connection to display values
  Serial.begin(9600);
}

void loop() {
  // read the value on AnalogIn pin 0 and store it in a variable (10-bit sensor value)
  int sensorVal = analogRead(sensorPin);

  // convert the ADC reading to voltage
  float voltage = (sensorVal / 1024.0) * 5.0;

  // convert the voltage to temperature in degrees C
  // the sensor changes 10 mV per degree
  // the datasheet says there's a 500 mV offset
  float temperature = (voltage - .5) * 100;
  
  // write the temperature to the serial port
  Serial.print(temperature);
  
  // loop repeated every 10 seconds
  delay(10000);
}
