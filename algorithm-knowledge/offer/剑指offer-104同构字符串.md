## [同构字符串](https://leetcode.cn/problems/isomorphic-strings/description/)

给定两个字符串 s 和 t ，判断它们是否是同构的。

如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。

每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。


````
示例 1:

输入：s = "egg", t = "add"
输出：true
示例 2：

输入：s = "foo", t = "bar"
输出：false
示例 3：

输入：s = "paper", t = "title"
输出：true
````

提示：


- 1 <= s.length <= 5 * 104
- t.length == s.length
- s 和 t 由任意有效的 ASCII 字符组成

### 解法：下标法

````java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            //各个字符的下标应该对称
            if (s.indexOf(s.charAt(i)) != t.indexOf(t.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
````

### 解法：哈希映射

````java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            char y = t.charAt(i);
            // s中的x应该映射y,t中的y应该映射x，互相映射。如果有一个对不上就是false
            if ((map1.containsKey(x) && map1.get(x) != y) || (map2.containsKey(y) && map2.get(y) != x)) {
                return false;
            }
            map1.put(x, y);
            map2.put(y, x);
        }
        return true;
    }
}
````