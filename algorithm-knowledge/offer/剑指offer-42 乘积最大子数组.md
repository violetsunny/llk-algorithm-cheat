## [42.2 乘积最大子数组](https://leetcode.cn/problems/maximum-product-subarray/description/)

给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组
（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

测试用例的答案是一个 32-位 整数。
```
示例 1:
输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
```
```
示例 2:
输入: nums = [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是连续子数组。
```


提示:
- 1 <= nums.length <= 2 * 104
- -10 <= nums[i] <= 10
- nums 的任何子数组的乘积都 保证 是一个 32-位 整数

### （记住）解法：动态规划
题解: 由于当前最小可能是负数，再下一次有负数的情况相乘后可能就是最大。
````java
class Solution {
    public int maxProduct(int[] nums) {
        long maxF = nums[0], minF = nums[0];
        int ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            long mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            if (minF < Integer.MIN_VALUE) {
                minF = nums[i];
            }
            ans = Math.max((int) maxF, ans);
        }
        return ans;
    }
}
````

### 解法：正反相乘
````java
class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int a = 1;
        int n = nums.length;
        int max = nums[0];
        for (int i = 0; i < n; i++) { //正着来一遍
            a *= nums[i];
            max = Math.max(a, max);
            if (a == 0) {//等于0了，a需要重新计算
                a = 1;
            }
        }

        a = 1;
        for (int i = n - 1; i >= 0; i--) {//反着再来一遍，为了在有负数的情况可以负负得正
            a *= nums[i];
            max = Math.max(a, max);
            if (a == 0) {
                a = 1;
            }
        }

        return max;
    }
}
````
