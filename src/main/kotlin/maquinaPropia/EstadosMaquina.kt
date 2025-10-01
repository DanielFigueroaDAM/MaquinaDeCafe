
package org.example.maquinaPropia

import org.example.maquinaPropia.MaquinaCafe.estadoActual


interface EntradaMaquinaCafe{
    fun onEnter(maquinaCafe: MaquinaCafe)
}


sealed class EstadosMaquina: EntradaMaquinaCafe {


    protected var estáLimipia: Boolean = true
    class EsperandoDinero(var dinero: Double) : EstadosMaquina(){
        override fun onEnter(maquinaCafe: MaquinaCafe) {

            if (MaquinaCafe.contadorLimpieza >= 5) {
                MaquinaCafe.setMaquinaEstado(ErrorLimpieza(false))
                return
            }
            MaquinaCafe.dineroMaquina += dinero
            if ( MaquinaCafe.dineroMaquina >= 1.0) {
                println("Dinero suficiente")
                MaquinaCafe.contadorLimpieza++
                MaquinaCafe.setMaquinaEstado(EsperandoInstruccion())
            } else if(MaquinaCafe.dineroMaquina != 0.0) {
                println("Por favor, inserta al menos 1.0 unidad de dinero.")
            }
        }

        override fun toString(): String {
            return "EsperandoDinero(dinero=$dinero)"
        }
    }
    class EsperandoInstruccion(var eleccion: Int = 0) : EstadosMaquina() {
        override fun onEnter(maquinaCafe: MaquinaCafe) {
            if (eleccion != 0) {
                println("Has seleccionado la opción: $eleccion")
            }
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
            MaquinaCafe.tieneVaso = true
            MaquinaCafe.setMaquinaEstado(EsperandoExtraccion(MaquinaCafe.tieneVaso))

        }
        override fun toString(): String {
            return "Elaborando"
        }
    }
    class EsperandoExtraccion(var retirarVaso: Boolean) : EstadosMaquina(){
        override fun onEnter(maquinaCafe: MaquinaCafe) {
            MaquinaCafe.tieneVaso = retirarVaso
            if(MaquinaCafe.tieneVaso){
                println("¡Café listo! Por favor, recoge tu café.")
            }else{
                println("Vaso retirado. Disfruta tu café.")
                MaquinaCafe.dineroMaquina = 0.0
                MaquinaCafe.setMaquinaEstado(EsperandoDinero(0.0) )
            }
        }
        override fun toString(): String {
            return "EsperandoExtraccion"
        }
    }
    class ErrorLimpieza(var seLimpio: Boolean = false) : EstadosMaquina(){
        override
        fun onEnter (maquinaCafe: MaquinaCafe){

            estáLimipia = false
            if (!estáLimipia && !seLimpio)
                println("La máquina necesita limpieza. Estado: $estadoActual")
            if (seLimpio) {
                println("Limpiando la máquina...")
                estáLimipia = true
                println("Máquina limpia. Estado: $estadoActual")
                MaquinaCafe.contadorLimpieza = 0
                MaquinaCafe.setMaquinaEstado(EsperandoDinero(0.0))
            }
        }

        override fun toString(): String {
            return "ErrorLimpieza"
        }
    }
}
