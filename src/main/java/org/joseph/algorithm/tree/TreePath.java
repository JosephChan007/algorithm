package org.joseph.algorithm.tree;

import org.joseph.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * 打印树的根节点到所有叶子节点的路径
 */
public class TreePath {

    public List<String> getPathToLeaf(TreeNode root) {
        List<String> list = new ArrayList<>();
        printPath(root, list, "");
        return list;
    }

    public void printPath(TreeNode node, List<String> list, String s) {
        if(null == node) return;

        if (null == node.left && null == node.right) {
            list.add(s + node.val);
            return;
        }

        if (null != node.left) {
            printPath(node.left, list, s + node.val + "->");
        }

        if (null != node.right) {
            printPath(node.right, list, s + node.val + "->");
        }

    }


}
