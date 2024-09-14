package org.example.medicine

import org.koin.core.annotation.Single

@Single
class MedicineService(private val medicineRepository: MedicineRepository) {
    fun get解熱剤(): Medicine {
        val 解熱剤 = medicineRepository.findByName("解熱剤")
        return 解熱剤 ?: error("解熱剤が見つかりませんでした")
    }
}
