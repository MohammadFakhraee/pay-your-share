package ir.maddev.payyourshare.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.maddev.payyourshare.data.model.local.GroupLocal
import ir.maddev.payyourshare.data.model.local.PaymentLocal
import ir.maddev.payyourshare.data.model.local.PersonLocal
import ir.maddev.payyourshare.data.model.local.ShareLocal
import ir.maddev.payyourshare.data.model.local.crossrefs.GroupPersonCrossRef
import ir.maddev.payyourshare.data.model.local.crossrefs.PaymentTagCrossRef

@Database(
    entities = [ShareLocal::class, PersonLocal::class, PaymentLocal::class, GroupLocal::class, GroupPersonCrossRef::class, PaymentTagCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun shareDao(): ShareDao

    abstract fun paymentDao(): PaymentDao

    abstract fun groupDao(): GroupDao
}