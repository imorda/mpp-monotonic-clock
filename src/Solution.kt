/**
 * В теле класса решения разрешено использовать только переменные делегированные в класс RegularInt.
 * Нельзя volatile, нельзя другие типы, нельзя блокировки, нельзя лазить в глобальные переменные.
 *
 * @author Belousov Timofey
 */
class Solution : MonotonicClock {
    private var c1 by RegularInt(0)
    private var c2 by RegularInt(0)
    private var c3 by RegularInt(0)
    private var d1 by RegularInt(0)
    private var d2 by RegularInt(0)
    private var d3 by RegularInt(0)

    override fun write(time: Time) {
        c1 = time.d1
        c2 = time.d2
        c3 = time.d3

        d3 = time.d3
        d2 = time.d2
        d1 = time.d1
    }

    override fun read(): Time {
        val attemptD = Time(d1, d2, d3)  // is <= than currently writing value (or the original value)
        val readC3 = c3
        val readC2 = c2
        val readC1 = c1
        val attemptC = Time(readC1, readC2, readC3)  // is >= than currently writing value (or the original value)

        if (attemptD < attemptC) {
            if (attemptD.d1 < attemptC.d1) {
                return Time(attemptC.d1, 0, 0)
            }
            if (attemptD.d2 < attemptC.d2) {
                return Time(attemptC.d1, attemptC.d2, 0)
            }
        }

        return attemptC
    }
}
