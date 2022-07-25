package com.bobrovskii.scidtest.di

import com.bobrovskii.detail.presentation.DetailRouter
import com.bobrovskii.list.presentation.ListRouter
import com.bobrovskii.scidtest.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

	@Provides
	@Singleton
	fun provideNavigator(): Navigator =
		Navigator()

	@Provides
	@Singleton
	fun provideListRouter(navigator: Navigator): ListRouter =
		navigator

	@Provides
	@Singleton
	fun provideDetailRouter(navigator: Navigator): DetailRouter =
		navigator
}