package adventofcode.year2021.day2;

import static org.junit.jupiter.api.Assertions.*;

import static adventofcode.year2021.day2.AdventOfCodeDay2.INPUT_DAY_2A_TXT;
import static adventofcode.year2021.day2.AdventOfCodeDay2.INPUT_DAY_2_TXT;
import static adventofcode.year2021.day2.AdventOfCodeDay2.getInstructionsFromFile;
import static adventofcode.year2021.day2.AdventOfCodeDay2.getTotalMovement;
import static adventofcode.year2021.day2.AdventOfCodeDay2.getTotalMovementPart2;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.year2021.Constants;

class AdventOfCodeDay2Test
{

    @Test
    void testGetInstructionsFromFile()
    {
        List<AdventOfCodeDay2.Instruction> instructions = getInstructionsFromFile(Constants.BASE_DIR, INPUT_DAY_2_TXT);
        assertEquals(6, instructions.size());
    }

    @Test
    void testGetTotalMovement()
    {
        List<AdventOfCodeDay2.Instruction> instructions = getInstructionsFromFile(Constants.BASE_DIR, INPUT_DAY_2_TXT);
        assertEquals(150, getTotalMovement(instructions));
        assertEquals(900, getTotalMovementPart2(instructions));
        instructions = getInstructionsFromFile(Constants.BASE_DIR, INPUT_DAY_2A_TXT);
        assertEquals(1692075, getTotalMovement(instructions));
        assertEquals(1749524700, getTotalMovementPart2(instructions));
    }

}