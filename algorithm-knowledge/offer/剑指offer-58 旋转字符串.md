## [旋转字符串](https://leetcode.cn/problems/rotate-string/description/)

给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。

s 的 旋转操作 就是将 s 最左边的字符移动到最右边。

例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。


示例 1:

输入: s = "abcde", goal = "cdeab"
输出: true
示例 2:

输入: s = "abcde", goal = "abced"
输出: false


提示:

- 1 <= s.length, goal.length <= 100
- s 和 goal 由小写英文字母组成

### （记住）解法：字符串匹配
如果 goal 是 s + s 的子串，则返回 true。

````java
class Solution {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (goal + goal).contains(s);
    }
}
````