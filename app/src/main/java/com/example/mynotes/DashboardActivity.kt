package com.example.mynotes

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
try {
    var b=intent.extras
    id=b.getInt("id")
    if(id!=0){
        et_title.setText(intent.getStringExtra("title"))
        et_desc.setText(intent.getStringExtra("desc"))
    }
}
catch (e:Exception){

}
        add.setOnClickListener {
            var db= DataBase(this)
            var task = Taskbean(et_title.text.toString(),et_desc.text.toString())
           if(id==0) {
               db.insertData(task)
               startActivity(Intent(this, MainActivity::class.java))
           }
            else{
               var selectionArgs= arrayOf(id.toString())
               db.updateData(task,selectionArgs)
               startActivity(Intent(this, MainActivity::class.java))
           }
    }
    }
}
