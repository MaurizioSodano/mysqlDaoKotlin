package com.mynewco.model

class User {
    var id = 0
    var name: String

    constructor(name: String) {
        this.name = name
    }

    constructor(id: Int, name: String) {
        this.id = id
        this.name = name
    }

    override fun toString(): String {
        return "User(id=$id, name='$name')"
    }

}