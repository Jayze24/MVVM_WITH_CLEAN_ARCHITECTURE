package space.jay.mvvm_with_clean_architecture._core.common.log

import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import space.jay.mvvm_with_clean_architecture._core.common.BuildConfig

object Log {

    init {
        val adapter = object : DiskLogAdapter(
            PrettyFormatStrategy.newBuilder()
                .tag("jayDebug")
                .methodOffset(1)
                .build()
        ) {
            override fun isLoggable(priority : Int, tag : String?) : Boolean {
                return BuildConfig.DEBUG
            }
        }
        Logger.addLogAdapter(adapter)
    }

    fun send(message : Any) {
        Logger.d(message)
    }
}