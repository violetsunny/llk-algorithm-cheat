## [20. 表示数值的字符串](https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/)
同：[65. 有效数字](https://leetcode.cn/problems/valid-number/description/)

### 题目描述

请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。

例如，字符串`"+100"`,`"5e2"`,`"-123"`,`"3.1416"`和`"-1E-16"`都表示数值。

但是`"12e"`,`"1a3.14"`,`"1.2.3"`,`"+-5"`和`"12e+4.3"`都不是。

**注意**:

- 小数可以没有整数部分，例如.123 等于 0.123；
- 小数点后面可以没有数字，例如 233.等于 233.0；
- 小数点前面和后面可以有数字，例如 233.666;
- 当 e 或 E 前面没有数字时，整个字符串不能表示数字，例如.e1、e1；
- 当 e 或 E 后面没有整数时，整个字符串不能表示数字，例如 12e、12e+5.4;

**样例**：

```
输入: "0"

输出: true
```

### 解法: 正则匹配

利用正则表达式匹配即可。

```
[]  ： 字符集合
()  ： 分组
?   ： 重复 0 ~ 1
+   ： 重复 1 ~ n
*   ： 重复 0 ~ n
.   ： 任意字符
\\. ： 转义后的 .
\\d ： 数字
```

```java
public class Solution {
    /**
     * 判断是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        return str != null
                && str.length != 0
                && new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }
}
```

### （记住）解法二：归纳法
针对情况赋值变量

```java
public class Solution {
    /**
     * 判断是否是数字
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        char[] str = s.trim().toCharArray();//取出空格
        int n = str.length;
        boolean isdot = false;
        boolean iseorE = false;
        boolean isnume = false;

        for (int i = 0; i < n; i++) {
            if (str[i] >= '0' && str[i] <= '9') {
                isnume = true;
            } else if (str[i] == '.') {
                //不能重复. e/E
                if (isdot || iseorE) {
                    return false;
                }
                isdot = true;

            } else if (str[i] == 'e' || str[i] == 'E') {
                if (isdot || iseorE || !isnume) {
                    return false;
                }
                iseorE = true;
                isnume = false;//重置下，例如11E++
            } else if (str[i] == '-' || str[i] == '+') {
                if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return isnume;
    }
}
```

### 解法三：有限状态机

````java
class Solution {
    public boolean isNumber(String s) {
        Map[] states = {
            new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
            new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1.
            new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
            new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
            new HashMap<>() {{ put('d', 3); }},                                        // 4.
            new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5.
            new HashMap<>() {{ put('d', 7); }},                                        // 6.
            new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7.
            new HashMap<>() {{ put(' ', 8); }}                                         // 8.
        };
        int p = 0;
        char t;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                t = 'd';
            } else if (c == '+' || c == '-') {
                t = 's';
            } else if (c == 'e' || c == 'E') {
                t = 'e';
            } else if (c == '.' || c == ' ') {
                t = c;
            } else {
                t = '?';
            }
            if (!states[p].containsKey(t)) {
                return false;
            }
            p = (int) states[p].get(t);
        }
        return p == 2 || p == 3 || p == 7 || p == 8;
    }
}
````