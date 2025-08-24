package com.abhishek.githubusers.utils

import com.abhishek.githubusers.utils.AppConstants.NETWORK_ERROR
import com.abhishek.githubusers.utils.AppConstants.UNKNOWN_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

fun <T> safeApiCall(apiCall: suspend () -> T): Flow<Result<T>> = flow {
    emit(Result.Loading)
    try {
        emit(Result.Success(apiCall()))
    } catch (e: HttpException) {
        emit(Result.Error(e.localizedMessage ?: UNKNOWN_ERROR))
    } catch (e: IOException) {
        emit(Result.Error(NETWORK_ERROR))
    } catch (e: Exception) {
        emit(Result.Error(e.localizedMessage ?: UNKNOWN_ERROR))
    }
}
