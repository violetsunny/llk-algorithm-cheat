## [48. 不含重复字符的最长子串](https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/)
同:[3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/)
### 题目描述

请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

假设字符串中只包含从 `a` 到 `z`的字符。

### 解法一: 动态规划 + 哈希表

`res[i]` 表示以 `s[i]` 字符结尾的最长不重复字符串的长度。判断 `s[i]`：

- 若 `s[i]` 在前面没出现过，那么 `res[i] = res[i - 1] + 1`；
- 若 `s[i]` 在前面有出现过，判断它上一次出现的位置 `index` 到 `i` 的距离 `d` 与 `res[i - 1]` 的大小关系：
    - 若 `d <= res[i - 1]`，说明它被包含在 `res[i - 1]` 构成的子串中，那么 `res[i] = i - index`；
    - 若 `d > res[i - 1]`，说明它在 `res[i - 1]` 构成的子串的左侧，那么 `res[i] = res[i - 1] + 1`。

需要用一个数组 t 记录一下当前出现的字符在哪个位置。

```java
class Solution {
    /**
     * 最长不含重复字符的子字符串
     *
     * @param s 字符串
     * @return 最长不重复字符子串
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] t = new int[26];
        for (int i = 0; i < 26; ++i) {
            t[i] = -1;
        }
        t[chars[0] - 'a'] = 0;//存储下标
        int n = chars.length;
        int[] res = new int[n];
        res[0] = 1;//至少一个
        int max = res[0];//比较每次不重复子串的最大长度
        for (int i = 1; i < n; ++i) {
            int index = t[chars[i] - 'a'];
            int d = i - index;
            res[i] = (index == -1 || d > res[i - 1]) ? res[i - 1] + 1 : d;//没有出现过和d>res[i - 1]上次结果，都要+1
            t[chars[i] - 'a'] = i;//更新下标，出现过也会更新到最新的下标值
            max = Math.max(max, res[i]);
        }
        return max;
    }
}
```

### （记住）解法：动态规划 - 空间优化
*时间复杂度：$O(n)$，空间复杂度：$O(1)$*
```java
class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] t = new int[26];
        for (int i = 0; i < 26; ++i) {
            t[i] = -1;
        }
        t[chars[0] - 'a'] = 0;//存储下标
        int n = chars.length;
        int a = 1;
        int max = a;//比较每次不重复子串的最大长度
        for (int i = 1; i < n; ++i) {
            int index = t[chars[i] - 'a'];
            int d = i - index;
            a = (index == -1 || d > a) ? a + 1 : d;//用之前a判断后，再重新赋值给当前a
            t[chars[i] - 'a'] = i;//更新下标，出现过也会更新到最新的下标值
            max = Math.max(max, a);
        }
        return max;
    }
}
```

### （记住）解法二：滑动窗口

滑动窗口：l,r r右移动，如果有重复字符l右移动

*时间复杂度：$O(n)$，空间复杂度：$O(1)$*
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] ss = new boolean[128];
        int max = 0;
        int j = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            while (ss[c]) {
                //如果当前窗口中存在相同的，j就往右挪动一位，并重新初始为false，直到窗口中字符不重复
                ss[s.charAt(j++)] = false;
            }
            ss[c] = true;
            //当前i减去j挪到的位置就是不同字符的大小
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
}
```