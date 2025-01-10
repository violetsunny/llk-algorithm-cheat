## [至少有K个重复字符的最长子串](https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/description/)

给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。

如果不存在这样的子字符串，则返回 0。


````
示例 1：

输入：s = "aaabb", k = 3
输出：3
解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
示例 2：

输入：s = "ababbc", k = 2
输出：5
解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
````

提示：

- 1 <= s.length <= 104
- s 仅由小写英文字母组成
- 1 <= k <= 105

### 解法：分治 + 哈希表

````java
class Solution {
    public int longestSubstring(String s, int k) {
        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }

    public int dfs(String s, int l, int r, int k) {
        if (l>r){
            return 0;
        }
        if (r-l+1<k){//长度小于k肯定不符合了
            return 0;
        }
        
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {//计算字符出现次数
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {//找到一个不符合的字符
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return r - l + 1;
        }

//        int i = l;
//        int max = 0;
//        while (i <= r) {
//            while (i <= r && s.charAt(i) == split) {//如果当前是不符合字符，就右移
//                i++;
//            }
//            if (i > r) {
//                break;
//            }
//            int start = i;//第一个符合的位置
//            while (i <= r && s.charAt(i) != split) {//最后一个符合的位置
//                i++;
//            }
//
//            int length = dfs(s, start, i - 1, k);//第一个到最后一个符合位置的大小
//            max = Math.max(ret, length);//历次最大的
//        }
        
        String[] ss = s.split(String.valueOf(split));//分割字符进行统计
        int max = 0;
        for(String s1:ss){
            int temp = dfs(s1,0,s1.length()-1,k);//因为有不符合字符，必然不能包含，只能分割重新查找
            max = Math.max(max,temp);
        }
        return max;
    }
}
````