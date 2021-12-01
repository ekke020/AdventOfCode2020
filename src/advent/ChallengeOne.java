package advent;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChallengeOne {


    public static void main(String[] args) throws IOException {
        String text;
        List<Integer> expenses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/expenses.txt"))) {
            while ((text = reader.readLine()) != null) {
                expenses.add(Integer.parseInt(text));
            }
        }
        for (Integer expense : expenses) {
            for (Integer integer : expenses) {
                for (Integer expense1 : expenses) {
                    if (expense + integer + expense1 == 2020) {
                        System.out.println(expense * integer * expense1);
                    }
                }
            }
        }
    }


}
