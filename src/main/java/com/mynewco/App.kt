package com.mynewco

import kotlin.jvm.JvmStatic
import com.mynewco.db.Database
import com.mynewco.db.UserDao
import com.mynewco.db.UserDaoImpl
import com.mynewco.model.User
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

        val userDao:UserDao= UserDaoImpl()
        userDao.save(User("Jupiter"))
        userDao.save(User("Venus"))


        try {
            db.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        println("Hello World!")
    }
}