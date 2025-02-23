# [72.回文数字](https://leetcode.cn/problems/palindrome-number/description/)

给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

回文数
是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

例如，121 是回文，而 123 不是。

````
示例 1：
输入：x = 121
输出：true
示例 2：
输入：x = -121
输出：false
解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3：
输入：x = 10
输出：false
解释：从右向左读, 为 01 。因此它不是一个回文数。
````


提示：
- -231 <= x <= 231 - 1

进阶：你能不将整数转为字符串来解决这个问题吗？


````java
class Solution {
    public Boolean palindrome(int x) {
        String s = String.valueOf(x);
        char[] chars = s.toCharArray();
        boolean res= true;
        for(int i=0;i<(s.length()-1)/2;i++){
            if (chars[i] != chars[s.length() - 1 - i]) {
                res = false;
                break;
            }
        }
        return res;
    }
}

````
第二种写法，不转成字符串
````java
class Solution {
    public Boolean palindrome(int x) {
        if(x >= 0 && x < 10){
            return true;
        }
        if(x < 0 || (x%10 == 0)) {
            return false;
        }

        int res = 0;
        while (x > res) {//后面往前，前面的往后
            res = res * 10 + x % 10;
            x /= 10;
        }
        return x == res || x == res / 10;
    }
}

````
