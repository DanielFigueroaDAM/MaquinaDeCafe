package org.example.maquinaPropia





object MaquinaCafe{
    public var estadoActual: EstadosMaquina = EstadosMaquina.EsperandoDinero(0.0)
    fun makeCoffee(dinero: Double = 0.0, eleccion: Int = 0, retirarVaso: Boolean = false, limpiar: Boolean = false){
        println("Estado actual: $estadoActual")

        when (estadoActual) {
            is EstadosMaquina.EsperandoDinero -> {

            }
            is EstadosMaquina.EsperandoInstruccion -> {


            }
            is EstadosMaquina.Elaborando -> {
                println("¡Espera! La máquina ya está haciendo café.")
                Thread.sleep(2000)

                tieneVaso = true
            }
            is EstadosMaquina.EsperandoExtraccion -> {
                tieneVaso = retirarVaso
                if(!tieneVaso){
                    println("Retire su café. ¡Disfrútalo!")
                }else{
                    println("chaos")
                    estadoActual = EstadosMaquina.EsperandoDinero(0.0)
                }
            }
            is EstadosMaquina.ErrorLimpieza -> {
                println("La máquina necesita limpieza. Por favor, limpia la máquina.")
                if (limpiar) {
                    println("Limpiando la máquina...")
                    estadoActual = EstadosMaquina.EsperandoDinero(0.0)
                    println("Máquina limpia. Estado: $estadoActual")
                    contadorLimpieza = 0
                } else {
                    println("La máquina sigue sucia. No se puede hacer café.")
                }
            }

        }
    }

}