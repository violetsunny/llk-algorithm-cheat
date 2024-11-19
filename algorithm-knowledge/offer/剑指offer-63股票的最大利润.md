## [63. 股票的最大利润](https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/)


### 题目描述

假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖交易该股票可能获得的利润是多少？

例如一只股票在某些时间节点的价格为 `[9, 11, 8, 5, 7, 12, 16, 14]`。

如果我们能在价格为 5 的时候买入并在价格为 16 时卖出，则能收获最大的利润 11。

**样例**

```
输入：[9, 11, 8, 5, 7, 12, 16, 14]

输出：11
```

### 解法

遍历到 nums[i] 时，求 nums[i] 与前 i 个数的最小值 `min` 的差值，最后求出最大的差值即可。

```java
class Solution {
    /**
     * 股票的最大利润
     *
     * @param nums 数组
     * @return 最大利润
     */
    public int maxDiff(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int min = nums[0];
        int maxGap = nums[1] - nums[0];
        for (int i = 2, n = nums.length; i < n; ++i) {
            min = Math.min(min, nums[i - 1]);
            maxGap = Math.max(maxGap, nums[i] - min);
        }
        return maxGap > 0 ? maxGap : 0;
    }
}
```