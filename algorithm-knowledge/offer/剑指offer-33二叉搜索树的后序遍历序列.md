## [33. 二叉搜索树的后序遍历序列](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)


### 题目描述

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出`Yes`,否则输出`No`。假设输入的数组的任意两个数字都互不相同。

### 解法一：递归

序列的最后一个元素是二叉搜索树的根节点。根据二叉搜索树的性质，根节点左边的元素都小于根节点，根节点右边的元素都大于根节点。

在序列中从左到右找到根节点的左子树(比根节点小)、右子树(比根节点大)。

- 如果右子树中出现比根节点小的元素，那么为 false。
- 否则递归左右子树。

```java
public class Solution {
    /**
     * 判断数组是否是某个二叉搜索树的后序遍历序列
     *
     * @param sequence 数组
     * @return 是否属于某二叉搜索树的后序遍历序列
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length < 1) {
            return false;
        }
        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int root = sequence[end];
        int i = start;//标记第一个大于根节点的位置
        for (; i <= end; ++i) {
            if (sequence[i] >= root) {//往右挪动找到第一个大于根节点的就是右子树
                break;
            }
        }
        //while(sequence[i] < root){++i;} //也是找到第一个大于根节点的位置

        for (int j = i; j < end; ++j) {
            if (sequence[j] < root) {//右边如果有小于根节点的就是false
                return false;
            }
        }
        //左节点，右节点依次查找最后
        return verify(sequence, start, i - 1) && verify(sequence, i, end - 1);

    }
}
```
### 解法二：单调栈
- 2 6 5 9 8 11 13 12 10  左右根
- 10 12 13 11 8 9 5 6 2  根右左

1. 从右往左，按照搜索树和后序遍历特性。如果$array[i] < array[i+1]$，则$i+1$是 $i$ 的右节点。

2. $array[i] > array[i+1]$那么$i+1$是$0....i$中某个`root`的左节点， 并且`root`一定是$0....i$中大于$array[i+1]$中最小的那个。

3. 根右左，递增后递减的第一个是前面中最小的是左，root是这其中第二小的。


右子树会先压栈，如果栈顶元素大于当前值，则当前为某个的左子树，右子树会出栈。 如果当前值大于右子树的根，那就是false;



```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        int mx = 1 << 30;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = postorder.length - 1; i >= 0; --i) {
            int x = postorder[i];
            if (x > mx) {//左子树小于右子树和根，如果大于说明有问题
                return false;
            }
            while (!stk.isEmpty() && stk.peek() > x) {//大于x为左子树
                mx = stk.pop();//右子树及其根节点会出栈
            }
            stk.push(x);//一开始是放入跟和右，等pop出栈了，当前x是左，但是出栈了就是剩下的二叉树的根和右了
        }
        return true;
    }
}
```