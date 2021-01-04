package adventofcode.year2020.day20;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class AdventOfCode2020Day20Test
{
    @Test
    void testReadInput()
    {
        List<AdventOfCode2020Day20.Tile> tiles = AdventOfCode2020Day20.readTiles("day20/inputDay20Test.txt");
        assertEquals(9, tiles.size());
        AdventOfCode2020Day20.Tile tile = tiles.stream().filter(t -> t.id == 2311).findFirst().get();
        assertArrayEquals("..##.#..#.".toCharArray(), toCharArray(tile.getEdge(AdventOfCode2020Day20.Edge.UPPER, false)));
        assertArrayEquals("...#.##..#".toCharArray(), toCharArray(tile.getEdge(AdventOfCode2020Day20.Edge.RIGHT, false)));
        assertArrayEquals("###..###..".toCharArray(), toCharArray(tile.getEdge(AdventOfCode2020Day20.Edge.LOWER, false)));
        assertArrayEquals(".#..#####.".toCharArray(), toCharArray(tile.getEdge(AdventOfCode2020Day20.Edge.LEFT, false)));
        assertArrayEquals("#..##.#...".toCharArray(), toCharArray(tile.getEdge(AdventOfCode2020Day20.Edge.RIGHT, true)));

        assertArrayEquals("..##.#..#.".toCharArray(), toCharArray(tile.chars[0]));
        assertArrayEquals("##..#.....".toCharArray(), toCharArray(tile.chars[1]));
        assertArrayEquals("#...##..#.".toCharArray(), toCharArray(tile.chars[2]));
        assertArrayEquals("####.#...#".toCharArray(), toCharArray(tile.chars[3]));
        assertArrayEquals("##.##.###.".toCharArray(), toCharArray(tile.chars[4]));
        assertArrayEquals("##...#.###".toCharArray(), toCharArray(tile.chars[5]));
        assertArrayEquals(".#.#.#..##".toCharArray(), toCharArray(tile.chars[6]));
        assertArrayEquals("..#....#..".toCharArray(), toCharArray(tile.chars[7]));
        assertArrayEquals("###...#.#.".toCharArray(), toCharArray(tile.chars[8]));
        assertArrayEquals("..###..###".toCharArray(), toCharArray(tile.chars[9]));
        assertEquals(20899048083289D, AdventOfCode2020Day20.getProductOfIdsOfCornerTiles(tiles));
    }

    @Test
    void testRealInput()
    {
        List<AdventOfCode2020Day20.Tile> tiles = AdventOfCode2020Day20.readTiles("day20/inputDay20.txt");
        assertEquals(111936085519519D, AdventOfCode2020Day20.getProductOfIdsOfCornerTiles(tiles));
    }


        char[] toCharArray(List<Character> objectList)
    {
        Character[] objectArray = objectList.toArray(new Character[0]);
        return toCharArray(objectArray);
    }

    private char[] toCharArray(Character[] objectArray)
    {
        char[] charArray = new char[objectArray.length];
        for (int i = 0; i < objectArray.length; i++)
        {
            charArray[i] = objectArray[i];
        }
        return charArray;
    }



}