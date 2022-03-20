package com.mynewco

import com.mynewco.db.Database
import com.mynewco.db.UserDao
import com.mynewco.db.UserDaoImpl
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
            println("Connected!")
        } catch (e: SQLException) {
            println("Cannot connect to database.")
        }

        val userDao: UserDao = UserDaoImpl()
        //userDao.save(User("Jupiter"))
        //userDao.save(User("Venus"))
        val users = userDao.getAll()

        users.forEach(System.out::println)

        var retrievedUser = userDao.findById(21)
        retrievedUser?.let {  println("Retrieved $it")}?:println("Retrieved none")

        retrievedUser = userDao.findById(1)
        retrievedUser?.let {  println("Retrieved $it")}?:println("Retrieved none")

        retrievedUser?.let { userDao.delete(it) }
        users.forEach(System.out::println)
        try {
            db.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        //println("Hello World!")
    }
}