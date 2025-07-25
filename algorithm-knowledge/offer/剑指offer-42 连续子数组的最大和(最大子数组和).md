## [42. 连续子数组的最大和](https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/)
同：[53. 最大子数组和](https://leetcode.cn/problems/maximum-subarray/description/)
### 题目描述

输入一个**非空**整型数组，数组里的数可能为正，也可能为负。
数组中一个或连续的多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为`O(n)`。

### 解法: 动态规划

$res[i]$ 表示以第 $i$ 个数字结尾的子数组的最大和，那么求出 $max(res[i])$ 即可。

- if `res[i - 1] < 0`,`res[i] = array[i]`,
- if `res[i - 1] >= 0`,`res[i] = res[i - 1] + array[i]`

```java
public class Solution {
    /**
     * 求连续子数组的最大和
     *
     * @param array 数组
     * @return 最大和
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int n = array.length;
        int[] res = new int[n];
        res[0] = array[0];
        int max = res[0];
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] > 0 ? res[i - 1] + array[i] : array[i];//要是小于0就是减，不如不要
            max = Math.max(max, res[i]);
        }
        return max;
    }
}
```

### （记住）最优写法：O(1)
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int f = 0;
        for (int x : nums) {
            f = Math.max(f, 0) + x;//f为上一个值，Math.max(f, 0) 也是 f>0?f:0
            ans = Math.max(ans, f);
        }
        return ans;
    }
}
```
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int num : nums) {
            pre = Math.max(num, pre + num);// pre = Math.max(pre,0) + num;
            max = Math.max(max, pre);
        }
        return max;
    }
}
```
