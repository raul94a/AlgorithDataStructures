package array;

import java.awt.font.GlyphMetrics;
import java.util.Arrays;

public class Scoreboard {

    public static void main(String[] args) {
        var scoreboard = new Scoreboard(5);

        scoreboard.addEntry(new GameEntry("Raul", 3));
        scoreboard.addEntry(new GameEntry("Ana", 5));
        scoreboard.addEntry(new GameEntry("PEPITA", 2));
        scoreboard.addEntry(new GameEntry("GATO", 10));
        scoreboard.addEntry(new GameEntry("TEST", 4));
        scoreboard.addEntry(new GameEntry("Perro", 3));
        scoreboard.addEntry(new GameEntry("Perro2", 2));
        scoreboard.addEntry(new GameEntry("Master", 29));
        scoreboard.addEntry(new GameEntry("Master 2", 7));


        System.out.println(Arrays.toString(scoreboard.board));

    }

    private int numEntries = 0;
    private GameEntry[] board;


    public Scoreboard(int capacity) {
        this.board = new GameEntry[capacity];
    }

    public void addEntry(GameEntry gameEntry) {

        var score = gameEntry.getScore();
        // ¿Es un high score?
        // Es un high score si board no está lleno O si es mayor que el último elemento
        boolean isNotFull = board.length > numEntries;


        if (isNotFull) {
            board[numEntries] = gameEntry;
            numEntries++;
            // sort
           bubbleSorting();
          //  insertionSort();
        } else {

            for (int i = 0; i < board.length; i++) {
                int positionScore = board[i].getScore();
                if (positionScore <= score) {
                    for (int j = board.length - 1; j > i; j--) {
                        board[j] = board[j - 1];
                    }
                    board[i] = gameEntry;
                    return;
                }
            }


        }

    }

    private void insertionSort(){
        int n = this.numEntries;
        for (int i = 1;  i < n; i++){
            double currentValue = this.board[i].getScore();
            int j = i;
            while (j > 0 && this.board[j - 1].getScore() > currentValue){
                this.board[j]  = this.board[j - 1];
                j--;
            }
        }
    }

    private void bubbleSorting() {
        for (int i = 0; i < numEntries; i++) {
            for (int j = i + 1; j < numEntries; j++) {
                var elementA = board[i];
                var elementB = board[j];
                if (elementB.getScore() > elementA.getScore()) {
                    board[i] = elementB;
                    board[j] = elementA;
                }
            }
        }
    }
    


}