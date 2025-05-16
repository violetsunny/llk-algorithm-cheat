## [49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/description/)

给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的所有字母得到的一个新单词。


````
示例 1:

输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
示例 2:

输入: strs = [""]
输出: [[""]]
示例 3:

输入: strs = ["a"]
输出: [["a"]]
````

提示：

- 1 <= strs.length <= 104
- 0 <= strs[i].length <= 100
- strs[i] 仅包含小写字母

### （记住）解法：排序
````java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);//排序后相同的字母异位词就会变成相同的字符串
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());//如果key不存在，就返回一个空列表
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
````