package edu.towson.outfitapp.DatabaseData.UserData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.towson.outfitapp.DatabaseData.PostData.Comment
import edu.towson.outfitapp.DatabaseData.PostData.Post
import edu.towson.outfitapp.DatabaseData.PostData.PostDao


@Database(entities = [User::class, Post::class, Comment::class], version = 1, exportSchema = false )
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context):UserDatabase{ // this is default code(aka boilerplate code)
            val tempInstance = INSTANCE
            if(tempInstance != null ){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_and_post"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}