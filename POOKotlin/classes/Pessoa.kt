
class Pessoa{
    var nome: String = "Janilson"
    var cpf:String = "333.556.896.532-43"
    private set
    constructor(){

    }

    inner class Endereco{
        var rua:String = "Dois de Dezembro"
        var cep:String = "66816-030"
        var numbero = "7"
    }

    override fun toString() = "class $nome"
}

fun main(){
    val pessoa = Pessoa()
    println(pessoa.nome)
    println(pessoa.cpf)
    println(pessoa)
}