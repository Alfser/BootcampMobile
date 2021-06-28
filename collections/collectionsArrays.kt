
//int array
fun funIntArray(){
    val values = IntArray(5)
    values[0] = 10
    values[1] = 20
    values[2] = 30
    values[3] = 40
    values[4] = 50

    values.forEach{
        value -> print("$value ")
    }
}

//int array no especific size
fun funIntArrayOf(){
    val values = intArrayOf(9, 8, 6, 4, 3, 2, 1)
    values.forEach{
        println(it)
    }
}

//string array
fun funArrays(){
    val values = Array<String>(5){""}
    values[0] = "Maria"
    values[1] = "José"
    values[2] = "João"
    values[3] = "Carlos"
    values[4] = "Rogério"

    val values2 = arrayOf("Maria", "José", "João", "Carlos", "Rogério")
    
    values2.forEach{
        print("$it ")
    }
}

//double array. Iterate with index
fun funDoubleArray(){
    val values = doubleArrayOf(12.4, 35.7, 12.9, 12.89, 12.56, 45.67)

    values.forEachIndexed{
        index, value -> println("$index - ${value*0.12}")
    }
}

fun salarios(){
    val salarios = doubleArrayOf(1223.4, 4335.7, 5412.9, 2312.89, 6712.56, 4645.67)
    
    println("Menor Salário: ${salarios.minOrNull()}")
    println("Maior Slário: ${salarios.maxOrNull()}")
    println("Média Salarial: ${salarios.average()}")

    println("Salários acima de 5 mill: ${salarios.filter{it> 5000.0}}")

    println("Quantidade de salários acima de 5 mill ${salarios.count{it > 5000.0}}")

    println("Salários acima de 5000.0 usando 'find': ${salarios.find{it > 5000.0}}")

    println("Existe salários acima de 5 mill? ${if(salarios.any{it > 5000.0}) "sim" else "não"}")
}

fun main(){
    salarios()
}