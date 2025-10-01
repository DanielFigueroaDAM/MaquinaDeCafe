package org.example.maquinaPropia

import org.example.maquinaPropia.MaquinaCafe.estadoActual


interface EntradaMaquinaCafe{
    fun onEnter(maquinaCafe: MaquinaCafe)
}


sealed class EstadosMaquina: EntradaMaquinaCafe {

    protected var tieneVaso: Boolean = true
    protected var contadorLimpieza: Int = 0
    protected var estáLimipia: Boolean = true
    protected var instruccion : Int = 0
    class EsperandoDinero(var dinero: Double) : EstadosMaquina(){
        override fun onEnter(maquinaCafe: MaquinaCafe) {
            contadorLimpieza++
            if (contadorLimpieza >= 5) {
                println("La máquina necesita limpieza. Estado: $estadoActual")
                MaquinaCafe.setMaquinaEstado(ErrorLimpieza(false))
                return
            }
            MaquinaCafe.dineroMaquina += dinero
            if ( MaquinaCafe.dineroMaquina >= 1.0) {
                println("Dinero suficiente")
                MaquinaCafe.setMaquinaEstado(EsperandoInstruccion())
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
                MaquinaCafe.setMaquinaEstado((Elaborando()))
            }else if(eleccion == 0){
                println("Por favor, elige una opción: 1. Espresso, 2. Latte, 3. Cappuccino")
            } else {
                println("Elección inválida. Por favor, elige 1, 2 o 3.")
            }
        }
        override fun toString(): String {
            return "EsperandoInstruccion(eleccion=$eleccion)"
        }
    }
    class Elaborando() : EstadosMaquina(){
        override fun onEnter(maquinaCafe: MaquinaCafe) {
            println("¡Espera! La máquina ya está haciendo café.")
            Thread.sleep(2000)
            tieneVaso = true
            MaquinaCafe.setMaquinaEstado(EsperandoExtraccion(false))

        }
        override fun toString(): String {
            return "Elaborando"
        }
    }
    class EsperandoExtraccion(var retirarVaso: Boolean) : EstadosMaquina(){
        override fun onEnter(maquinaCafe: MaquinaCafe) {
            tieneVaso = retirarVaso
            if(!tieneVaso){
                println("¡Café listo! Por favor, recoge tu café.")
            }else{
                println("chaos")
                MaquinaCafe.setMaquinaEstado(EsperandoDinero(0.0) )
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
                MaquinaCafe.setMaquinaEstado(EsperandoDinero(0.0))

            } else {
                println("La máquina sigue sucia. No se puede hacer café.")
            }
        }

        override fun toString(): String {
            return "ErrorLimpieza"
        }
    }
}
