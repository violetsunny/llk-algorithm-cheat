/**
 * LY.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package top.kexcellent.back.code;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 前序遍历、中序遍历、后序遍历和层序遍历  广度优先搜索（BFS） 深度优先搜索（DFS）
 * @author kanglele
 * @version $Id: BinaryTreeTraversal, v 0.1 2024/10/16 下午4:15 kanglele Exp $
 */
public class BinaryTreeTraversal {

    /**
     * 前序遍历
     * @param root
     */
    public void preOrderTraversal(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inOrderTraversal(TreeNode root) {
        if (root == null) return;
        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }

    /**
     * 后序遍历
     * @param root
     */
    public void postOrderTraversal(TreeNode root) {
        if (root == null) return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 层序遍历
     * @param root
     */
    public void levelOrderTraversal(TreeNode root) {
        if (root == null) {return;}
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
    /**
     * 二叉树的S形层次遍历
     */
    public static void slOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode ptr = root;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(ptr);
        while (!stack1.isEmpty()) {
            while (!stack1.isEmpty()) {
                ptr = stack1.pop();
                System.out.print(ptr.val + " ");
                if (ptr.left != null) {
                    stack2.push(ptr.left);
                }
                if (ptr.right != null) {
                    stack2.push(ptr.right);
                }
            }
            while (!stack2.isEmpty()) {
                ptr = stack2.pop();
                System.out.print(ptr.val + " ");
                if (ptr.right != null) {
                    stack1.push(ptr.right);
                }
                if (ptr.left != null) {
                    stack1.push(ptr.left);
                }
            }
        }
    }

    /**
     * 广度优先搜索（BFS）,时间复杂度：O(n),空间复杂度：O(1)-O(w)，其中 w 是树的最大宽度
     * @param root
     */
    public void breadthFirstSearch(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    /**
     * 深度优先搜索（DFS）,时间复杂度：O(n),空间复杂度：O(h)，其中 h 是树的高度
     *
     * @param root
     */
    public void depthFirstSearch(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        depthFirstSearch(root.left);
        depthFirstSearch(root.right);
    }

    /**
     * 时间复杂度：O(n),空间复杂度：O(w)，其中 w 是树的最大宽度
     *
     * @param root
     */
    public void depthFirstSearchIterative(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.print(current.val + " ");
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
