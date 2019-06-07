package com.codingfeline.fktest.base

import com.codingfeline.fktest.getNativeSqlDriver
import com.squareup.sqldelight.db.SqlDriver

actual fun getSqlDriver(): SqlDriver {
    return getNativeSqlDriver()
}
