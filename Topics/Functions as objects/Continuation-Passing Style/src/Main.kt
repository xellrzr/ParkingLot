fun square(value: Int, context: Any, continuation: (Int, Any) -> Unit) {
    // TODO: provide implementation here
    val x = value * value
    continuation(x, context)
}
