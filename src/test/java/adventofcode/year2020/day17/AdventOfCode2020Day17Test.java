package adventofcode.year2020.day17;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AdventOfCode2020Day17Test
{

    @Test
    void getMapPart1()
    {
        List<AdventOfCode2020Day17Part1.Cell> map = AdventOfCode2020Day17Part1.getMap("day17/inputDay17Test.txt");
        assertEquals(9, map.size());
        assertEquals(0, AdventOfCode2020Day17Part1.getMinX(map));
        assertEquals(-2, AdventOfCode2020Day17Part1.getMinY(map));
        assertEquals(0, AdventOfCode2020Day17Part1.getMinZ(map));
        assertEquals(2, AdventOfCode2020Day17Part1.getMaxX(map));
        assertEquals(0, AdventOfCode2020Day17Part1.getMaxY(map));
        assertEquals(0, AdventOfCode2020Day17Part1.getMaxZ(map));
        map = AdventOfCode2020Day17Part1.getMapAfterXCycles(map, 6);
        assertEquals(112, AdventOfCode2020Day17Part1.getNumberOfActiveStates(map));
        map = AdventOfCode2020Day17Part1.getMap("day17/inputDay17.txt");
        map = AdventOfCode2020Day17Part1.getMapAfterXCycles(map, 6);
        assertEquals(426, AdventOfCode2020Day17Part1.getNumberOfActiveStates(map));
    }

    @Test
    @Disabled // takes too long
    void getMapPart2()
    {
        List<AdventOfCode2020Day17Part2.Cell> map = AdventOfCode2020Day17Part2.getMap("day17/inputDay17Test.txt");
        assertEquals(9, map.size());
        assertEquals(0, AdventOfCode2020Day17Part2.getMinX(map));
        assertEquals(-2, AdventOfCode2020Day17Part2.getMinY(map));
        assertEquals(0, AdventOfCode2020Day17Part2.getMinZ(map));
        assertEquals(2, AdventOfCode2020Day17Part2.getMaxX(map));
        assertEquals(0, AdventOfCode2020Day17Part2.getMaxY(map));
        assertEquals(0, AdventOfCode2020Day17Part2.getMaxZ(map));
        map = AdventOfCode2020Day17Part2.getMapAfterXCycles(map, 6);
        assertEquals(848, AdventOfCode2020Day17Part2.getNumberOfActiveStates(map));
//        map = AdventOfCode2020Day17Part1.getMap("day17/inputDay17.txt");
//        map = AdventOfCode2020Day17Part1.getMapAfterXCycles(map, 6);
//        assertEquals(426, AdventOfCode2020Day17Part1.getNumberOfActiveStates(map));
    }


}