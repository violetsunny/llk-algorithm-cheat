# [68 - I. 二叉搜索树的最近公共祖先](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/)

## 题目描述

<p>给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。</p>

<p><a href="https://baike.baidu.com/item/%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/8918834?fr=aladdin" target="_blank">百度百科</a>中最近公共祖先的定义为：&ldquo;对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（<strong>一个节点也可以是它自己的祖先</strong>）。&rdquo;</p>

<p>例如，给定如下二叉搜索树:&nbsp; root =&nbsp;[6,2,8,0,4,7,9,null,null,3,5]</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9868%20-%20I.%20%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/images/binarysearchtree_improved.png"></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9868%20-%20I.%20%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/images/binarysearchtree_improved.png">
<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
<strong>输出:</strong> 6
<strong>解释:</strong> 节点 2 和节点 8 的最近公共祖先是 6。
</pre>
### 解法一：遍历

从上到下遍历二叉树，找到第一个值位于 $[p.val,.. q.val]$ 之间的结点即可。既可以用迭代实现，也可以用递归实现。

时间复杂度 $O(n)$，其中 $n$ 是二叉树的结点数。空间复杂度方面，迭代实现的空间复杂度为 $O(1)$，递归实现的空间复杂度为 $O(n)$。
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 二叉搜索树的中序遍历是升序的,即左根右，左比根小，根比右小
        while (root != null) {
            if (root.val < p.val && root.val < q.val) {//root小于p又小于q，说明在右子树
                root = root.right;
            } else if (root.val > p.val && root.val > q.val) {//root大于p又大于q，说明在左子树
                root = root.left;
            } else {
                return root;//都不在说明，一个在左一个在右，最近的就是当前根
            }
        }
        
        return root;
    }
}
```
### 解法二：递归
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 二叉搜索树的中序遍历是升序的,即左根右，左比根小，根比右小
        if (root.val < p.val && root.val < q.val) {//root小于p又小于q，说明在右子树
            return lowestCommonAncestor(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {//root大于p又大于q，说明在左子树
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;//都不在说明，一个在左一个在右，最近的就是当前根
    }
}
```
