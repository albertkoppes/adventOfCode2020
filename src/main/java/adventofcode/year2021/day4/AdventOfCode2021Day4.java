package adventofcode.year2021.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import adventofcode.utils.Utils;

public class AdventOfCode2021Day4
{
    static final String INPUT_DAY_4_TXT = "input2021Day4.txt";
    static final String INPUT_DAY_4A_TXT = "input2021Day4a.txt";

    private AdventOfCode2021Day4()
    {
    }

    static int calculateSquid(List<String> lines)
    {
        Game game = getGame(lines);
        Result result = playGame(game, false);
        return result.calculateSquid();
    }

    static int calculateGiantSquid(List<String> lines)
    {
        Game game = getGame(lines);
        Result result = playGame(game, true);
        return result.calculateSquid();
    }

    private static Result playGame(Game game, boolean forGiantSquid)
    {
        for (int draw :game.draw.draws)
        {
            Result result = updateCards(draw, game.cards);
            if (result != null)
            {
                if (!forGiantSquid)
                {
                    return result;
                }
                else
                {
                    if (game.cards.size() ==  1)
                    {
                        return result;
                    }
                    game.cards.removeAll(result.cards);
                }
            }
        }
        throw new IllegalArgumentException("Illegal game, no result");
    }

    private static Result updateCards(int draw, List<Card> cards)
    {

        for (Card card: cards)
        {
            card.updateCard(draw);

        }
        List<Card> bingos = bingoFound(cards);
        if (!bingos.isEmpty())
        {
            return new Result(bingos, draw);
        }

        return null;
    }

    private static List<Card> bingoFound(List<Card> cards)
    {
        List<Card> bingoFoundFor = new ArrayList<>();
        for (Card card: cards)
        {
            if (card.bingoFound())
            {
                bingoFoundFor.add(card);
            }
        }
        return bingoFoundFor;
    }

    static Game getGame(List<String> lines)
    {
        Draw draw = new Draw(lines.get(0));

        lines.remove(0);
        lines.remove(0);

        List<Card> cards = readCards(lines);
        return new Game(draw, cards);
    }

    static List<Card> readCards(List<String> lines)
    {
        List<Card> cards = new ArrayList<>();
        List<String> thisCard = new ArrayList<>();
        for (String line : lines)
        {
            if (line.isBlank())
            {
                cards.add(new Card(thisCard));
                thisCard = new ArrayList<>();
            }
            else
            {
                thisCard.add(line);
            }
        }
        cards.add(new Card(thisCard));
        return cards;
    }

    static class Draw
    {
        List<Integer> draws = new ArrayList<>();

        public Draw(String line)
        {
            try (Scanner scanner = new Scanner(line))
            {
                scanner.useDelimiter(",");
                while (scanner.hasNext())
                {
                    draws.add(Integer.valueOf(scanner.next()));
                }
            }
        }

    }

    static class Card
    {
        int[][] horizontal = new int[5][5];
        int[][] vertical;
        boolean[][] horizontalChecks = new boolean[5][5];
        boolean[][] verticalChecks = new boolean[5][5];

        public Card(List<String> lines)
        {
            for (int i = 0; i < lines.size(); i++)
            {
                int j = 0;
                try (Scanner scanner = new Scanner(lines.get(i)))
                {
                    while (scanner.hasNext())
                    {
                        horizontal[i][j++] = Integer.parseInt(scanner.next());
                    }
                }
            }
            vertical = Utils.transposeMatrix(horizontal);
            Arrays.stream(horizontalChecks).forEach(a -> Arrays.fill(a, false));
            Arrays.stream(verticalChecks).forEach(a -> Arrays.fill(a, false));
        }

        public void updateCard(int draw)
        {
            for (int i=0; i< horizontal.length;i++)
            {
                for (int j=0; j< horizontal[0].length;j++)
                {
                    if (horizontal[i][j] == draw)
                    {
                        horizontalChecks[i][j] = true;
                    }
                }
            }
            for (int i=0; i< vertical.length;i++)
            {
                for (int j=0; j< vertical[0].length;j++)
                {
                    if (vertical[i][j] == draw)
                    {
                        verticalChecks[i][j] = true;
                    }
                }
            }
        }

        public boolean bingoFound()
        {
            List<boolean[]> lines = Arrays.stream(horizontalChecks).collect(Collectors.toList());
            for (boolean[] line : lines)
            {
                Stream<Boolean> stream = IntStream.range(0, line.length)
                    .mapToObj(idx -> line[idx]);
                if (stream.allMatch(Boolean.TRUE::equals))
                {
                    return true;
                }
            }
            lines = Arrays.stream(verticalChecks).collect(Collectors.toList());
            for (boolean[] line : lines)
            {
                Stream<Boolean> stream = IntStream.range(0, line.length)
                    .mapToObj(idx -> line[idx]);
                if (stream.allMatch(Boolean.TRUE::equals))
                {
                    return true;
                }
            }
            return false;
         }

        public int sumUnmarkedNumbers()
        {
            int sum = 0;
            for (int i=0; i< horizontalChecks.length;i++)
            {
                for (int j=0; j< horizontalChecks[0].length;j++)
                {
                    if (!horizontalChecks[i][j])
                    {
                        sum += horizontal[i][j];
                    }
                }
            }
            return sum;
        }
    }

    static class Game
    {
        private final Draw draw;
        private final List<Card> cards;

        public Game(Draw draw, List<Card> cards)
        {
            this.draw = draw;
            this.cards = cards;
        }

        public Draw getDraw()
        {
            return draw;
        }

        public List<Card> getCards()
        {
            return cards;
        }
    }

    private static class Result
    {
        List<Card> cards;
        int draw;

        public Result(List<Card> cards, int draw)
        {
            this.cards = cards;
            this.draw = draw;
        }

        public int calculateSquid()
        {
            int sumUnmarkedNumbers = cards.get(0).sumUnmarkedNumbers();
            return sumUnmarkedNumbers * draw;
        }
    }

}
