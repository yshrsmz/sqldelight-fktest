package com.codingfeline.fktest

import com.squareup.sqldelight.db.SqlDriver

const val DB_NAME = "fktest.db"


object FkTestSchema : SqlDriver.Schema by Database.Schema {
    override fun create(driver: SqlDriver) {
        driver.execute(null, "PRAGMA foreign_keys=1;", 0)
        Database.Schema.create(driver)
    }
}

expect class DbHelper {
    fun getDatabase(): Database
}
