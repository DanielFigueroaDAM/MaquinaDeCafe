package org.example

sealed class CoffeeMachineState {
    object Idle : CoffeeMachineState()
    object MakingCoffee : CoffeeMachineState()
    data class ServingCoffee(val brand:String) : CoffeeMachineState()
    data class Error(val message : String) : CoffeeMachineState()
}