## [34. 二叉树中和为某一值的路径](https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/)
同：[113. 路径总和 II](https://leetcode.cn/problems/path-sum-ii/description/)

### 题目描述

输入一颗二叉树的根节点和一个整数，打印出二叉树中跟节点到叶子结点路径中国所有值的和等于输入整数值的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的`list`中，数组长度大的数组靠前)

### （记住）解法：深度优先dfs + 回溯

```java
import java.util.ArrayList;


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

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        findPath(root, targetSum);
        return res;
    }

    private void findPath(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        targetSum = targetSum - root.val;
        if (targetSum == 0 && root.left == null && root.right == null) {//必须从根节点到叶节点
            res.add(new ArrayList<>(list));//不能在这里直接return,因为需要回溯，不然会重复
        }
        //深度搜索
        findPath(root.left, targetSum);
        findPath(root.right, targetSum);
        list.remove(list.size() - 1);//回溯
    }
}
```