enum class Meses(val description:String){
    JAN(description="mês de janeiro."),
    FEV(description="mês de fevereiro."),
    MAR(description="mês de março."),
    ABR(description="mês de abril."),
    MAI(description="mês de maio."),
    JUN(description="mês de junho."),
    JUL(description="mês de julho."),
    AGO(description="mês de agosto."),
    SET(description="mês de setembro."),
    OUT(description="mês de outubro."),
    NOV(description="mês de novembro."),
    DEZ(description="mês de dezembro.")
}

fun main(){
    
    for(mes in Meses.values()){
        println("${mes.ordinal}-> ${mes.name}")
    }
}