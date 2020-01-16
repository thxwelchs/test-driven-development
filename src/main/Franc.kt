package main

class Franc(amount: Int = 0) : Money() {

    fun times(multiplier: Int): Franc {
        return Franc(amount * multiplier)
    }
}
