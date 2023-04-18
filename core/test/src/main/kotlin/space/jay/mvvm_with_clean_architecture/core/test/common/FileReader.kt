package space.jay.mvvm_with_clean_architecture.core.test.common

import android.content.Context
import java.io.File

class FileReader {
    fun readJson(context : Context, fileName : String) : String {
        val resourceName = if (!fileName.endsWith(".json")) "$fileName.json" else fileName
        val file = File(context.classLoader.getResource(resourceName).file)
        return file.bufferedReader().use { it.readText() }
    }
}