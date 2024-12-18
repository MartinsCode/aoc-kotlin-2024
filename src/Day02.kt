
class ReactorReport(listOfLevelsInput: List<String>) {
    val listOfLevels = listOfLevelsInput.map { levels ->
        levels.split(" ").map { it.toInt() }
    }

    private fun isSafe(listOfMeasurements: List<Int>): Boolean {
        val listOfDiffs =
            listOfMeasurements.windowed(size = 2,step = 1).map { it[1] - it[0] }
        val allSafeIncreasing = listOfDiffs.all { it > 0 && it < 4 }
        val allSafeDecreasing = listOfDiffs.all { it < 0 && it > -4}
        return allSafeIncreasing || allSafeDecreasing
    }

    fun countSafeReports(): Int {
        return listOfLevels.count { isSafe(it) }
    }

    private fun isAlmostSafe(listOfMeasurements: List<Int>): Boolean {
        val safeWithoutMeasurement = listOfMeasurements.mapIndexed { index, measurement ->
            val testedList = listOfMeasurements.toMutableList()
            testedList.removeAt(index)
            isSafe(testedList)
        }
        return safeWithoutMeasurement.any { it }
    }

    fun countAlmostSafeReports(): Int {
        return listOfLevels.count { isAlmostSafe(it) }
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return ReactorReport(input).countSafeReports()
    }

    fun part2(input: List<String>): Int {
        println(ReactorReport(input).countAlmostSafeReports())
        return ReactorReport(input).countAlmostSafeReports()
    }

    // Test if implementation meets criteria from the description, like:
    //check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
