/**
 * Calculadora do execício final
 * do curso de introdução ao Kotlin
 */
const val PLUS = 0
const val MINUS = 1
const val DIV = 2
const val MULT = 3

fun main() {
	var result = calculator(3F, 6F, PLUS)
    println("resultado da suma eh $result")
    result = calculator(3F, 6F, MINUS)
    println("resultado da subtração eh $result")
    result = calculator(3F, 6F, DIV)
    println("resultado da divisão eh $result")
    result = calculator(3F, 6F, MULT)
    println("resultado da multiplicação é $result")
    result = calculator(null, 6F, PLUS)
    println(result)
    result = calculator(3F, null, MINUS)
    println(result)
}

fun calculator(a:Float?, b:Float?, operation:Int): Float{
    
    if(a==null || b==null){
      	println("Os valores de entrada não podem ser nulo.")
     return 0F
    } 
    else{
     	return when(operation){
       	 	PLUS -> a+b 
        	MINUS -> a-b 
        	DIV -> a/b 
        	MULT -> a*b 
       		else -> 0F
       }
    }
  }