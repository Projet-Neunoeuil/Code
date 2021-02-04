import serial,pymysql
from datetime import datetime

if __name__ == '__main__':
    #connection to the database
    conn = pymysql.connect(host = "avalgan.ch", user = "Appli", password = "#M0td3p@553", database = "TempNeunoeil")
    cur = conn.cursor()
    
    #recovery of the temperature range desired by the user
    cur.execute(f"SELECT minTemp, maxTemp FROM Parametters")
    limitTemp = cur.fetchone()    
    minTemp = limitTemp[0]
    maxTemp = limitTemp[1]
    
    #connection to the serial port of the arduino
    with serial.Serial("/dev/ttyACM0", 9600, timeout=9.9) as arduino:
        if arduino.isOpen():
            try:
                while True:
                    #recovery of the temperature on the serial port measured by the arduino
                    answer = str(arduino.readline())
                    array = answer.split("'")
                    temperature = float(array[1])
                    
                    #check if temperature measured is valid
                    isValid = int(temperature >= minTemp and temperature <= maxTemp)
                    
                    #recovery of the current date
                    date_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                    
                    #execution of the sql query
                    cur.execute(f"INSERT INTO Temperature (value, time, inRange) VALUES ('{temperature}', '{date_time}', '{isValid}')")
                    conn.commit()
                    
                    #display the measured temperature and the current date
                    print("Température : {}".format(temperature))
                    print(date_time)
                    
                    #remove data after reading
                    arduino.flushInput()
            except KeyboardInterrupt:
                print("KeyboardInterrupt has been caught.")
    conn.close()