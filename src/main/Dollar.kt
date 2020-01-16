package main

class Dollar(amount: Int = 0) : Money(amount) {

    fun times(multiplier: Int): Dollar {
        return Dollar(amount * multiplier)
    }
}