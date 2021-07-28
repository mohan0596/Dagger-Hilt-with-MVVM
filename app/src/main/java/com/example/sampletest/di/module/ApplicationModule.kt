package com.example.sampletest.di.module

import android.content.Context
import androidx.room.Room
import com.example.sampletest.data.api.ApiHelper
import com.example.sampletest.data.api.ApiHelperImpl
import com.example.sampletest.data.api.ApiService
import com.example.sampletest.data.localdb.LocalDatabaseHelper
import com.example.sampletest.data.localdb.LocalRoomDatabase
import com.example.sampletest.data.localdb.RoomDatabaseManager
import com.example.sampletest.utils.Constant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = Constant.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
       return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }



    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Singleton
    @Provides
    fun provideLocalRoomDatabase(context: Context): LocalRoomDatabase {
        return Room.databaseBuilder(context, LocalRoomDatabase::class.java, "db")
            .build()
    }

    @Singleton
    @Provides
    fun provideLocalDatabaseHelper(localRoomDatabase: LocalRoomDatabase): LocalDatabaseHelper {
        return RoomDatabaseManager(localRoomDatabase)
    }

    @Provides
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

}