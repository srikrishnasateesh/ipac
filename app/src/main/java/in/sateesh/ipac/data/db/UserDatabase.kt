package `in`.sateesh.ipac.data.db

import `in`.sateesh.ipac.data.db.dao.UserDao
import `in`.sateesh.ipac.data.model.User
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao():UserDao

}