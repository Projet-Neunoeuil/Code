import serial,pymysql
from datetime import datetime
from time import strftime


if __name__ == '__main__':
    conn = pymysql.connect(host = "db4free.net", user = "appli1", password = "#M0td3p@553", database = "neunoeiltest")
    cur = conn.cursor()
    cur.execute(f"SELECT minTemp, maxTemp FROM Parametters")
    limitTemp = cur.fetchone()
    minTemp = limitTemp[0]
    maxTemp = limitTemp[1]    
    #print('Running. Press CTRL-C to exit.')
    with serial.Serial("/dev/ttyACM0", 9600, timeout=9.9) as arduino:
        #time.sleep(0.1) #wait for serial to open
        if arduino.isOpen():
            #print("{} connected!".format(arduino.port))
            try:
                while True:
                    answer=str(arduino.readline())
                    #print(answer)
                    dataList=answer.split("'")
                    temperature = float(dataList[1])
                    date = strftime(%Y-%m-%d %H:%M:%S, datetime.now())                    
                    isValid = int(temperature >= minTemp and temperature <= maxTemp)
                    cur.execute(f"INSERT INTO Temperature (value, time, inRange) VALUES ('{temperature}', '{date}', '{isValid}')")
                    conn.commit()
                    print("Température : {}".format(temperature))
                    arduino.flushInput() #remove data after reading
            except KeyboardInterrupt:
                print("KeyboardInterrupt has been caught.")
    conn.close()