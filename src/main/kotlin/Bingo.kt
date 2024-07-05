import kotlin.random.Random

/**
 * Bingo class containing constructor(s), calling and card generation functions along with a column enum and emoji mode
 *
 * Empty constructor sets [elements] to classic American 1-75 with plain, aligned [header] and ∅ as the [freeSpace] symbol
 * @param elements The list of elements to choose from
 * @param freeSpace The character to use for representing the centered free space
 * @param header The String to put before outputting the card's elements
 * @param emoji Whether to enable emoji mode which optimizes for Discord shortcodes
 */
class Bingo(
    var elements: List<String> = (1..75).map { if (it < 10) " $it " else "$it " },
    var freeSpace: String = "∅ ",
    var header: String = "B   I   N   G   O",
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
     * only for [numToEmoji]
     */
    private var num: List<String> = listOf("0️⃣", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣", "9️⃣")

    /**
     * Converts numbers to their emoji equivalents
     *
     * Only used for [call] in emoji mode
     * @return Emoji for each digit; empty for non-digits
     */
    private fun numToEmoji(n: String): String {
        var ans = ""
        for (i in n) {
            if (i.isDigit()) ans += ":${num[i.digitToInt()]}:"
            else return ""
        }
        return ans
    }

    /**
     * Assisted constructor
     *
     * It is expected that the number of elements ≥ 25 and is divisible by 5.
     * @param nums A String containing all the elements to choose from, separated by spl.
     * @param spl The character that the string is split by. '@' by default.
     * @param emoji Whether to toggle emoji mode, which will give an emoji header and use no padding. False by default unless [nums] starts with ':', which is a character that signals the start of an emoji shortcode on many platforms.
     * @param freeSpace The representation of the free space in the middle. If not specified, will be first element in nums.
     * @param header A string containing what's put before every bingo card.
     * @param lexiSort Whether to sort by lexicographical order. If set to false (default behavior), will sort by length instead.
     * @param space A verb. Whether elements are automatically spaced to give consistent column sizes when viewed with a monospace font.
     */
    constructor(
        nums: String,
        spl: Char = '@',
        emoji: Boolean = nums[0] == ':',
        freeSpace: String = nums.substringBefore(spl),
        header: String? = null,
        /*
        if (!emoji) "B I N G O"
        else "\uD83C\uDDE7  \uD83C\uDDEE  \uD83C\uDDF3  \uD83C\uDDEC  \uD83C\uDDF4", //regional indicators for BINGO
        */
        lexiSort: Boolean = false,
        space: Boolean = true
    ) : this(freeSpace = freeSpace, emoji = emoji) {
        if (nums.count { it == spl } < 25) return // less than 25 elements
        val rem = nums.split(spl).mapNotNull { if (it == "") null else (if (space) it.trim() else it) }.toMutableList()
        // if (rem.size < 25) return
        while (rem.size % 5 != 0) rem.remove(rem.random())
        elements = rem
        if (!emoji) {
            elements = if (lexiSort) elements.sorted() else elements.sortedBy { it.length }

            if (space) {
                // max length of an element, for padding purposes
                val max: Int = if (!lexiSort) elements.last().length else elements.maxByOrNull { it.length }!!.length
                elements = elements.map { " ".repeat(max - it.length) + it + " " }
                this.freeSpace = " ".repeat(max - this.freeSpace.length) + this.freeSpace + " "
                if (header == null) {
                    if (!emoji) {
                        this.header = (0..4).joinToString("") { "" + Column.entries[it] + " ".repeat(max) }
                    } else {
                        this.header = (0..4).joinToString("") {
                            "\uD83C" + ('\uDDE6' + Column.entries[it].toString()[0].code - 'A') + " ".repeat(max + 1)
                        }
                    }
                } else {
                    this.header = header
                }
            } else {
                this.header = header ?: if (!emoji) "B I N G O"
                else "\uD83C\uDDE7 \uD83C\uDDEE \uD83C\uDDF3 \uD83C\uDDEC \uD83C\uDDF4" //regional indicators for BINGO
            }

        }
        cur = elements.associateBy({ elements.indexOf(it) }, { it }).toMutableMap()
    }

    /**
     * Generates a 5x5 American bingo card
     *
     * @return A 5x5 2D array of strings, each of which is the corresponding element
     */
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

    /**
     * Remove a random element from the list and returns it along with the column it is in
     *
     * @return The [Column] paired with the element we got
     */
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