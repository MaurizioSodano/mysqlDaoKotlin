package com.mynewco.db

import java.sql.SQLException
import java.lang.RuntimeException

class DaoException(e: SQLException?) : RuntimeException(e)