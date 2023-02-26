package `in`.sateesh.ipac.di

import `in`.sateesh.ipac.BuildConfig
import `in`.sateesh.ipac.constants.AppConstants
import `in`.sateesh.ipac.data.db.UserDatabase
import `in`.sateesh.ipac.data.remote.api.UserApi
import `in`.sateesh.ipac.util.connectivity.ConnectivityObserver
import `in`.sateesh.ipac.util.connectivity.NetworkConnectivityObserver
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = AppConstants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,baseUrl:String):Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(UserApi::class.java)

    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        AppConstants.USER_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideTripDao(db:UserDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideConnectivityObserver(@ApplicationContext app: Context) = NetworkConnectivityObserver(app)
}