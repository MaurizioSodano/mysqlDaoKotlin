package com.mynewco.db

import com.mynewco.model.User
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.Connection
import java.util.function.Supplier
import kotlin.streams.asSequence


class UserDaoImplTest {
    private var conn: Connection? = null

    private var users: List<User>? = null

    companion object {
        const val NUM_TEST_USERS = 2
    }

    private fun loadUsers(): List<User> {
        //val users = arrayListOf<User>()
        val path = Paths.get(
            Companion::class.java.classLoader?.getResource("greaterexpectations.txt")?.toURI()!!
        )

        val streamSupplier =
            Supplier {
                Files.lines(path).map { line -> line.split("\\W+".toRegex()) }
            }
        //val temp = streamSupplier.get().asSequence().flatten().filter { word -> word.length > 3 }


        //temp.forEach(::println)
        return streamSupplier.get().asSequence()
            .flatten()
            .filter { word -> word.length > 3 }
            .take(NUM_TEST_USERS)
            .map { name -> User(name) }

            .toList()


    }

    @After
    fun tearDown() {
        println("After")
        val db = Database.instance
        db.close()
    }

    @Before
    fun setUp() {
        println("Before")
        users = loadUsers()
        println(users?.size)
        //users?.forEach { user -> run { val userDao = UserDaoImpl(); userDao.save(user); } }
        val db = Database.instance
        val props = Profile.getProperties("db")

        db.connect(props)
        conn = db.connection
        conn?.autoCommit = false
    }

    @Test
    fun save() {
        val user = User("Jupiter")
        val userDao = UserDaoImpl()

        userDao.save(user)
        val stmt = conn?.createStatement()
        val rs = stmt?.executeQuery("select id, name from user order by id desc;")

        val result = rs?.next() == true
        assertTrue("cannot retrieve inserted user", result)

        val name = rs?.getString("name")

        assertEquals("user name doesn't match retrieved", user.name, name)

        stmt?.close()

    }


    private fun getMaxId(): Int {
        val stmt = conn?.createStatement()
        val rs = stmt?.executeQuery("select max(id) as id from user")

        val maxInt = if (rs?.next() == true) {
            rs.getInt("id")

        } else {
            0
        }

        stmt?.close()
        return maxInt
    }

    private fun getUserInRange(minId: Int, maxId: Int): List<User> {
        val stmt = conn?.prepareStatement("select id, name from user where id>=? and id<=?")
        stmt?.setInt(1, minId)
        stmt?.setInt(2, maxId)
        val rs=stmt?.executeQuery()
        val users= arrayListOf<User>()
        while (rs?.next()!=false){
            val id= rs!!.getInt("id")
            val name= rs.getString("name")
            users.add(User(id,name))
        }
        stmt.close()
        return users
    }

    @Test
    fun saveMultiple() {
        val userDao = UserDaoImpl()
        for (user in users!!) {
            userDao.save(user)
        }
        val maxId = getMaxId()

        println("maxId=$maxId")
        for ( i  in 0 until users!!.size){
            val id=(maxId-users!!.size)+i+1
            println("id=$id")
            users!![i].id=id
        }
        val retrievedUsers=getUserInRange(maxId-users!!.size+1,maxId)
        assertEquals("max Id differs from expected", retrievedUsers.size, NUM_TEST_USERS)
        assertEquals("max Id differs from expected", users,retrievedUsers)

    }
}