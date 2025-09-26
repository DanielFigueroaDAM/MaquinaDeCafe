package org.example.maquinaPropia


interface EntradaMaquinaCafe{
    fun onEnter(maquinaCafe: MaquinaCafe)
}


sealed class EstadosMaquina: EntradaMaquinaCafe {

    private var tieneVaso: Boolean = true
    private var contadorLimpieza: Int = 0
    class EsperandoDinero(var dinero: Double) : EstadosMaquina(){
        override fun onEnter(maquinaCafe: MaquinaCafe) {
            contadorLimpieza++
            if (contadorLimpieza >= 5) {
                println("La máquina necesita limpieza. Estado: $estadoActual")
                estadoActual = EstadosMaquina.ErrorLimpieza
                return
            }
            (estadoActual as EstadosMaquina.EsperandoDinero).dinero += dinero
            if ( (estadoActual as EstadosMaquina.EsperandoDinero).dinero >= 1.0) {
                println("Dinero suficiente")
                setMaquinaEstado(EstadosMaquina.EsperandoInstruccion())
            } else {
                println("Por favor, inserta al menos 1.0 unidad de dinero.")
            }
        }

        override fun toString(): String {
            return "EsperandoDinero(dinero=$dinero)"
        }
    }
    class EsperandoInstruccion(var eleccion: Int = 0) : EstadosMaquina() {
        override fun onEnter(maquinaCafe: MaquinaCafe) {
            println("Has seleccionado la opción: $eleccion")
            if (eleccion in 1..3) {
                println("Preparando tu café...")
                setMaquinaEstado(EstadosMaquina.Elaborando())
                setMaquinaEstado(EstadosMaquina.EsperandoExtraccion())
                println("¡Café listo! Por favor, recoge tu café.")
            } else {
                println("Elección inválida. Por favor, elige 1, 2 o 3.")
            }
        }
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
