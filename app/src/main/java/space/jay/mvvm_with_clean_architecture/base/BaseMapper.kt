package space.jay.mvvm_with_clean_architecture.base

interface BaseMapper<in I, out O> {
    fun map(input: I) : O
}