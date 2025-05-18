## [二叉树中的最大路径和](https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/)

二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。

 
````
示例 1：


输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
示例 2：


输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 
````

提示：

- 树中节点数目范围是 [1, 3 * 104]
- -1000 <= Node.val <= 1000

### （记住）解法：贪心

````java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int max = -1001;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int cur = root.val;
        int left = Math.max(dfs(root.left), 0);//有没有贡献。比0大才有贡献
        int right = Math.max(dfs(root.right), 0);
        max = Math.max(max, (cur + left + right));//节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值

        return cur + Math.max(left, right);//选左或选右
    }
}
````