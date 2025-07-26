## [53 - II. 0 ～ n-1 中缺失的数字](https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/)


### 题目描述

一个长度为 `n-1` 的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围 `0` 到 `n-1` 之内。

在范围 `0` 到 `n-1` 的 `n` 个数字中有且只有一个数字不在该数组中，请找出这个数字。

**样例**

```
输入：[0,1,2,4]

输出：3
```

### （记住）解法：二分查找

找出第一个与下标不对应的数字即可。

特殊情况：

- 下标都对应，那么应该返回 `最后一个数+1`；
- 缺失的数字是第一个，那么返回 0。

*时间复杂度 $O(log n)$*
```java
class Solution {
    /**
     * 获取0~n-1缺失的数字
     *
     * @param nums 数组
     * @return 缺失的数字
     */
    public int getMissingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int start = 0, end = n - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] != mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nums[start] == start ? start + 1 : start;

    }
}
```

### $高斯求和公式： total = n*(n+1)/2$
*时间复杂度 $O(n)$*
```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        for (int num : nums) {
            total -= num;
        }
        return total;
    }
}
```
