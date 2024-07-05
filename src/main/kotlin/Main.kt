import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun LabelCheckbox(
	checked: Boolean,
	onCheckedChange: ((Boolean) -> Unit)?,
	rowModifier: Modifier = Modifier,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
	colors: CheckboxColors = CheckboxDefaults.colors(),
	label: (@Composable () -> Unit)
) {
	Row(
		rowModifier.then(Modifier.clickable { onCheckedChange?.invoke(!checked) }),
//        horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	) {
		Checkbox(checked, onCheckedChange, modifier, enabled, colors, interactionSource)
		label()
	}
}

var bingo = Bingo()

@Composable
@Preview
fun CardScreen() {
	var text by remember { mutableStateOf("") }
	MaterialTheme {
		Column(horizontalAlignment = Alignment.CenterHorizontally) {
			Text("Welcome to Bingo! Go to the Call tab to start calling elements, and the Input tab to change the elements on the Bingo card. The middle space is free.")
			TextField(
				value = text,
				onValueChange = {},
				Modifier.fillMaxWidth(),
				readOnly = true,
				minLines = 6,
				textStyle = TextStyle(fontFamily = FontFamily.Monospace)
			)
			Button({
				text = bingo.header + '\n' + bingo.card().joinToString("\n") { it.joinToString(" ") }
			}, Modifier.fillMaxWidth()) { Text("Generate card") }
		}
	}
}

@Composable
@Preview //workaround for previewparameter not being implemented
fun CallScreenPreview() {
	CallScreen(418.dp, 15f) { _, _ -> run {} }
}

@Composable
fun CallScreen(maxHeight: Dp, sliderPosition: Float, onPositionChange: (Float, Boolean) -> Unit) {
	MaterialTheme {
		val error = mutableStateOf(false)
		var calling by remember { mutableStateOf(false) }
		val calls by remember { mutableStateOf(mutableListOf<Pair<Bingo.Column, String>>()) }
		val clipboard = LocalClipboardManager.current
		var pair: Pair<Bingo.Column, String> by remember { mutableStateOf(Pair(Bingo.Column.Empty, "")) }
		LazyColumn(Modifier.verticalScroll(rememberScrollState()).height(maxHeight), horizontalAlignment = Alignment.CenterHorizontally) {
			item {
				Text("Calls will be copied to clipboard unless the delay between calls is less than 1. Set the delay between calls in seconds below.")
				Row(
					verticalAlignment = Alignment.CenterVertically /*, horizontalArrangement = Arrangement.SpaceBetween*/
				) {
					Slider(
						value = sliderPosition,
						onValueChange = { onPositionChange(it, true) },
						valueRange = 0f..60f,
						steps = 59,
						modifier = Modifier.weight(3f)
					) //why
					TextField(
						sliderPosition.toString(),
						{
							try {
								error.value = false
								onPositionChange(it.toFloat(), false)
							} catch (e: Exception) {
								error.value = true
							}
						},
						Modifier.weight(1f).semantics {
							if (error.value) error("Please enter a valid decimal.")
						},
						label = { Text("Delay (s)") },
						isError = error.value,
						singleLine = true
					)
				}
				// Calling logic ***
				Button({
					calling = !calling
					if (calling) {
						calls.clear()
					}
				}, Modifier.fillMaxWidth()) {
					Text(
						if (!calling) "(Re)start calling"
						else "Stop calling"
					)
					LaunchedEffect(calling) {
						while (calling) {
							pair = bingo.call()
							if (pair.first != Bingo.Column.Empty) {
								calls.add(pair)
								clipboard.setText(AnnotatedString(pair.first.name + " " + pair.second))
							} else {
								calling = false;
								calls.add(pair);
								break;
							}
							delay(sliderPosition.toDouble().toDuration(DurationUnit.SECONDS))
						}
					}
				}
			}
//			Card {
			items(calls.asReversed()) {
				if (it.first != Bingo.Column.Empty) {
					Row {
						Text(
							it.first.name,
							Modifier.fillMaxWidth(0.5f),
							fontWeight = FontWeight.Black
						)
						Text(it.second, Modifier.fillMaxWidth(0.5f))
					}
				}
			}
//			}
			item {
				Text(calls.toString())
				Text(pair.toString())
			}
		}
	}
}


var changed = false

@Composable
@Preview
fun InputScreen() {
	var input by mutableStateOf(bingo.elements.joinToString(separator = "@"))
	var emoji by mutableStateOf(bingo.emoji)
	var space by remember { mutableStateOf(true) }

	MaterialTheme {
		Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
			Text("Input the elements (i.e. \"numbers\") that will appear on Bingo cards and separate them with the '@' character. Please make sure the total number of elements (excluding the free space) is divisible by 5, as the remainder will be ignored. The recommended number of elements is 75.")
			Row(horizontalArrangement = Arrangement.SpaceBetween) {
				LabelCheckbox(emoji, { emoji = !emoji; changed = true }) { Text("Emoji mode") }
				LabelCheckbox(space, { space = !space; changed = true }) { Text("Automatically space") }

			}
			/*            Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
							Checkbox(bingo.emoji, { bingo.emoji = true })
							Text("Emoji mode")
						}*/
			TextField(input, { input = it; changed = true }, Modifier.fillMaxWidth()/*, minLines = 6*/)
			Button(onClick = {
				if (changed) {
					//@TODO: Check size of generated elements and alert or remove elements appropriately
					bingo = Bingo(nums = input, emoji = emoji, freeSpace = "∅", space = space); changed = false
					input = bingo.elements.joinToString(separator = "@")
				}
			}, Modifier.fillMaxWidth(), enabled = changed) { Text("Apply changes") }
		}
	}
}

@Composable
@Preview
fun TabContainer() {
	var index by mutableStateOf(0)
	val tabs = listOf("Card", "Call", "Input")
	val newTab = mutableStateOf(-1)
	var sliderPosition by remember { mutableStateOf(15f) }
	MaterialTheme {
		Scaffold(Modifier.fillMaxWidth(), topBar = {
			TabRow(index) {
				tabs.forEachIndexed { i, title ->
					Tab(index == i, {
						if (changed) {
							newTab.value = i
						} else index = i
					}, text = { Text(title) })
				}
			}
		}) { // to host the dialog box
			BoxWithConstraints(Modifier.padding(it)) {
				when (index) {
					0 -> CardScreen()
					1 -> CallScreen(maxHeight, sliderPosition) { it: Float, round: Boolean ->
						sliderPosition = if (!round) it else it.roundToInt().toFloat()
					}

					2 -> InputScreen()
				}
			}
			if (newTab.value != -1) {
				AlertDialog(
					onDismissRequest = {},
					icon = { Icon(Icons.Sharp.Warning, contentDescription = "Warning") },
					title = { Text("Discard changes?") },
					text = { Text("You have not saved your settings changes by clicking \" Apply changes\". Are you sure you want to discard your changes?") },
					confirmButton = {
						TextButton({
							changed = false; index = newTab.value; newTab.value = -1
						}) { Text("Discard") }
					},
					dismissButton = { TextButton({ newTab.value = -1 }) { Text("Cancel") } })
			}
		}
	}
}

fun main() = application {
	Window(onCloseRequest = ::exitApplication) {
		TabContainer()
	}
}
