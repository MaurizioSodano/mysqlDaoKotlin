package com.mynewco.db

import com.mynewco.model.User
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.sql.Connection
import java.util.Properties

class UserDaoImplTest {
    private  var conn: Connection? = null

    @After
    fun tearDown() {
        println("After")
        val db=Database.instance
        db.close()
    }

    @Before
    fun setUp() {
        println("Before")
        val db=Database.instance
        val props=Profile.getProperties("db")

        db.connect(props)
        conn=db.connection
        conn?.autoCommit=false
    }

    @Test
    fun save() {
        val user=User("Jupiter")
        val userDao=UserDaoImpl()

        userDao.save(user)
        val stmt=conn?.createStatement()
        val rs=stmt?.executeQuery("select id, name from user order by id desc;")

        val result=rs?.next()==true
        assertTrue("cannot retrieve inserted user",result)

        val name=rs?.getString("name")

        assertEquals("user name doesn't match retrieved",user.name,name)

        stmt?.close()

    }
}