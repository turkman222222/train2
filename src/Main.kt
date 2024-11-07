import kotlin.random.Random

fun main() {
    var prod = true
    while (prod) {
        println("Хотите составить план поезда (да/нет) или выйти из программы (EXIT)?")
        val input = readLine()!!.lowercase()

        when (input) {
            "да", "yes" -> sozdatpoezd()
            "нет", "no" -> prod = false
            "exit" -> {
                prod = false
                println("До свидания!")
            }
            else -> println("Неверный ввод. Пожалуйста, введите 'да', 'нет' или 'EXIT'.")
        }
    }
}

fun sozdatpoezd() {
    val direction = naprav()
    println("Создано направление: $direction")

    val pas = bilets()
    println("Продано билетов: $pas")

    val train = tr(pas)
    println("Создан поезд: $train")

    otpr(train)
}

fun naprav(): String {
    val goroda = listOf(
        "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Красноярск",
        "Челябинск", "Нижний Новгород", "Казань", "Краснодар", "Пермь",
        "Воронеж", "Ростов-на-Дону", "Уфа", "Самара", "Омск",
        "Иркутск", "Хабаровск", "Владивосток", "Ярославль", "Чебоксары",
        "Тюмень", "Волгоград", "Саратов", "Калининград", "Курск"
    )

    var a: String
    var b: String

    do {
       a = goroda.random()
        b = goroda.random()
    } while (a == b)

    return "$a - $b"
}

fun bilets(): Int {
    return Random.nextInt(5, 202)
}

fun tr (pas: Int): List<Int> {
    val vg = mutableListOf<Int>()
    var vibor = pas

    while (vibor > 0) {
        val colvo = Random.nextInt(5, 26)
        vg.add(colvo)
       vibor -= colvo
    }

    return vg
}

fun otpr(train: List<Int>) {
    var pas = 0
    var number = 0

    println("Поезд [${train.joinToString(" - ")}] отправлен!")

    train.forEach { capacity ->
        println("Вместимость вагона: $capacity")


        val sk = if (pas + capacity > train.sum()) {
            train.sum() - pas
        } else {
            capacity
        }

        pas += sk
        println("Пассажиров в вагоне: $sk")
        number++
    }
}
