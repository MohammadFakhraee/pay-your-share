package ir.maddev.payyourshare.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.maddev.payyourshare.data.source.local.ApplicationDatabase
import ir.maddev.payyourshare.data.source.local.GroupDao
import ir.maddev.payyourshare.data.source.local.PaymentDao
import ir.maddev.payyourshare.data.source.local.ShareDao
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context): ApplicationDatabase =
        Room.inMemoryDatabaseBuilder(context, ApplicationDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @Provides
    @Named("share_dao")
    fun provideShareDao(@Named("test_db") applicationDatabase: ApplicationDatabase): ShareDao = applicationDatabase.shareDao()

    @Provides
    @Named("payment_dao")
    fun providePaymentDao(@Named("test_db") applicationDatabase: ApplicationDatabase): PaymentDao = applicationDatabase.paymentDao()

    @Provides
    @Named("group_dao")
    fun provideGroupDao(@Named("test_db") applicationDatabase: ApplicationDatabase): GroupDao = applicationDatabase.groupDao()
}