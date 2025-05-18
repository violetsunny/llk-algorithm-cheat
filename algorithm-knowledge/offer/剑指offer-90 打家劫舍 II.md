## [213. 打家劫舍 II](https://leetcode.cn/problems/house-robber-ii/description/)

你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。


````
示例 1：

输入：nums = [2,3,2]
输出：3
解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
示例 2：

输入：nums = [1,2,3,1]
输出：4
解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
偷窃到的最高金额 = 1 + 3 = 4 。
示例 3：

输入：nums = [1,2,3]
输出：3
````

提示：

- 1 <= nums.length <= 100
- 0 <= nums[i] <= 1000

### 解法：动态规划
1. 我们定义 $dp[i]$ 表示前 i 间房屋能偷窃到的最高总金额。
2. 转移方程：$dp[i]=max(dp[i−2]+nums[i],dp[i−1])$，$(start,end)=(0,i-1)和(start,end)=(1,i)$进行计算。
   - 由于环的原因，取最后一个就不能取第一个，所以可以将原来环数组拆分开，$MAX([0,i-1],[1,i])$ (i = length-1)
3. 定义边界：$dp[start + 0]=nums[start], d[start + 1] = MAX(nums[start],nums[start + 1])$。

````java
class Solution {
   public int rob(int[] nums) {
      if (nums.length == 1) {
         return nums[0];
      }
      if (nums.length == 2) {
         return Math.max(nums[0], nums[1]);
      }
      // MAX([0,i-1],[1,i])
      return Math.max(robRange(nums, 0, nums.length - 1), robRange(nums, 1, nums.length));
   }

   private int robRange(int[] nums, int start, int end) {
      int[] dp = new int[end];
      dp[start + 0] = nums[start];
      dp[start + 1] = Math.max(nums[start], nums[start + 1]);
      for (int i = start + 2; i < end; i++) {
         dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
      }
      return dp[end - 1];
   }
}
````

### （记住）解法：动态规划 - 优化写法
````java
class Solution {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 1), robRange(nums, 1, length));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
````