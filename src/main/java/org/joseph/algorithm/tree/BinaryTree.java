package org.joseph.algorithm.tree;

import org.joseph.algorithm.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class BinaryTree<T> {

    public TreeNode createTree(List<Integer> list) {
        List<TreeNode> nodeList = list.stream().map(TreeNode::new).collect(Collectors.toList());
        TreeNode root = nodeList.get(0);
        for (int i = 0; i < nodeList.size() / 2; i++) {
            nodeList.get(i).left = nodeList.get(i * 2 + 1);
            if (i * 2 + 2 < nodeList.size()) {
                nodeList.get(i).right = nodeList.get(i * 2 + 2);
            }
        }
        return root;
    }


    /****************** 递归实现 ********************/

    /**
     * 前序遍历
     */
    private void preOrder(TreeNode node) {
        if(null != node) System.out.println(node.getVal());
        if(null != node.left) preOrder(node.left);
        if(null != node.right) preOrder(node.right);
    }

    /**
     * 中序遍历
     */
    private void inOrder(TreeNode node) {
        if(null != node.left) inOrder(node.left);
        if(null != node) System.out.println(node.getVal());
        if(null != node.right) inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    private void postOrder(TreeNode node) {
        if(null != node.left) postOrder(node.left);
        if(null != node.right) postOrder(node.right);
        if(null != node) System.out.println(node.getVal());
    }


    /**
     * 层序遍历
     */
    public void levelorder(TreeNode node){
        if(null != node){
            LinkedList<TreeNode> list = new LinkedList<TreeNode>();
            list.add(node);
            TreeNode currentNode;
            while (!list.isEmpty()){
                currentNode = list.poll();
                System.out.print(currentNode.val);
                if(null != currentNode.left){
                    list.add(currentNode.left);
                }
                if(null != currentNode.right){
                    list.add(currentNode.right);
                }
            }
        }
    }



    /****************** 非递归实现 ********************/

    /**
     * 前序遍历
     */
    private void preOrder1(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (null != node || !stack.isEmpty()) {
            while (null != node) {
                System.out.println(node.getVal());
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }


    /**
     * 中序遍历
     */
    private void inOrder1(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (null != node || !stack.isEmpty()) {
            while (null != node) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println(node.getVal());
                node = node.right;
            }
        }
    }


    /**
     * 后序遍历
     */
    private void postOrder1(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (null != node || !stack.isEmpty()) {
            while (null != node) {
                stack.push(node);
                node = node.left;
            }

            boolean tag = true;
            TreeNode preNode = null;  // 前驱节点
            while (!stack.isEmpty() && tag == true) {
                node = stack.peek();
                if (node.right == preNode) { // 之前访问的为空节点或是栈顶节点的右子节点
                    node = stack.pop();
                    System.out.println(node.getVal());
                    if (stack.isEmpty()) {
                        return;
                    } else {
                        preNode = node;
                    }
                } else {
                    node = node.right;
                    tag = false;
                }
            }
        }
    }

}