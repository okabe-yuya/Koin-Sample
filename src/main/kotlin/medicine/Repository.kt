package org.example.medicine

import org.koin.core.annotation.Single

interface MedicineRepository {
    fun findByName(name: String): Medicine?
    fun save(medicine: Medicine): Medicine
}

@Single
class MedicineRepositoryImpl : MedicineRepository {
    var medicines = mutableListOf<Medicine>(
        Medicine("解熱剤", 80),
        Medicine("頭痛薬", 120),
        Medicine("咳止め", 160),
    )
    override fun findByName(name: String): Medicine? = medicines.find { it.name == name }

    override fun save(medicine: Medicine): Medicine {
        medicines.add(medicine)
        return medicine
    }
}
