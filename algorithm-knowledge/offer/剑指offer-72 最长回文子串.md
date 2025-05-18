## [72.最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring)
### 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>，找到 <code>s</code> 中最长的 <span data-keyword="palindromic-string">回文</span> <span data-keyword="substring-nonempty">子串</span>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "babad"
<strong>输出：</strong>"bab"
<strong>解释：</strong>"aba" 同样是符合题意的答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "cbbd"
<strong>输出：</strong>"bb"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 仅由数字和英文字母组成</li>
</ul>



## 解法


### （记住）解法一：动态规划

我们定义 $f[i][j]$ 表示字符串 $s[i..j]$ 是否为回文串，初始时 $f[i][j] = true$。

接下来，我们定义变量 $k$ 和 $mx$，其中 $k$ 表示最长回文串的起始位置，$mx$ 表示最长回文串的长度。初始时 $k = 0$, $mx = 1$。

考虑 $f[i][j]$，如果 $s[i] = s[j]$，那么 $f[i][j] = f[i + 1][j - 1]$；否则 $f[i][j] = false$。如果 $f[i][j] = true$ 并且 $mx \lt j - i + 1$，那么我们更新 $k = i$, $mx = j - i + 1$。

由于 $f[i][j]$ 依赖于 $f[i + 1][j - 1]$，因此我们需要保证 $i + 1$ 在 $j - 1$ 之前，因此我们需要从大到小地枚举 $i$，从小到大地枚举 $j$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是字符串 $s$ 的长度。


```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (boolean[] g : f) {
            Arrays.fill(g, true);
        }
        int k = 0, mx = 1;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = false;
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1];
                    if (f[i][j] && mx < j - i + 1) {
                        mx = j - i + 1;
                        k = i;
                    }
                }
            }
        }
        return s.substring(k, k + mx);
    }
}
```

### 方法二：枚举回文中间点

我们可以枚举回文中间点，向两边扩散，找到最长的回文串。

时间复杂度 $O(n^2)$，空间复杂度 $O(1)$。其中 $n$ 是字符串 $s$ 的长度。


```java
class Solution {
    private String s;
    private int n;

    public String longestPalindrome(String s) {
        this.s = s;
        n = s.length();
        int start = 0, mx = 1;
        for (int i = 0; i < n; ++i) {
            int a = f(i, i);
            int b = f(i, i + 1);
            int t = Math.max(a, b);
            if (mx < t) {
                mx = t;
                start = i - ((t - 1) >> 1);
            }
        }
        return s.substring(start, start + mx);
    }

    private int f(int l, int r) {
        while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
            --l;
            ++r;
        }
        return r - l - 1;
    }
}
```
