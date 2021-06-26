data class Banco(
    var nome:String,
    var numero:Int

    
){
    override fun toString() = "$nome : $numero"
}

fun main(){
    var bancoDoPara = Banco(nome="BanPará", numero=37)

    println(bancoDoPara)
    val bancoDoParaCopia = bancoDoPara.copy(numero=037)
    println(bancoDoParaCopia)
}