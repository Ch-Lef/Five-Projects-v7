import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Project1 {

    public static void main(String[] args) {

        File file = new File("input.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private boolean isEven(int[] arr, int max) {
        int count = 0;
        for (int v : arr) {
            if (v % 2 == 0) {
                count++;
            }
            if (count > max) {
                return true;
            }
        }
        return false;
    }

    private boolean isOdd(int[] arr, int max) {
        int count = 0;
        for (int v : arr) {
            if (v % 2 != 0) {
                count++;
            }
            if (count > max) {
                return true;
            }
        }
        return false;
    }

    private boolean isContinuous(int[] arr, int max) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] + 1 == arr[i + 1]) {
                count++;
            }
            if (count > max) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameEnding(int[] arr, int max) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                count++;
            }
            if (count > max) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameTen(int[] arr, int max) {
        int count10 = 0;
        int count20 = 0;
        int count30 = 0;
        int count40 = 0;
        int count50 = 0;
        for (int el : arr) {
            if (el % 10 == 0) {
                count10++;
            }
            if (el % 20 == 0) {
                count20++;
            }
            if (el % 30 == 0) {
                count30++;
            }
            if (el % 40 == 0) {
                count40++;
            }
            if (el % 50 == 0) {
                count50++;
            }
            if (count10 > max || count20 > max || count30 > max || count40 > max || count50 > max) {
                return true;
            }
        }
        return false;
    }


}


