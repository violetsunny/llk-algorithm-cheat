## [57 - II. 和为 s 的连续正数序列](https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/)

### 题目描述

输入一个正数 s，打印出所有和为 s 的连续正数序列（至少含有两个数）。

例如输入 15，由于 `1+2+3+4+5=4+5+6=7+8=15`，所以结果打印出 3 个连续序列 1 ～ 5、4 ～ 6 和 7 ～ 8。

**样例**

```
输入：15

输出：[[1,2,3,4,5],[4,5,6],[7,8]]
```

### 解法：滑动窗口

用两个指针 `p, q` 指示序列的最小值和最大值。如果序列和大于 s，则从序列中去掉较小的值，即 `++p`；如果序列和小于 s，则序列向右再包含一个数字，即 `++q`。

当 p 超过 s 的一半时，停止。

```java
import java.util.*;


class Solution {

    /**
     * 找出和为sum的连续正整数序列
     *
     * @param sum 和
     * @return 结果列表
     */
    public List<List<Integer>> findContinuousSequence(int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (sum < 3) {
            return res;
        }
        int p = 1, q = 2;
        int mid = (1 + sum) >> 1;
        int curSum = p + q;
        while (p < mid) {
            if (curSum == sum) {
                res.add(getList(p, q));
            }
            //时间窗口方式进行移动
            while (curSum > sum && p < mid) {
                curSum -= p;
                ++p;
                if (curSum == sum) {
                    res.add(getList(p, q));
                }
            }
            ++q;
            curSum += q;
        }
        return res;
    }

    private List<Integer> getList(int from, int to) {
        List<Integer> res = new ArrayList<>();
        for (int i = from; i <= to; ++i) {
            res.add(i);
        }
        return res;
    }
}
```
### （记住）解法：双指针-帅地简单写法

*时间复杂度：$O(n)$*
```java
class Solution {

    /**
     * 找出和为sum的连续正整数序列
     *
     * @param target 和
     * @return 结果列表
     */
    public List<List<Integer>> findContinuousSequence(int target) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 1;
        int j = 1;
        int sum = 1;
        while(i<target/2){
            if(sum < target){
                j++;//右边移动
                sum = sum + j;//加上右边
            }else if(sum > target){
                sum = sum - i;//减去左边
                i++;//左边移动
            }else {
                res.add(getList(i,j));//相等就可以取出当前i到j之间的数
                sum = sum - i;//减去左边
                i++;//都移动检查下一个窗口
                j++;
                sum = sum + j;//加上右边
            }
        }
        return res;
    }

    private List<Integer> getList(int from, int to) {
        List<Integer> res = new ArrayList<>();
        for (int i = from; i <= to; ++i) {
            res.add(i);
        }
        return res;
    }
}
```