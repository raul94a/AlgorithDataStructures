package trees;

import java.util.concurrent.ArrayBlockingQueue;

public class main {
    public static void main(String[] args) {
        var binaryTree = new LinkedBinaryTree<Integer>();


        binaryTree.addRoot(0);


        var node = binaryTree.root;


        var rightNode = new LinkedBinaryTree.Node<>(10, node, null, null);
        rightNode.setRight(new LinkedBinaryTree.Node<>(9,rightNode,null,null));
        rightNode.setLeft(new LinkedBinaryTree.Node<>(8,rightNode,null,null));
        rightNode.getLeft().setRight(new LinkedBinaryTree.Node<>(7, rightNode.getLeft(),null, null));
        rightNode.getLeft().setLeft(new LinkedBinaryTree.Node<>(6, rightNode.getLeft(),null, null));
        node.setRight(rightNode);

        var leftNode = new LinkedBinaryTree.Node<>(5, node, null, null);
        leftNode.setRight(new LinkedBinaryTree.Node<>(4,leftNode,null,null));
        leftNode.setLeft(new LinkedBinaryTree.Node<>(3,leftNode,null,null));
        leftNode.getLeft().setRight(new LinkedBinaryTree.Node<>(2, leftNode.getLeft(),null, null));
        leftNode.getLeft().setLeft(new LinkedBinaryTree.Node<>(1, leftNode.getLeft(),null, null));
        node.setLeft(leftNode);



        var positions = binaryTree.preorder();

        binaryTree.printParanthetic();

        binaryTree.printDraw();

        var inOrderResult = binaryTree.inOrderPositions();
        System.out.println(inOrderResult);


    }
}
