package fr.isen.toigo.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.isen.toigo.androiderestaurant.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.toigo.androiderestaurant.basket.BasketActivity

import fr.isen.toigo.androiderestaurant.ui.theme.AndroidERestaurantTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


enum class DishType {
    STARTER, MAIN, DESSERT;

    @Composable
    fun title(): String {
        return when(this) {
            STARTER -> stringResource(id = R.string.menu_starter)
            MAIN -> stringResource(id = R.string.menu_main)
            DESSERT -> stringResource(id = R.string.menu_dessert)
        }
    }

    fun filterKey(): String {
        return when(this) {
            STARTER -> "Entrées"
            MAIN -> "Plats"
            DESSERT -> "Desserts"
        }
    }
}

interface MenuInterface {
    fun dishPressed(dishType: DishType)
}

class HomeActivity : ComponentActivity(), MenuInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //getString(R.string.menu_starter)
            AndroidERestaurantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupView(this)
                }
            }
        }
        Log.d("lifeCycle", "Home Activity - OnCreate")
    }

    override fun dishPressed(dishType: DishType) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra(MenuActivity.CATEGROY_EXTRA_KEY, dishType)
        startActivity(intent)
    }

    override fun onPause() {
        Log.d("lifeCycle", "Home Activity - OnPause")
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifeCycle", "Home Activity - OnResume")
    }

    override fun onDestroy() {
        Log.d("lifeCycle", "Home Activity - onDestroy")
        super.onDestroy()
    }
}

@Composable
fun SetupView(menu: MenuInterface) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(width = 250.dp, height = 250.dp)
        )
        Text(
            text = "Bienvenue!",
            modifier = Modifier.padding(vertical = 8.dp), style = TextStyle(
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Choisissez votre commande:",
            modifier = Modifier.padding(vertical = 8.dp), style = TextStyle(
                fontSize = 20.sp,
            )
        )
        Button(
            onClick = { menu.dishPressed(DishType.STARTER) },
            colors = ButtonDefaults.buttonColors(Color(0xFF553425)),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(DishType.STARTER.title())
        }
        Button(
            onClick = { menu.dishPressed(DishType.MAIN) },
            colors = ButtonDefaults.buttonColors(Color(0xFF553425)),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(DishType.MAIN.title())
        }
        Button(
            onClick = { menu.dishPressed(DishType.DESSERT) },
            colors = ButtonDefaults.buttonColors(Color(0xFF553425)),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(DishType.DESSERT.title())
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidERestaurantTheme {
        SetupView(HomeActivity())
    }
}