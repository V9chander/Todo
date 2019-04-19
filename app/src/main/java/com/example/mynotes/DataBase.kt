
package com.example.mynotes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

val DATABASE_NAME ="MyDB"
val TABLE_NAME="Users"
val COL_NAME = "name"
val COL_AGE = "age"
val COL_ID = "_id"

class DataBase(var context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_AGE +" VARCHAR(256))"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int,newVersion: Int) {

    }

    fun insertData(user : Taskbean){

        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,user.title)
        cv.put(COL_AGE,user.desc)
        var result = db.insert(TABLE_NAME,null,cv)
        if(result == -1.toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
    }

    fun readData():MutableList<Taskbean>{

        var tlist:MutableList<Taskbean>?=ArrayList()
        var qb=SQLiteQueryBuilder()
        qb.tables= TABLE_NAME
        val db = this.readableDatabase
        //val query = "Select * from " + TABLE_NAME
        val cursor = db.query(TABLE_NAME, null,null,null,null,null,null,null)
        tlist!!.clear()
        if(cursor.moveToFirst()){
            do {
                var user = Taskbean()
                user.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                user.title = cursor.getString(cursor.getColumnIndex(COL_NAME))
                user.desc = cursor.getString(cursor.getColumnIndex(COL_AGE))
                tlist!!.add(user)
            }while (cursor.moveToNext())
        }
        cursor.close()

        return tlist!!
        db.close()
    }

    fun deleteData(todoId:Int):Int{

        //var whereArgs = arrayOf(taskbean.id.toString())
        val db = this.writableDatabase
        var status=db.delete(TABLE_NAME,  "_id=?", arrayOf(todoId.toString()))
        if(status != 0)
            Toast.makeText(context,"Deleted", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Failed to delete", Toast.LENGTH_SHORT).show()

        db.close()
        return status
    }

    fun updateData(task:Taskbean,whereArgs:Array<String>):Int {

        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,task.title)
        cv.put(COL_AGE,task.desc)
        val result = db.update(TABLE_NAME,cv,"_id=?",whereArgs)
        return result
        db.close()
    }
}