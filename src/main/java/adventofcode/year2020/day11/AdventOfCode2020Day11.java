package adventofcode.year2020.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adventofcode.utils.Utils;

public class AdventOfCode2020Day11
{
    public static void main(String[] args)
    {
        char[][] seatMap = AdventOfCode2020Day11.getSeatMap("inputDay11.txt");
        System.out.println(AdventOfCode2020Day11.nofRemainingOccupied(seatMap));
        System.out.println(AdventOfCode2020Day11.nofRemainingOccupiedPart2(seatMap));

    }

    static char[][] getSeatMap(String fileName)
    {
        List<String> lines = Utils.readLines(fileName);
        char[][] seatMap = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++)
        {
            seatMap[i] = lines.get(i).toCharArray();
        }
        return seatMap;
    }

    static void printSeatMap(char[][] seatMap)
    {
        Arrays.stream(seatMap).forEach(AdventOfCode2020Day11::printCharArray);
    }

    static void printCharArray(char[] charArray)
    {
        for (char c : charArray)
        {
            System.out.print(c);
        }
        System.out.println();
    }

    static boolean isOccupied(Character c)
    {
        return '#' == c;
    }

    static long numberOfSeatsAdjacent(char[][] seatMap, int x, int y)
    {
        int xLimit = seatMap[0].length - 1;
        int yLimit = seatMap.length - 1;

        Character upperLeft = (x > 0 && y > 0) ? seatMap[y - 1][x - 1] : ' ';
        Character upper = (y > 0) ? seatMap[y - 1][x] : ' ';
        Character upperRight = (x < xLimit && y > 0) ? seatMap[y - 1][x + 1] : ' ';
        Character left = (x > 0) ? seatMap[y][x - 1] : ' ';
        Character right = (x < xLimit) ? seatMap[y][x + 1] : ' ';
        Character lowerLeft = (x > 0 && y < yLimit) ? seatMap[y + 1][x - 1] : ' ';
        Character lower = (y < yLimit) ? seatMap[y + 1][x] : ' ';
        Character lowerRight = (x < xLimit && y < yLimit) ? seatMap[y + 1][x + 1] : ' ';
        List<Character> adjacent = List.of(upperLeft, upper, upperRight, left, right, lowerLeft, lower, lowerRight);
        return adjacent.stream().filter(AdventOfCode2020Day11::isOccupied).count();
    }

    static long numberOfSeatsIn8Directions(char[][] seatMap, int x, int y)
    {
        List<Character> northWest = getNorthWestCharacters(seatMap, x, y);
        List<Character> north = getNorthCharacters(seatMap, x, y);
        List<Character> northEast = getNorthEastCharacters(seatMap, x, y);
        List<Character> east = getEastCharacters(seatMap, x, y);
        List<Character> southEast = getSouthEastCharacters(seatMap, x, y);
        List<Character> south = getSouthCharacters(seatMap, x, y);
        List<Character> southWest = getSouthWestCharacters(seatMap, x, y);
        List<Character> west = getWestCharacters(seatMap, x, y);
        return getNumberOfOccupies(northWest, north, northEast, east, southEast, south, southWest, west);
    }

    private static long getNumberOfOccupies(List<Character>... lists)
    {
        return Arrays.stream(lists).filter(AdventOfCode2020Day11::occupiedSeatVisible).count();
    }

    private static boolean occupiedSeatVisible(List<Character> list)
    {
        if (!(list.contains('#')))
        {
            return false;
        }
        else
        {
            for (Character character : list)
            {
                if (character == 'L')
                {
                    return false;
                }
                else if (character == '#')
                {
                    return true;
                }
            }
        }
        throw new IllegalArgumentException("something went wrong with list: " + list.toString());
    }

    public static List<Character> getNorthWestCharacters(char[][] seatMap, int x, int y)
    {
        List<Character> chars = new ArrayList<>();
        for (int i=(y-1), j = (x-1); i >=0 && j>= 0; i--,j--)
        {
            chars.add(seatMap[i][j]);
        }
        return chars;
    }
    public static List<Character> getNorthCharacters(char[][] seatMap, int x, int y)
    {
        List<Character> chars = new ArrayList<>();
        for (int i = (y-1); i >=0 && (x) >= 0; i--)
        {
            chars.add(seatMap[i][(x)]);
        }
        return chars;
    }
    public static List<Character> getNorthEastCharacters(char[][] seatMap, int x, int y)
    {
        List<Character> chars = new ArrayList<>();
        for (int i=(y-1), j = (x+1); i >=0 && j< (seatMap[0].length); i--,j++)
        {
            chars.add(seatMap[i][j]);
        }
        return chars;
    }

    public static List<Character> getSouthEastCharacters(char[][] seatMap, int x, int y)
    {
        List<Character> chars = new ArrayList<>();
        for (int i=(y+1), j = (x+1); i < (seatMap.length) && j< (seatMap[0].length); i++,j++)
        {
            chars.add(seatMap[i][j]);
        }
        return chars;
    }

    public static List<Character> getEastCharacters(char[][] seatMap, int x, int y)
    {
        List<Character> chars = new ArrayList<>();
        for (int j = (x+1); (y) >=0 && j< (seatMap[0].length); j++)
        {
            chars.add(seatMap[(y)][j]);
        }
        return chars;
    }

    public static List<Character> getSouthCharacters(char[][] seatMap, int x, int y)
    {
        List<Character> chars = new ArrayList<>();
        for (int i = (y+1); i >=0 && i < (seatMap.length); i++)
        {
            chars.add(seatMap[i][(x)]);
        }
        return chars;
    }
    public static List<Character> getSouthWestCharacters(char[][] seatMap, int x, int y)
    {
        List<Character> chars = new ArrayList<>();
        for (int i=(y+1), j = (x-1); i < (seatMap.length) && j>=0; i++,j--)
        {
            chars.add(seatMap[i][j]);
        }
        return chars;
    }

    public static List<Character> getWestCharacters(char[][] seatMap, int x, int y)
    {
        List<Character> chars = new ArrayList<>();
        for (int j = (x-1); (y) >=0 && j>=0; j--)
        {
            chars.add(seatMap[(y)][j]);
        }
        return chars;
    }

    static char[][] processCharArray(char[][] seatMap)
    {
        char[][] outputMap = new char[seatMap.length][];
        for (int i = 0; i < seatMap.length; i++)
        {
            outputMap[i] = new char[seatMap[0].length];
            for (int j = 0; j < seatMap[0].length; j++)
            {
                outputMap[i][j] = calcNewChar(seatMap, j, i);
            }
        }
        return outputMap;
    }

    static char[][] processCharArrayPart2(char[][] seatMap)
    {
        char[][] outputMap = new char[seatMap.length][];
        for (int i = 0; i < seatMap.length; i++)
        {
            outputMap[i] = new char[seatMap[0].length];
            for (int j = 0; j < seatMap[0].length; j++)
            {
                outputMap[i][j] = calcNewCharPart2(seatMap, j, i);
            }
        }
        return outputMap;
    }

    private static char calcNewChar(char[][] seatMap, int j, int i)
    {
        char thisChar = seatMap[i][j];
        long nofOccupied = numberOfSeatsAdjacent(seatMap, j, i);
        if (thisChar == 'L' && nofOccupied == 0)
        {
            return '#';
        }
        else if (thisChar == '#' && nofOccupied >= 4)
        {
            return 'L';
        }
        return thisChar;
    }

    private static char calcNewCharPart2(char[][] seatMap, int j, int i)
    {
        char thisChar = seatMap[i][j];
        long nofOccupied = numberOfSeatsIn8Directions(seatMap, j, i);
        if (thisChar == 'L' && nofOccupied == 0)
        {
            return '#';
        }
        else if (thisChar == '#' && nofOccupied >= 5)
        {
            return 'L';
        }
        return thisChar;
    }

    static int nofRemainingOccupied(char[][] seatMap)
    {
        char[][] input = twoDimensionalArrayClone(seatMap);
        char[][] output = processCharArray(input);
        while (!Arrays.deepEquals(input, output))
        {
            input = twoDimensionalArrayClone(output);
            output = processCharArray(input);
        }
        return countNumberOfOccupiedSeats(output);
    }

    static int nofRemainingOccupiedPart2(char[][] seatMap)
    {
        char[][] input = twoDimensionalArrayClone(seatMap);
        char[][] output = processCharArrayPart2(input);
        while (!Arrays.deepEquals(input, output))
        {
            input = twoDimensionalArrayClone(output);
            output = processCharArrayPart2(input);
        }
        return countNumberOfOccupiedSeats(output);
    }

    private static int countNumberOfOccupiedSeats(char[][] output)
    {
        int count = 0;
        for (char[] chars : output)
        {
            for (int j = 0; j < output[0].length; j++)
            {
                if (chars[j] == '#')
                {
                    count++;
                }
            }
        }
        return count;
    }

    // clone two dimensional array
    public static char[][] twoDimensionalArrayClone(char[][] a)
    {
        char[][] b = new char[a.length][];
        for (int i = 0; i < a.length; i++)
        {
            b[i] = a[i].clone();
        }
        return b;
    }

}
