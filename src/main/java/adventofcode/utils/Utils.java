package adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("squid:S106")
public class Utils
{
    private Utils()
    {
    }

    public static final String BASE_DIR = System.getProperty("user.dir") + "/src/main/java/adventofcode/year2020/input/";


    public static List<String> readLines(String baseDir, String fileName)
    {
        return privateReadLines(baseDir+fileName);

    }

    public static List<String> readLines(String fileName)
    {
            return privateReadLines(BASE_DIR + fileName);
    }
    public static List<String> privateReadLines(String fileName)
    {
        try
        {
            return Files.readAllLines(Paths.get(fileName));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    public static String readString(String fileName)
    {
        try
        {
            return Files.readString(Paths.get(BASE_DIR + fileName));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static void printIntArray(String prefix, int... array)
    {
        StringBuilder buf = new StringBuilder();
        buf.append(prefix);
        String sep = "";
        for (Object obj: array)
        {
            buf.append(sep).append(obj.toString());
            sep=", ";
        }
        System.out.println(buf);
    }
    public static void printObjectArray(String prefix, Object... array)
    {
        StringBuilder buf = new StringBuilder();
        buf.append(prefix);
        String sep = "";
        for (Object obj: array)
        {
            buf.append(sep).append(obj.toString());
            sep=", ";
        }
        System.out.println(buf);
    }

    public static int[] getIntArrayFromSeparatedInput(String input, String pattern)
    {

        String[] cells = input.split(pattern);
        return parseIntArray(cells);
    }
    public static long[] getLongArrayFromSeparatedInput(String input, String pattern)
    {

        String[] cells = input.split(pattern);
        return parseLongArray(cells);
    }

    public static int[] parseIntArray(String[] arr)
    {
        return Stream.of(arr).mapToInt(Integer::parseInt).toArray();
    }

    public static long[] parseLongArray(String[] arr)
    {
        return Stream.of(arr).mapToLong(Long::parseLong).toArray();
    }

    public static int[] getIntDigits(String input)
    {
        int[] digits = new int[input.length()];
        for (int i=0;i<input.length();i++)
        {
            digits[i] = Integer.parseInt(input.substring(i, i+1));
        }
        return digits;
    }

    public static List<Integer> toIntegerList(List<String> lines)
    {
        return lines.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    public static List<Long> toLongList(List<String> lines)
    {
        return lines.stream().map(Long::valueOf).collect(Collectors.toList());
    }

    public static int[][] transposeMatrix(int[][] m)
    {
        int[][] transposed = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
        {
            for (int j = 0; j < m[0].length; j++)
            {
                transposed[j][i] = m[i][j];
            }
        }

        return transposed;
    }

    public static int maxFreq(int[] arr)
    {
        // using moore's voting algorithm
        int res = 0;
        int count = 1;
        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i] == arr[res])
            {
                count++;
            }
            else
            {
                count--;
            }

            if (count == 0)
            {
                res = i;
                count = 1;
            }

        }

        return arr[res];
    }
}
