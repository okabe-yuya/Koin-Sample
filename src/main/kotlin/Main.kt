package org.example

import org.example.medicine.MedicineRepository
import org.example.medicine.MedicineRepositoryImpl
import org.example.medicine.MedicineService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.ksp.generated.*

class Application : KoinComponent {
    private val _medicineService: MedicineService by inject()

    val medicineService get() = _medicineService
}

fun main() {
    val appModule = module {
        single<MedicineRepository> { MedicineRepositoryImpl() }
        single { MedicineService(get()) }
    }

    // 通常の記述でもKoin Annotationsを使った記述でもどちらでも動く
    // 同時に2つでDIをすることは通常考えられないので、片方はコメントアウトしてある
    startKoin {
//        modules(appModule)
        modules(AppModule().module)
    }

    val medicine = Application().medicineService.get解熱剤()
    println("薬の情報: ${medicine.name}は${medicine.price}円です")
}
