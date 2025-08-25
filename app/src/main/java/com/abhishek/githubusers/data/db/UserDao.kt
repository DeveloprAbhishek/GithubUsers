package com.abhishek.githubusers.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE login LIKE '%' || :query || '%' COLLATE NOCASE")
    fun searchUsers(query: String): Flow<List<UserEntity>>

    @Query("DELETE FROM users")
    suspend fun clearAllUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetails(userDetails: UserDetailsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRepositories(repositories: List<UserRepositoryEntity>)

    @Transaction
    @Query("SELECT * FROM user_details WHERE login = :username")
    fun getUserDetailsWithRepos(username: String): Flow<UserDetailsWithRepos?>

    @Transaction
    suspend fun upsertUserDetailAndRepos(
        details: UserDetailsEntity,
        repos: List<UserRepositoryEntity>
    ) {
        insertUserDetails(details)
        deleteReposForUser(details.login)
        insertUserRepositories(repos)
    }

    @Query("DELETE FROM user_repositories WHERE userLogin = :login")
    suspend fun deleteReposForUser(login: String)
}
