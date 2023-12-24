import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

var nums: String = ""
var bingo = Bingo()

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}

@Composable
@Preview
fun CardScreen() {
    var text by remember { mutableStateOf("") }
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome to Bingo! Go to the Call tab to start calling elements, and the Input tab to change the elements on the Bingo card. The middle space is free.")
            TextField(value = text, onValueChange = {}, Modifier.fillMaxWidth(), readOnly = true, minLines = 6)
            Button({
                text = bingo.header + '\n' + bingo.card().joinToString("\n")
            }, Modifier.fillMaxWidth()) { Text("Generate card") }
        }
    }
}

@Composable
@Preview
fun InputScreen() {
    var input = bingo.elements.joinToString(separator = "@")
    var changed: Boolean by remember { mutableStateOf(false) }
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Input the elements that will appear on Bingo cards (i.e. \"numbers\") and separate them with the '@' character. First element will be for the free space. Please make sure the total number of elements (excluding the free space) is divisible by 5, as the remainder will be ignored. Unless shortcode mode is on, elements will be automatically spaced.")
            Row {
                Text("Emoji mode")
                Switch(bingo.emoji, { bingo.emoji = true })
            }
            TextField(input, { input = it; changed = true }, Modifier.fillMaxWidth()/*, minLines = 6*/)
            Button(onClick = {
                if (changed) {
                    bingo = Bingo(nums = input); changed = false
                    input = bingo.elements.joinToString(separator = "@")
                }
            }, Modifier.fillMaxWidth(), enabled = changed) { Text("Apply changes") }
        }
    }
}

@Composable
@Preview
fun TabContainer() {
    var index by remember { mutableStateOf(0) }
    val tabs = listOf("Card", "Call", "Input")
    MaterialTheme {
        Column(Modifier.fillMaxWidth()) {
            TabRow(index) {
                tabs.forEachIndexed { i, title ->
                    Tab(index == i, { index = i }, text = { Text(title) })
                }
            }
            when (index) {
                0 -> CardScreen()
                2 -> InputScreen()
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        TabContainer()
    }
}
