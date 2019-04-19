package com.example.mynotes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.*
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.indiview_task.view.*
import kotlin.contracts.contract
import android.content.Context.CLIPBOARD_SERVICE
import android.support.v4.content.ContextCompat.getSystemService

class TaskAdapter(var activity: MainActivity?,var list:MutableList<Taskbean>?): RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(vg: ViewGroup, p1: Int): MyViewHolder {

        var inflator= LayoutInflater.from(activity)
        var v=inflator.inflate(R.layout.indiview_task,vg, false)
        return MyViewHolder(v)
    }
    override fun getItemCount(): Int= list!!.size

    override fun onBindViewHolder(mh: MyViewHolder, pos: Int) {

        var pos=list!!.get(pos)
        mh.titleObj!!.text=pos.title
        mh.descObj!!.text=pos.desc
        mh.delete!!.setOnClickListener {
           // var selectionArgs = arrayOf(taskbean.id.toString())
            var db= DataBase(activity!!)
            db.deleteData(pos.id)
            activity!!.prepareList()
        }
        mh.update!!.setOnClickListener {
           activity!!.gotoUpdateFun(pos)
        }
        mh.share!!.setOnClickListener {
            val title = mh!!.titleObj!!.text.toString()
            val desc = mh!!.descObj!!.text.toString()
            val s = title + "\n" + desc
            var i = Intent()
            i.action = Intent.ACTION_SEND
            i.type = "text/plan"
            i.putExtra(Intent.EXTRA_TEXT, s)
            var share=Intent.createChooser(i,s)
            startActivity(activity!!,share,null)


        }
    }
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var titleObj: TextView?=null
        var descObj: TextView?=null
        var delete:Button?=null
        var update: Button?=null
        var share:Button?=null
        init {
            titleObj=view.tvTitle
            descObj=view.tvDesc
            delete=view.btn_delete
            update=view.btn_write
            share=view.btn_share
        }
    }
}
