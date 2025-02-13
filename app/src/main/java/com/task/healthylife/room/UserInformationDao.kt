package com.task.healthylife.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.task.healthylife.model.UserInformation
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInformationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userInformation: UserInformation)

    @Query("SELECT * FROM user_information ORDER BY username DESC")
    fun getAllUsers():Flow<List<UserInformation>>

    @Update
    suspend fun updateUser(userInformation: UserInformation)
}