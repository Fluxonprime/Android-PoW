package com.testingout.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.testingout.myapplication.ui.theme.MyApplicationTheme
import kotlin.system.measureTimeMillis
import java.security.MessageDigest
import java.math.BigInteger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val (totalTime, result) = measureTotalTime(1, 2147483647, 3, 20)
                    Greeting(
                        name = "Sakthi Vignesh Ramesh Subramaniam, \nWelcome Back!",
                        modifier = Modifier.padding(innerPadding),
                        totalTime = totalTime,
                        result = result
                    )
                }
            }
        }
    }
    private fun measureTotalTime(start: Int, end: Int, noz: Int, h1: Int): Pair<Long, Int> {
        var result = 0
        var hashString = ""
        val totalTime = measureTimeMillis {
            for (i in start..end) {
                result += i
                val combinedString = result.toString() + h1.toString()
                val md = MessageDigest.getInstance("SHA-256")
                md.update(combinedString.toByteArray())
                val hashBytes = md.digest()
                hashString = hashBytes.joinToString("") { "%02x".format(it) }
                if (hashString.startsWith("0".repeat(noz))) {
                    break
                }
            }
        }
        return Pair(totalTime, result)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, totalTime: Long, result: Int) {
    Text(
        text = "Hello $name!\n\nTotal time taken for iterations: $totalTime ms\nSum of numbers from 1 to 1000: $result",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android", totalTime = 0, result = 0)
    }
}
