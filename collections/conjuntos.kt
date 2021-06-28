

fun main(){
    
    val mario = Pessoa("Mario", 1, true)
    val karina = Pessoa("Karina", 2, false)
    val lucas = Pessoa("Lucas", 3, false)
    val larissa = Pessoa("Larissa", 4, true)

    val setHasJob = setOf(mario, larissa)
    val setNoJob = setOf(karina, lucas)

    val resultUnion = setHasJob.union(setNoJob)

    
    println(resultUnion)
}

data class Pessoa(
    var nome:String = "Foo",
    val id: Int,
    val hasJob:Boolean
){
    override fun toString() = "$id : $nome"
}