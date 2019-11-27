package org.joseph.algorithm.tree;

import org.joseph.algorithm.common.TreeNode;

public class TreeMath {

    /**
     * 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == root || q == root) return root;

        TreeNode left = lowestCommonAncestor(root.getLeft(), p, q);
        TreeNode right = lowestCommonAncestor(root.getRight(), p, q);

        if(left != null && right != null) return root;
        return left == null ? right : left;
    }

}
