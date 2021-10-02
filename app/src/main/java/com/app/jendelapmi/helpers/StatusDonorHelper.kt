package com.app.jendelapmi.helpers

object StatusDonorHelper {
    fun parseStatusDonor(data: String): ArrayList<Int> {
        val status_donor_int: ArrayList<Int> = arrayListOf()
        var tanggal = 0
        var bulan = 0
        var tahun = 0
        // split data with " " delimiter
        val rawStatus = data.split(" ").toTypedArray()
        // put into tanggal, bulan ,and tahun
        tanggal = rawStatus[0].toInt()
        tahun = rawStatus[2].toInt()
        // parse bulan from String to Int
        when (rawStatus[1]) {
            "Januari" -> bulan = 0
            "Februari" -> bulan = 1
            "Maret" -> bulan = 2
            "April" -> bulan = 3
            "Mei" -> bulan = 4
            "Juni" -> bulan = 5
            "Juli" -> bulan = 6
            "Agustus" -> bulan = 7
            "September" -> bulan = 8
            "Oktober" -> bulan = 9
            "November" -> bulan = 10
            "Desember" -> bulan = 11
        }
        // bulan +3
        if (bulan > 8){
            bulan = bulan + 3 - 12
        } else {
            bulan = bulan + 3
        }
        // return tanggal, bulan, dan tahun
        status_donor_int.add(tanggal)
        status_donor_int.add(bulan)
        status_donor_int.add(tahun)
        return status_donor_int
    }
}