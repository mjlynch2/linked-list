import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Random;

public class LinkedListTests {
    private LinkedList<String> listOf(String... args) {
        LinkedList<String> list = new MikeLinkedList<>(); // Replace this with your linked list name
        for (int i = args.length - 1; i >= 0; i--) {
            list.insert(0, args[i]);
        }
        return list;
    }

    @Test
    public void test_insertAddsToEmptyList() {
        LinkedList<String> list = listOf();
        assertEquals(0, list.size());
        list.insert(0, "a");
        assertEquals(1, list.size());
        assertEquals("a", list.get(0));
    }

    @Test
    public void test_insertThenRemoveSeveralItemsFromFront() {
        LinkedList<String> list = listOf();
        for (char c : "abcdefg".toCharArray()) {
            list.append(String.valueOf(c));
        }
        for (char c : "abcdefg".toCharArray()) {
            assertEquals(String.valueOf(c), list.remove(0));
        }
    }

    @Test
    public void test_insertThenRemoveSeveralItemsFromBack() {
        LinkedList<String> list = listOf();
        for (char c : "abcdefg".toCharArray()) {
            list.append(String.valueOf(c));
        }
        for (char c : "gfedcba".toCharArray()) {
            assertEquals(String.valueOf(c), list.remove(list.size() - 1));
        }
    }

    @Test
    public void test_itDoesNotCrashAfter100RandomOperations() {
        int maxListSize = 0;
        LinkedList<String> list = listOf();
        for (int i = 0; i < 1000; i++) {
            ListOperation.makeRandomOp(list.size()).applyToList(list);
            maxListSize = Math.max(maxListSize, list.size());
        }
        System.out.println(maxListSize);
    }

    static class ListOperation {
        private static Random random = new Random();
        private String op; // I'd use an enum here if this wasn't a test for learning stuff
        private int position;
        private static int nextStringAsInt = 0;
        public ListOperation(String op, int position) {
            this.op = op;
            this.position = position;
        }

        public static ListOperation makeRandomOp(int currListSize) {
            if (currListSize == 0) { // can only insert at 0
                return new ListOperation("insert", 0);
            } else {
                String op = new String[]{"insert", "remove"}[random.nextInt(2)];
                int position = random.nextInt(currListSize);
                return new ListOperation(op, position);
            }
        }

        public static String nextValue() {
            String nextString = "";
            int asInt = nextStringAsInt;
            while (asInt >= 26) {
                // You almost want to use a stringbuilder in a loop, but
                // this isn't going to be more than 2 or 3 characters so it doesn't matter
                nextString = String.valueOf((char) ('a' + (asInt % 26))) + nextString;
                asInt /= 26; // This is basically pulling a base-26 bit out of the asInt integer
                // then bitshifting it 1 base-26
            }
            nextString = String.valueOf((char) ('a' + (asInt))) + nextString;
            nextStringAsInt++;
            return nextString;
        }

        public void applyToList(LinkedList<String> list) {
            if (op.equals("insert")) {
                list.insert(position, nextValue());
            } else {
                list.remove(position);
            }
        }
    }

}
