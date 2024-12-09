package implementaciones_prueba;



public class Main {
    public static void main(String[] args) {
        var binaryTree = new LinkedBinaryTree<Integer>();


        binaryTree.addRoot(0);


        var node = binaryTree.root;


        binaryTree.addRight(node,10);
        var rightNode = node.getRight(); //10
        binaryTree.addRight(rightNode, 9);
        binaryTree.addLeft(rightNode, 8);
        var leftSubNode = rightNode.getLeft();
        binaryTree.addRight(leftSubNode, 7);
        binaryTree.addLeft(leftSubNode, 6);


        binaryTree.addLeft(node,5);
        var leftNode = node.getLeft();
        binaryTree.addRight(leftNode, 4);
        binaryTree.addLeft(leftNode, 3);
        var leftLeftSubNode = leftNode.getLeft();
        binaryTree.addRight(leftLeftSubNode, 2);
        binaryTree.addLeft(leftLeftSubNode, 1);

        System.out.println(binaryTree.preorder());
         System.out.println(binaryTree.postorder());
        System.out.println(binaryTree.breadfirst());
        System.out.println(binaryTree.inOrder());
    }
}
