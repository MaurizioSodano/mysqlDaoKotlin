package com.mynewco.db

import com.mynewco.model.User
import java.sql.SQLException

class UserDaoImpl : UserDao {
    override fun save(user: User) {
        val conn = Database.instance.connection;
        try {
            val stmt = conn?.prepareStatement("insert into user (name) values (?)")
            stmt?.setString(1, user.name)
            stmt?.executeUpdate()

            stmt?.close()

        } catch (e: SQLException) {
            throw DaoException(e)
        }

    }

    override fun findById(id: Int): User? {
        val conn = Database.instance.connection;

        var user: User? = null

        try {
            val stmt = conn?.prepareStatement("select * from user where id =?")
            stmt?.setInt(1, id)
            val rs = stmt?.executeQuery()

            if (rs?.next() == true) {
                val name = rs.getString(2)
                user = User(id, name)
            }

            stmt?.close()

        } catch (e: SQLException) {
            throw DaoException(e)
        }
        return user
    }

    override fun update(t: User) {
        TODO("Not yet implemented")
    }

    override fun delete(t: User) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<User> {
        val conn = Database.instance.connection;
        val users = arrayListOf<User>()


        try {
            val stmt = conn?.createStatement()
            val rs = stmt?.executeQuery("select id, name from user")
            while (rs?.next() == true) {
                val id = rs.getInt("id")
                val name = rs.getString("name")
                users.add(User(id, name))

            }
            stmt?.close()

        } catch (e: SQLException) {
            throw DaoException(e)
        }
        return users
    }

}