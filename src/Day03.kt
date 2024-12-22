
class Memory(val memory: List<String>) {
    fun getSumOfAllMul(): Int {
        val muls = Regex("mul\\(\\d{1,3},\\d{1,3}\\)").findAll(memory.joinToString()).toList()
        val pairs = muls.map { Regex("\\d{1,3}").findAll(it.value).toList() }
        val product = pairs.map { it[0].value.toInt() * it[1].value.toInt()  }
        return product.sum()
    }
    fun getInstructionList(): List<String> {
        return Regex("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)")
            .findAll(memory.joinToString())
            .map { it.value }
            .toList()
    }
    fun execInstrList(): Int {
        val instrList = getInstructionList()
        var doIt = true
        var prodSum = 0
        instrList.forEach {
            println("State Do: $doIt, $prodSum, $it")
            when (it) {
                "do()" -> doIt = true
                "don't()" -> doIt = false
                else -> {
                    if (doIt) {
                        val pairs = Regex("\\d{1,3}").findAll(it).map { it.value.toInt() }.toList()
                        prodSum += pairs[0] * pairs[1]
                    }
                }
            }
        }
        return prodSum
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return Memory(input).getSumOfAllMul()
    }

    fun part2(input: List<String>): Int {
        return Memory(input).execInstrList()
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
