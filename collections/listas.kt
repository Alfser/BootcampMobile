




fun main(){
    val pessoas = listOf(
        Pessoa("Mario", 1, true), 
        Pessoa("Karina", 2, false), 
        Pessoa("Lucas", 3, false), 
        Pessoa("Larissa", 4, true)
    )

    pessoas.sortedBy{it.id}.forEach{println(it)}

    pessoas.groupBy{it.hasJob}.forEach{println(it)}
}

data class Pessoa(
    var nome:String = "Foo",
    val id: Int,
    val hasJob:Boolean
){
    override fun toString() = "$id : $nome"
}