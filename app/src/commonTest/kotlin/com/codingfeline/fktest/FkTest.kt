package com.codingfeline.fktest

import com.codingfeline.fktest.base.AndroidJUnit4
import com.codingfeline.fktest.base.RunWith
import com.codingfeline.fktest.base.getSqlDriver
import com.squareup.sqldelight.db.SqlDriver
import kotlin.test.*

@RunWith(AndroidJUnit4::class)
class FkTest {

    private lateinit var sqlDriver: SqlDriver

    private lateinit var database: Database

    val author1 = Author.Impl(99, "Michael Ende", "12 November 1929")
    val book1 = Book.Impl(1, "The Neverending Story", author1.id)

    val author2 = Author.Impl(89, "John Ronald Reuel Tolkien", "3 January 1892")
    val book2 = Book.Impl(2, "The Loard of the Rings", author2.id)

    val author3 = Author.Impl(79, "Ursula Kroeber Le Guin", "21 October 1929")
    val book3 = Book.Impl(3, "A Wizard of Earthsea", author3.id)

    @BeforeTest
    fun setup() {
        sqlDriver = getSqlDriver()
        database = Database(sqlDriver)
    }

    @AfterTest
    fun tearDown() {
        database.bookQueries.deleteAll()
        database.authorQueries.deleteAll()
        sqlDriver.close()
    }

    @Test
    fun `should succeed if FK is disabled`() {
//        database.pragmasQueries.foreignKeysOn()

        val books = database.bookQueries.count().executeAsOne()
        val authors = database.authorQueries.count().executeAsOne()

        assertEquals(0, books)
        assertEquals(0, authors)

        database.bookQueries.insert(id = book1.id, name = book1.name, author_id = book1.author_id)
    }

    @Test
    fun `should fail as FK constraint is not satisfied`() {
        database.pragmasQueries.foreignKeysOn()

        val books = database.bookQueries.count().executeAsOne()
        val authors = database.authorQueries.count().executeAsOne()

        assertEquals(0, books)
        assertEquals(0, authors)

        var failed = false
        try {
            database.bookQueries.insert(id = book2.id, name = book2.name, author_id = book2.author_id)
        } catch (e: Throwable) {
            println(e)
            failed = true
        }

        if (!failed) {
            fail("should throw exception")
        }
    }

    @Test
    fun `should fail as FK constraint is not satisfied - transaction`() {
        database.pragmasQueries.foreignKeysOn()

        val books = database.bookQueries.count().executeAsOne()
        val authors = database.authorQueries.count().executeAsOne()

        assertEquals(0, books)
        assertEquals(0, authors)

        var failed = false
        database.transaction {
            try {
                database.bookQueries.insert(id = book3.id, name = book3.name, author_id = book3.author_id)
            } catch (e: Throwable) {
                println(e)
                failed = true
            }
        }

        val books2 = database.bookQueries.count().executeAsOne()
        println("books count after insertion attempt: $books2")
        if (!failed) {
            fail("should throw exception")
        }
    }

    @Test
    fun `should succeed if FK constraint is satisfied`() {
        database.pragmasQueries.foreignKeysOn()

        val books = database.bookQueries.count().executeAsOne()
        val authors = database.authorQueries.count().executeAsOne()

        assertEquals(0, books)
        assertEquals(0, authors)

        database.authorQueries.insert(id = author1.id, name = author1.name, birthday = author1.birthday)
        database.bookQueries.insert(id = book1.id, name = book1.name, author_id = book1.author_id)
    }
}
