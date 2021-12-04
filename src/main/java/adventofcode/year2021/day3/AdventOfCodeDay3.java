package adventofcode.year2021.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adventofcode.utils.Utils;

public class AdventOfCodeDay3
{
    static final String INPUT_DAY_3_TXT = "input2021Day3.txt";
    static final String INPUT_DAY_3A_TXT = "input2021Day3a.txt";

    private AdventOfCodeDay3()
    {
    }

    static int[][] getBinaryCodesFromLines(List<String> lines)
    {
        int[][] binaryCodes = new int[lines.size()][5];
        for (int i = 0; i < lines.size(); i++)
        {
            String line = lines.get(i);
            binaryCodes[i] = getBinaryCodeFromLine(line);
        }
        return binaryCodes;
    }

    private static int[] getBinaryCodeFromLine(String line)
    {
        int[] binaryCode = new int[line.length()];
        for (int i = 0; i < line.length(); i++)
        {
            binaryCode[i] = Integer.parseInt(line.substring(i, i+1));
        }
        return binaryCode;
    }

    static int[] getMostCommonBits(int[][] binaryCodes)
    {
        int[] mostCommonBits = new int[binaryCodes[0].length];
        int[][] transposed = Utils.transposeMatrix(binaryCodes);
        for (int i = 0; i < transposed.length; i++)
        {
            mostCommonBits[i] = Utils.maxFreq(transposed[i]);
        }

        return mostCommonBits;
    }

    static int binaryArrayToInt(int[] binaryArray)
    {
        int pow = 0;
        int result = 0;
        final int length = binaryArray.length;
        for (int i = 0; i < length; i++)
        {
            if (binaryArray[length-1-i] == 1)
            {
                result += Math.pow(2, pow);
            }
            pow++;
        }
        return result;
    }

    static int getPowerConsumption(int[][] binaryCodes)
    {
        int[] mostCommonBits = getMostCommonBits(binaryCodes);
        int[] mirrored = getMirroredBinArray(mostCommonBits);
        return binaryArrayToInt(mostCommonBits) * binaryArrayToInt(mirrored);
    }

    static int getLifeSupportRating(int[][] binaryCodes)
    {
        int[] oxygenRating = getOxygenGeneratorString(binaryCodes);
        int[] cO2ScrubbingRating = getCO2ScrubberRating(binaryCodes);
        return binaryArrayToInt(oxygenRating) * binaryArrayToInt(cO2ScrubbingRating);
    }

    static int[] getMirroredBinArray(int[] binArray)
    {
        final int length = binArray.length;
        int[] mirrored = new int[length];
        for (int i = 0; i < length; i++)
        {
            mirrored[i] = binArray[i] == 0 ? 1 : 0;
        }
        return mirrored;
    }

    static int[] getOxygenGeneratorString(int[][] binaryCodes)
    {
        return getFilter(binaryCodes, true);
    }

    static int[] getCO2ScrubberRating(int[][] binaryCodes)
    {
        return getFilter(binaryCodes, false);
    }

    static int[] getFilter(int[][] binaryCodes, boolean filterOnMostCommon)
    {
        int[][] filtered = binaryCodes.clone();
        for (int i = 0; i < binaryCodes.length; i++)
        {
            filtered = filter(i, filtered, filterOnMostCommon);
            if (filtered.length == 1)
            {
                break;
            }
        }
        return filtered[0];
    }

    private static int[][] filter(int i, int[][] filtered, boolean filterOnMostCommon)
    {
        int[][] transposed = Utils.transposeMatrix(filtered);
        int mostCommon = Utils.maxFreq(transposed[i]);
        if (isHalfOfFrequency(transposed[i], mostCommon))
        {
            mostCommon = 1;
        }
        List<int[]> filteredList = new ArrayList<>();
        for (int[] ints : filtered)
        {
            if ((ints[i] == mostCommon && filterOnMostCommon) || (ints[i] != mostCommon && !filterOnMostCommon))
            {
                filteredList.add(ints);
            }
        }
        return filteredList.toArray(new int[0][]);
            
    }

    private static boolean isHalfOfFrequency(int[] ints, int mostCommon)
    {
        if (ints.length%2 == 0)
        {
            long frequency = Arrays.stream(ints).filter(i -> i == mostCommon).count();
            return frequency == ints.length / 2;
        }
        return false;
    }

}
