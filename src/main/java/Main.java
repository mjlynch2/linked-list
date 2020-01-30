public class Main {

    public static void main(String[] args) {
        MikeLinkedList<String> list = new MikeLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.insert(i, String.valueOf((char) (i + 'a')));
        }
        for (int i = 9; i >= 0; i--) {
            list.remove(i);
        }
    }
}
