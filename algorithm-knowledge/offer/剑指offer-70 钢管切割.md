## 钢管切割

给定一段长度为n英寸的钢条和一个价格表pi(i=1,2,...n),求切割方案，使得销售收入r（）最大
- 长度 i  1 2 3 4 5  6  7  8  9  10
- 价格 pi 1 5 8 9 10 17 17 20 24 30

### 解法：记忆化搜索
1. 问题分解：将长度为n的钢条分解为左边一段和剩余部分，左边一段不再进行分割，剩余部分继续分割（子问题相同，递归处理）
2. 状态转移方程：
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

### （记住）解法：动态规划
1. $f(i)$ 表示i的最大价值。
2. 状态转移方程: $f(i) = MAX(f(i-j)+price(j))[0, i]$; 1到根号i之间 $[0, i]$
3. 边界：$f(0)=0$
````java
class Solution {
    public int cut(int[] price, int size) {
        int[] dp = new int[size + 1];
        for (int i = 1; i <= size; i++) {//为什么不直接i=size开始，因为需要[1,size]去填充前面f(i)的值
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {//0-i之间
                max = Math.max(max, dp[i - j] + price[j]);//i=size时,取[1, size]之间最小的数
            }
            dp[i] = max;
        }
        return dp[size];//所以要从1开始
    }
}
````