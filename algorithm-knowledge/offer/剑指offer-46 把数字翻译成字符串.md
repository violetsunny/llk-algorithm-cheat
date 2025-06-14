## [46. 把数字翻译成字符串](https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)

### 题目描述

给定一个数字，我们按照如下规则把它翻译为字符串：

0 翻译成 ”a”，1 翻译成 ”b”，……，11 翻译成 ”l”，……，25 翻译成 ”z”。

一个数字可能有多个翻译。例如 12258 有 5 种不同的翻译，它们分别是 ”bccfi”、”bwfi”、”bczi”、”mcfi”和”mzi”。

请编程实现一个函数用来计算一个数字有多少种不同的翻译方法。

### 解法：动态规划
1. `res[i]`是 长度为 `i`时有多少种方案。

2. 先写入递推式，res 表示共有多少种翻译方法。看最后一个字符，判断它与前一个字符能否构成有效翻译，计算 res[i]：

   - `10 - 25` 能，那么 `res[i] = res[i - 1] + res[i - 2]`；
   - 不能，那么 `res[i] = res[i - 1]`。

3. 边界值，`res[0] = 1`

```java
class Solution {
    /**
     * 获取翻译字符串的方法个数
     *
     * @param s 字符串
     * @return 个数
     */
    public int getTranslationCount(String s) {
        if (s == null || s.length() < 2) {
            return 1;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] res = new int[n];
        res[0] = 1;
        res[1] = isInRange(chars[0], chars[1]) ? 2 : 1;
        for (int i = 2; i < n; ++i) {
            res[i] = res[i - 1] + (isInRange(chars[i - 1], chars[i]) ? res[i - 2] : 0);
        }
        return res[n - 1];
    }

    private boolean isInRange(char a, char b) {
        int s = (a - '0') * 10 + (b - '0');
        return s >= 10 && s <= 25;
    }
}
```

### （记住）解法：动态规划-优化
````java
class Solution {
    public int translateNum(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int n = s.length;
        if(n < 2){
            return 1;
        }
        
        int a = 1;
        int b = 1;
        int c = 1;
        for (int i = 1; i < n; ++i) {
            if (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] < '6')) {// 10 - 25
                c = a + b;
            } else {
                c = b;
            }
            a = b;
            b = c;
        }
        return b;
    }
}
````