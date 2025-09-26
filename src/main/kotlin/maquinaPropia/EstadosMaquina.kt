package org.example.maquinaPropia


interface EntrandaMaquinaCafe{
    fun onEnter(MaquinaCafe: MaquinaCafe)
}


sealed class EstadosMaquina: EntradaMaquinaCafe {
    class EsperandoDinero(var dinero: Double) : EstadosMaquina(){
        override fun onEnter(MaquinaCafe: MaquinaCafe) {
            contadorLimpieza++
            if (contadorLimpieza >= 5) {
                println("La máquina necesita limpieza. Estado: $estadoActual")
                estadoActual = EstadosMaquina.ErrorLimpieza
                return
            }
            (estadoActual as EstadosMaquina.EsperandoDinero).dinero += dinero
            if ( (estadoActual as EstadosMaquina.EsperandoDinero).dinero >= 1.0) {
                println("Dinero suficiente")
                estadoActual = EstadosMaquina.EsperandoInstruccion(eleccion)
            } else {
                println("Por favor, inserta al menos 1.0 unidad de dinero.")
            }
        }

        override fun toString(): String {
            return "EsperandoDinero(dinero=$dinero)"
        }
    }
    class EsperandoInstruccion(var eleccion: Int = 0) : EstadosMaquina() {
        override fun toString(): String {
            return "EsperandoInstruccion(eleccion=$eleccion)"
        }
    }
    class Elaborando() : EstadosMaquina(){
        override fun toString(): String {
            return "Elaborando"
        }
    }
    class EsperandoExtraccion() : EstadosMaquina(){
        override fun toString(): String {
            return "EsperandoExtraccion"
        }
    }
    object ErrorLimpieza : EstadosMaquina(){
        override fun toString(): String {
            return "ErrorLimpieza"
        }
    }
}
