package com.codingfeline.fktest

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DbHelper private constructor(
    private val context: Context
) {
    private var database: Database? = null

    actual fun getDatabase(): Database {
        if (database != null) return database!!

        return Database(getAndroidSqlDriver(context)).also { database = it }
    }

    companion object {
        fun create(context: Context): DbHelper {
            return DbHelper(context)
        }
    }
}

internal fun getAndroidSqlDriver(context: Context): SqlDriver {
    return AndroidSqliteDriver(
        schema = FkTestSchema,
        context = context,
        name = DB_NAME
    )
}
