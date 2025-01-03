/*
 * Η κλάση Project2 περιλαμβάνει την υλοποίηση του αλγορίθμου Kadane για την εύρεση του μέγιστου αθροίσματος υποπίνακα.
 * Ο αλγόριθμος αυτός λύνει το πρόβλημα του μέγιστου αθροίσματος συνεχόμενων στοιχείων σε έναν πίνακα με γραμμική πολυπλοκότητα O(n).
 */
public class Project2 {
    static int[] arr = {2, -3, 2, -3, 1, 1, 2, 5, -7}; // Ο πίνακας που θα ελεγχθεί για το μέγιστο άθροισμα
    static int[] subArrayIndex = {0, 0};                // Ο πίνακας που θα περιέχει τους δείκτες του υποπίνακα με το μέγιστο άθροισμα

    public static void main(String[] args) {
        // Αρχικοποίηση του πίνακα με αρνητικά αριθμητικά στοιχεία

        try {
            // Εκτύπωση του μέγιστου αθροίσματος υποπίνακα χρησιμοποιώντας τη μέθοδο maxSubArray
            System.out.println("Το μέγιστο άθροισμα υποπίνακα: " + maxSubArray(arr));
            System.out.println("Προέρχεται από τον:");

            // Εκτύπωση των στοιχείων του υποπίνακα με το μέγιστο άθροισμα
            for (int i = subArrayIndex[0]; i <= subArrayIndex[1]; i++) {
                System.out.print(arr[i]);
                if (i < subArrayIndex[1]) {
                    System.out.print(", ");
                }
            }

        } catch (Exception e) {
            // Εκτύπωση του σφάλματος σε περίπτωση που ο πίνακας είναι κενός ή άλλου σφάλματος
            System.err.println(e.getMessage());
        }
    }

    /*
     * Η μέθοδος maxSubArray υπολογίζει το μέγιστο άθροισμα συνεχόμενων στοιχείων σε έναν πίνακα με χρήση του αλγορίθμου Kadane.
     * Αν ο πίνακας είναι κενός ή null, ρίχνει εξαίρεση.
     */
    static int maxSubArray(int[] arr) throws Exception {
        // Έλεγχος για κενό ή μη έγκυρο πίνακα
        if (arr == null || arr.length == 0) {
            throw new Exception("Κενός πίνακας.");  // Ρίχνει εξαίρεση αν ο πίνακας είναι κενός
        }

        // Αρχικοποίηση των μεταβλητών:
        // maxSoFar: Το μέγιστο άθροισμα υποπίνακα μέχρι τώρα
        int maxSoFar = arr[0];
        // totalMax: Το συνολικό μέγιστο άθροισμα
        int totalMax = arr[0];
        // end: Δείκτης τέλους του υποπίνακα
        int end = 0;
        // tempStart: Προσωρινός δείκτης αρχής του υποπίνακα
        int tempStart = 0;

        // Διατρέχουμε τον πίνακα ξεκινώντας από το δεύτερο στοιχείο
        for (int i = 1; i < arr.length; i++) {
            // Ενημέρωση του maxSoFar: Αν είναι καλύτερο να ξεκινήσουμε νέο υποπίνακα ή να προσθέσουμε στο προηγούμενο
            if (arr[i] > maxSoFar + arr[i]) {
                maxSoFar = arr[i];  // Ξεκινάμε νέο υποπίνακα από το τρέχον στοιχείο
                tempStart = i;      // Ορίζουμε την αρχή του νέου υποπίνακα
            } else {
                maxSoFar = maxSoFar + arr[i];  // Συνεχίζουμε τον υπάρχοντα υποπίνακα
            }

            // Αν το maxSoFar είναι μεγαλύτερο από το συνολικό μέγιστο (totalMax), ενημερώνουμε το totalMax
            if (maxSoFar > totalMax) {
                totalMax = maxSoFar;  // Ενημέρωση του συνολικού μέγιστου αθροίσματος
                subArrayIndex[0] = tempStart;  // Ενημέρωση του δείκτη αρχής του υποπίνακα
                subArrayIndex[1] = i;          // Ενημέρωση του δείκτη τέλους του υποπίνακα
            }
        }

        // Επιστρέφουμε το μέγιστο άθροισμα και τους δείκτες του υποπίνακα
        return totalMax;
    }
}
