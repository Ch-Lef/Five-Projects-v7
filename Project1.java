import java.io.*;
import java.util.Arrays;

public class Project1 {
    static String file = "src/files/lotto.txt";
    static int[] inputNumbers = new int[49];
    static int indexOfInputNumbers = 0;

    public static void main(String[] args) {
        final int LOTTO_SIZE = 6;
        int[] inputNumbers = new int[49];
        int pivot = 0;
        int[] result = new int[LOTTO_SIZE];
        int num;
        int window;



        try {
            readInputNumbersFromFile();
        } catch (Exception e) {
            System.err.println("Το πρόγραμμα θα τερματίσει...");
            System.exit(1);
        //            throw new RuntimeException(e);
        }

        Arrays.sort(inputNumbers);

        for (int el : inputNumbers) {
            System.out.print(el + " ");
        }

    }

    static void readInputNumbersFromFile() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");

                for (String token : tokens) {
                    if (!token.isEmpty()) { // Ensure no empty tokens are parsed
                        inputNumbers[indexOfInputNumbers++] = Integer.parseInt(token);
                    }

                }
                if (indexOfInputNumbers > inputNumbers.length) {
                    // Αποφυγή υπερχείλισης του πίνακα
                    throw new Exception("Το αρχείο περιέχει παραπάνω από 49 ακεραίους.");
                }

            }
        } catch (Exception e) {
            System.err.println("Σφάλμα στο αρχείο ακεραίων: "+file+"\n"+e.getMessage());
            throw e;
        }
    }

    boolean isEven(int[] arr, int max) {
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

    boolean isOdd(int[] arr, int max) {
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

    boolean isContinuous(int[] arr, int max) {
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

    boolean isSameEnding(int[] arr, int max) {
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

    boolean isSameTen(int[] arr, int max) {
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


