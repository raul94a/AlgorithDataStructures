package trees;

public class main {
    public static void main(String[] args) {
        var binaryTree = new LinkedBinaryTree<Integer>();


        binaryTree.addRoot(1);


        var node = binaryTree.root;


//        var rightNode = new LinkedBinaryTree.Node<>(3, node, null, null);
//        rightNode.setRight(new LinkedBinaryTree.Node<>(19,rightNode,null,null));
//        rightNode.setLeft(new LinkedBinaryTree.Node<>(88,rightNode,null,null));
//        rightNode.getLeft().setRight(new LinkedBinaryTree.Node<>(100, rightNode.getLeft(),null, null));
//        rightNode.getLeft().setLeft(new LinkedBinaryTree.Node<>(200, rightNode.getLeft(),null, null));
//        node.setRight(rightNode);

        var leftNode = new LinkedBinaryTree.Node<>(60, node, null, null);
        leftNode.setRight(new LinkedBinaryTree.Node<>(70,leftNode,null,null));
        leftNode.setLeft(new LinkedBinaryTree.Node<>(61,leftNode,null,null));
        leftNode.getLeft().setRight(new LinkedBinaryTree.Node<>(68, leftNode.getLeft(),null, null));
        leftNode.getLeft().setLeft(new LinkedBinaryTree.Node<>(59, leftNode.getLeft(),null, null));
        node.setLeft(leftNode);



        var positions = binaryTree.preorder();

        binaryTree.printParanthetic();

        binaryTree.printDraw();


    }
}
