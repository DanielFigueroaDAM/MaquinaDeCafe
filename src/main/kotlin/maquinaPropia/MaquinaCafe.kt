package org.example.maquinaPropia





object MaquinaCafe{
    public var estadoActual: EstadosMaquina = EstadosMaquina.EsperandoDinero(0.0)
    println("Estado actual: $estadoActual")
    pirvate fun isValidTransition(desde: EstadosMaquina, hacia:EstadosMaquina): Boolean {
        return when (desde) {
            is EstadosMaquina.EsperandoDinero -> hacia is EstadosMaquina.EsperandoInstruccion
            is EstadosMaquina.EsperandoInstruccion -> hacia is EstadosMaquina.Elaborando
            is EstadosMaquina.Elaborando -> hacia is EstadosMaquina.EsperandoExtraccion
            is EstadosMaquina.EsperandoExtraccion -> hacia is EstadosMaquina.EsperandoDinero || hacia is EstadosMaquina.ErrorLimpieza
            is EstadosMaquina.ErrorLimpieza -> hacia is EstadosMaquina.EsperandoDinero
            else -> false
        }
    }
    fun setState(nuevoEstado: EstadosMaquina) {
        if (isValidTransition(estadoActual, nuevoEstado)) {
            estadoActual = nuevoEstado
            estadoActual.onEnter(this)
        } else {
            println("Transición inválida de $estadoActual a $nuevoEstado")
        }
    }

}