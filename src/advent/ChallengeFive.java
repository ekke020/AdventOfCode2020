package advent;


import java.io.File;
import java.io.IOException;
import java.util.*;

public class ChallengeFive {

    private static final List<Integer> seatIDs = new ArrayList<>();
    private static final HashMap<String, int[]> SEATS = new HashMap<>();
    private static int highestSeatID = 0;

    public static void main(String[] args) {
        List<String> boardingPasses = new ArrayList<>();
        GetSeatNumber seatNumber;

        try (Scanner scanner = new Scanner(new File("boardingPasses.txt"))) {
            while(scanner.hasNextLine()) {
                boardingPasses.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String boardingPass : boardingPasses) {
            seatNumber = new GetSeatNumber(boardingPass);
            SEATS.put(boardingPass,seatNumber.getSeatNumber());
            setHighestSeatID(boardingPass);
        }
        System.out.println(highestSeatID);
        System.out.println(findMySeat());
    }

    private static void setHighestSeatID(String boardingPass) {
        int[] seat = SEATS.get(boardingPass);
        int seatID = (seat[0] * 8) + seat[1];
        seatIDs.add(seatID);
        if (seatID > highestSeatID)
            highestSeatID = seatID;
    }

    private static int findMySeat() {
        Collections.sort(seatIDs);
        for (int i = 0; i < seatIDs.size(); i++) {
            if (Math.abs(seatIDs.get(i) - seatIDs.get(i + 1)) != 1)
                return seatIDs.get(i) + 1;
        }
        return 0;
    }
}

class GetSeatNumber {

    private final String boardingPassRow;
    private final String boardingPassColumn;
    private final int[] seatNumber = new int[2];
    private List<Integer> rows = new ArrayList<>();
    private List<Integer> column = new ArrayList<>();

    public GetSeatNumber(String boardingPass) {
        boardingPassRow = boardingPass.substring(0,7);
        boardingPassColumn = boardingPass.substring(7);
        for (int i = 0; i < 128; i++) {
            rows.add(i);
        }
        for (int i = 0; i < 8; i++) {
            column.add(i);
        }
    }

    public int[] getSeatNumber() {
        seatNumber[0] = getRowNumber();
        seatNumber[1] = getColumnNumber();
        return seatNumber;
    }
    private int getRowNumber() {
        for (String s : boardingPassRow.split("")) {
            getDirection(s, rows);
        }
        return rows.get(0);
    }
    private int getColumnNumber() {
        for (String s : boardingPassColumn.split("")) {
            getDirection(s, column);
        }
        return column.get(0);
    }
    private void getDirection(String direction, List<Integer> list) {
        List<Integer> head = list.subList(0, list.size() / 2);
        List<Integer> tail = list.subList(list.size() / 2, list.size());
        switch (direction) {
            case "F" -> rows = head;
            case "B" -> rows = tail;
            case "R" -> column = tail;
            case "L" -> column = head;
        }
    }

}