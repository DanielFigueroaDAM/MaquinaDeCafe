package org.example.maquinaPropia





object MaquinaCafe{
    public var dineroMaquina: Double = 0.0
    public var estadoActual: EstadosMaquina = EstadosMaquina.EsperandoDinero(0.0)

    init {
        println("Estado actual: $estadoActual")
    }



    private fun isValidTransition(desde: EstadosMaquina, hacia: EstadosMaquina): Boolean {
        return when (desde) {
            is EstadosMaquina.EsperandoDinero -> hacia is EstadosMaquina.EsperandoInstruccion || hacia is EstadosMaquina.EsperandoDinero
            is EstadosMaquina.EsperandoInstruccion -> hacia is EstadosMaquina.Elaborando || hacia is EstadosMaquina.EsperandoInstruccion
            is EstadosMaquina.Elaborando -> hacia is EstadosMaquina.EsperandoExtraccion
            is EstadosMaquina.EsperandoExtraccion ->
                hacia is EstadosMaquina.EsperandoDinero || hacia is EstadosMaquina.ErrorLimpieza || hacia is EstadosMaquina.EsperandoExtraccion
            is EstadosMaquina.ErrorLimpieza -> hacia is EstadosMaquina.EsperandoDinero
            else -> false
        }
    }

    public fun setMaquinaEstado(nuevoEstado: EstadosMaquina) {
        if (isValidTransition(estadoActual, nuevoEstado)) {
            estadoActual = nuevoEstado
            estadoActual.onEnter(this)
        } else {
            println("Transición inválida de $estadoActual a $nuevoEstado")
        }
    }

}