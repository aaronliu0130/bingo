import kotlin.random.Random

/**
 * Bingo class containing constructor(s), calling and card generation functions along with a column enum and emoji mode
 *
 * Empty constructor sets num to classic American 1-75 with plain, aligned header and ∅ as the free space symbol
 * @param elements The list of elements to choose from
 * @param freeSpace The character to use for representing the centered free space
 * @param header The String to put before outputting the card's elements
 * @param emoji Whether to enable emoji mode which optimizes for Discord shortcodes
 */
class Bingo(
    var elements: List<String> = (1..75).map { if (it < 10) " $it " else "$it " },
    var freeSpace: String = " ∅ ",
    var header: String = "B  I  N  G  O",
    var emoji: Boolean = false
) {
    var cur: MutableMap<Int, String> =
        elements.associateBy({ elements.indexOf(it) }, { it }).toMutableMap() // the currently remaining elements

    enum class Column {
        B,
        I,
        N,
        G,
        O,
        Empty
    }

    /**
     * only for numToEmoji()
     */
    internal enum class Num {
        one, two, three, four, five, six, seven, eight, nine
    }

    /**
     * Converts numbers to their Discord shortcodes
     *
     * Only used for call() in emoji mode
     * @return Discord shortcode for number; Empty if not a valid number
     */
    private fun numToEmoji(n: String): String {
        var ans = ""
        for (i in n) {
            if (i.isDigit()) ans += ":${Num.entries[i.digitToInt()]}:"
            else return ""
        }
        return ans
    }

    /**
     * Assisted constructor
     *
     * It is expected that the number of elements ≥ 25 and is divisible by 5
     * @param nums A String containing all the elements to choose from, separated by spl
     * @param freeSpace The representation of the free space in the middle. If not specified, will be first element in nums.
     * @param spl The character that the string is split by
     * @param lexiSort Whether to sort by lexicographical order. If set to false (default behavior), will sort by length instead.
     * @param emoji Whether to toggle emoji mode, which will give an emoji header and use no padding
     */
    constructor(
        nums: String,
        spl: Char = '@',
        emoji: Boolean = nums[0] == ':',
        freeSpace: String = nums.substringBefore(spl),
        header: String = if (!emoji) "B I N G O"
        else ":regional_indicator_b: :regional_indicator_i: :regional_indicator_n: :regional_indicator_g: :regional_indicator_o:",
        lexiSort: Boolean = false
    ) : this(freeSpace = freeSpace, header = header, emoji = emoji) {
        if (nums.count { it == spl } < 25) return // less than 25 elements
        val rem = nums.split(spl).mapNotNull { if (it == "") null else it }.toMutableList()
        if (rem.size < 25) return
        while (rem.size % 5 != 0) rem.remove(rem.random())
        elements = rem; this.freeSpace = nums.substringBefore(spl)
        if (!emoji) {
            elements = if (lexiSort) elements.sorted() else elements.sortedBy { it.length }
            val max = elements.last().length // max length of an element, for padding purposes
            elements = elements.map { " ".repeat(max - it.length) + it + " " }
            this.freeSpace = " ".repeat(max - this.freeSpace.length) + this.freeSpace + " "
            this.header = ""
            for (i in (0..4)) this.header += "" + Column.entries[i] + " ".repeat(max)
        }
        cur = elements.associateBy({ elements.indexOf(it) }, { it }).toMutableMap()
    }

    fun card(): Array<Array<String>> {
        val ret = Array(5) { Array(5) { "" } }
        var index: Int
        val done = mutableListOf<Int>()
        for (i in (1..5))
            for (j in (1..5)) {
                if (i == j && j == 3) {
                    ret[2][2] = freeSpace
                    continue
                }
                do {
                    index = Random.nextInt(elements.size / 5 * (j - 1), elements.size / 5 * j)
                } while (done.contains(index))
                ret[i - 1][j - 1] = elements[index]
                done.add(index)
            }
        return ret
    }

    fun call(): Pair<Column, String> {
        if (cur.isEmpty()) return Pair(Column.Empty, "")
        var index: Int
        do index = Random.nextInt(elements.size) while (cur[index] == null)
        val list = cur[index]!!.trim()
        cur.remove(index)
        return Pair(Column.entries[index / (elements.size / 5)], list)
    }

    fun reset() {
        cur = elements.associateBy({ elements.indexOf(it) }, { it }).toMutableMap()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val bingo =
                Bingo("0@1@2@3@4@5@6@7@8@9@10@11@12@13@14@15@16@17@18@19@20@21@22@23@24@25@26@27@28@29@30@31@32@33@34@35@36@37@38@39@40@41@42@43@44@45@46@47@48@49@50@51@52@53@54@55@56@57@58@59@60@61@62@63@64@65@66@67@68@69@70@71@72@73@74@75")
            print(bingo.elements.size)
        }
    }
}