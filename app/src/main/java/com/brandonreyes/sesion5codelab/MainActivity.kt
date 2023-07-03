package com.brandonreyes.sesion5codelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brandonreyes.sesion5codelab.data.Contact
import com.brandonreyes.sesion5codelab.ui.theme.Sesion5CodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sesion5CodelabTheme {
                    MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier){

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true)}

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false})
        } else {
            Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Handsome Contact ")
        Text("by Brandon Reyes!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Go to the List Contact")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    //names:List<String> = List(30) {"$it"}
    contacts: List<Contact> = contactList()

){
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items (items = contacts){ name ->
            Greeting(name)
        }
    }

}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    Sesion5CodelabTheme{
        OnboardingScreen(onContinueClicked = {})

    }
}

@Composable
private fun Greeting(contacts: Contact) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(contacts)
    }
}

@Composable
private fun CardContent(list: Contact ) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(list.id.toString())
            Text(
                text = list.name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(list.position)
                Text(list.plant)
                Text(list.email)
                Text(list.phone)

            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)

@Preview(showBackground = true, widthDp = 320, name = "VISTA PREVIA")
@Composable
fun DefaultPreview() {
    Sesion5CodelabTheme {
        Greetings()
    }
}

@Preview
@Composable
fun MyAppPreview(){
    Sesion5CodelabTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}

fun contactList(): List<Contact>{
    return listOf(
        Contact(1, "Brandon Cruz Reyes", "Asistente de Sistemas IT-EDI", "Planta Principal", "Email: Bcruz@handsome.com.ni", "Ext: 3993-3999"),
        Contact(2, "Jairo Emilio Gadea", "Asistente de Sistemas IT-EDI", "Planta 3", "Email: Jgadea@handsome.com.ni", "Ext: 3500"),
        Contact(3, "Marco Antonio Posadas", "Gerente de Sistemas IT-EDI", "Planta Principal", "Email: Mposadas@handsome.com.ni", "Ext: 3955")
    )
}