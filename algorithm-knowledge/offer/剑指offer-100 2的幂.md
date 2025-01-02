## [2的幂](https://leetcode.cn/problems/power-of-two/description/)

给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。

如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。


````
示例 1：

输入：n = 1
输出：true
解释：20 = 1
示例 2：

输入：n = 16
输出：true
解释：24 = 16
示例 3：

输入：n = 3
输出：false
````

提示：

- -231 <= n <= 231 - 1


进阶：你能够不使用循环/递归解决此问题吗？


### 解法：位运算
1.n & (n-1): 

8 & 7 => 1000 & 0111 = 0

2.(n & -n):

由于负数是按照补码规则在计算机中存储的，−n 的二进制表示为 n 的二进制表示的每一位取反再加上 1

8 & -8 => 00001000 &  (11110111 + 1 = 11111000) = 1000
````java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n>0 && (n & (n-1)) ==0;
    }

    public boolean isPowerOfTwo(int n) {
        return n>0 && (n & -n)== n;
    }
}
````

### 解法：数学-约数
如果是2的幂，肯定也是2最大幂的因子
````java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n>0 && ((1<<30) % n) == 0;
    }
}
````