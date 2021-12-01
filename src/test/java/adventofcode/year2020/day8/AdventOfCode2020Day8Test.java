package adventofcode.year2020.day8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.utils.Utils;

class AdventOfCode2020Day8Test
{

    @Test
    void testReadInput()
    {
        List<String> lines = Utils.readLines("inputDay8Test.txt");
        List<AdventOfCode2020Day8.Instruction> instructions = AdventOfCode2020Day8.getInstructions(lines);
        assertEquals(9, instructions.size());
        AdventOfCode2020Day8.Instruction inst = instructions.get(4);
        assertEquals(AdventOfCode2020Day8.Operation.JUMP, inst.operation);
        assertEquals(-3, inst.argument);

    }

    @Test
    void testSwapInput()
    {
        List<String> lines = Utils.readLines("inputDay8Test.txt");
        List<AdventOfCode2020Day8.Instruction> instructions = AdventOfCode2020Day8.getInstructions(lines);
        instructions = AdventOfCode2020Day8.swapInstruction(instructions, 7);
        AdventOfCode2020Day8.Instruction inst = instructions.get(7);
        assertEquals(AdventOfCode2020Day8.Operation.NOP, inst.operation);
        AdventOfCode2020Day8.State state = AdventOfCode2020Day8.handleCode(instructions);
        assertEquals(9, state.index);
        assertFalse(state.inLoop);
    }

    @Test
    void testTrySwapInput()
    {
        List<String> lines = Utils.readLines("inputDay8Test.txt");
        List<AdventOfCode2020Day8.Instruction> instructions = AdventOfCode2020Day8.getInstructions(lines);
        AdventOfCode2020Day8.State state = AdventOfCode2020Day8.tryCodeUntilNonInfiniteLoop(instructions);
        assertFalse(state.inLoop);
        assertEquals(8, state.accumulator);
    }

    @Test
    void testHandleCode()
    {
        List<String> lines = Utils.readLines("inputDay8Test.txt");
        List<AdventOfCode2020Day8.Instruction> instructions = AdventOfCode2020Day8.getInstructions(lines);
        AdventOfCode2020Day8.State state = AdventOfCode2020Day8.handleCode(instructions);
        assertEquals(5, state.accumulator);
        assertTrue(state.inLoop);
    }
    
    @Test
    void testRealInput()
    {
        List<String> lines = Utils.readLines("inputDay8.txt");
        List<AdventOfCode2020Day8.Instruction> instructions = AdventOfCode2020Day8.getInstructions(lines);
        AdventOfCode2020Day8.State state = AdventOfCode2020Day8.handleCode(instructions);
        assertEquals(1816, state.accumulator);

        state = AdventOfCode2020Day8.tryCodeUntilNonInfiniteLoop(instructions);
        assertEquals(1149, state.accumulator);
    }

}