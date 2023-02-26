package `in`.sateesh.ipac.data.db.dao

import `in`.sateesh.ipac.constants.AppConstants
import `in`.sateesh.ipac.data.model.User
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(list:List<User>)

    @Query("SELECT * from ${AppConstants.USERS_TABLE}")
    suspend fun list():List<User>
}