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
        val conn = Database.instance.connection;
        val users= arrayListOf<User>()


        try {
            val stmt = conn?.createStatement()
            val rs=stmt?.executeQuery("select id, name from user")
            while (rs?.next()==true){
                val id= rs.getInt("id")
                val name=rs.getString("name")
                users.add(User(id,name))

            }
            stmt?.close()

        } catch (e: SQLException) {
            throw DaoException(e)
        }
        return users
    }

}