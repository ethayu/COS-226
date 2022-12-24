package Queues;

import java.util.Iterator;
import java.util.Scanner;

public class Permutation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(args[0]);
        System.out.print("Enter sequence of strings: ");
        String[] strings = sc.nextLine().split(" ");
        System.out.println("Printing random sequence of " + k + " elements:");
        RandomizedQueue randomizedQueue = new RandomizedQueue();
        for (String s : strings) randomizedQueue.enqueue(s);
        Iterator iterator = randomizedQueue.iterator();
        int i = 0;
        while (iterator.hasNext() && i++ < k) System.out.println(iterator.next());
    }
}
