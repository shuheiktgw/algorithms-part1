import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while(true) {
            try {
                queue.enqueue(StdIn.readString());
            } catch (NoSuchElementException e) {
                break;
            }
        }

        int n = Integer.parseInt(args[0]);
        String s = "";
        for (int i = 0; i < n; i += s.length()) {
            s = queue.dequeue();
            System.out.println(s);
        }
    }
}
