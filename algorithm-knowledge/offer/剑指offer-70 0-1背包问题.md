## 0-1 背包问题

给定一个容量为 V 的背包和 N 个物品，每个物品有体积 weight[i] 和价值 value[i]。要求选择物品装入背包，使得总价值最大，且总体积不超过背包容量。每个物品只能选一次。

### （记住）解法：动态规划
1. 状态定义： $dp[i][j]$ 表示前 i 个物品，在背包容量为 j 时的最大价值。

2. 状态转移方程：
    - 不选第 i 个物品：$dp[i][j] = dp[i-1][j]$
    - 选第 i 个物品（需满足 $j >= weight[i-1]$）：
      
      $dp[i][j] = max(dp[i-1][j], dp[i-1][j - weight[i-1]] + value[i-1])$

````java
class Solution {
   public int knapsack(int[] weight, int[] value, int capacity) {
      int n = weight.length;
      int[][] dp = new int[n + 1][capacity + 1];

      for (int i = 1; i <= n; i++) {
         for (int j = 0; j <= capacity; j++) {
            // 不选当前物品
            dp[i][j] = dp[i - 1][j];
            // 选当前物品（需满足容量条件）
            if (j >= weight[i - 1]) {
               dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
            }
         }
      }
      return dp[n][capacity];
   }
}
````

### （记住）解法：动态规划 - 空间优化
逆序遍历容量以避免重复计算。
````java
class Solution {
   public int knapsack(int[] weight, int[] value, int capacity) {
      int[] dp = new int[capacity + 1];
      // 外层遍历物品 → 组合数（不考虑顺序）
      for (int i = 0; i < weight.length; i++) {
         // 逆序遍历容量，防止重复计算
         for (int j = capacity; j >= weight[i]; j--) {
            dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
         }
      }
      return dp[capacity];
   }
}
````