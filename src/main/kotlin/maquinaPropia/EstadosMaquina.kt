package org.example.maquinaPropia


interface EntradaMaquinaCafe{
    fun onEnter(maquinaCafe: MaquinaCafe)
}


sealed class EstadosMaquina: EntradaMaquinaCafe {

    private var tieneVaso: Boolean = true
    private var contadorLimpieza: Int = 0
    private var dineroMaquina: Double = 0.0
    private var estáLimipia: Boolean = true
    class EsperandoDinero(var dinero: Double) : EstadosMaquina(){
        override fun onEnter(maquinaCafe: MaquinaCafe) {
            contadorLimpieza++
            if (contadorLimpieza >= 5) {
                println("La máquina necesita limpieza. Estado: $estadoActual")
                set MaquinaEstado(EstadosMaquina.ErrorLimpieza)
                return
            }
            dineroMaquina += dinero
            if ( dineroMaquina >= 1.0) {
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
        fun onEnter(maquinaCafe: MaquinaCafe) {
            println("¡Espera! La máquina ya está haciendo café.")
            Thread.sleep(2000)
            tieneVaso = true

        }
        override fun toString(): String {
            return "Elaborando"
        }
    }
    class EsperandoExtraccion() : EstadosMaquina(){
        override fun onEnter(maquinaCafe: MaquinaCafe) {
            tieneVaso = retirarVaso
            if(!tieneVaso){
                println("Retire su café. ¡Disfrútalo!")
            }else{
                println("chaos")
                estadoActual = EstadosMaquina.EsperandoDinero(0.0)
            }
        }
        override fun toString(): String {
            return "EsperandoExtraccion"
        }
    }
    class ErrorLimpieza(var seLimpio: Boolean) : EstadosMaquina(){
        override
        fun onEnter (maquinaCafe: MaquinaCafe){
            println("La máquina necesita limpieza. Por favor, limpia la máquina.")
            if (seLimpio) {
                println("Limpiando la máquina...")
                estáLimipia = true
                println("Máquina limpia. Estado: $estadoActual")
                contadorLimpieza = 0
                setMaquinaEstado(EstadosMaquina.EsperandoDinero(0.0))

            } else {
                println("La máquina sigue sucia. No se puede hacer café.")
            }
        }

        override fun toString(): String {
            return "ErrorLimpieza"
        }
    }
}
