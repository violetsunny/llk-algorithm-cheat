## [一维数组的动态和](https://leetcode.cn/problems/running-sum-of-1d-array/description/)

给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。

请返回 nums 的动态和。


````
示例 1：

输入：nums = [1,2,3,4]
输出：[1,3,6,10]
解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
示例 2：

输入：nums = [1,1,1,1,1]
输出：[1,2,3,4,5]
解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
示例 3：

输入：nums = [3,1,2,10,1]
输出：[3,4,6,16,17]
````

提示：

- 1 <= nums.length <= 1000
- -10^6 <= nums[i] <= 10^6

### （记住）解法：动态规划
1. f(i):设前i+1个数字的和为f(i)；
2. 转移方程：$f(i)=f(i−1)+nums[i]；$
3. 初始状态： $f(0)=nums[0]；$

````java
class Solution {
    public int[] runningSum(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        return dp;
    }
}
````