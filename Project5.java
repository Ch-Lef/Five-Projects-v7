import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Η κλάση TheaterSeats διαχειρίζεται ένα σύστημα κρατήσεων θέσεων για ένα θέατρο.
 * Το θέατρο έχει μέχρι 30 σειρές και 12 στήλες (A-L). Οι θέσεις είναι είτε διαθέσιμες είτε κρατημένες.

 * Λειτουργίες:
 * 1. Εμφάνιση της κατάστασης των θέσεων (διαθέσιμες/κρατημένες).
 * 2. Κράτηση μιας θέσης που επιλέγεται από τον χρήστη.
 * 3. Ακύρωση κράτησης μιας θέσης.
 * 4. Έξοδος από το πρόγραμμα.

 * Ο πίνακας θέσεων αναπαριστάται ως δισδιάστατος πίνακας `boolean`:
 * - `true`: η θέση είναι κρατημένη.
 * - `false`: η θέση είναι διαθέσιμη.
 */


public class Project5 {

    // Σταθερές για τις μέγιστες στήλες και σειρές στο θέατρο
    private static final int MAX_COL = 12; // Πλήθος στηλών (Α-Λ)
    private static final int MAX_ROW = 30; // Πλήθος σειρών (1-30)

    // Πίνακας θέσεων που δείχνει αν μια θέση είναι κρατημένη (true) ή διαθέσιμη (false)
    private static final boolean[][] seats = new boolean[MAX_ROW][MAX_COL];

    // Scanner για εισαγωγή δεδομένων από τον χρήστη
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Αρχικοποιεί όλες τις θέσεις ως διαθέσιμες
        initializeSeats(); //Δεν είναι απολύτως απαραίτητο αφού οι boolean αρχικοποιούνται πάντα σε false.
        System.out.println("Welcome to the Theater Management System!");

        while (true) {
            // Εκτυπώνει το μενού επιλογών και λαμβάνει την επιλογή του χρήστη
            int menuSelection = printMenu();

            switch (menuSelection) {
                case 0:
                    // Έξοδος από το πρόγραμμα
                    System.out.println("Έξοδος...\nΑντίο!");
                    return;
                case 1:
                    // Κράτηση θέσης
                    try {
                        bookSeat();
                    } catch (Exception e) {
                        System.out.println("Σφάλμα: " + e.getMessage());
                    }
                    break;
                case 2:
                    // Ακύρωση κράτησης
                    try {
                        cancelSeat();
                    } catch (Exception e) {
                        System.out.println("Σφάλμα: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Προβολή της κατάστασης των θέσεων
                    showSeats();
                    break;
                default:
                    // Μήνυμα για μη έγκυρη επιλογή
                    System.out.println("Παρακαλώ διαλέξτε από τις διαθέσιμες επιλογές.");
            }
        }
    }

    // Εμφανίζει το μενού επιλογών και επιστρέφει την επιλογή του χρήστη
    private static int printMenu() {
        System.out.println("\nΠαρακαλώ επιλέξτε:");
        System.out.println("1. Κράτηση θέσης");
        System.out.println("2. Ακύρωση Κράτησης");
        System.out.println("3. Εμφάνιση θέσεων/κρατήσεων");
        System.out.println("0. Έξοδος");
        System.out.print("Επιλογή: ");

        try {
            // Επιστρέφει την επιλογή του χρήστη
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            // Διαχείριση μη έγκυρης εισόδου
            System.out.print("Σφάλμα! ");
            scanner.next(); // Καθαρίζει την είσοδο
            return -1;
        }
    }

    // Επιτρέπει στον χρήστη να κρατήσει μια θέση
    private static void bookSeat() throws Exception {
        int col = selectCol();
        if (col == -1) throw new Exception("Ακυρώθηκε από το χρήστη");

        int row = selectRow();
        if (row == -1) throw new Exception("Ακυρώθηκε από το χρήστη");

        if (seats[row][col]) throw new Exception("Η θέση είναι ήδη κρατημένη");

        seats[row][col] = true;
        System.out.println("Η κράτηση για την θέση: " + (char) (col + 'A') + (row + 1) + " έγινε επιτυχώς.");
    }

    // Επιτρέπει στον χρήστη να ακυρώσει μια κράτηση
    private static void cancelSeat() throws Exception {
        int col = selectCol();
        if (col == -1) throw new Exception("Ακυρώθηκε από το χρήστη");

        int row = selectRow();
        if (row == -1) throw new Exception("Ακυρώθηκε από το χρήστη");

        if (!seats[row][col]) throw new Exception("Η θέση δεν είναι κρατημένη");

        seats[row][col] = false;
        System.out.println("Η θέση: " + (char) (col + 'A') + (row + 1) + " είναι πλέον διαθέσιμη.");
    }

    // Επιλέγει τη σειρά από τον χρήστη
    private static int selectRow() {
        int rowIndex = -1;
        while (true) {
            System.out.println("Επιλογή σειράς: (1-" + MAX_ROW + ") ή 0 για ακύρωση");

            try {
                rowIndex = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Λάθος επιλογή");
                scanner.next();
                continue;
            }

            if (rowIndex == 0) return -1;

            if (rowIndex < 1 || rowIndex > MAX_ROW) {
                System.out.println("Λάθος επιλογή");
                continue;
            }

            break;
        }

        return --rowIndex; // Επιστρέφει το index της σειράς
    }

    // Επιλέγει τη στήλη από τον χρήστη
    private static int selectCol() {
        int colIndex = -1;
        String inputString;
        char colChar;
        while (true) {
            System.out.println("Διαλέξτε στήλη: (A-" + (char) (MAX_COL + 'A' - 1) + ") ή 0 για ακύρωση");

            try {
                inputString = scanner.next();
            } catch (Exception e) {
                System.out.println("Λάθος επιλογή");
                scanner.next();
                continue;
            }

            if (inputString.equals("0")) return -1;

            if (inputString.length() > 1) {
                System.out.println("Λάθος επιλογή");
                continue;
            }

            colChar = Character.toUpperCase(inputString.charAt(0));

            if (colChar < 'A' || colChar > (char) MAX_COL + 'A' - 1) {
                System.out.println("Λάθος επιλογή");
                continue;
            }

            colIndex = colChar - 'A';
            break;
        }

        return colIndex;
    }

    // Αρχικοποιεί τον πίνακα θέσεων ως όλες διαθέσιμες
    private static void initializeSeats() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                seats[i][j] = false;
            }
        }
    }

    // Εμφανίζει την κατάσταση των θέσεων στο θέατρο
    private static void showSeats() {
        System.out.print("[ ] ");
        for (int i = 0; i < MAX_ROW; i++) {
            String s = "";
            if (i < 9) s += " ";
            System.out.printf("[%2d] ", i + 1);
        }
        System.out.println();

        for (int i = 0; i < MAX_COL; i++) {
            System.out.print("[" + (char) ('A' + i) + "] ");
            for (int j = 0; j < MAX_ROW; j++) {
                if (seats[j][i]) {
                    System.out.print("[ΜΔ] "); // Θέση κρατημένη (Μη Διαθέσιμη)
                } else {
                    System.out.print("[  ] "); // Θέση διαθέσιμη
                }
                if (j == MAX_ROW - 1) System.out.println();
            }
        }
    }
}
