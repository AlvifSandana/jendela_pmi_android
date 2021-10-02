package com.app.jendelapmi.helpers

import org.junit.Assert.*
import org.junit.Test

class StatusDonorHelperTest{
    @Test
    fun `status is 2 Oktober 2021`(){
        val expected = arrayListOf<Int>(2, 0, 2021)
        val result = StatusDonorHelper.parseStatusDonor("2 Oktober 2021")
        assertEquals(expected, result)
    }

    @Test
    fun `status is 1 November 2021`(){
        val expected = arrayListOf<Int>(2, 1, 2021)
        val result = StatusDonorHelper.parseStatusDonor("2 November 2021")
        assertEquals(expected, result)
    }
}