package com.example.as1.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.as1.model.User

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,"User_db",null, 1) {

    private var db=writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(User.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS ${User.TABLE_NAME}")
        onCreate(db)
    }
    fun addUser(name:String, email: String, phone_number:Int, password: String?):Boolean{
        val cv= ContentValues()
        cv.put(User.COL_NAME,name)
        cv.put(User.COL_EMAIL,email)
        cv.put(User.COL_PHONE_NUMBER,phone_number)
        cv.put(User.COL_PASSWORD,password)

        return db.insert(User.TABLE_NAME,null,cv)>0
    }

    fun checkUser(email: String, password: String): Boolean {
        val p0= this.writableDatabase
        val query = "select * from ${User.TABLE_NAME} where ${User.COL_EMAIL} = '$email' and ${User.COL_PASSWORD} = '$password' "
        val cursor = p0.rawQuery(query, null)
        if(cursor.count <= 0){
            cursor.close()
            return false
        }
        cursor.close()
        return true



    }
}
