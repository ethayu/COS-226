package Autocomplete;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Autocomplete {
    private Term[] terms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new IllegalArgumentException();
        for (Term term : terms) {
            if (term == null) throw new IllegalArgumentException();
            else this.terms = terms;
        }
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException();
        int first = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        if (first == -1) return null;
        Term[] matches = new Term[BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length())) - first + 1];
        System.arraycopy(terms, first, matches, 0, matches.length);
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        int first = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        return first == -1 ? 0 : BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length())) - first + 1;
    }

    // unit testing (required)
    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        Scanner sc = new Scanner(new File(filename));
        Term[] terms = new Term[sc.nextInt()];
        for (int i = 0; i < terms.length; i++) {
            long temp = sc.nextLong();
            terms[i] = new Term(sc.next(), temp);
        }

        Autocomplete autocomplete = new Autocomplete(terms);
        while (sc.hasNextLine()); {
            String line = sc.nextLine();
            Term[] matches = autocomplete.allMatches(line);
            System.out.printf("%d matches\n", autocomplete.numberOfMatches(line));
            for (int i = 0; i < k && i < matches.length; i++) System.out.println(matches[i]);
        }

    }

}