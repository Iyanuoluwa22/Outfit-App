package edu.towson.outfitapp.DatabaseData.PagesForTesting

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MyDialog(onDismiss:() -> Unit, onConfirm:() -> Unit){
    AlertDialog(
        title = { Text("Error") },
        text = { Text(text = "Please fill in all fields ") },
        onDismissRequest = { onDismiss() },
        confirmButton = { Button({onConfirm()}){ Text("Confirm") } }
    )
}