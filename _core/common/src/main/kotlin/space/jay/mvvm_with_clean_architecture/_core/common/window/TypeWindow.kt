package space.jay.mvvm_with_clean_architecture._core.common.window

data class TypeWindow(
    val pane : TypePane,
    val Navigation : TypeNavigationPosition
)

enum class TypePane {
    SINGLE,
    DUAL
}

enum class TypeNavigationPosition {
    BOTTOM,
    START,
    START_WIDE
}