package adventofcode.year2021.day1;

import java.util.List;

public abstract class AdventOfCode2021Day1
{
    public static final String INPUT_DAY_1_TXT = "input2021Day1.txt";
    public static final String INPUT_DAY_1A_TXT = "input2021Day1a.txt";

    private AdventOfCode2021Day1()
    {
    }

    static int getNrOfIncreases(List<Integer> integers)
    {
        return getNrOfIncreases(integers, 1);
    }

    static int getNrOfIncreases(List<Integer> integers, int slidingWindowSize)
    {
        int nrOfIncreases = 0;
        for (int i = 0; i < integers.size() - slidingWindowSize; i++)
        {
            int firstWindowSum = 0;
            int secondWindowSum = 0;
            for (int j = 0; j<slidingWindowSize; j++)
            {
                firstWindowSum += integers.get(i+j);
                secondWindowSum += integers.get(i+j+1);
            }
            if (secondWindowSum > firstWindowSum)
            {
                nrOfIncreases++;
            }
        }
        return nrOfIncreases;
    }

}
