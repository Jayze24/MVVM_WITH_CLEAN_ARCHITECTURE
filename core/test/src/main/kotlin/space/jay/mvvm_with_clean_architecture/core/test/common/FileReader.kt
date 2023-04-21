package space.jay.mvvm_with_clean_architecture.core.test.common

import android.content.Context
import java.io.File

object FileReader {
    fun readJson(context : Context, fileName : String) : String {
        val resourceName = if (!fileName.endsWith(".json")) "$fileName.json" else fileName
        val file = File(context.classLoader.getResource(resourceName).file)
        return file.bufferedReader().use { it.readText() }
    }

    fun readRaw(context : Context, id : Int) = context.resources.openRawResource(id).bufferedReader().use { it.readText() }
}