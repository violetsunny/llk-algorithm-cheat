## [最长回文串](https://leetcode.cn/problems/longest-palindrome/description/)

给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的
回文串
的长度。

在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。


````
示例 1:

输入:s = "abccccdd"
输出:7
解释:
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
示例 2:

输入:s = "a"
输出:1
解释：可以构造的最长回文串是"a"，它的长度是 1。
````

提示:

- 1 <= s.length <= 2000
- s 只由小写 和/或 大写英文字母组成

### （记住）解法：哈希表

````java
class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[58];
        for (char c : s.toCharArray()) {
            count[c - 'A']++;
        }
        int sum = 0;
        for (int c : count) {
            if (c > 1 && (c & 1) == 0) {
                sum += c;
            }
            if (c > 1 && (c & 1) == 1) {
                sum += c - 1;
            }
        }
        return s.length() > sum ? sum + 1 : sum;
    }
}
````