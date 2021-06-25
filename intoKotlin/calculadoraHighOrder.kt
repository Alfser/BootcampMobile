/**
 * Calculadora criada a partir da motivação
 * do curso de introdução ao Kotlin
 */
fun main(){
    var soma = calculator(5F, 6F){a, b -> a+b}
    println("resuldado da soma : $soma")
    var sub = calculator(5F, 6F){a, b -> a-b}
    println("resultado da subtração : $sub")
    var mult = calculator(5F, 6F){a, b -> a*b}
    println("resultado da multiplicação $mult")
    var div = calculator(5F, 6F){a, b -> a/b}
    println("resultado da divisão $div")
}

fun calculator(a:Float, b:Float, operator:(Float, Float) -> Float) = operator(a, b)