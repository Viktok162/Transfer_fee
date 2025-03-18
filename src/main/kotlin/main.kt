fun main() {
    val cardType1 = "Mastercard"         //  Тип карты Mastercard
    val cardType2: String = "Visa"       //  Тип карты Visa
    val cardType3: String = "Мир"        //  Тип карты Мир
    val previousTransferMonth: Int       //  Сумма предыдущих переводов в этом месяце
    val previousTransferDay = 0          //  Сумма предыдущих переводов в этот день, в задании ошибочно не указана
    val transferAmount: Int = 1_000      // Сумма перевода

    transferCost(transferAmount, previousTransferDay)
}

fun transferCost(amount: Int, prevTransD: Int, prevTransM: Int = 0, card: String = "Мир") {
    // amount - сумма перевода
    // prevTransD - сумма предыдущих переводов в этот день
    // prevTransM - сумма предыдущих переводов в этом месяце
    // card - тип карты

    val dayLimit = 150_000   // максимальная сумма перевода с одной карты в сутки
    val monthLimit = 600_000 // максимальная сумма перевода с одной карты в месяц
    val limitMaster = 75_000 // лимит перевода с карты Mastercard в месяц
    val taxMaster1 = 0.6     // комиссия за перевод с карты Mastercard 0.6%
    val taxMaster2 = 20      // комиссия за перевод с карты Mastercard 20 руб.
    val taxVisa = 0.75       // комиссия за перевод с карты Visa 0.75%
    val taxVisaMin = 35      // минимальный размер комиссии за перевод с карты Visa
    val tax: Int             // комиссия за перевод

    // проверка лимитов на перевод за сутки и за месяц
    if (amount + prevTransD <= dayLimit && amount + prevTransM <= monthLimit) {
        when (card) {
            "Mastercard" -> {
                if (prevTransM + amount <= limitMaster)
                    println("Комиссия за перевод составляет: 0 руб.")
                else if (prevTransM >= limitMaster) {
                    tax = (amount * taxMaster1 / 100 + taxMaster2).toInt()
                    println("Комиссия за перевод составит: $tax руб.")
                } else {
                    tax = ((amount + prevTransM - limitMaster) * taxMaster1 / 100 + taxMaster2).toInt()
                    println("Комиссия за перевод составит: $tax руб.")
                }
            }

            "Visa" -> {
                tax = if (amount * taxVisa / 100 > taxVisaMin) (amount * taxVisa / 100).toInt() else taxVisaMin
                println("Комиссия за перевод составит: $tax руб.")
            }

            "Мир" -> println("Комиссия за перевод составит: 0 руб.")
        }
    } else println("Операция по переводу средств блокирована в связи с превышением лимита.")
}