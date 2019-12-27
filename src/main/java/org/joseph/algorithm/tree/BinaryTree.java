package org.joseph.algorithm.tree;

import org.joseph.algorithm.common.BinaryTreeNode;

import java.util.Iterator;
import java.util.List;

public class BinaryTree<T> {

    private BinaryTreeNode<T> root;

    public void createBiTree(List<T> list) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            BinaryTreeNode<T> node = new BinaryTreeNode<T>();
            node.setData(iterator.next());
            insertTree(node);
        }
    }

    public void insertTree(BinaryTreeNode<T> node) {
        if (root == null) {
            root = node;
        } else {
            BinaryTreeNode<T> current = root;
            while (true) {//循环用于左右节点都有时，遍历下级节点
                if (current.leftChild == null) {
                    current.leftChild = node;
                    //跳出循环
                    return;
                } else if (root.rightChild == null) {
                    current.rightChild = node;
                    return;
                } else {
                    current = current.leftChild;
                }
            }
        }
    }





}
