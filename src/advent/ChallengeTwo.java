package advent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ChallengeTwo {

    /*
        1-3 a: abcde
        1-3 b: cdefg
        2-9 c: ccccccccc
     */
    public static void main(String[] args) {
        String line;
        int amount = 0;
        try (Scanner scanner = new Scanner(new FileReader("resources/passwords.txt"))) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (checkLines(line))
                    amount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(amount);
    }

    private static boolean checkLines(String line) {
        int[] numbers = new int[2];
        String[] lines = line.split(":");
        String[] firstLine = lines[0].split("-");
        numbers[0] = Integer.parseInt(firstLine[0]);
        String[] secondLine = firstLine[1].split(" ");
        numbers[1] = Integer.parseInt(secondLine[0]);
        String character = secondLine[1].trim();
        int amount = 0;

        try {
            if (String.valueOf(lines[1].charAt(numbers[0])).equals(character)) {
                amount++;
            }
            if (String.valueOf(lines[1].charAt(numbers[1])).equals(character)) {
                amount++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("out of bounds");
        }
//        for (int i = 0; i < lines[1].trim().length(); i++) {
//            if (String.valueOf(lines[1].trim().charAt(i)).equals(character)) {
//                amount++;
//            }
//        }
//        return amount >= numbers[0] && amount <= numbers[1];
        return amount == 1;
    }
}
