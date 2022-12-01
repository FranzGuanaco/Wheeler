package com.order
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context, factory: SQLiteDatabase.CursorFactory?) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            val query = ("CREATE TABLE " + TABLE_NAME + " ("
                    + ID + " INTEGER PRIMARY KEY, " +
                    PASSWORD + " TEXT" +")")

            db.execSQL(query)
        }

        override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
            // this method is to check if table already exists
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            onCreate(db)
        }

        fun AddData(id : String, password : String ){

            // below we are creating a content values variable
            val values = ContentValues()

            // we are inserting our values in the form of key-value pair
            values.put(ID, id)
            values.put(PASSWORD, password)

            // here we are creating a writable variable of our database as we want to insert value in our database
            val db = this.writableDatabase

            // all values are inserted into database
            db.insert(TABLE_NAME, null, values)

            db.close()
        }

        // below method is to get all data from our database
        fun getName(): Cursor? {

            // here we are creating a readable variable of our database as we want to read value from it
            val db = this.readableDatabase

            // below code returns a cursor to read data from the database
            return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

        }

        companion object{
            // here we have defined variables for our database

            // below is variable for database name
            private val DATABASE_NAME = "Db_user"

            // below is the variable for database version
            private val DATABASE_VERSION = 1

            // below is the variable for table name
            val TABLE_NAME = "user_table"

            // below is the variable for id column
            val ID = "id"

            // below is the variable for name column
            val PASSWORD = "name"
        }
    }
