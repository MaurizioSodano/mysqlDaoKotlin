package com.mynewco

import com.mynewco.db.Database
import com.mynewco.db.Profile
import com.mynewco.db.UserDao
import com.mynewco.db.UserDaoImpl
import com.mynewco.model.User
import java.sql.SQLException
import java.util.*

/**
 * Hello world!
 */
object App {
    @JvmStatic
    fun main(args: Array<String>) {

        val db = Database.instance
        try {
            db.connect(Profile.getProperties("db"))
            println("Connected!")
        } catch (e: SQLException) {
            println("Cannot connect to database.")
            return
        }

        val userDao: UserDao = UserDaoImpl()
        //userDao.save(User("Jupiter"))
        //userDao.save(User("Venus"))
        val users = userDao.getAll()

        users.forEach(System.out::println)

        var retrievedUser = userDao.findById(21)
        retrievedUser?.let { println("Retrieved $it") } ?: println("Retrieved none")

        retrievedUser = userDao.findById(1)
        retrievedUser?.let { println("Retrieved $it") } ?: println("Retrieved none")

        retrievedUser?.let { userDao.delete(it) }
        users.forEach(System.out::println)




        userDao.update(User(5, "Saturn"))
        users.forEach(System.out::println)
        try {
            db.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}