package org.joseph.algorithm.tree;

import java.util.LinkedList;

public class NodeDistance {

    // 两节点最大距离
    private int maxDistance;

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        int leftLen;
        int rightLen;

        TreeNode(int val) {
            this.val = val;
        }
    }


    /**
     * 1. 返回值为树的最大深度
     * 2. 遍历时计算树的最大节点距离maxDistance
     */
    public int maxLen(TreeNode root) {

        if(null == root) return 0;

        if (null != root.left) {
            root.leftLen = maxLen(root.left) + 1;
        } else {
            root.leftLen = 0;
        }

        if (null != root.right) {
            root.rightLen = maxLen(root.right) + 1;
        } else {
            root.rightLen = 0;
        }

        maxDistance = Integer.max(root.leftLen + root.rightLen, maxDistance);

        return root.rightLen > root.leftLen ? root.rightLen : root.leftLen;
    }

    /**
     * 树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if(null == root) return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return Integer.max(l, r) + 1;
    }

    /**
     * 树的最小深度
     */
    public int minDepth(TreeNode root) {
        if(null == root) return 0;
        int l = minDepth(root.left);
        int r = minDepth(root.right);
        return Integer.min(l, r) + 1;
    }

    /**
     * 树的最大宽度
     */
    public int widthOfTree(TreeNode root) {
        if(null == root) return 0;

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        int width = 1, len = 1;

        while (list.size() > 0) {
            while (len > 0) {
                TreeNode node = list.poll();
                if(null != node.left) list.add(node.left);
                if(null != node.right) list.add(node.right);
                len--;
            }
            len = list.size();
            width = Integer.max(len, width);
        }
        return width;
    }


}
