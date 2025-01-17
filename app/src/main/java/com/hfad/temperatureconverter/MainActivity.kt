package com.hfad.temperatureconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfad.temperatureconverter.ui.theme.TemperatureConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TemperatureConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainActivityContent(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainActivityContent(modifier: Modifier = Modifier) {
    var celsius by remember { mutableIntStateOf(0) }
    var newCelsius by remember { mutableStateOf("") }

    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        Header(image = R.drawable.sunrise, description = "Sunrise image")
        EnterTemperature(temperature = newCelsius) {
            newCelsius = it
        }
        CovertButton(modifier = Modifier.align(alignment = Alignment.CenterHorizontally).padding(vertical = 16.dp)) {
            newCelsius.toIntOrNull()?.let {
                celsius = it
            }
        }
        TemperatureText(celsius = celsius)
    }
}

@Composable
fun Header(image: Int, description: String) {
    Image(
        painter = painterResource(image),
        contentDescription = description,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun EnterTemperature(temperature: String, changed: (String) -> Unit) {
    TextField(
        value = temperature,
        label = { Text("Enter a temperature in Celsius") },
        onValueChange = changed,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CovertButton(modifier: Modifier = Modifier, clicked: () -> Unit) {
    Button(onClick = clicked, modifier = modifier) {
        Text("Convert", fontSize = 20.sp)
    }
}

@Composable
fun TemperatureText(celsius: Int) {
    val fahrenheit = (celsius.toDouble() * 9/5) + 32
    Text("$celsius Celsius is $fahrenheit Fahrenheit", fontSize = 20.sp)
}



@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    TemperatureConverterTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainActivityContent(Modifier.padding(innerPadding))
        }
    }
}