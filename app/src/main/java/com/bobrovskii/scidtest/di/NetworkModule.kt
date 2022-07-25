package com.bobrovskii.scidtest.di

import android.content.Context
import androidx.room.Room
import com.bobrovskii.books.data.api.BooksApi
import com.bobrovskii.books.data.db.BookDB
import com.bobrovskii.books.data.db.BookDao
import com.bobrovskii.books.data.db.BookRemoteKeysDao
import com.bobrovskii.books.data.repository.BooksRepositoryImpl
import com.bobrovskii.books.domain.repository.BooksRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

	private val contentType = "application/json".toMediaType()

	private val json = Json {
		ignoreUnknownKeys = true
	}

	@Provides
	@Singleton
	fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
		HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}

	@Provides
	@Singleton
	fun provideOkHttpClient(
		httpLoggingInterceptor: HttpLoggingInterceptor,
	): OkHttpClient =
		OkHttpClient
			.Builder()
			.addInterceptor(httpLoggingInterceptor)
			.build()

	@Provides
	@Singleton
	fun provideRetrofit(client: OkHttpClient): Retrofit =
		Retrofit
			.Builder()
			.baseUrl("http://candidate.scid.ru/")
			.addConverterFactory(json.asConverterFactory(contentType))
			.client(client)
			.build()

	@Provides
	@Singleton
	fun provideBooksApi(retrofit: Retrofit): BooksApi =
		retrofit.create()

	@Provides
	@Singleton
	fun provideBookDB(@ApplicationContext context: Context): BookDB =
		Room.databaseBuilder(
			context,
			BookDB::class.java,
			"BookDataBase",
		).build()

	@Provides
	@Singleton
	fun provideBookDao(db: BookDB): BookDao =
		db.bookDao()

	@Provides
	@Singleton
	fun provideBookRemoteKeysDao(db: BookDB): BookRemoteKeysDao =
		db.bookRemoteKeysDao()

	@Provides
	@Singleton
	fun provideBooksRepository(api: BooksApi, db: BookDB): BooksRepository =
		BooksRepositoryImpl(api, db)
}