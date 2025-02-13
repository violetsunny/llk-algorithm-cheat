## [32 - I. 从上到下打印二叉树](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/)


### 题目描述

从上往下打印出二叉树的每个节点，同层节点从左至右打印。

### 解法：层序遍历

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

### 解法：多行层序遍历

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

## [32 - III. 从上到下打印二叉树 III](https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/description/)


### 题目描述

请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

如二叉树：

```
            1
    	   /  \
    	  2    3
    	 / \  / \
    	4  5 6  7
```

打印结果为：

```
1
3 2
4 5 6 7
```

### 解法：S型遍历（锯齿形层序遍历）

对于上述二叉树：

首先访问根结点，之后把 2、3 存入某结构。打印的时候，先打印 3、2。这不就是栈？

依次弹出栈元素，分别是 3、2。弹出时需要把 3、2 的子结点存入结构。由于访问时顺序是`4 5 6 7`。所以也需要用栈来存放。而且，此时需要先存放右孩子，再存放左孩子。（奇数层/偶数层存放左右孩子的顺序不同）

这里需要用两个栈来实现。如果只用一个栈，那么当弹出 3、2 时，先将 3 的孩子节点压入栈。之后弹栈的时候不是先弹出 2，而是弹出了 3 的 孩子节点，就错了。

```java
import java.util.ArrayList;
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
     * 按之字形打印二叉树
     * @param root 二叉树的根节点
     * @return 结果list
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);
        while (!stack1.isEmpty()) {
            List<Integer> array = new ArrayList<>();
            while (!stack1.isEmpty()) {
                TreeNode ptr = stack1.pop();
                array.add(ptr.val);
                if (ptr.left != null) {
                    stack2.push(ptr.left);
                }
                if (ptr.right != null) {
                    stack2.push(ptr.right);
                }
            }
            if(!array.isEmpty()){
                list.add(array);
            }

            List<Integer> array2 = new ArrayList<>();
            while (!stack2.isEmpty()) {
                TreeNode ptr = stack2.pop();
                array2.add(ptr.val);
                if (ptr.right != null) {
                    stack1.push(ptr.right);
                }
                if (ptr.left != null) {
                    stack1.push(ptr.left);
                }
            }
            if(!array2.isEmpty()){
                list.add(array2);
            }

        }

        return list;
    }

}
```
ps: 也可以在第二个上面直接用标记sum++，根据sum%2==0,偶数可以LinkedList.addFirst();操作

````java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        int sum = 0;
        while (!queue.isEmpty()) {
            List<Integer> tmp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if (sum % 2 == 0) {
                    tmp.addLast(node.val);
                } else {
                    tmp.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(tmp);
            sum++;
        }
        return res;
    }
}

````