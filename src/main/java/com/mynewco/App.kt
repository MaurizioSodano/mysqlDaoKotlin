package com.mynewco

import kotlin.jvm.JvmStatic
import com.mynewco.db.Database
import java.sql.SQLException

/**
 * Hello world!
 */
object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val db = Database.instance
        try {
            db.connect()
        } catch (e: SQLException) {
            println("Cannot connect to database.")
        }
        try {
            db.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        println("Hello World!")
    }
}