package Autocomplete;

import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String query;
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return (term1, term2) -> Long.compare(term2.weight, term1.weight);
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return (term1, term2) -> {
            int curr = 0;
            do {
                if (curr == term1.query.length()) {
                    if (curr == term2.query.length()) return 0;
                    return -1;
                } else if (curr == term2.query.length()) return 1;
                if (term1.query.charAt(curr) > term2.query.charAt(curr)) return 1;
                else if (term1.query.charAt(curr) < term2.query.charAt(curr)) return -1;
            } while (curr++ < r);
            return 0;
        };
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return weight + "\t" + query;
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("asdf".compareTo("asd"));
    }

}