
import java.awt.*
import java.awt.datatransfer.StringSelection
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.table.DefaultTableModel

// :69:@:2_more:@:AAAAAAAA:@:Anii:@:Chesse:@:Dudr:@:Fudge:@:Happylemon:@:KEK:@:KermitTea:@:LL:@:Mexicat:@:Pokaren:@:Pompeplop:@:TrollLaff:@:WW:@:aaron:@:angries:@:ani:@:aspect:@:ayowhatthe:@:baby_pepe:@:babyboss:@:beanlisa:@:belt:@:ben:@:blob_icecream:@:blob_pirate:@:blob_sandwich:@:blobross:@:boohoo:@:bozopepe:@:bruh:@:buff_pepe:@:business_pepe:@:buster_nerfed:@:chad:@:chade:@:clown~1:@:clown_pepe:@:concerned:@:cop_pepe:@:cringe:@:cryingpepe:@:dafuq:@:deskerd:@:discord_mod:@:dissapointedpepe:@:dkdbtl:@:drunk_pepe:@:eee:@:frogaaa:@:frogblush:@:frogcozy:@:frogcry:@:frogdrool:@:frogfainting:@:froggers:@:froggy:@:froggycelebrate:@:froggyconfused:@:froggycry:@:froggyheart:@:froggyheartbroken:@:frogicecream:@:frogknife:@:froglove:@:froglove~1:@:frogmoney:@:frogpat:@:frogplant:@:frogplease:@:frogsadday:@:frogsleepy:@:frogsus:@:frogswear:@:frogthink:@:frogtongue:@:gentlemen:@:googoo:@:gun_pepe:@:hand:@:happies:@:harthart:@:heehee:@:hmmmm:@:horsemen:@:john_lennon_confused:@:juice_pepe:@:kek:@:kekaprio:@:king_pepe:@:lemon~1:@:lemon_gang:@:lmao:@:lmoa:@:love_pepe:@:luna:@:maracat:@:marioify:@:mark_wtf:@:meii:@:nerdy_clown:@:niko_blob:@:no_bitches:@:no_pepe:@:noway:@:okay:@:oooh:@:pandagun:@:pea:@:pepe_cry:@:pepe_flush:@:pepe_pause:@:pepeskull:@:pikachu:@:ping_pepe:@:plasticrear:@:pog:@:praypepe:@:pumpkin_pepe:@:pwease:@:rip:@:roxelle:@:sadge:@:sadge_but_better:@:sand:@:secretrecipe:@:sheesh:@:shifu:@:simp_pepe:@:skem:@:skully:@:snadge:@:swag:@:sword:@:thinkies:@:trainingtm:@:troll~1:@:typing:@:unCreative:@:uuuhtom:@:waaaaa:@:whaa:@:why_pepe:@:whyy:@:willie:@:wtf:@:yes_pepe:
/**
 * New things: enums, enum.values(), comparators, arrays.sort, BorderLayout, JTabbedPane, JScrollPane, JTextArea, find integer occurence in array, foreach loops are by copy, slider, changelistener, gridlayout, clipboard
 */
internal class JBingo : JTabbedPane() {
    private var dis = false

    private val card = JTextArea()
    private val numbre =
        JTextArea(
            if (dis) ":white_square_button:@:69:@:2_more:@:AAAAAAAA:@:Anii:@:Chesse:@:Dudr:@:Fudge:@:Happylemon:@:KEK:@:KermitTea:@:LL:@:Mexicat:@:Pokaren:@:Pompeplop:@:TrollLaff:@:WW:@:aaron:@:angries:@:ani:@:aspect:@:ayowhatthe:@:baby_pepe:@:babyboss:@:beanlisa:@:belt:@:ben:@:blob_icecream:@:blob_pirate:@:blob_sandwich:@:blobross:@:boohoo:@:bozopepe:@:bruh:@:buff_pepe:@:business_pepe:@:buster_nerfed:@:chad:@:chade:@:clown~1:@:clown_pepe:@:concerned:@:cop_pepe:@:cringe:@:cryingpepe:@:dafuq:@:deskerd:@:discord_mod:@:dissapointedpepe:@:dkdbtl:@:drunk_pepe:@:eee:@:frogaaa:@:frogblush:@:frogcozy:@:frogcry:@:frogdrool:@:frogfainting:@:froggers:@:froggy:@:froggycelebrate:@:froggyconfused:@:froggycry:@:froggyheart:@:froggyheartbroken:@:frogicecream:@:frogknife:@:froglove:@:froglove~1:@:frogmoney:@:frogpat:@:frogplant:@:frogplease:@:frogsadday:@:frogsleepy:@:frogsus:@:frogswear:@:frogthink:@:frogtongue:@:gentlemen:@:googoo:@:gun_pepe:@:hand:@:happies:@:harthart:@:heehee:@:hmmmm:@:horsemen:@:john_lennon_confused:@:juice_pepe:@:kek:@:kekaprio:@:king_pepe:@:lemon~1:@:lemon_gang:@:lmao:@:lmoa:@:love_pepe:@:luna:@:maracat:@:marioify:@:mark_wtf:@:meii:@:nerdy_clown:@:niko_blob:@:no_bitches:@:no_pepe:@:noway:@:okay:@:oooh:@:pandagun:@:pea:@:pepe_cry:@:pepe_flush:@:pepe_pause:@:pepeskull:@:pikachu:@:ping_pepe:@:plasticrear:@:pog:@:praypepe:@:pumpkin_pepe:@:pwease:@:rip:@:roxelle:@:sadge:@:sadge_but_better:@:sand:@:secretrecipe:@:sheesh:@:shifu:@:simp_pepe:@:skem:@:skully:@:snadge:@:swag:@:sword:@:thinkies:@:trainingtm:@:troll~1:@:typing:@:unCreative:@:uuuhtom:@:waaaaa:@:whaa:@:why_pepe:@:whyy:@:willie:@:wtf:@:yes_pepe:"
            else "0@1@2@3@4@5@6@7@8@9@10@11@12@13@14@15@16@17@18@19@20@21@22@23@24@25@26@27@28@29@30@31@32@33@34@35@36@37@38@39@40@41@42@43@44@45@46@47@48@49@50@51@52@53@54@55@56@57@58@59@60@61@62@63@64@65@66@67@68@69@70@71@72@73@74@75"
        )
    private var old = numbre.text
    private val call = DefaultTableModel(arrayOf<Any>("Column", "Element"), 0)
    private val control = JButton("(Re)start calling")
    private val slide = JSlider(0, 60, 15)
    private val timer = Timer(15 * 1000, CallL())
    private val clipboard = Toolkit.getDefaultToolkit().systemClipboard

