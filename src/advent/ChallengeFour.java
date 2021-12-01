package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChallengeFour {

    private static final List<HashMap<String, String>> HASH_MAP_LIST = new ArrayList<>();
    private static int count = 0;
    public static void main(String[] args) {
        String line;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("passports.txt"))) {
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    divideLine(builder.toString());
                    builder = new StringBuilder();
                } else {
                    builder.append(line).append(" ");
                }
            }
            divideLine(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (HashMap<String, String> stringStringHashMap : HASH_MAP_LIST) {
            if (isPassportValid(stringStringHashMap))
                if (isPassportFieldsValid(stringStringHashMap))
                    count++;
        }
        System.out.println(count);
    }
    private static void divideLine(String line) {
        HashMap<String, String> hashMap = new HashMap<>();
        line = line.trim();
        String[] keyValuePairs = line.split(" ");
        for (String keyValuePair : keyValuePairs) {
            String[] pair = keyValuePair.split(":");
            hashMap.put(pair[0], pair[1]);
        }
        HASH_MAP_LIST.add(hashMap);
    }
    private static boolean isPassportValid(HashMap<String, String> hashMap) {
        if (hashMap.size() == 8) {
            return true;
        }
        else return hashMap.size() == 7 && !hashMap.containsKey("cid");
    }

    private static boolean isPassportFieldsValid(HashMap<String, String> hashMap) {
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0 -> {
                    if(!checkBirthYear(hashMap.get("byr")))
                        return false;
                }
                case 1 -> {
                    if(!checkExpirationYear(hashMap.get("eyr")))
                        return false;
                }
                case 2 -> {
                    if(!checkHeight(hashMap.get("hgt")))
                        return false;
                }
                case 3 -> {
                    if(!checkHairColor(hashMap.get("hcl")))
                        return false;
                }
                case 4 -> {
                    if(!checkEyeColor(hashMap.get("ecl")))
                        return false;
                }
                case 5 -> {
                    if(!checkPassportID(hashMap.get("pid")))
                        return false;
                }
                case 6 -> {
                    if(!checkIssueYear(hashMap.get("iyr")))
                        return false;
                }
            }
        }
        return true;
    }

    private static boolean checkBirthYear(String field) {
        if (field.length() != 4)
            return false;
        int year = Integer.parseInt(field);
        return year <= 2002 && year >= 1920;
    }

    private static boolean checkIssueYear(String field) {
        if (field.length() != 4)
            return false;
        int year = Integer.parseInt(field);
        return year <= 2020 && year >= 2010;
    }

    private static boolean checkExpirationYear(String field) {
        if (field.length() != 4)
            return false;
        int year = Integer.parseInt(field);
        return year <= 2030 && year >= 2020;
    }

    private static boolean checkHeight(String field) {
        int length = 0;
        String system = "";
        for (int i = 0; i < field.length(); i++) {
            if (!Character.isDigit(field.charAt(i))) {
                length = Integer.parseInt(field.substring(0, i - 1));
                system = field.substring(i - 1);
            }
        }
        if (system.equals("cm")) {
            return length >= 150 && length <= 193;
        } else {
            return length >= 59 && length <= 76;
        }
    }

    private static boolean checkHairColor(String field) {
        if (field.length() != 7) {
            return false;
        }
        return field.matches("[#][a-f0-9]*");
    }

    private static boolean checkEyeColor(String field) {
        return switch (field) {
            case "amb", "blu", "brn", "gry", "grn", "hzl", "oth" -> true;
            default -> false;
        };
    }

    private static boolean checkPassportID(String field) {
        if (field.length() != 9)
            return false;

        return field.matches("[0-9]*");
    }
}
