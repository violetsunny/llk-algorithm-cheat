# [68 - II. 二叉树的最近公共祖先](https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/)
同：[236. 二叉树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/)
## 题目描述

<p>给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。</p>

<p><a href="https://baike.baidu.com/item/%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/8918834?fr=aladdin" target="_blank">百度百科</a>中最近公共祖先的定义为：&ldquo;对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（<strong>一个节点也可以是它自己的祖先</strong>）。&rdquo;</p>

<p>例如，给定如下二叉树:&nbsp; root =&nbsp;[3,5,1,6,2,0,8,null,null,7,4]</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9868%20-%20II.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/images/binarytree.png"></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
<strong>输出:</strong> 3
<strong>解释: </strong>节点 5 和节点 1 的最近公共祖先是节点 3。
</pre>

### （记住）方法一：递归

根据“**最近公共祖先**”的定义，若 $root$ 是 $p$, $q$ 的最近公共祖先，则只可能为以下情况之一：

-   如果 $p$ 和 $q$ 分别是 $root$ 的左右节点，那么 $root$ 就是我们要找的最近公共祖先；
-   如果 $p$ 和 $q$ 都是 $root$ 的左节点，那么返回 $lowestCommonAncestor(root.left, p, q)$；
-   如果 $p$ 和 $q$ 都是 $root$ 的右节点，那么返回 $lowestCommonAncestor(root.right, p, q)$。

**边界条件讨论**：

-   如果 $root$ 为 `null`，则说明我们已经找到最底了，返回 `null` 表示没找到；
-   如果 $root$ 与 $p$ 相等或者与 $q$ 相等，则返回 $root$；
-   如果左子树没找到，递归函数返回 `null`，证明 $p$ 和 $q$ 同在 $root$ 的右侧，那么最终的公共祖先就是右子树找到的结点；
-   如果右子树没找到，递归函数返回 `null`，证明 $p$ 和 $q$ 同在 $root$ 的左侧，那么最终的公共祖先就是左子树找到的结点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。
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
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);//通过null判断是否找到
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;//不在左，就都是右
        if (right == null) return left;//不在右，就都是左
        return root;//左右，返回root
    }
}
```