    private var bingo = Bingo(old)
    private lateinit var pair: Pair<Bingo.Column, String>

    private var index = 0
    fun processNumbre() {
        old = numbre.text
        index = old.count { it == '@' }
        if (index < 5) {
            JOptionPane.showMessageDialog(
                numbre,
                """
                You only have $index elements specified.
                The minimum amount of elements is 25, while the recommended amount of elements is 75.
                Please add some elements in the Input tab.
                """.trimIndent(), "Error: Not Enough Elements", JOptionPane.ERROR_MESSAGE
            )
            index *= -1
            return
        }
        bingo = Bingo(old)
        if (bingo.elements.size % 5 != 0)
            if (JOptionPane.showConfirmDialog(
                    numbre, """
                You have $index elements specified, which is indivisible by 5.
                This will cause the following elements to disappear:
                ${bingo.elements[index - index % 5]..bingo.elements[index]}
                You may want to add or remove some elements in the Input tab.
                Do you still want to continue?
                """.trimIndent(), "Warning: Indivisible Elements", JOptionPane.YES_NO_OPTION
                ) == JOptionPane.NO_OPTION
            )
                index *= -1
        if (index in 1..74)
            if (JOptionPane.showConfirmDialog(
                    numbre, """
                You have $index elements specified, while the recommended amount is 75.
                You may want to add some elements in the Input tab.
                Do you still want to continue?
            """.trimIndent(), "Warning: Indivisible Elements", JOptionPane.YES_NO_OPTION
                ) == JOptionPane.NO_OPTION
            )
                index *= -1
        if (dis && index > 75) {
            while (bingo.cur.size > 75) //TODO: Fix
                bingo.cur.remove((0 until bingo.elements.size).random())
            bingo = Bingo(":white_square_button:@" + bingo.cur.values.joinToString("@"))
        }
        /*
        nums = old.split('@').dropLastWhile { it.isEmpty() }.toTypedArray()
        len = (nums.size - 1 - nums.size % 5) / 5
        if (old[0] == ':') {
            maxLen = 0
            return
        }
        Arrays.sort(nums, 1, nums.size - 1, Comparator.comparingInt { obj: String -> obj.length })
        maxLen =
            nums[nums.size - 1].length //it's so confusing how arrays have a length FIELD while strings have a length METHOD
        for (i in nums.indices) { //java foreach loops can't by reference for some reason
            nums[i] = " ".repeat(maxLen - nums[i].length) + nums[i] + " "
        }
        */
    }

