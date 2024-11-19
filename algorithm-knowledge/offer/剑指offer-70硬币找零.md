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

## 解法：贪心
对于金额 i，我们可以从任意一个面值的硬币 cjc_jcj 出发（只要 i≥cj），如果选择了面值为 cj 的硬币，则问题转化为子问题，即剩下的金额 i−cj：
<pre>
dp[i]=min(dp[i−cj]+1)  for each cj in coins
</pre>
通过迭代的方式，从金额 1 到 n 逐步填充 dp[] 数组，每次选择最优解。
````java
class Solution {
    public int coin(int[] coins,int sum){
        if(sum <= 0){
            return 1;
        }
        int p = Integer.MAX_VALUE;
        for(int i=0;i<coins.length;i++){
            p = Math.min(p,coin(coins,sum-coins[i])+1);
        }

        if(p == Integer.MAX_VALUE){
            p = -1;
        }
        return p;
    }
}

````
