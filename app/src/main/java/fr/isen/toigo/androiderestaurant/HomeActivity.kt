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
import fr.isen.toigo.androiderestaurant.ui.theme.AndroidERestaurantTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

enum class DishType {
    STARTERS, MAIN, DESSERTS;

    @Composable
    fun title(): String {
        return when(this) {
            STARTERS -> stringResource(id = R.string.starters)
            MAIN -> stringResource(id = R.string.main)
            DESSERTS -> stringResource(id = R.string.desserts)
        }
    }

    @Composable
    fun key(): String {
        return when(this) {
            STARTERS -> "Entrées"
            MAIN -> "Plats"
            DESSERTS -> "Desserts"
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
        Image(painterResource(R.drawable.ic_launcher_foreground), null)
        CustomButton(type = DishType.STARTERS, menu)
        Divider()
        CustomButton(type = DishType.MAIN, menu)
        Divider()
        CustomButton(type = DishType.DESSERTS, menu)
    }
}

@Composable fun CustomButton(type: DishType, menu: MenuInterface) {
    TextButton(onClick = { menu.dishPressed(type) }) {
        Text(type.title())
    }
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    AndroidERestaurantTheme {
        SetupView(HomeActivity())
    }
}