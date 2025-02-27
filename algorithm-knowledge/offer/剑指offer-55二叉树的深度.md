## [55 - I. 二叉树的深度](https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/)
同:[104.二叉树的深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/)

### 题目描述

输入一棵二叉树的根结点，求该树的深度。

从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。

**样例**

```
输入：二叉树[8, 12, 2, null, null, 6, 4, null, null, null, null]如下图所示：
    8
   / \
  12  2
     / \
    6   4

输出：3
```

### 解法：深度优先搜索（DFS）

递归即可。后序遍历

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * 求二叉树的深度
     *
     * @param root 二叉树根结点
     * @return 深度
     */
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lDepth = treeDepth(root.left);
        int rDepth = treeDepth(root.right);
        return 1 + Math.max(lDepth, rDepth);
    }
}
```

#### 从上到下，前序遍历
````java
class Solution {
    // 全局变量，存放结果
    int res = 0;    
    public int maxDepth(TreeNode root) {
        // 深度遍历，一开始的深度为 0
        dfs(root, 0);    
        // 返回结果
        return this.res;     
    }

    public void dfs(TreeNode root, int length){
        // 如果到达叶子节点，就更新结果
        if (root == null){      
            // 选取最深的叶子节点作为结果
            res = Math.max(this.res, length);
            return;
        }
        // 由于当前节点有值，故深度要+1
        dfs(root.left, length+1);    // 查找左节点
        dfs(root.right, length+1);   // 查找右节点
    }
}
````

### 解法：层序遍历
1. 使用队列来存放节点

2. 一开始先知道当前层数的节点个数，然后根据个数出队，并对其孩子节点入队
````java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        // 创建一个队列
        Deque<TreeNode> deque = new LinkedList<>(); 
        // 根结点入队列，对应第一层
        deque.offer(root);                          
        int res = 0;
        // 如果队列不为空
        while (!deque.isEmpty()){
            // 层数
            res += 1;          
            // 找到当前层的节点数
            int length = deque.size();       
            // 清空当前层，并把孩子节点入队
            for (int i=0; i < length; i++){     
                TreeNode node = deque.pollFirst(); 
                if (node.left != null)
                    deque.offer(node.left);
                if (node.right != null)
                    deque.offer(node.right);
            }
        }
        return res;
    }
}
````