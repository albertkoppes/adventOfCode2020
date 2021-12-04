package adventofcode.year2021.day4;

import static org.junit.jupiter.api.Assertions.*;

import static adventofcode.year2021.day4.AdventOfCode2021Day4.INPUT_DAY_4A_TXT;
import static adventofcode.year2021.day4.AdventOfCode2021Day4.INPUT_DAY_4_TXT;
import static adventofcode.year2021.day4.AdventOfCode2021Day4.calculateGiantSquid;
import static adventofcode.year2021.day4.AdventOfCode2021Day4.calculateSquid;
import static adventofcode.year2021.day4.AdventOfCode2021Day4.getGame;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.utils.Utils;
import adventofcode.year2021.Constants;
import adventofcode.year2021.day4.AdventOfCode2021Day4.Card;
import adventofcode.year2021.day4.AdventOfCode2021Day4.Game;

class AdventOfCode2021Day4Test
{

    @Test
    void testGetGame()
    {
        List<String> lines = Utils.readLines(Constants.BASE_DIR, INPUT_DAY_4_TXT);
        Game game = getGame(lines);
        final List<Integer> draws = game.getDraw().draws;
        assertEquals(27, draws.size());
        final List<Card> cards = game.getCards();
        assertEquals(3, cards.size());
        assertEquals(1, draws.get(draws.size()-1));
        assertEquals(21, cards.get(2).horizontal[0][1]);
        assertEquals(10, cards.get(2).vertical[0][1]);
    }

    @Test
    void testCalculateSquid()
    {
        List<String> lines = Utils.readLines(Constants.BASE_DIR, INPUT_DAY_4_TXT);
        assertEquals(4512, calculateSquid(lines));
        lines = Utils.readLines(Constants.BASE_DIR, INPUT_DAY_4A_TXT);
        assertEquals(49860, calculateSquid(lines));
    }

    @Test
    void testCalculateGiantSquid()
    {
        List<String> lines = Utils.readLines(Constants.BASE_DIR, INPUT_DAY_4_TXT);
        assertEquals(1924, calculateGiantSquid(lines));
        lines = Utils.readLines(Constants.BASE_DIR, INPUT_DAY_4A_TXT);
        assertEquals(24628, calculateGiantSquid(lines));
    }

}