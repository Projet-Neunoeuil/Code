package com.example.aqualala

/*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}*/


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_hello.setOnClickListener {
            Thread(Runnable {
                val sql = "SELECT * FROM users"
                mysqlConnection(sql)
            }).start()
        }
    }

    /**
     * 连接数据库
     */
    fun mysqlConnection(sql:String) {
        var cn: Connection
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver")
            //建立连接
            cn = DriverManager.getConnection("jdbc:mysql://39.99.141.184:3306/jdbc",
                "ccsu", "123456")
            val ps = cn.createStatement()
            val resultSet = ps!!.executeQuery(sql)
            while (resultSet.next()) {
                Log.d("mysqlConnection: " , resultSet.getString("id") +
                        resultSet.getString("name") +
                        resultSet.getString("password")+resultSet.getString("email"))
            }
            if (ps != null) {
                ps!!.close()
            }
            if (cn != null) {
                cn.close()
            }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }
}