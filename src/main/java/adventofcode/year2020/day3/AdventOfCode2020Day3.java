package adventofcode.year2020.day3;

import java.util.Arrays;
import java.util.List;

import adventofcode.year2020.utils.Utils;

public class AdventOfCode2020Day3
{
    public static void main(String[] args)
    {
        char[][] map = getMapFromFile("inputDay3.txt");
        System.out.println(countTrees(map, new Slope( 3,1)));

        System.out.println(multiplyTreesForDifferentSlopes("inputDay3.txt"));
    }
    static char[][] getMapFromFile(String filename)
    {
        List<String> lines = Utils.readLines(filename);
        char[][] map = new char[lines.size()][];

        for (int i = 0; i < lines.size(); i++)
        {
            String line = lines.get(i);
            char[] chars = line.toCharArray();
            map[i] = chars;
        }
        printMap(map);
        return map;
    }

    static long multiplyTreesForDifferentSlopes(String fileName)
    {
        long product = 1;
        char[][] map = getMapFromFile(fileName);
        product  *= countTrees(map, new Slope(1,1));
        product  *= countTrees(map, new Slope(3,1));
        product  *= countTrees(map, new Slope(5,1));
        product  *= countTrees(map, new Slope(7,1));
        product  *= countTrees(map, new Slope(1,2));
        return product;
    }

    static void printMap(char[][] map)
    {
        Arrays.stream(map).forEach(AdventOfCode2020Day3::printLine);
    }

    static void printLine(char[] chars)
    {
        for (char c : chars)
        {
            System.out.print(c);
        }
        System.out.println();
    }

    static int countTrees(char[][] map, Slope slope)
    {
        int count = 0;
        Coordinate currentPosition = new Coordinate(0, 0);
        while (currentPosition.x < map.length)
        {
            char next = map[currentPosition.x][currentPosition.y%(map[currentPosition.x].length)];
            if (isTree(next))
            {
                count++;
            }
            currentPosition = currentPosition.nextCoordinate(slope.y, slope.x);
        }
        return count;
    }

    static boolean isTree(char character)
    {
        return '#' == character;
    }

    static class Coordinate
    {
        int x;
        int y;

        public Coordinate(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString()
        {
            return "(" + x + "," + y + ")";
        }

        public Coordinate nextCoordinate(int x, int y)
        {
            return new Coordinate(this.x + x, this.y + y);
        }
    }

    static class Slope
    {
        int x;
        int y;

        public Slope(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

}
