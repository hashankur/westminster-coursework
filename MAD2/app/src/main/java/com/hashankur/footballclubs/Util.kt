package com.hashankur.footballclubs

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

fun readJSON(context: Context): JSONObject {
    val inputStream: InputStream = context.assets.open("football_leagues.txt")
    val size: Int = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    return JSONObject(String(buffer))
}

suspend fun fetch(service: String): String {
    val url = URL(service)

    // collecting all the JSON string
    val stb = StringBuilder()

    // run the code of the launched coroutine in a new thread
    withContext(Dispatchers.IO) {
        val bf = BufferedReader(InputStreamReader(url.openConnection().inputStream))
        var line: String? = bf.readLine()
        while (line != null) { // keep reading until no more lines of text
            stb.append(line + "\n")
            line = bf.readLine()
        }
    }
    return stb.toString()
}

suspend fun getImage(imgUrl: URL): ImageBitmap {
    return withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(
            imgUrl.openConnection().getInputStream()
        ).asImageBitmap()
    }
}
