import kotlin.math.abs


class Distances (listOfDistances: List<String>) {
    val list1 = listOfDistances.map { distances ->
        println(distances)
        distances.split("   ")[0].toInt()
    }.sorted()
    val list2 = listOfDistances.map { distances ->
        distances.split("   ")[1].toInt()
    }.sorted()

    fun differences () : List<Int> {
        return list1.mapIndexed { index, i ->
            abs(i - list2[index])
        }
    }

    fun summedDistances() : Int {
        return differences().sum()
    }

    fun countIn2(x: Int): Int {
        return list2.count { it == x }
    }

    fun part2(): Int {
        return list1.map { it * countIn2(it) }.sum()
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return Distances(input).summedDistances()
    }

    fun part2(input: List<String>): Int {
        return Distances(input).part2()
    }

    // Test if implementation meets criteria from the description, like:
    //check(part1(listOf("test_input")) == 11)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
