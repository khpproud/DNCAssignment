package dev.patrick.dncassignment.data.local.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun clearUsers()

    @Query("SELECT * FROM users WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchUsers(query: String): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Long): Flow<UserEntity?>
}