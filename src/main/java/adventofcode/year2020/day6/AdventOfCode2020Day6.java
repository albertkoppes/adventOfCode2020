package adventofcode.year2020.day6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adventofcode.utils.Utils;

public class AdventOfCode2020Day6
{
    public static void main(String[] args)
    {
        System.out.println(getSumOfCounts("inputDay6.txt", false)); // 6273
        System.out.println(getSumOfCounts("inputDay6.txt", true)); //3254
    }

    static int getSumOfCounts(String fileName, boolean countEveryOnesAnswers)
    {
        List<String> lines = Utils.readLines(fileName);
        List<Set<Character>> answers = new ArrayList<>();
        Set<Character> currentSet = new HashSet<>();
        currentSet.add(' '); // trick to identify a new group
        for (String line : lines)
        {
            if (line.isEmpty())
            {
                answers.add(currentSet);
                currentSet = new HashSet<>();
                currentSet.add(' ');
            }
            else
            {
                addAllSeparateAnswersToCurrentSet(line, currentSet, countEveryOnesAnswers);
            }
        }
        answers.add(currentSet);
        return getSumOfCounts(answers);
    }

    private static int getSumOfCounts(List<Set<Character>> answers)
    {
        int sum = 0;
        for (Set<Character> thisSet:answers)
        {
            sum += thisSet.size();
        }
        return sum;
    }

    private static void addAllSeparateAnswersToCurrentSet(String line, Set<Character> currentSet, boolean countEveryOnesAnswers)
    {
        if (countEveryOnesAnswers)
        {
            if (currentSet.contains(' '))
            {
                currentSet.remove(' ');
                addAllCharactersToSet(line, currentSet);
            }
            else
            {
                Set<Character> set = new HashSet<>(6);
                for (char character : line.toCharArray())
                {
                    set.add(character);
                }
                List<Character> toBeRemoved = new ArrayList<>();
                for (char character : currentSet)
                {
                    if (!set.contains(character))
                    {
                        toBeRemoved.add(character);
                    }
                }
                currentSet.removeAll(toBeRemoved);
            }

        }
        else
        {
            addAllSeparateAnswersToCurrentSet(line, currentSet);
        }
    }

    private static void addAllCharactersToSet(String line, Set<Character> currentSet)
    {
        currentSet.remove(' '); // remove the indicator for a new group
        for (char character : line.toCharArray())
        {
            currentSet.add(character);
        }
    }

    private static void addAllSeparateAnswersToCurrentSet(String line, Set<Character> currentSet)
    {
        addAllCharactersToSet(line, currentSet);
    }

}
