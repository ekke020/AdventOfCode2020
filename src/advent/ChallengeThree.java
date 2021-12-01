package advent;

import java.io.*;
import java.util.Scanner;

public class ChallengeThree {

    private static final String[] FOREST = new String[323];

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new FileReader("resources/forest.txt"))){
            int i = 0;
            while (scanner.hasNextLine()) {
                FOREST[i] = scanner.nextLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(traverseForest());
    }

    private static int traverseForest() {
        int pos = 0;
        int trees = 0;

        for (int i = 0; i < FOREST.length; i+= 2) {
            if (String.valueOf(FOREST[i].charAt(pos)).equals("#")) {
                trees++;
            }
            pos += 1;
            if (pos >= 31) {
                pos -= 31;
            }
        }
        return trees;
    }
}
