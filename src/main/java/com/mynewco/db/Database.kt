package com.mynewco.db


import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

class Database private constructor() {
    val connection get() = _connection
    private var _connection: Connection? = null

    //Holder object & lazy instance is used to ensure only one instance of Singleton is created.
    private object Holder {
        val INSTANCE = Database()
    }

    @Throws(SQLException::class)
    fun connect(props: Properties) {
        val server=props.getProperty("server")
        val database=props.getProperty("database")
        val port=props.getProperty("port")
        val user=props.getProperty("user")
        val pwd=props.getProperty("password")
        _connection = DriverManager.getConnection("$URL$server:$port/$database", user, pwd)
    }

    @Throws(SQLException::class)
    fun close() {
        _connection!!.close()
    }

    companion object {
        val instance: Database by lazy { Holder.INSTANCE }
        private const val URL = "jdbc:mysql://"
    }
}  // Lazy Singleton Inizialization Holder
