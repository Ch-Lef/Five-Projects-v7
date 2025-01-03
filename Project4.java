/*
 * Η κλάση Project4 υλοποιεί το παιχνίδι της τρίλιζας. Κάθε παίχτης διαλέγει που θα τοποθετήσει το σύμβολό του.
 * Το ταμπλό του παιχνιδιού είναι μονοδιάστατος αλλά απεικονίζεται δισδιάστατος στην εκτύπωση.
 * Οι κενές θέσης του πίνακα είναι οι αριθμοί 1-9. Οι παίχτες αντιπροσωπεύονται το σύμβολό τους "Χ" ή "Ο" (turn)
 * Το παιχνίδι ξεκινάει με το σύμβολο Χ όπως αυτό είθισται.
 * Οι παίχτες παίζουν εναλλάξ διαλέγοντας μόνο από τα κενά κελιά του ταμπλό.
 * Μετά το κάθε επιτυχές παίξιμο γίνεται έλεγχος αν υπάρχει νικητής.
 */

import java.util.Scanner;

public class Project4 {
    // Δηλώνουμε αν το παιχνίδι έχει τελειώσει
    static boolean gameIsOver = false;
    // Ο πίνακας που αναπαριστά το ταμπλό της Τρίλιζας
    static char[] gameBoard = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    // Για είσοδο από τον χρήστη
    static Scanner scanner = new Scanner(System.in);
    // Ποιος παίκτης παίζει αυτή τη στιγμή ('X' ή 'O')
    static char turn = 'X';

    // Μετρητής για τις κενές θέσεις που απομένουν
    static int emptyCells = 9;

    public static void main(String[] args) {
        // Μήνυμα καλωσορίσματος για το παιχνίδι
        System.out.println("\n---------------------\nΑς παίξουμε ΤΡΙΛΙΖΑ\n---------------------");

        // Επαναλαμβάνουμε μέχρι να τελειώσει το παιχνίδι
        while (!gameIsOver) {
            printGameBoard(); // Εμφάνιση του ταμπλό
            play(turn);       // Ο παίκτης κάνει την κίνηση του
            checkGameBoard(); // Έλεγχος αν κάποιος κέρδισε ή αν το παιχνίδι έληξε
            changeTurn();     // Αλλάζει σειρά ο παίκτης
        }

        // Μήνυμα τέλους παιχνιδιού
        System.out.println("-----------------\nΤέλος Παιχνιδιού!");
    }

    // Ελέγχει αν κάποιος έχει κερδίσει ή αν το παιχνίδι έληξε
    static void checkGameBoard() {
        gameWon(); // Ελέγχει για νίκη

        // Αν κάποιος κέρδισε, εμφανίζουμε μήνυμα και το παιχνίδι τελειώνει
        if (gameIsOver) {
            printGameBoard();
            System.out.println("Νίκησε ο παίκτης: " + turn);
            return;
        }

        // Αν δεν υπάρχουν άλλες κενές θέσεις, το παιχνίδι είναι ισόπαλο
        if (emptyCells == 0) {
            printGameBoard();
            System.out.println("Το παιχνίδι είναι ισόπαλο");
            gameIsOver = true;
        }
    }

    // Εμφανίζει το ταμπλό της Τρίλιζας
    static void printGameBoard() {
        int rowLength = 3; // Ο αριθμός των κελιών ανά γραμμή
        System.out.println("_______"); // Επάνω γραμμή του ταμπλό

        for (char c : gameBoard) {
            if (rowLength == 3) System.out.print("|"); // Αρχή γραμμής
            System.out.print(c + "|"); // Εμφανίζει το κελί
            if (--rowLength == 0) {   // Αν τελείωσε η γραμμή
                System.out.println(); // Μεταβαίνει στην επόμενη γραμμή
                rowLength = 3;        // Επαναφορά του μετρητή
            }
        }
        System.out.println("-------"); // Κάτω γραμμή του ταμπλό
    }

    // Επιτρέπει στον παίκτη να επιλέξει πού θα βάλει το σύμβολό του
    static void play(char turn) {
        int cell = 0; // Η θέση που επιλέγει ο παίκτης

        // Επαναλαμβάνει μέχρι να δοθεί έγκυρη θέση
        while (cell < 1 || cell > 9) {
            System.out.println("Σε ποια θέση θα μπει το " + turn + "; (1-9)");

            try {
                cell = Integer.parseInt(scanner.nextLine().trim()); // Διαβάζει τη θέση από τον χρήστη

                if (cell < 1 || cell > 9) { // Έλεγχος αν η θέση είναι εκτός ορίων
                    System.out.println("Λάθος επιλογή");
                    continue;
                }

                if (!isEmpty(cell - 1)) { // Έλεγχος αν η θέση είναι ήδη κατειλημμένη
                    System.out.println("Η θέση έχει παιχτεί ήδη");
                    cell = 0; // Επαναφορά σε άκυρη τιμή για να ξαναζητήσει θέση
                }

            } catch (Exception e) { // Αν ο χρήστης δώσει μη έγκυρη είσοδο
                System.out.println("Λάθος επιλογή");
            }
        }

        // Τοποθέτηση του συμβόλου του παίκτη στη θέση που επέλεξε
        gameBoard[cell - 1] = turn;
        emptyCells--; // Μείωση των κενών θέσεων
    }

    // Ελέγχει αν μια θέση είναι κενή
    static boolean isEmpty(int cell) {
        return gameBoard[cell] >= '1' && gameBoard[cell] <= '9'; // Οι κενές θέσεις είναι αριθμοί
    }

    // Ελέγχει αν υπάρχει νίκη
    static void gameWon() {
        String winner = String.valueOf(turn).repeat(3); // Τρία ίδια σύμβολα στη σειρά είναι νίκη

        // Δημιουργία γραμμών, στηλών και διαγωνίων για έλεγχο νίκης
        String winRow1 = "" + gameBoard[0] + gameBoard[1] + gameBoard[2];
        String winRow2 = "" + gameBoard[3] + gameBoard[4] + gameBoard[5];
        String winRow3 = "" + gameBoard[6] + gameBoard[7] + gameBoard[8];
        String winCol1 = "" + gameBoard[0] + gameBoard[3] + gameBoard[6];
        String winCol2 = "" + gameBoard[1] + gameBoard[4] + gameBoard[7];
        String winCol3 = "" + gameBoard[2] + gameBoard[5] + gameBoard[8];
        String winDiag1 = "" + gameBoard[0] + gameBoard[4] + gameBoard[8];
        String winDiag2 = "" + gameBoard[6] + gameBoard[4] + gameBoard[2];

        // Αν κάποια από αυτές είναι ίση με το "winner", κάποιος κέρδισε
        if (winRow1.equals(winner) || winRow2.equals(winner) || winRow3.equals(winner) ||
                winCol1.equals(winner) || winCol2.equals(winner) || winCol3.equals(winner) ||
                winDiag1.equals(winner) || winDiag2.equals(winner)) {
            gameIsOver = true; // Το παιχνίδι τελειώνει
        }
    }

    // Αλλάζει σειρά στον επόμενο παίκτη
    static void changeTurn() {
        turn = (turn == 'X') ? 'O' : 'X'; // Εναλλαγή μεταξύ 'X' και 'O'
    }
}
