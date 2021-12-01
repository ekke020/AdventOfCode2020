package advent;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class ChallengeSeven {
    private static List<Bag> bags = new ArrayList<>();
    private static List<Bag> outerBags = new ArrayList<>();
    private static List<String> innerBagsRaw = new ArrayList<>();
    private static List<String[]> rawData = new ArrayList<>();
    private static boolean hasGold = false;
    private static int goldCount = 0;

    public static void main(String[] args) {
        String line;
        // answer == 193.
        try(BufferedReader br = new BufferedReader(new FileReader("bagsTest.txt"))) {
            while ((line = br.readLine()) != null) {
                addBagToList(operateLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String[] data : rawData) {
            Bag primaryBag = findBag(data[0]);
            for (int i = 1; i < data.length; i++) {
                Bag innerBag = findBag(data[i].trim());
                if (innerBag != null)
                    primaryBag.addToInnerBags(innerBag);
            }
        }
//        for (Bag bag : bags) {
//            System.out.println(bag.getBagName());
//            for (Bag innerBag : bag.getInnerBags()) {
//                System.out.println("\t" + innerBag.getBagName());
//            }
//        }
//        System.out.println(Arrays.toString(s.split(",")));
//        bags.sort((o1, o2) -> o2.getInnerBags().size() - o1.getInnerBags().size());
        boolean outermostBag;
        for (Bag bag : bags) {
            outermostBag = true;
            for (Bag bag1 : bags) {
                for (Bag innerBag : bag1.getInnerBags()) {
                   if (innerBag.getBagName().equals(bag.getBagName())) {
                       outermostBag = false;
                   }
                }
            }
            if (outermostBag){
                outerBags.add(bag);
            }
        }
//        for (Bag bag : bags) {
//            hasGold = false;
//            checkBagTest(bag);
//            if (hasGold)
//                goldCount++;
//        }
        for (Bag bag : bags) {
            hasGold = false;
            checkBagTest(bag);
            if (hasGold)
                goldCount++;
        }


//        checkBagTest(bags.get(5));
//        checkBagTest(bags.get(6));

//        for (Bag bag : outerBags) {
//            hasGold = false;
//            checkIfBagCanContainGold(bag);
//            if (hasGold)
//                goldCount++;
//        }
//        for (Bag bag : bags) {
//            if (bag.isHoldingGold())
//                goldCount++;
//        }
//        System.out.println(outerBags.size());
        System.out.println(goldCount);
    }

    private static void checkBagTest(Bag bag) {
//        System.out.println(bag.getBagName());
        if (bag.isHoldingGold()) {
            hasGold = true;
            return;
//            System.out.println(bag.getBagName() + ": True");
        }
        else if (!hasGold) {
            for (Bag innerBag : bag.getInnerBags()) {
                checkBagTest(innerBag);
//            System.out.println("\t" + innerBag.getBagName());
            }
        }
    }

    private static void checkIfBagCanContainGold(Bag bag) {
        for (Bag innerBag : bag.getInnerBags()) {
            if (innerBag.isGoldBag()) {
                hasGold = true;
            }
            checkIfBagCanContainGold(innerBag);
        }
    }

    //    private static void searchBags(Bag bag) {
//        if (bag.isContainingGold()) {
//            hasGold = true;
//        } else {
//            for (String innerBag : bag.getInnerBags()) {
//                searchBags(findBag(innerBag));
//            }
//        }
//    }
    private static String operateLine(String line) {
        Pattern p= Pattern.compile("(contain)|[0-9]|[,.]");
        line = line.replaceAll(p.pattern(), "");
        line = line.replaceAll("bags", "bag");
        rawData.add(line.split("  "));
        return line;
    }

    private static void addBagToList(String line) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < line.split("  ").length; i++) {
            builder.append(line.split("  ")[i]).append(" ");
        }
        String s = builder.toString().trim();
        String[] innerBags = s.split("bag|bags");
        builder = new StringBuilder();
        for (String innerBag : innerBags) {
            innerBag = innerBag.trim();
            builder.append(innerBag).append(",");
        }
        innerBagsRaw.add(builder.toString());
        bags.add(new Bag(line.split("  ")[0]));
    }

    private static Bag findBag(String bag) {
        for (Bag bag1 : bags) {
            if (bag1.getBagName().equals(bag)) {
                return bag1;
            }
        }
        return null;
    }
}

class Bag {

    private String bagName;

    private List<Bag> innerBags = new ArrayList<>();
    private boolean goldBag = false;
    private boolean holdingGold = false;

    public boolean isHoldingGold() {
        return holdingGold;
    }

    public String getBagName() {
        return bagName;
    }

    public void addToInnerBags(Bag bag) {
        if (bag.isGoldBag())
            holdingGold = true;
        innerBags.add(bag);
    }

    public List<Bag> getInnerBags() {
        return innerBags;
    }

    public boolean isGoldBag() {
        return goldBag;
    }

    public Bag(String bagName){
        this.bagName = bagName.replace("bags", "").trim();
        if (bagName.contains("gold"))
            goldBag = true;
    }


//    private void setupBag(String line) {
//        List<String> tempLis = new ArrayList<>(List.of(line.split("  ")));
//        bagName = tempLis.get(0);
//        tempLis.remove(0);
//        tempLis.replaceAll(String::trim);
//        if (tempLis.get(0).equals("no other bag")) {
//            tempLis.remove(0);
//        }
//        innerBags = tempLis;
//        for (String innerBag : innerBags) {
//            System.out.print(innerBag);
//            if (innerBag.contains("gold")) {
//                System.out.print(" : true\n");
//                containingGold = true;
//                break;
//            }
//            System.out.println();
//        }
//    }

}
