package Autocomplete;

import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        int lo = 0;
        int hi = a.length - 1;
        int mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(a[mid], key);
            if (compare < 0) lo = mid + 1;
            else if (compare > 0) hi = mid - 1;
            else if (mid == 0) return mid;
            else if (comparator.compare(a[mid - 1], key) != 0) return mid;
            else hi = mid - 1;
        }
        return -1;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        int lo = 0;
        int hi = a.length - 1;
        int mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(a[mid], key);
            if (compare < 0) lo = mid + 1;
            else if (compare > 0) hi = mid - 1;
            else if (mid == 0) return mid;
            else if (comparator.compare(a[mid + 1], key) != 0) return mid;
            else lo = mid + 1;
        }
        return -1;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}