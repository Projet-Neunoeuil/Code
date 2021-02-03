import serial,pymysql

if __name__ == '__main__':
    conn = pymysql.connect(host = "193.26.21.39", user = "Appli", password = "#M0td3p@553", database = "Application")    
    cur = conn.cursor()
    cur.execute(f"SELECT minTemp, maxTemp FROM Parameters")
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
                    isValid = int(temperature >= minTemp and temperature <= maxTemp)
                    cur.execute(f"INSERT INTO Temperature (value, inRange) VALUES ('{temperature}', '{isValid}')")
                    conn.commit()
                    print("Température : {}".format(temperature))
                    arduino.flushInput() #remove data after reading
            except KeyboardInterrupt:
                print("KeyboardInterrupt has been caught.")
    conn.close()