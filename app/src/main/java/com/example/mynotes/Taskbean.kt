package com.example.mynotes

class Taskbean {
    var id:Int=0
    var title:String=""
    var desc:String=""
constructor(noteId:Int,title: String, desc: String){
    this.id=noteId
    this.title=title
    this.desc=desc
}
    constructor(title: String, desc: String){
        this.title=title
        this.desc=desc
    }
    constructor(){

    }
}
