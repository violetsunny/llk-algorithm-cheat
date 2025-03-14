## 完全背包问题

给定一个容量为 V 的背包和 N 个物品，每个物品有体积 weight[i] 和价值 value[i]。要求选择物品装入背包，使得总价值最大，且总体积不超过背包容量。每个物品可以选无限次。

### 解法：动态规划
允许多次重复选择

1. 状态定义： $dp[i][j]$ 表示前 i 个物品，在背包容量为 j 时的最大价值。

2. 状态转移方程：
    - 不选第 i 个物品：$dp[i][j] = dp[i-1][j]$
    - 选第 i 个物品（需满足 $j >= weight[i-1]$）：
      $dp[i][j] = max(dp[i-1][j], dp[i][j - weight[i-1]] + value[i-1])$

````java
class Solution {
   public int knapsack(int[] weight, int[] value, int capacity) {
      int n = weight.length;
      int[][] dp = new int[n + 1][capacity + 1];

      for (int i = 1; i <= n; i++) {
         for (int j = 0; j <= capacity; j++) {
            // 不选当前物品
            dp[i][j] = dp[i - 1][j];
            // 选当前物品（允许重复）
            if (j >= weight[i - 1]) {
               dp[i][j] = Math.max(dp[i][j], dp[i][j - weight[i - 1]] + value[i - 1]);
            }
         }
      }
      return dp[n][capacity];
   }
}
````

#### 空间优化
正序遍历容量以允许重复选择。
````java
class Solution {
   public int knapsack(int[] weight, int[] value, int capacity) {
      int[] dp = new int[capacity + 1];

      for (int i = 0; i < weight.length; i++) {
         // 正序遍历容量，允许重复选择
         for (int j = weight[i]; j <= capacity; j++) {
            dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
         }
      }
      return dp[capacity];
   }
}
````