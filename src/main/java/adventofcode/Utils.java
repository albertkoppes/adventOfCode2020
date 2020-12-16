package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;



public class Utils
{
    public static List<String> readLines(String fileName) throws IOException
    {
        return Files.readAllLines(Paths.get("C:/Temp/adventofcode/" + fileName));
    }
    public static String readString(String fileName) throws IOException
    {
        return Files.readString(Paths.get("C:/Temp/adventofcode/" + fileName));
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
            digits[i] = Integer.valueOf(input.substring(i, i+1));
        }
        return digits;
    }

}
