## [72.分割回文串](https://leetcode.cn/problems/palindrome-partitioning/description/)
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

示例 1：

输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]
示例 2：

输入：s = "a"
输出：[["a"]]

提示：

1 <= s.length <= 16
s 仅由小写英文字母组成

````java
class Solution {
    private List<List<String>> reslist = new ArrayList<>();

    public List<List<String>> partition(String s) {
        palindrome(s,0,new ArrayList<>());
        return reslist;
    }

    private void palindrome(String s,int index,List<String> res){
        if(index == s.length()){
            reslist.add(new ArrayList<>(res));
            return;
        }
        for(int i=index;i<s.length();i++){
            if(isPalindrome(s,index,i)){
                res.add(s.substring(index,i+1));//从第一个字符，前两个字符。。。
                palindrome(s,i+1,res);//从第二次字符，第二第三两个字符。。。。
                res.remove(res.size()-1);//取到最后返回，一定要清空
            }
        }

    }

     private boolean isPalindrome(String s ,int left, int right){
        while (left <= right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
````