package com.example.calculatorsederhana

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorApp()
                }
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("+") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calculator",
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input field for first number
        InputField(value = firstNumber, onValueChange = { firstNumber = it }, label = "First Number")

        Spacer(modifier = Modifier.height(16.dp))

        // Input field for second number
        InputField(value = secondNumber, onValueChange = { secondNumber = it }, label = "Second Number")

        Spacer(modifier = Modifier.height(16.dp))

        // Select operation
        Row {
            OperationButton(text = "+") { operation = "+" }
            Spacer(modifier = Modifier.width(8.dp))
            OperationButton(text = "-") { operation = "-" }
            Spacer(modifier = Modifier.width(8.dp))
            OperationButton(text = "×") { operation = "×" }
            Spacer(modifier = Modifier.width(8.dp))
            OperationButton(text = "÷") { operation = "÷" }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Calculate Button
        Button(onClick = {
            Log.d("CalculatorApp", "Calculating result")
            result = calculateResult(firstNumber, secondNumber, operation)
            Log.d("CalculatorApp", "Result: $result")
        }) {
            Text(text = "Calculate", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Result Text
        Text(
            text = if (result.isNotEmpty()) "Result: $result" else "",
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}

@Composable
fun InputField(value: String, onValueChange: (String) -> Unit, label: String) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(1.dp, Color.Gray)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = label, fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                innerTextField()
            }
        }
    )
}

@Composable
fun OperationButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(50.dp)
            .height(50.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}

fun calculateResult(firstNumber: String, secondNumber: String, operation: String): String {
    Log.d("CalculatorApp", "Calculating: $firstNumber $operation $secondNumber")

    if (firstNumber.isEmpty() || secondNumber.isEmpty()) {
        Log.e("CalculatorApp", "Empty input fields")
        return "Please enter both numbers"
    }
    val num1 = firstNumber.toDoubleOrNull()
    val num2 = secondNumber.toDoubleOrNull()

    if (num1 == null || num2 == null) {
        Log.e("CalculatorApp", "Invalid number input")
        return "Invalid input"
    }

    return when (operation) {
        "+" -> {
            (num1 + num2).toString()
        }
        "-" -> {
            (num1 - num2).toString()
        }
        "×" -> {
            (num1 * num2).toString()
        }
        "÷" -> {
            if (num2 != 0.0) {
                (num1 / num2).toString()
            } else {
                Log.e("CalculatorApp", "Division by zero")
                "Cannot divide by zero"
            }
        }
        else -> {
            Log.e("CalculatorApp", "Unknown operation: $operation")
            "Invalid Operation"
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorTheme {
        CalculatorApp()
    }
}
