package com.mynewco.db

import com.mynewco.model.User
import java.sql.SQLException
import java.util.*

class UserDaoImpl : UserDao {
    override fun save(user: User) {
        val conn = Database.instance.connection;
        try {
            val stmt = conn?.prepareStatement("insert into user (name) values (?)")
            stmt?.setString(1,user.name)
            stmt?.executeUpdate()

            stmt?.close()

        } catch (e: SQLException) {
            throw DaoException(e)
        }

    }

    override fun findById(id: Int): Optional<User>? {
        TODO("Not yet implemented")
    }

    override fun update(t: User) {
        TODO("Not yet implemented")
    }

    override fun delete(t: User) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<User> {
        TODO("Not yet implemented")
    }

}