    init {
        card.isEditable = false
        card.font = Font(Font.MONOSPACED, Font.BOLD, 12)
        val carder = JScrollPane(card)
        val infoca =
            JLabel("<html><b>Welcome to Bingo! Go to the Call tab to start calling elements, and the Input tab to change the elements on the Bingo card. The middle space is free.</b></html>") // metal bolds by default for some reason but not in other skins
        val gencard = JButton("Generate card")
        gencard.addActionListener(CardL())
        val car = JPanel(BorderLayout())
        car.add(infoca, BorderLayout.PAGE_START)
        car.add(gencard, BorderLayout.PAGE_END)
        car.add(carder, BorderLayout.CENTER)
        addTab("Card", car)

        val table = JTable(call)
        val output = JScrollPane(table)
        output.verticalScrollBar.addAdjustmentListener { e ->
            e.adjustable.value = e.adjustable.maximum
        }
        val infocl =
            JLabel("<html><b>Calls will be copied to clipboard unless the duration is less than 1. Set duration between calls below.</b></html>")
        slide.majorTickSpacing = 15
        slide.minorTickSpacing = 1
        slide.paintTicks = true
        slide.paintLabels = true
        val info = JPanel(GridLayout(2, 1, 0, 0))
        info.add(infocl)
        info.add(slide)
        val cal = JPanel(BorderLayout())
        control.addActionListener(ControlL())
        cal.add(info, BorderLayout.PAGE_START)
        cal.add(output, BorderLayout.CENTER)
        cal.add(control, BorderLayout.PAGE_END)
        addTab("Call", cal)

        if (dis) {
            while (bingo.cur.size > 75) //TODO: Fix
                bingo.cur.remove((0 until bingo.elements.size).random())
            bingo = Bingo(":white_square_button:@" + bingo.cur.values.joinToString("@"))
        }
        index = bingo.cur.size + 1
        numbre.lineWrap = true
        //numbre.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        val input = JScrollPane(numbre)
        val inp = JPanel(BorderLayout())
        val infoin =
            JLabel("<html><b>Input the elements that will appear on Bingo cards (i.e. \"numbers\") and separate them with the '@' character. First element will be for the free space. Please make sure the total number of elements (excluding the free space) is divisible by 5, as the remainder will be ignored.</b></html>")
        inp.add(infoin, BorderLayout.PAGE_START)
        inp.add(input, BorderLayout.CENTER)
        addTab("Input", inp)
        preferredSize = Dimension(400, 300)
    }

    private inner class CardL : ActionListener {
        override fun actionPerformed(event: ActionEvent) {
            if (old != numbre.text) processNumbre()
            if (index <= 0) {
                index *= -1
                return
            }
            card.text = if (!dis) "```\n${bingo.header}" else bingo.header
            for (i in bingo.card()) {
                card.append("\n")
                for (j in i)
                    card.append(j)
            }
            if (!dis) card.append("\n```")
            clipboard.setContents(StringSelection(card.text), null)
        }
    }

    private inner class ControlL : ActionListener {
        override fun actionPerformed(event: ActionEvent) {
            if (old != numbre.text) processNumbre()
            if (index <= 0) {
                index *= -1
                return
            }
            timer.delay = slide.value * 1000
            if (control.text == "Stop calling") {
                timer.stop()
                control.text = "(Re)start calling"
            } else {
                call.rowCount = 0
                control.text = "Stop calling"
                timer.initialDelay = 0
                timer.isRepeats = true
                timer.start()
            }
        }
    }

    internal enum class Num {
        one, two, three, four, five, six, seven, eight, nine
    }

    private fun numToEmoji(n: String): String {
        var ans = ""
        for (i in n)
            ans += ":${Num.entries[i.code - '0'.code]}:"
        return ans
    }

    private inner class CallL : ActionListener {
        override fun actionPerformed(event: ActionEvent) {
            pair = bingo.call()
            if (pair.first == Bingo.Column.Empty) {
                timer.stop()
                control.text = "(Re)start calling"
                return
            }
            call.addRow(arrayOf<Any>(pair.first, pair.second))
            if (timer.delay >= 1000) {
                clipboard.setContents(
                    StringSelection(
                        /*if (old[0] == ':') ":regional_indicator_${
                            pair.first.toString().lowercase()
                        }:${pair.second}" else "${pair.first} ${pair.second}" */
                        when (old) {
                            "0@1@2@3@4@5@6@7@8@9@10@11@12@13@14@15@16@17@18@19@20@21@22@23@24@25@26@27@28@29@30@31@32@33@34@35@36@37@38@39@40@41@42@43@44@45@46@47@48@49@50@51@52@53@54@55@56@57@58@59@60@61@62@63@64@65@66@67@68@69@70@71@72@73@74@75"
                            -> if (dis) ":regional_indicator_${
                                pair.first.toString().lowercase()
                            }: ${numToEmoji(pair.second)}" else "${pair.first} ${pair.second}"

                            else -> if (old[0] == ':') ":regional_indicator_${
                                pair.first.toString().lowercase()
                            }: ${pair.second}" else "${pair.first} ${pair.second}"
                        }
                    ), null
                )
            }
        }
    }

    companion object {
        /**
         * Contructs the JFrame with the nimbus look and feel or the system look and feel if that fails.
         */
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                for (info in UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus" == info.name) {
                        UIManager.setLookAndFeel(info.className)
                        break
                    }
                }
            } catch (eek: Exception) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            val frame = JFrame("JBingo 1.0")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.contentPane.add(JBingo())
            frame.pack()
            frame.isVisible = true
        }
    }
}
