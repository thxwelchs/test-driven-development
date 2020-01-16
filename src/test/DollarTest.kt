package test

import main.Dollar
import main.Franc
import org.junit.Assert.assertThat
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class DollarTest {

    @Test
    fun `달러제대로_곱해질까`() {
        val five = Dollar(5)
        assertEquals(Dollar(10), five.times(2))
        assertEquals(Dollar(15), five.times(3))
    }

    @Test
    fun `두달러객체가같을까`() {
        /**
         * Dollar의 amount값으로 비교하도록 equals를 overirde하여 재구성 한다.
         */
        assertEquals(Dollar(5), Dollar(5))
        assertNotEquals(Dollar(5), Dollar(6))
    }

    @Test
    fun `프랑제대로_곱해질까`() {
        val five = Franc(5)
        assertEquals( Franc(10), five.times(2))
        assertEquals( Franc(15), five.times(3))
    }

    @Test
    fun `두프랑객체가같을까`() {
        /**
         * Dollar의 amount값으로 비교하도록 equals를 overirde하여 재구성 한다.
         */
        assertEquals( Franc(5), Franc(5))
        assertNotEquals( Franc(5), Franc(6))
    }



}