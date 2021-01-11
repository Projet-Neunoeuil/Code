package com.example.myapplication

/*import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text
import java.lang.Exception
import java.sql.Driver
import java.sql.DriverManager
import java.sql.PreparedStatement*/

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

c/*lass MainActivity : AppCompatActivity() {
    var sum=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

   fun increment(view: View){
        sum++
        disp(sum)
    }

    fun decrement(view:View){
        sum--
        disp(sum)
    }

    private fun disp(sum: Int){
        val textView: TextView = findViewById(R.id.textView)
            textView.text=sum.toString()
    }
}*/
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

/*interface Connection {
    fun init(){
        var driver="com.mysql.jdbc.Driver"
        var url="jdbc:mysql://10.0.2.2:3306/test"
        var user="root"
        var psd="rs123456"
        try {
            Class.forName(driver).newInstance()
            println("Réussi1")
        }catch (e:Exception){
            e.printStackTrace()
            println("Echoué")
        }

        try{
            Thread({
                val conn=DriverManager.getConnection(url,user,psd)

                var sql:String="insert into stuinfo(id,name)values(?,?)"
                val ps:PreparedStatement=conn.prepareStatement(sql)
                ps.setString(1,"3")
                ps.setString(2,"vocus")
                ps.execute()
            }).start()

        }catch (e:Exception) {
            e.printStackTrace()
            println("Echoué")
        }

    }
}*/



