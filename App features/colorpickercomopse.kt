package stock.register.godown.stock.record.shop.stock.ledger.ui.setting.business_setting.bill_settings.billSettingOptions.bill_settings_ui.themeAndColor

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ColorLens
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import stock.register.godown.stock.record.shop.stock.ledger.ui.setting.business_setting.components.SettingsTopAppBar
import stock.register.godown.stock.record.shop.stock.ledger.utils.ComposeColors

@Composable
fun ThemeAndColor(
    onNavigateUp: () -> Unit,
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = ComposeColors.greyBackground,
        topBar = {
            SettingsTopAppBar(
                title = "Theme & Colour",
                onBackPressed = onNavigateUp,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ThemeAndColorScreen(onNavigateUp = {}, navController = navController)
        }
    }
}

@Composable
fun ThemeAndColorScreen(
    onNavigateUp: () -> Unit,
    navController: NavController,
) {
    val controller = rememberColorPickerController()
    val isColorPickerClicked = remember { mutableStateOf(true) }
    val horizontalState = rememberScrollState(0)
    val showDialog = remember { mutableStateOf(false) }
    val colorPicked = remember { mutableStateOf("") }
    val colorList = remember {
        mutableStateListOf(
            ColorStruct(0xFFFF0000, "Red", false),
            ColorStruct(0xFF00FF00, "Green", false),
            ColorStruct(0xFF0000FF, "Blue", false),
            ColorStruct(0xFFFFFF00, "Yellow", false),
            ColorStruct(0xFFFF00FF, "Pink", false),
            ColorStruct(0xFF00FFFF, "Cyan", false),
            ColorStruct(0xFFFFA500, "Orange", false),
            ColorStruct(0xFF800080, "Purple", false),
            ColorStruct(0xFF000000, "Black", false),
            ColorStruct(0xFFFFFFFF, "White", false),
        )
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Row(
                modifier = Modifier.horizontalScroll(horizontalState)
            ) {
                Icon(
                    Icons.Rounded.ColorLens,
                    tint = ComposeColors.topBarColor,
                    modifier = Modifier
                        .size(64.dp)
                        .clickable {
                            isColorPickerClicked.value = false
                            showDialog.value = true
                        },
                    contentDescription = "This is a color palette"
                )

                colorList.forEachIndexed { index, color ->
                    ColorPalettes(
                        color = color,
                        onClick = {
                            colorList.forEach { it.isSelected = false }
                            colorList[index].isSelected = true
                        }
                    )
                }
            }
        }
    }
    if (isColorPickerClicked.value) {
        //
    } else {
        Dialog(
            onDismissRequest = { showDialog.value = true },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .pointerInput(Unit) { detectTapGestures { } }
                        .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                        .fillMaxWidth(0.95f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            MaterialTheme.colorScheme.surface,
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        HsvColorPicker(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(450.dp)
                                .padding(10.dp),
                            controller = controller,
                            onColorChanged = {
                                colorPicked.value = it.hexCode
                                Log.d("Color", colorPicked.value)
                            }
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = { isColorPickerClicked.value = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .background(
                                        color = ComposeColors.themeColor,
                                        shape = CircleShape
                                    ),
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(containerColor = ComposeColors.redColorText)

                            ) {
                                Text(
                                    text = "Cancel",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Button(
                                onClick = {
                                    isColorPickerClicked.value = true
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = ComposeColors.themeColor)
                            ) {
                                Text(
                                    text = "Confirm",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }

                        }
                    }
                }
            }
        }
    }

    colorList.forEachIndexed { index, color ->
        if (color.isSelected) {
            Log.d("Selected Color", color.name)
        }
    }
}

@Composable
fun ColorPalettes(color: ColorStruct, onClick: () -> Unit) {
    val selectedColor = remember { mutableStateOf(color.isSelected) }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .background(color = Color(color.color), shape = RoundedCornerShape(100))
                .clickable {
                    onClick()
                    selectedColor.value = !selectedColor.value
                }
        ) {
            if (selectedColor.value) {
                Icon(
                    Icons.Rounded.Done,
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp),
                    contentDescription = "This is a selected color"
                )
            }
        }
    }
}


data class ColorStruct(
    val color: Long = 0xFFFF0000,
    val name: String,
    var isSelected: Boolean = false
)
