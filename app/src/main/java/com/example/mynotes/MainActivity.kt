package com.example.mynotes

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        var gManager = GridLayoutManager(this,  2)
        var lmanager=LinearLayoutManager(this,1,false)
        rview.layoutManager = lmanager //  lManager //
        var db=DataBase(this)
        rview.adapter=TaskAdapter(this,db.readData())
        rview!!.adapter!!.notifyDataSetChanged()

        fab.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }
    fun prepareList() {
        var db = DataBase(this)
        rview.adapter= TaskAdapter(this, db.readData())
    }

    fun gotoUpdateFun(task:Taskbean) {
        var i = Intent(this, DashboardActivity::class.java)
        i.putExtra("id", task.id)
        i.putExtra("title", task.title)
        i.putExtra("desc", task.desc)
        startActivity(Intent())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        /* val sv=menu!!.findItem(R.id.action_search).actionView as? SearchView
        val sm=getSystemService(Context.SEARCH_SERVICE) as? SearchManager
        sv?.setSearchableInfo(sm?.getSearchableInfo(componentName))
        sv?.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                var db=DataBase(this@MainActivity)
                loadQuery("%"+query+"%")
                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                var db=DataBase(this@MainActivity)
                loadQuery("%"+query+"%")
                return false
            }
        })*/
        return super.onCreateOptionsMenu(menu)
    }
}

