package com.mynewco.db

import java.util.Optional

interface Dao<T> {
    fun save(t: T)
    fun findById(id: Int): Optional<T>?
    fun update(t: T)
    fun delete(t: T)
}