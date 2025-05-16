## [36. 二叉搜索树与双向链表](https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/)
同：[426.将二叉搜索树转化为排序是双向链表](https://leetcode.cn/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/)

### 题目描述

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。

### 解法

由于是二叉搜索树，因此中序遍历的结果就是排序的。

中序遍历利用栈来实现。遍历时，前一个结点的 right 指向后一个结点，后一个结点的 left 指向前一个结点。

```java
pre.right = cur;
cur.left = pre;
```

```java
import java.util.Stack;

/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */
public class Solution {
    /**
     * 将二叉搜索树转换为双向链表
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = pRootOfTree;
        TreeNode res = null;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre == null) {
                    pre = cur;
                    res = pre;
                } else {
                    pre.right = cur;
                    cur.left = pre;
                    pre = cur;
                }
                cur = cur.right;

            }
        }
        return res;
    }
}
```

### （记住）dfs实现中序遍历(inorder) - 帅地实现

````java
import java.util.*;

class Solution {

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        inOrder(root, queue);

        Node head = queue.poll();
        Node pre = head;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            pre.right = cur;//当前给前一个节点的右
            cur.left = pre;//前一个节点给当前的左
            pre = cur;//当前为下一次的前
        }
        
        pre.right = head;
        head.left = pre;
        
        return head;
    }

    private void inOrder(Node cur, Queue<Node> queue) {
        if (cur == null) {
            return;
        }
        //左中右
        inOrder(cur.left, queue);
        queue.add(cur);
        inOrder(cur.right, queue);
    }
}
````