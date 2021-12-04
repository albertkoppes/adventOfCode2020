package adventofcode.year2021.day2;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import adventofcode.utils.Utils;

public class AdventOfCodeDay2
{
    static final String INPUT_DAY_2_TXT = "input2021Day2.txt";
    static final String INPUT_DAY_2A_TXT = "input2021Day2a.txt";

    static List<Instruction> getInstructionsFromFile(String baseDir, String inputFileName)
    {
        List<String> lines = Utils.readLines(baseDir, inputFileName);
        return lines.stream().map(Instruction::new).collect(Collectors.toList());
    }

    static int getTotalMovement(List<Instruction> instructions)
    {
        int horizontalPosition = 0;
        int depth = 0;
        for (Instruction instruction : instructions)
        {
            Movement movement = instruction.movement;
            switch (movement)
            {
                case up:
                    depth  -= instruction.distance;
                    break;
                case down:
                    depth += instruction.distance;
                    break;
                case forward:
                    horizontalPosition += instruction.distance;
                    break;
                default:
                    throw new IllegalArgumentException("Not a legal movement: " + movement);
            }
        }
        return horizontalPosition * depth;
    }

    static int getTotalMovementPart2(List<Instruction> instructions)
    {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;
        for (Instruction instruction : instructions)
        {
            Movement movement = instruction.movement;
            switch (movement)
            {
                case up:
                    aim  -= instruction.distance;
                    break;
                case down:
                    aim += instruction.distance;
                    break;
                case forward:
                    horizontalPosition += instruction.distance;
                    depth += aim * instruction.distance;
                    break;
                default:
                    throw new IllegalArgumentException("Not a legal movement: " + movement);
            }
        }
        return horizontalPosition * depth;
    }

    static class Instruction
    {
        private final Movement movement;
        private final int distance;

        private Instruction(String line)
        {
            try (Scanner scanner = new Scanner(line))
            {
                movement = Movement.valueOf(scanner.next());
                distance = Integer.parseInt(scanner.next());
            }
        }
    }

    private enum Movement
    {
        forward,
        up,
        down

    }
}
