package adventofcode.year2020.day11;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.year2020.day11.AdventOfCode2020Day11;

class AdventOfCode2020Day11Test
{
    @Test
    void testGetSeatMap()
    {
        char[][] seatMap = AdventOfCode2020Day11.getSeatMap("inputDay11Test.txt");
        assertEquals('L', seatMap[0][0]);
        assertEquals('L', seatMap[1][0]);
    }

    @Test
    void testOccupied()
    {
        char[][] seatMap = AdventOfCode2020Day11.getSeatMap("inputDay11Test1.txt");
        assertEquals('#', seatMap[0][0]);
        assertEquals('#', seatMap[0][1]);
        assertEquals('#', seatMap[0][2]);
        assertEquals('#', seatMap[0][3]);
        assertEquals(3, AdventOfCode2020Day11.numberOfSeatsAdjacent(seatMap, 0, 0));
        assertEquals(5, AdventOfCode2020Day11.numberOfSeatsAdjacent(seatMap, 1, 0));
        assertEquals(8, AdventOfCode2020Day11.numberOfSeatsAdjacent(seatMap, 1, 1));
        assertEquals(3, AdventOfCode2020Day11.numberOfSeatsAdjacent(seatMap, 3, 2));
    }

    @Test
    void testNofRemainingOccupied()
    {
        char[][] seatMap = AdventOfCode2020Day11.getSeatMap("inputDay11Test.txt");
        assertEquals(37, AdventOfCode2020Day11.nofRemainingOccupied(seatMap));
        seatMap = AdventOfCode2020Day11.getSeatMap("inputDay11.txt");
        assertEquals(2310, AdventOfCode2020Day11.nofRemainingOccupied(seatMap));
    }

    @Test
    void testNofOccupiedPart2()
    {
        char[][] seatMap = AdventOfCode2020Day11.getSeatMap("inputDay11Test.txt");
        assertEquals(26, AdventOfCode2020Day11.nofRemainingOccupiedPart2(seatMap));
        seatMap = AdventOfCode2020Day11.getSeatMap("inputDay11.txt");
        assertEquals(2074, AdventOfCode2020Day11.nofRemainingOccupiedPart2(seatMap));

    }


    @Test
    void testCorrectXAndYCoordinates()
    {
        final char[][] seatMap1 = AdventOfCode2020Day11.getSeatMap("inputDay11Test1.txt");
        assertDoesNotThrow(() -> AdventOfCode2020Day11.nofRemainingOccupied(seatMap1));
        final char[][] seatMap2 = AdventOfCode2020Day11.getSeatMap("inputDay11Test2.txt");
        assertDoesNotThrow(() -> AdventOfCode2020Day11.nofRemainingOccupied(seatMap2));
    }

    //    12345
    //    67890
    //    abcde
    //    fghij
    @Test
    void testNorthWestCharacters()
    {
        final char[][] seatMap1 = AdventOfCode2020Day11.getSeatMap("inputDay11Test3.txt");
        List<Character> chars = AdventOfCode2020Day11.getNorthWestCharacters(seatMap1, 3, 2);
        assertCharListEquals(chars, '8', '2');
        chars = AdventOfCode2020Day11.getNorthWestCharacters(seatMap1, 3, 3);
        assertCharListEquals(chars, 'c', '7', '1');
        chars = AdventOfCode2020Day11.getNorthWestCharacters(seatMap1, 1, 3);
        assertCharListEquals(chars, 'a');
    }

    private void assertCharListEquals(List<Character> charList, char... chars )
    {
        assertEquals(chars.length, charList.size());
        for (int i=0; i<chars.length; i++)
        {
            assertEquals(chars[i], charList.get(i));
        }
    }

    @Test
    void testNorthCharacters()
    {
        final char[][] seatMap1 = AdventOfCode2020Day11.getSeatMap("inputDay11Test3.txt");
        List<Character> chars = AdventOfCode2020Day11.getNorthCharacters(seatMap1, 3, 2);
        assertCharListEquals(chars, '9', '4');
        chars = AdventOfCode2020Day11.getNorthCharacters(seatMap1, 3, 3);
        assertCharListEquals(chars, 'd', '9', '4');
        chars = AdventOfCode2020Day11.getNorthCharacters(seatMap1, 1, 3);
        assertCharListEquals(chars, 'b', '7', '2');

    }

    @Test
    void testNorthEastCharacters()
    {
        final char[][] seatMap1 = AdventOfCode2020Day11.getSeatMap("inputDay11Test3.txt");
        List<Character> chars = AdventOfCode2020Day11.getNorthEastCharacters(seatMap1, 2, 3);
        assertCharListEquals(chars, 'd', '0');
        chars = AdventOfCode2020Day11.getNorthEastCharacters(seatMap1, 0, 2);
        assertCharListEquals(chars, '7', '3');

    }
    @Test
    void testEastCharacters()
    {
        final char[][] seatMap1 = AdventOfCode2020Day11.getSeatMap("inputDay11Test3.txt");
        List<Character> chars = AdventOfCode2020Day11.getEastCharacters(seatMap1, 2, 3);
        assertCharListEquals(chars, 'i', 'j');
        chars = AdventOfCode2020Day11.getEastCharacters(seatMap1, 0, 3);
        assertCharListEquals(chars, 'g', 'h', 'i', 'j');

    }

    @Test
    void testSouthEastCharacters()
    {
        final char[][] seatMap1 = AdventOfCode2020Day11.getSeatMap("inputDay11Test3.txt");
        List<Character> chars = AdventOfCode2020Day11.getSouthEastCharacters(seatMap1, 0, 0);
        assertCharListEquals(chars, '7', 'c', 'i');
        chars = AdventOfCode2020Day11.getSouthEastCharacters(seatMap1, 2, 2);
        assertCharListEquals(chars, 'i');

    }

    @Test
    void testSouthCharacters()
    {
        final char[][] seatMap1 = AdventOfCode2020Day11.getSeatMap("inputDay11Test3.txt");
        List<Character> chars = AdventOfCode2020Day11.getSouthCharacters(seatMap1, 0, 0);
        assertCharListEquals(chars, '6', 'a', 'f');
        chars = AdventOfCode2020Day11.getSouthCharacters(seatMap1, 2, 2);
        assertCharListEquals(chars, 'h');
    }

    @Test
    void testSouthWestCharacters()
    {
        final char[][] seatMap1 = AdventOfCode2020Day11.getSeatMap("inputDay11Test3.txt");
        List<Character> chars = AdventOfCode2020Day11.getSouthWestCharacters(seatMap1, 3, 1);
        assertCharListEquals(chars, 'c', 'g');
        chars = AdventOfCode2020Day11.getSouthWestCharacters(seatMap1, 4, 0);
        assertCharListEquals(chars, '9', 'c', 'g');

    }

    @Test
    void testWestCharacters()
    {
        final char[][] seatMap1 = AdventOfCode2020Day11.getSeatMap("inputDay11Test3.txt");
        List<Character> chars = AdventOfCode2020Day11.getWestCharacters(seatMap1, 3, 1);
        assertCharListEquals(chars, '8', '7', '6');
        chars = AdventOfCode2020Day11.getWestCharacters(seatMap1, 4, 0);
        assertCharListEquals(chars, '4', '3', '2','1');
    }

}