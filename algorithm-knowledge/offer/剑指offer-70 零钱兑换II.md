## [518. 零钱兑换 II](https://leetcode.cn/problems/coin-change-ii/description/)

给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。

请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。

假设每一种面额的硬币有无限个。

题目数据保证结果符合 32 位带符号整数。


````
示例 1：

输入：amount = 5, coins = [1, 2, 5]
输出：4
解释：有四种方式可以凑成总金额：
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
示例 2：

输入：amount = 3, coins = [2]
输出：0
解释：只用面额 2 的硬币不能凑成总金额 3 。
示例 3：

输入：amount = 10, coins = [10]
输出：1
````

提示：

- 1 <= coins.length <= 300
- 1 <= coins[i] <= 5000
- coins 中的所有值 互不相同
- 0 <= amount <= 5000

### 解法：动态规划
1. 定义 $dp(i,c)$ 表示用前 `i` 种硬币组成金额 `c` 的方案数.
2. 考虑「选或不选」，有：
   - 不再继续选第 i 种硬币：$dp(i−1,c)$。
   - 继续选一枚第 i 种硬币：$dp(i,c−coins[i])$。
   
   关系式：$dp(i,c)=dp(i−1,c)+dp(i,c−coins[i]);$
3. 边界：$dp[0] = 1;$

````java
class Solution {
    public int change(int amount, int[] coins) {
        /*
        完全背包问题，每个物品可以选择多次
        需要注意的是需求求解的是组合数 即不考虑所选物品的顺序,[1,2] 和 [2,1] 被视为同一方案
        完全背包 先循环物品、在循环背包
        */
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        //外层遍历物品 → 组合数（不考虑顺序） 即 3 [1,2] [2,1] 只能算一种
        for (int i = 0; i < coins.length; i++) {
            // 正序
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
````

## 解法：记忆化搜索

````java
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] memo = new int[n][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(n - 1, amount, coins, memo);
    }

    private int dfs(int i, int c, int[] coins, int[][] memo) {
        if (i < 0) {
            return c == 0 ? 1 : 0;
        }
        if (memo[i][c] != -1) { // 之前算过了
            return memo[i][c];
        }
        if (c < coins[i]) {
            return memo[i][c] = dfs(i - 1, c, coins, memo);
        }
        return memo[i][c] = dfs(i - 1, c, coins, memo) + dfs(i, c - coins[i], coins, memo);
    }
}
````
