package advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ChallengeSix {

    private static HashMap<Integer, Integer> answers = new HashMap<>();
    private static int counts = 0;
    public static void main(String[] args) {
        List<String> groupAnswers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("questionnaire.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    getGroupAnswers(groupAnswers);
                    groupAnswers.clear();
                } else {
                    groupAnswers.add(line);
                }
            }
            getGroupAnswers(groupAnswers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(counts);
    }

    private static void getGroupAnswers(List<String> groupAnswers) {
        StringBuilder builder = new StringBuilder();
        String first = groupAnswers.get(0);
        if (groupAnswers.size() == 1) {
            counts += first.length();
            System.out.println(first);
        } else {
            for (String s : first.split("")) {
                boolean agreement = true;
                for (int i = 1; i < groupAnswers.size(); i++) {
                    if (!groupAnswers.get(i).contains(s)) {
                        agreement = false;
                    }
                }
                if (agreement) {
                    builder.append(s);
                }
            }
        }
        counts += builder.toString().length();
        System.out.println(builder);
    }

    private static void gatherAnswers() {
        StringBuilder builder = new StringBuilder();
        String line;
        int group = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("questionnaire.txt"))) {
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    answers.put(group, sortAnswers(builder.toString()));
                    builder = new StringBuilder();
                    group++;
                }
                builder.append(line);
            }
            group++;
            answers.put(group, sortAnswers(builder.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void presentAnswers() {
        int total = 0;
        for (int answers : answers.values()) {
            total += answers;
        }
        System.out.println(total);
    }

    private static int sortAnswers(String answers) {
        List<Character> answerList = new ArrayList<>();
        for (String s : answers.split("")) {
            if (!answerList.contains(s.charAt(0))) {
                answerList.add(s.charAt(0));
            }
        }
        return answerList.size();
    }

}
