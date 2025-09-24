package org.example.maquinaPropia

sealed class EstadosMaquina{
    class EsperandoDinero(var dinero: Double) : EstadosMaquina(){
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
}
