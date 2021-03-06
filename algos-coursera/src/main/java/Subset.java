import java.util.Iterator;

/**
 * @author aks
 * @since 16/08/15
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        Iterator<String> itr = queue.iterator();
        for (int i = 0; i < k; i++) {
            System.out.println(itr.next());
        }
    }
}
