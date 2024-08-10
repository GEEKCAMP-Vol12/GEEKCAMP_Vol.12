package component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AddLogDialog(
    onDismissRequest: () -> Unit,
    showReferenceDialog: () -> Unit,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
) {
    var caffeineMg by remember {
        mutableStateOf("")
    }
    var sleepMin by remember {
        mutableStateOf("")
    }
    var caffeineMgError by remember {
        mutableStateOf(false)
    }
    var sleepMinError by remember {
        mutableStateOf(false)
    }
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth(),
                    text = "記録を追加",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )
                Text("カフェイン摂取量")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextField(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .weight(1f),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = caffeineMg,
                        isError = caffeineMgError,
                        onValueChange = {
                            if (it.toIntOrNull() == null) {
                                caffeineMgError = true
                            } else {
                                caffeineMgError = false
                            }
                            caffeineMg = it
                        })
                    Text("mg")
                }
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = showReferenceDialog,
                    ) {
                        Text("カフェイン量が分からない場合")
                    }
                }
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
                Text("睡眠時間")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextField(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .weight(1f),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = sleepMin,
                        isError = sleepMinError,
                        onValueChange = {
                            sleepMin = it
                        })
                    Text("分")
                }
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = onCancel
                    ) {
                        Text("キャンセル")
                    }
                    TextButton(
                        onClick = onSubmit
                    ) {
                        Text("追加")
                    }
                }
            }
        }
    }
}
