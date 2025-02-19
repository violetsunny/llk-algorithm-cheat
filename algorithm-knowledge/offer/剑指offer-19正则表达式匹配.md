## [19. 正则表达式匹配](https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/)
同：[10. 正则表达式匹配](https://leetcode.cn/problems/regular-expression-matching/description/)

### 题目描述

请实现一个函数用来匹配包括 `'.'` 和 `'*'` 的正则表达式。

模式中的字符 `'.'` 表示任意一个字符，而 `'*'` 表示它前面的字符可以出现任意次（含 0 次）。

在本题中，匹配是指字符串的所有字符匹配整个模式。

例如，字符串 `"aaa"` 与模式 `"a.a"` 和 `"ab*ac*a"` 匹配，但是与 `"aa.a"` 和 `"ab*a"` 均不匹配。

**样例**

```
输入：

s="aa"
p="a*"

输出:true
```


### 解法：动态规划

判断模式中第二个字符是否是 `*`：

- 若是，看如果模式串第一个字符与字符串第一个字符是否匹配：
    - 若不匹配，在模式串上向右移动两个字符`j+2`，相当于 a\* 被忽略。
    - 若匹配，字符串后移`i+1`。此时模式串可以移动两个字符`j+2`，也可以不移动`j`。
- 若不是，看当前字符与模式串的当前字符是否匹配，即 `str[i] == pattern[j] || pattern[j] == '.'`：
    - 若匹配，则字符串与模式串都向右移动一位，`i+1`，`j+1`。
    - 若不匹配，返回 false。

```java
class Solution {

    /**
     * 判断字符串是否与模式串匹配
     * 时间过长
     * @param s 字符串
     * @param p 模式串
     * @return 是否匹配
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        return match(str, 0, str.length, pattern, 0, pattern.length);
    }

    private boolean match(char[] str, int i, int len1, char[] pattern, int j, int len2) {
        if (i == len1 && j == len2) {
            return true;
        }

        // pattern已经走到最后，而str还有未匹配的
        // str走到最后，而pattern还没走完，此时是允许的
        if (j == len2) {
            return false;
        }

        if (j + 1 < len2 && pattern[j + 1] == '*') {
            if (i < len1 && (str[i] == pattern[j] || pattern[j] == '.')) {
                return match(str, i, len1, pattern, j + 2, len2)
                        || match(str, i + 1, len1, pattern, j, len2)
                        || match(str, i + 1, len1, pattern, j + 2, len2);
            }
            return match(str, i, len1, pattern, j + 2, len2);
        }

        if (i < len1 && (str[i] == pattern[j] || pattern[j] == '.')) {
            return match(str, i + 1, len1, pattern, j + 1, len2);
        }
        return false;

    }
}
```

#### 帅地的比较好理解的写法：动态规划

判断字符串是否与模式串匹配
- $p[j] == '.' || p[j] == s[i]$ : 
   - $dp[i][j]=dp[i-1][j-1]$;

- $p[j] == '*'$ ：
   - $p[j-1] != s[i]$ : 
      - $dp[i][j]=dp[i][j-2];$ //`*`代表 0 个，就是 i 和 j-2 比,例如 a 和 as`*`
   - $p[j-1] == s[i] || p[j-1] == '.' $:
      - $dp[i][j]=dp[i][j-1];$ //`*`代表 1 个，就是 i 和 j-1 比,例如 aa 和 aa`*`
      - $dp[i][j]=dp[i][j-2];$ //`*`代表 0 个，例如a 和 aa`*`
      - $dp[i][j]=dp[i-1][j];$ //`*`代表多个，就是`*`可以指向多个 j-1 的字符，
                                          i 和 j 的相等就是 i-1 和 j 的相等，因为 j 又是`*`所以看的是 i-1 和 j-1 ，
                                          因为会有多个，所以是 i-1 和 `*` 比，
                                          所以应该 i-1 往前走，一直到 `*` 代表 0 或 1 为止。
                                          例如：aaaa 和 a`*`


````java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;//默认第一个true
        //初始化值和特殊情况 s="" p="a*a*"
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        // 循环匹配 从1 - length
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i][j - 1] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}
````