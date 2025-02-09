---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9860.%20n%E4%B8%AA%E9%AA%B0%E5%AD%90%E7%9A%84%E7%82%B9%E6%95%B0/README.md
---

<!-- problem:start -->

# [n 个骰子的点数](https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/)

## 题目描述

<!-- description:start -->

<p>把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。</p>

<p>&nbsp;</p>

<p>你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 1
<strong>输出:</strong> [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> 2
<strong>输出:</strong> [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>1 &lt;= n &lt;= 11</code></p>

<!-- description:end -->

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示投掷 $i$ 个骰子，点数和为 $j$ 的方案数。那么我们可以写出状态转移方程：

$$
f[i][j] = \sum_{k=1}^6 f[i-1][j-k]
$$

其中 $k$ 表示当前骰子的点数，而 $f[i-1][j-k]$ 表示投掷 $i-1$ 个骰子，点数和为 $j-k$ 的方案数。

初始条件为 $f[1][j] = 1$，表示投掷一个骰子，点数和为 $j$ 的方案数为 $1$。

最终，我们要求的答案即为 $\frac{f[n][j]}{6^n}$，其中 $n$ 为骰子个数，而 $j$ 的取值范围为 $[n, 6n]$。

时间复杂度 $O(n^2)$，空间复杂度 $O(6n)$。其中 $n$ 为骰子个数。

```java
class Solution {
    public double[] dicesProbability(int n) {
        int[][] f = new int[n + 1][6 * n + 1];
        for (int j = 1; j <= 6; ++j) {
            f[1][j] = 1;//初始值
        }
        for (int i = 2; i <= n; ++i) {//n个
            for (int j = i; j <= 6 * i; ++j) {//6n可能性
                for (int k = 1; k <= 6; ++k) {//1-6
                    if (k > j) {
                        break;//越界不需要计算，也是比如j=2，说明最多也就是1，2
                    }
                    f[i][j] += f[i - 1][j - k];
                }
            }
        }
        double m = Math.pow(6, n);//6^n
        double[] ans = new double[5 * n + 1];//n-6n
        for (int j = n; j <= 6 * n; ++j) {
            ans[j - n] = f[n][j] / m;
        }
        return ans;
    }
}
```

### 方法二：动态规划（空间优化）

我们可以发现，上述方法中的 $f[i][j]$ 的值仅与 $f[i-1][j-k]$ 有关，因此我们可以使用滚动数组的方式，将空间复杂度优化至 $O(6n)$。


```java
class Solution {
    public double[] dicesProbability(int n) {
        int[] f = new int[7];
        Arrays.fill(f, 1);
        f[0] = 0;
        for (int i = 2; i <= n; ++i) {
            int[] g = new int[6 * i + 1];
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j - k >= 0 && j - k < f.length) {
                        g[j] += f[j - k];
                    }
                }
            }
            f = g;
        }
        double m = Math.pow(6, n);
        double[] ans = new double[5 * n + 1];
        for (int j = n; j <= 6 * n; ++j) {
            ans[j - n] = f[j] / m;
        }
        return ans;
    }
}
```

#### 帅地解法
<p>定义：</p>

dp[i][j] 是 当骰子的个数为i，点数为j,有dp[i][j]种组合。

$\frac{dp[i][j]}{6^n}$

<p>状态转移公式：</p>

$dp[i][j] = dp[i-1][j-1] + dp[i-1][j-2] + dp[i-1][j-3] + dp[i-1][j-4] + dp[i-1][j-5] + dp[i-1][j-6];$
就是i比i-1多一个骰子，投一个点或者两个点，三个点...六个点等等。

<p>初始值：</p>

$dp[1][1]=1;dp[1][2]=1;...dp[1][6]=1;$当一个骰子时投一个点或者两个点，三个点。。。其实只有一种组合。


````java
class Solution {
    public double[] dicesProbability(int n) {
        int[][] dp = new int[n+1][6*n+1];
        for(int i=1;i<=6;i++){
            dp[1][i] = 1;
        }
        for(int i=2;i<=n;i++){
            for(int j=i;j<=6*n;j++){
                for(int k=1;k<=6;k++){
                    if(j<k){break;}
                    dp[i][j] += dp[i-n][j-k];
                }
            }
        }
        double[] d = new double[6*n - n + 1];
        double sum = Math.pow(6,n);
        for(int i=n;i<=6*n;i++){
            d[i-n] = d[n][i] / sum;
        }
        return d;
    }
}
````
