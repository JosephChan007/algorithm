package org.joseph.algorithm.tree;

import org.joseph.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeOrder {

    private List<TreeNode> result = new ArrayList<>();


    /****************** 递归实现 ********************/

    /**
     * 前序遍历
     */
    private void preOrder(TreeNode node) {
        if(null != node) result.add(node);
        if(null != node.left) preOrder(node.left);
        if(null != node.right) preOrder(node.right);
    }

    /**
     * 中序遍历
     */
    private void midOrder(TreeNode node) {
        if(null != node.left) midOrder(node.left);
        if(null != node) result.add(node);
        if(null != node.right) midOrder(node.right);
    }

    /**
     * 后序遍历
     */
    private void backOrder(TreeNode node) {
        if(null != node.left) backOrder(node.left);
        if(null != node.right) backOrder(node.right);
        if(null != node) result.add(node);
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
                result.add(node);
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
    private void midOrder1(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (null != node || !stack.isEmpty()) {
            while (null != node) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                result.add(node);
                node = node.right;
            }
        }
    }


    /**
     * 后序遍历
     */
    private void backOrder1(TreeNode node) {
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
                    result.add(node);
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
