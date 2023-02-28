package ir.maddev.payyourshare.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.maddev.payyourshare.core.DB_NAME
import ir.maddev.payyourshare.data.source.local.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideApplicationDatabase(@ApplicationContext context: Context): ApplicationDatabase =
        Room.databaseBuilder(context, ApplicationDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideShareDao(applicationDatabase: ApplicationDatabase): ShareDao = applicationDatabase.shareDao()

    @Provides
    @Singleton
    fun providePaymentDao(applicationDatabase: ApplicationDatabase): PaymentDao = applicationDatabase.paymentDao()

    @Provides
    @Singleton
    fun provideGroupDao(applicationDatabase: ApplicationDatabase): GroupDao = applicationDatabase.groupDao()

    @Provides
    @Singleton
    fun provideTagDao(applicationDatabase: ApplicationDatabase): TagDao = applicationDatabase.tagDao()

    @Provides
    @Singleton
    fun providePaymentTagDao(applicationDatabase: ApplicationDatabase): PaymentTagCrossRefDao = applicationDatabase.paymentTagDao()
}