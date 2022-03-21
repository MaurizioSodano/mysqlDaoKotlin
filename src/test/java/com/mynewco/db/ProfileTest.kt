package com.mynewco.db

import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ProfileTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testLoadDbConfig(){
        val props=Profile.getProperties("db")

        assertNotNull("cannot load db properties",props);

        val dbName=props.getProperty("database")
        assertEquals("dbname incorrect",dbName,"peopletest")
    }
}