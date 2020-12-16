package adventofcode.year2020.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdventOfCode2020Day8
{
    static int getNextIndexOf(List<Instruction> instructions, int startFrom, Operation... operations)
    {
        List<Operation> containsList = Arrays.asList(operations);
        for (int i = startFrom; i < instructions.size(); i++)
        {
            if (containsList.contains(instructions.get(i).operation))
            {
                return i;
            }
        }
        throw new IllegalArgumentException("No next index");
    }

    static State tryCodeUntilNonInfiniteLoop(List<Instruction> instructions)
    {
        State state;
        int indexOfSwap = 0;
        List<Instruction> swappedInstructions = List.copyOf(instructions);
        do
        {
            state = handleCode(swappedInstructions);
            if (state.inLoop)
            {
                indexOfSwap = getNextIndexOf(instructions, indexOfSwap + 1, Operation.JUMP, Operation.NOP);
                swappedInstructions = swapInstruction(instructions, indexOfSwap);
            }
        }
        while (state.inLoop);
        return state;

    }

    enum Operation
    {
        ACCUMULATOR("acc"),
        JUMP("jmp"),
        NOP("nop");

        String operationString;

        Operation(String operationString)
        {
            this.operationString = operationString;
        }

        static Operation getOperation(String operationString)
        {
            for (Operation operation : values())
            {
                if (operation.operationString.equals(operationString))
                {
                    return operation;
                }
            }
            throw new IllegalArgumentException("Not an operation: " + operationString);
        }

        @Override
        public String toString()
        {
            return "Operation{" + "operation='" + operationString + '\'' + '}';
        }
    }

    static class Instruction
    {
        Operation operation;
        int argument;

        public Instruction(Instruction instruction)
        {
            this.operation = instruction.operation;
            this.argument = instruction.argument;
        }

        public Instruction(String line)
        {
            try (Scanner scanner = new Scanner(line))
            {
                operation = Operation.getOperation(scanner.next());
                argument = scanner.nextInt();
            }
        }

        @Override
        public String toString()
        {
            return "Instruction{" + "operation=" + operation + ", argument=" + argument + '}';
        }

    }

    static List<Instruction> getInstructions(List<String> lines)
    {
        List<Instruction> instructions = new ArrayList<>();
        lines.forEach(l -> instructions.add(new Instruction(l)));
        return instructions;
    }

    static State handleCode(List<Instruction> instructions)
    {
        State state = new State();
        List<Integer> indexesHit = new ArrayList<>();
        int maxNofIterations = 1000;
        int count = 0;
        while (count++ < maxNofIterations && !(state.index < 0 || state.index == instructions.size()) && !indexesHit.contains(state.index))
        {
            indexesHit.add(state.index);
            handleInstruction(instructions.get(state.index), state);
            state.inLoop = indexesHit.contains(state.index);
        }
        return state;
    }

    private static void handleInstruction(Instruction instruction, State state)
    {
        switch (instruction.operation)
        {
            case ACCUMULATOR:
                state.accumulator += instruction.argument;
                state.index += 1;
                break;
            case JUMP:
                state.index += instruction.argument;
                break;
            case NOP:
                state.index += 1;
                break;
            default:
                throw new IllegalArgumentException("Unhandled instruction: " + instruction);
        }
    }

    static class State
    {
        long accumulator = 0;
        int index = 0;
        boolean inLoop = false;

        @Override
        public String toString()
        {
            return "State{" + "accumulator=" + accumulator +
                ", index=" + index +
                ", inLoop=" + inLoop +
                '}';
        }
    }

    static List<Instruction> swapInstruction(List<Instruction> instructions, int toSwap)
    {
        List<Instruction> swapped = new ArrayList<>(instructions);
        Instruction instruction = new Instruction(swapped.get(toSwap));
        Operation operation = instruction.operation;
        if (operation.equals(Operation.JUMP))
        {
            instruction.operation = Operation.NOP;
        }
        else if (operation.equals(Operation.NOP))
        {
            instruction.operation = Operation.JUMP;
        }
        else
        {
            throw new IllegalArgumentException("Instruction cannot be swapped: " + instruction);
        }
        swapped.set(toSwap, instruction);
        return swapped;
    }
}
