package org.example.maquinaPropia




object MaquinaCafe{
    public var estadoActual: EstadosMaquina = EstadosMaquina.EsperandoDinero(0.0)
    private var tieneVaso: Boolean = true
    fun makeCoffee(dinero: Double = 0.0, eleccion: Int = 0, retirarVaso: Boolean = false){
        println("Estado actual: $estadoActual")

        when (estadoActual) {
            is EstadosMaquina.EsperandoDinero -> {
                (estadoActual as EstadosMaquina.EsperandoDinero).dinero += dinero
                if ( (estadoActual as EstadosMaquina.EsperandoDinero).dinero >= 1.0) {
                    println("Dinero suficiente")
                    estadoActual = EstadosMaquina.EsperandoInstruccion(eleccion)
                } else {
                    println("Por favor, inserta al menos 1.0 unidad de dinero.")
                }
            }
            is EstadosMaquina.EsperandoInstruccion -> {

                (estadoActual as EstadosMaquina.EsperandoInstruccion).eleccion = eleccion
                println("Has seleccionado la opción: $eleccion")
                if (eleccion in 1..3) {
                    println("Preparando tu café...")
                    estadoActual = EstadosMaquina.Elaborando()
                    estadoActual = EstadosMaquina.EsperandoExtraccion()
                    println("¡Café listo! Por favor, recoge tu café.")
                } else {
                    println("Elección inválida. Por favor, elige 1, 2 o 3.")
                }
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

        }
    }

}