# 钢管切割
给定一段长度为n英寸的钢条和一个价格表pi(i=1,2,...n),求切割方案，使得销售收入r（）最大
- 长度 i  1 2 3 4 5  6  7  8  9  10
- 价格 pi 1 5 8 9 10 17 17 20 24 30

## 解法：贪心+递归+记忆化搜索
问题分解：将长度为n的钢条分解为左边一段和剩余部分，左边一段不再进行分割，剩余部分继续分割（子问题相同，递归处理）
状态转移方程：
<pre>
r(n)=max(p(i)+r(n-i))  1<=i<=n 注意i不能取0
r(n)是长度为n的钢条的收益。p(i):长度为i的钢条的价格
</pre>
````java
class Solution {

    public int cut(int[] price, int size) {
        if (size == 0) {
            return 0;
        }
        return cut(price, size, new int[size + 1]);
    }

    private int cut(int[] price, int size, int[] count) {
        if (size == 0) {
            return 0;
        }
        if (count[size - 1] != 0) {
            return count[size - 1];
        }
        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= size; i++) {
            q = Math.max(q, price[i - 1] + cut(price, size - i, count));//递归拿到所有的可能，通过max比较最大的价值
        }
        count[size - 1] = q;
        return q;
    }
}

````

## 解法：动态规划

````java
class Solution {
    public int cut(int[] price, int size) {
        int[] dp = new int[size + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {//为什么不直接i=n开始，因为需要[1,n]去填充前面f(i)的值
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {//[1, 根号i]
                max = Math.max(max, dp[i] + price[j]);//i=n时,取[1, 根号n]之间最小的数+1
            }
            dp[i] = max;
        }
        return dp[size];
    }
}
````