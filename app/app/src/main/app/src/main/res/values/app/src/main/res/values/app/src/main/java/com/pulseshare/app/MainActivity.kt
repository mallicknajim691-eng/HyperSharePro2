package com.pulseshare.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PulseScreen() }
    }
}

private val Bg = Color(0xFF0B0F1A)
private val Mint = Color(0xFF00FFA3)
private val Pink = Color(0xFFFF3D8B)

@Composable
fun PulseScreen() {
    val transition = rememberInfiniteTransition(label = "pulse")
    val p by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2400, easing = LinearEasing), RepeatMode.Restart),
        label = "p"
    )

    Box(
        modifier = Modifier.fillMaxSize().background(Bg),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(300.dp)) {
            val maxR = size.minDimension / 2f
            for (i in 0..2) {
                val q = (p + i / 3f) % 1f
                drawCircle(
                    color = Mint.copy(alpha = 1f - q),
                    radius = maxR * q,
                    style = Stroke(width = 3.dp.toPx())
                )
            }
            drawCircle(color = Pink, radius = 16.dp.toPx())
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 72.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("PulseShare", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Text("Send anything. Instantly.", color = Mint, fontSize = 14.sp)
        }
    }
}
