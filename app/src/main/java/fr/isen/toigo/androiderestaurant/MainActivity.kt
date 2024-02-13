package fr.isen.toigo.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.toigo.androiderestaurant.ui.theme.AndroidERestaurantTheme

enum class DishType {
    STARTERS,
    MAIN,
    DESSERTS,
}

interface MenuInterface {
    fun dishPressed(dish: DishType)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidERestaurantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(object : MenuInterface {
                        override fun dishPressed(dish: DishType) {
                            // Handle dishPressed event
                        }
                    })
                }
            }
        }
    }
}
@Composable
fun Greeting(menu: MenuInterface) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        ElevatedButton(onClick = {
            menu.dishPressed(DishType.STARTERS)
        },
            modifier = Modifier
                .padding(top = 8.dp)
                .height(100.dp)
                .fillMaxWidth(2f)
        ) {
            Text(
                text = stringResource(id = R.string.starters),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(25.dp),
            )
        }

        ElevatedButton(onClick = {
            menu.dishPressed(DishType.MAIN)
        },
            modifier = Modifier
                .padding(top = 8.dp)
                .height(100.dp)
                .fillMaxWidth(2f)
        ) {
            Text(
                text = stringResource(id = R.string.main),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(25.dp),
            )
        }

        ElevatedButton(onClick = {
            menu.dishPressed(DishType.DESSERTS)
        },
            modifier = Modifier
                .padding(top = 8.dp)
                .height(100.dp)
                .fillMaxWidth(2f)
        ) {
            Text(
                text = stringResource(id = R.string.desserts),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(25.dp),
            )
        }
    }
}


@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    AndroidERestaurantTheme {
        Greeting(object : MenuInterface {
            override fun dishPressed(dish: DishType) {
                // Nothing to do
            }
        })
    }
}
