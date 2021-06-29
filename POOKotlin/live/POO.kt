
abstract class Conta(
    val numero:Int,
    val agencia:String,
    private var _saldo: Double
){

    fun saldo(){

    }
}

class ContaPessoaFisica(
    numero:Int,
    agencia:String
):Conta(numero, agencia){
    init{
        println("num: $numero - agência: $agencia")
    }
}

fun main(){
    val contaPessoaFisica = ContaPessoaFisica(1234, "121212-121")
}