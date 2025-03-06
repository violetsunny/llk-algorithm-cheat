## [打家劫舍](https://leetcode.cn/problems/Gu0c2T/description/)

你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

 
````
示例 1：

输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
示例 2：

输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 
````
提示：

- 1 <= nums.length <= 100
- 0 <= nums[i] <= 400

### 解法：动态规划
1. 我们定义 $dp[i]$ 表示前 i 间房屋能偷窃到的最高总金额。
2. 转移方程：$dp[i]=max(dp[i−2]+nums[i],dp[i−1])$
3. 定义边界：$d[0] = nums[0], d[1] = MAX(nums[0],nums[1])$

````java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);//选择0，1中价值最大的
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);//往前两步的和 + 当前，和往前一步的和对比哪个价值更大。本质就是每次都选择价值最大的进行
        }
        return dp[nums.length - 1];
    }
}
````

#### 优化写法
````java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        //f(n) = max(f(n-2)+nums[n],f(n-1));
        int a = nums[0];
        int b = Math.max(nums[0], nums[1]);//f(n)是第一个和第二个最大的那个
        for (int i = 2; i < nums.length; i++) {
            int temp = b;//临时f(n-1)
            b = Math.max(a + nums[i], b);//a+nums[i]和f(n-1)比较，给f(n)也就是下次的f(n-1)了。
            a = temp;//f(n-1)给f(n-2),也就是下次的f(n-2)了
        }
        return b;
    }
}
````