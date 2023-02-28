package ir.maddev.payyourshare.utils

import android.graphics.Color
import ir.maddev.payyourshare.data.model.local.PaymentLocal
import ir.maddev.payyourshare.data.model.local.PersonLocal
import ir.maddev.payyourshare.data.model.local.ShareLocal
import ir.maddev.payyourshare.data.model.local.TagLocal
import ir.maddev.payyourshare.data.model.local.crossrefs.PaymentTagCrossRef

/**
 * [PersonLocal] objects used for tests.
 */
val testPersons = arrayListOf(
    PersonLocal(id = 1, name = "Mohammad", avatarPath = ""),
    PersonLocal(id = 2, name = "Hassan", avatarPath = ""),
    PersonLocal(id = 3, name = "Ahmad", avatarPath = "")
)

/**
 * Single object of [PersonLocal] used to test individually.
 */
val testPerson = testPersons[0]

/**
 * [PaymentLocal] objects used for tests.
 */
val testPayments = arrayListOf(
    PaymentLocal(id = 1, personOwnerId = 1, title = "Android", description = "An OS owned by google", totalAmount = 100),
    PaymentLocal(id = 2, personOwnerId = 1, title = "B", description = "An OS owned by google", totalAmount = 250),
    PaymentLocal(id = 3, personOwnerId = 1, title = "C", description = "An OS owned by google", totalAmount = 150)
)

/**
 * Single object of [PaymentLocal] used to test individually.
 */
val testPayment = testPayments[0]

/**
 * [ShareLocal] objects used for first item at [testPayments] tests.
 */
val testShares1 = arrayListOf(
    ShareLocal(id = 1, paymentOwnerId = testPayments[0].id, personOwnerId = 1, amount = 30),
    ShareLocal(id = 2, paymentOwnerId = testPayments[0].id, personOwnerId = 2, amount = 60),
    ShareLocal(id = 3, paymentOwnerId = testPayments[0].id, personOwnerId = 3, amount = 10)
)

/**
 * [ShareLocal] objects used for second item at [testPayments] tests.
 */
val testShares2 = arrayListOf(
    ShareLocal(id = 4, paymentOwnerId = testPayments[1].id, personOwnerId = 1, amount = 150),
    ShareLocal(id = 5, paymentOwnerId = testPayments[1].id, personOwnerId = 2, amount = 50),
    ShareLocal(id = 6, paymentOwnerId = testPayments[1].id, personOwnerId = 3, amount = 50)
)

/**
 * [ShareLocal] objects used for third item at [testPayments] tests.
 */
val testShares3 = arrayListOf(
    ShareLocal(id = 7, paymentOwnerId = testPayments[2].id, personOwnerId = 1, amount = 50),
    ShareLocal(id = 8, paymentOwnerId = testPayments[2].id, personOwnerId = 2, amount = 50),
    ShareLocal(id = 9, paymentOwnerId = testPayments[2].id, personOwnerId = 3, amount = 50)
)

/**
 * Returns a list of all the generated [ShareLocal]s
 */
val testShareAll: List<ShareLocal> get() = testShares1 + testShares2 + testShares3

/**
 * [TagLocal] objects used for tests.
 */
val testTags = arrayListOf(
    TagLocal(id = 1, title = "FastFood", color = Color.WHITE, iconPath = ""),
    TagLocal(id = 2, title = "Cinema", color = Color.BLACK, iconPath = ""),
    TagLocal(id = 3, title = "Mounting", color = Color.BLUE, iconPath = "")
)

/**
 * [PaymentTagCrossRef] object for testing.
 */
val testPaymentTags = arrayListOf(
    PaymentTagCrossRef(paymentId = 1, tagId = 1),
    PaymentTagCrossRef(paymentId = 1, tagId = 2),
    PaymentTagCrossRef(paymentId = 1, tagId = 3)
)