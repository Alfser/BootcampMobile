class Analista(
    val nome:String, 
    val cpf:String, 
    val salario:Float,
    val nivel:String
):Funcionario(nome, cpf, salario){
    override fun toString() = "$nome : $cpf"
}


fun main(){
    val analista = Analista("Foo", "324.354.565-65", 5000.342, "junior")
    println(analista)
}