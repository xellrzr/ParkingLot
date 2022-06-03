fun main() {
    var list = (1..20).toList()
    println(list)
    list = list.filter { it % 2 == 0 }
    println(list)
}