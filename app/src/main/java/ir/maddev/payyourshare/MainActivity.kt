package ir.maddev.payyourshare

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.maddev.payyourshare.data.model.local.PaymentLocal
import ir.maddev.payyourshare.data.model.local.ShareLocal
import ir.maddev.payyourshare.data.source.local.PaymentDao
import ir.maddev.payyourshare.data.source.local.ShareDao
import ir.maddev.payyourshare.ui.theme.PayYourShareTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var paymentDao: PaymentDao

    @Inject
    lateinit var shareDao: ShareDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            val paymentLocal = PaymentLocal(id = 1, totalAmount = 100)
            paymentDao.save(paymentLocal)
            Log.i("RoomTesting", "onCreate: saving PaymentLocal with id = 1")

            val shareLocal1 = ShareLocal(id = 1, paymentOwnerId = 1, amount = 45)
            val shareLocal2 = ShareLocal(id = 2, paymentOwnerId = 1, amount = 55)
            shareDao.save(shareLocal1)
            Log.i("RoomTesting", "onCreate: saving ShareLocal with   id = 1")
            shareDao.save(shareLocal2)
            Log.i("RoomTesting", "onCreate: saving ShareLocal with   id = 2")

            val paymentWithShares = paymentDao.getAll()[0]
            Log.i("RoomTesting", "onCreate: loading PaymentWithShares: $paymentWithShares")
        }

        setContent {
            PayYourShareTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PayYourShareTheme {
        Greeting("Android")
    }
}