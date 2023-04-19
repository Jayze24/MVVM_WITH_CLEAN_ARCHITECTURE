package space.jay.mvvm_with_clean_architecture.core.common.window

data class TypeWindow(
    val pane : TypePane,
    val orientation : TypeOrientation
)

enum class TypePane {
    SINGLE,
    DUAL
}

enum class TypeOrientation {
    PORTRAIT,
    LANDSCAPE,
    LANDSCAPE_WIDE
}