import pymysql
import serial
import time

#connection to the serial port of the arduino
ser = serial.Serial("/dev/ttyACM0", 9600, timeout = 9)

#connection to the database and opening the cursor
db = pymysql.connect(host = "xiangyu-an.fr", user = "Appli", password = "#M0td3p@553", database = "Application")
cursor = db.cursor()
    
#recovery of the temperature range desired by the user
sql = f"SELECT minTemp, maxTemp FROM Parameters"
cursor.execute(sql)
limitTemp = cursor.fetchone()
minTemp = limitTemp[0]
maxTemp = limitTemp[1]

while 1:
    #recovery of the measured temperature by the arduino on the serial port
    temperature = float(ser.readline().decode("utf-8"))
    print(temperature)
    
    #check if temperature measured is valid
    isValid = int(temperature >= minTemp and temperature <= maxTemp)
    
    #execution of the sql query
    sql = f"INSERT INTO Temperature (value, inRange) VALUES ('{temperature}', '{isValid}')"
    cursor.execute(sql)
    db.commit()
    
#closing the cursor and disconnecting from the database
cursor.close()
db.close()

#closing the serial port
ser.close()