## [50.有效的字母异位词](https://leetcode.cn/problems/valid-anagram/description/)

给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。

- 示例 1:
输入: s = "anagram", t = "nagaram"
输出: true
- 示例 2:
输入: s = "rat", t = "car"
输出: false

提示:
- 1 <= s.length, t.length <= 5 * 104
- s 和 t 仅包含小写字母

进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

### （记住）解法：哈希表（消消乐）
由于字符串只包含 26 个小写字母，因此我们可以维护一个长度为 26 的频次数组 table
````java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        //利用26字母下标，进行++ --
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }
}
````

### 解法：排序后是否相等
````java
class Solution {
    public boolean isAnagram(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(s2);
        return Arrays.equals(s1, s2);
    }
}
````

进阶：Unicode 一个字符可能对应多个字节的问题，可以用Map<Character, Integer>维护，这样避免字符造成的 **离散未知** 的问题。