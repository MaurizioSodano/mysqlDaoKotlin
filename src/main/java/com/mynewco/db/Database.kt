package com.mynewco.db


import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Database private constructor() {
    private var connection: Connection? = null
    //Holder object & lazy instance is used to ensure only one instance of Singleton is created.
    private object Holder {
        val INSTANCE = Database()
    }

    @Throws(SQLException::class)
    fun connect() {
        connection = DriverManager.getConnection(URL, "root", "Mercogli@no2022M")
    }

    @Throws(SQLException::class)
    fun close() {
        connection!!.close()
    }

    companion object {
        val instance: Database by lazy { Holder.INSTANCE }
        private const val URL = "jdbc:mysql://localhost:3306/people"
    }
}  // Lazy Singleton Inizialization Holder
