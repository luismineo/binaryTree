public class Main {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        bt.insert(5);
        bt.insert(7);
        bt.insert(6);
        bt.insert(3);
        bt.insert(18);
        bt.insert(2);
        bt.insert(11);
        bt.insert(20);
        bt.insert(19);
        bt.insert(24);
        bt.insert(21);
        bt.insert(25);
        bt.insert(4);
        bt.insert(1);

        System.out.println("pre-order traversal:");
        bt.preOrder(bt.getRoot());

        bt.remove(7);

        System.out.println("pre-order traversal:");
        bt.preOrder(bt.getRoot());
    }
}