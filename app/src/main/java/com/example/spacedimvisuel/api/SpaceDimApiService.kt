package com.example.spacedimvisuel.api

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL =
    "https://spacedim.async-agency.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SpaceDimApiService {
    @GET("user/{id}")
    suspend fun logUser(@Path("id") userId: Int):
            User

    @GET("user/find/{name}")
    suspend fun findUser(@Path("name") userName: String):
            User

    @Headers("Content-Type:application/json")
    @POST("user/register")
    suspend fun createUser(@Body request: UserPost):
            Response<User>?
}

object SpaceDimApi {
    val retrofitService : SpaceDimApiService by lazy {
        retrofit.create(SpaceDimApiService::class.java) }
}




