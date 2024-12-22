
class Direction(val xOffset: Int, val yOffset: Int) {
    override fun toString(): String {
        return "Direction($xOffset, $yOffset)"
    }
}


class Puzzle(val puzzle: List<String>) {
    val directions = listOf(
        Direction( 0, 1),
        Direction( 0,-1),
        Direction( 1, 0),
        Direction(-1, 0),
        Direction( 1, 1),
        Direction(-1, 1),
        Direction(-1,-1),
        Direction( 1,-1),
    )

    val searchword = "XMAS"

    val xRange = 0..puzzle[0].lastIndex
    val yRange = 0..puzzle.lastIndex

    val maxIndex = searchword.lastIndex

    fun matchInPuzzle(x: Int, y: Int, direction: Direction): Boolean {
        // check boundaries
        if ((x + searchword.lastIndex * direction.xOffset) in xRange &&
            (y + searchword.lastIndex * direction.yOffset) in yRange) {
            // for each letter of the searchword, check if present (and at right pos)
            searchword.forEachIndexed { index, c ->
                // println(" Char $index is $c?")
                val xPos = x + index * direction.xOffset
                val yPos = y + index * direction.yOffset
                if (puzzle[yPos][xPos] != c) {
                    // println("-- wrong char in pos: ${puzzle}")
                    return false
                }
            }
            println("-- found")
            return true
        } else {
            // println("-- out of boundaries")
            return false
        }
    }

    fun howManyMatches(): Int {
        var matches = 0
        puzzle.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                directions.forEach { direction ->
                    println("Checking $x, $y: $direction")
                    if (matchInPuzzle(x, y, direction))
                        matches++
                }
            }
        }
        // println("- now $matches matches")
        return matches
    }

    fun isXAt(x: Int, y: Int): Boolean {
        var leg1 = false
        var leg2 = false
        if (puzzle[y-1][x-1] == 'M' && puzzle[y+1][x+1] == 'S') leg1 = true
        if (puzzle[y-1][x-1] == 'S' && puzzle[y+1][x+1] == 'M') leg1 = true
        if (puzzle[y+1][x-1] == 'M' && puzzle[y-1][x+1] == 'S') leg2 = true
        if (puzzle[y+1][x-1] == 'S' && puzzle[y-1][x+1] == 'M') leg2 = true
        println("$leg1, $leg2")
        return leg1 && leg2
    }

    fun countCrosses(): Int {
        var count = 0
        (1..(puzzle.lastIndex - 1)).forEach { y ->
            (1..(puzzle[0].lastIndex - 1)).forEach { x ->
                if (puzzle[y][x] == 'A') {
                    println("A found at $x, $y")
                    if (isXAt(x, y)) {
                        println(" X recognized")
                        count++
                    }
                }
            }
        }
        return count
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return Puzzle(input).howManyMatches()
    }

    fun part2(input: List<String>): Int {
        return Puzzle(input).countCrosses()
    }

    // Test if implementation meets criteria from the description, like:
    //check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    println (part1(testInput))
    check(part1(testInput) == 18)
    val testInput2 = readInput("Day04_test2")
    println (part2(testInput2))
    check(part2(testInput2) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    check(part1(input) > 2289)
    part1(input).println()
    part2(input).println()
}
