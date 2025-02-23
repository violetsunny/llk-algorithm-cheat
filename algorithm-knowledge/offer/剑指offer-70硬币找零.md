# [硬币找零](https://leetcode.cn/problems/coin-change/description/)

给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

你可以认为每种硬币的数量是无限的。

<pre>
示例 1：

输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1
示例 2：

输入：coins = [2], amount = 3
输出：-1
示例 3：

输入：coins = [1], amount = 0
输出：0
</pre>

<pre>
提示：

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
</pre>

## 解法：动态规划
对于金额 i，我们可以从任意一个面值的硬币 cj 出发（只要 i≥cj），如果选择了面值为 cj 的硬币，则问题转化为子问题，即剩下的金额 i−cj：
<pre>
dp[i]=min(dp[i],dp[i−cj]+1)  for each cj in coins
</pre>
通过迭代的方式，从金额 1 到 n 逐步填充 dp[] 数组，每次选择最优解。

````
F(3)=min(F(3−c1),F(3−c2),F(3−c3))+1
    =min(F(3−1),F(3−2),F(3−3))+1
    =min(F(2),F(1),F(0))+1
    =min(1,1,0)+1
    =1
````

````java
class Solution {
    public int coin(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);//初始化最大值，最大肯定amount+1
        dp[0] = 0;//从1开始，0位置要初始为0
        for (int i = 1; i <= amount; i++) {//先填充amount之前的数，才能计算amount
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);//各个面值比较,注意当前选择了coins[j]，所以是要 +1
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
````

## 解法：记忆化搜索
$F(S)=F(S−C)+1;$ 递归所有可能性，拿到最小的，并且将重复计算过的值存储，方便下次继续算。

````java
class Solution {
    public int coin(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem == 0) {
            return 0;
        }
        if (rem < 0) {
            return -1;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int p = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int res = coinChange(coins, rem - coins[i], count);//递归所有面值需要的可能性，取最小次数的
            if (res >= 0) {//防止等于-1
                p = Math.min(p, res + 1);//+1是因为当前面值也算
            }
        }
        if (p == Integer.MAX_VALUE) {
            p = -1;
        }
        count[rem - 1] = p;//记忆计算过的数据
        return p;
    }
}

````
