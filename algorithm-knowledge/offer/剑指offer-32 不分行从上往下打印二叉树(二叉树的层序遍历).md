## [32 - I. 从上到下打印二叉树](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/)
同：[102. 二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/description/)

### 题目描述

从上往下打印出二叉树的每个节点，同层节点从左至右打印。

### （记住）解法：层序遍历

层序遍历，先将根节点进入队列。

队头元素出队，将值存入 list，判断该元素是否有左/右子树，有的话依次进入队列中。队列为空时结束。

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


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
     * 从上到下打印二叉树
     * @param root 二叉树根节点
     * @return 结果list
     */
    public List<Integer> levelOrder(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        //层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();//从队列中获取并移除头部元素
            if (node.left != null) {
                queue.offer(node.left);//左边不为空放入左
            }
            if (node.right != null) {
                queue.offer(node.right);//右边不为空放入右
            }
            list.add(node.val);
        }
        return list;
    }
}
```

## [32 - II. 从上到下打印二叉树 II](https://leetcode.cn/problems/binary-tree-level-order-traversal/description/)


### 题目描述

从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。

### （记住）解法：多行层序遍历

与上一题类似，只不过需要用变量记录每一层要打印多少个节点。

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
     * 把二叉树打印成多行
     * @param pRoot 二叉树根节点
     * @return 结果list
     */
    public List<List<Integer> > Print(TreeNode pRoot) {
        List<List<Integer>> list = new ArrayList<>();
        if (pRoot == null) {
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            int num = queue.size();
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < num; ++i) {
                TreeNode node = queue.poll();
                res.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(res);
        }
        return list;
    }

}
```
