package com.codingfeline.fktest.base

import androidx.test.core.app.ApplicationProvider
import com.codingfeline.fktest.getAndroidSqlDriver
import com.squareup.sqldelight.db.SqlDriver

actual fun getSqlDriver(): SqlDriver {
    return getAndroidSqlDriver(ApplicationProvider.getApplicationContext())
}
