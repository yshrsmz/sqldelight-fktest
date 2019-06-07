package com.codingfeline.fktest

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver

actual class DbHelper {

    private var database: Database? = null

    actual fun getDatabase(): Database {
        if (database != null) return database!!
        return Database(getNativeSqlDriver()).also { database = it }
    }

    companion object {
        fun create(): DbHelper {
            return DbHelper()
        }
    }
}

internal fun getNativeSqlDriver(): SqlDriver {
    return NativeSqliteDriver(
        schema = FkTestSchema,
        name = DB_NAME
    )
}
