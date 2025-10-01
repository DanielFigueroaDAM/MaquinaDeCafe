package org.example.maquinaPropia

fun main (){
    MaquinaCafe.setMaquinaEstado(EstadosMaquina.EsperandoDinero(1.0))
    MaquinaCafe.setMaquinaEstado(EstadosMaquina.EsperandoInstruccion(2))
    MaquinaCafe.setMaquinaEstado(EstadosMaquina.EsperandoExtraccion(false))
    MaquinaCafe.setMaquinaEstado(EstadosMaquina.EsperandoDinero(0.5))
    MaquinaCafe.setMaquinaEstado(EstadosMaquina.EsperandoDinero(0.6))
    MaquinaCafe.setMaquinaEstado(EstadosMaquina.EsperandoInstruccion(3))


}