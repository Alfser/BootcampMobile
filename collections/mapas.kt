

fun main(){

    //tipo pair. Used to map element
    val pair:Pair<String, String> = Pair("maria", "trabalhando")

    val map1 = mapOf(pair)

    map1.forEach{
        (key, value) -> println("$key - $value")
    }

    println("##################")
    val map2 = mapOf("João" to "desempregado", "Carlos" to "estudando", "Isabela" to "estudando")
    map2.forEach{
        (key, value) -> println("$key - $value")
    }
}