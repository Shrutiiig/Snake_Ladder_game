import java.util.*;

public class SnakeLadder {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int players = scn.nextInt(); //no.of players

        if (players < 2) {
            System.out.println("Oops! Minimum no. of players required is 2");
            return;
        }

        String[] player_names = new String[players];
        for (int i = 0; i < players; i++) {
            player_names[i] = scn.next();
        }

        int[] pos_of_players = new int[players];
        int curr_player = 1;

        HashMap<Integer, Integer> snake_ladder = new HashMap<>();
        update_snake_ladder(snake_ladder);

        boolean repeat = true;
        while (true) {

            int dice = rolldice();  // finding random no. on dice
            System.out.println("The player is " + player_names[curr_player - 1] + " & no. on dice is " + dice);
            int pos = pos_of_players[curr_player - 1];

            if (pos == 0) {

                if (dice == 1 || dice == 6) {
                    pos_of_players[curr_player - 1] = 1;  //start
                } else {
                    pos_of_players[curr_player - 1] = 0;
                }

            } else {

                if (pos + dice <= 100) {
                    pos_of_players[curr_player - 1] += dice;
                    if (dice != 6) repeat = false;
                }

                if (snake_ladder.containsKey(pos)) {
                    int val = snake_ladder.get(pos);
                    pos_of_players[curr_player - 1] = val;
                    isSnakeOrLadder(pos, val); //finding whether its a snake or a ladder
                }

            }

            pos = pos_of_players[curr_player - 1];
            if (isWinner(pos, player_names, curr_player)) return;

            if (!repeat) {
                curr_player += 1;
                if (curr_player == players + 1) curr_player = 1;
                System.out.println(curr_player);
            }
        }
    }

    static void update_snake_ladder(HashMap<Integer, Integer> snake_ladder) {
        // ladders
        //3 - 21
        //87 - 98
        //54 - 88

        //snakes:
        //99 - 35
        //19 - 2
        //39 - 13
        snake_ladder.put(3, 21);
        snake_ladder.put(87, 98);
        snake_ladder.put(54, 88);
        snake_ladder.put(99, 35);
        snake_ladder.put(19, 2);
        snake_ladder.put(39, 13);
    }

    static int rolldice() {
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        return dice;
    }

    static void isSnakeOrLadder(int key, int val) {
        if (key < val) {
            System.out.println("Hoorayy!!! It's a ladder. I'm climbing to " + val + ":)");
        } else {
            System.out.println("Oh no!!! It's a snake. Going down to " + val + ":(");
        }
    }

    static boolean isWinner(int pos, String[] player_names, int curr_player) {
        if (pos == 100) {
            System.out.println("Congratulations !!! Winner is " + player_names[curr_player - 1]);
            return true;
        }
        return false;
    }
}
