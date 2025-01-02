## [3的幂](https://leetcode.cn/problems/power-of-three/description/)

给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。

整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x


````
示例 1：

输入：n = 27
输出：true
示例 2：

输入：n = 0
输出：false
示例 3：

输入：n = 9
输出：true
示例 4：

输入：n = 45
输出：false
````

提示：

- -2^31 <= n <= 2^31 - 1


进阶：你能不使用循环或者递归来完成本题吗？

### 解法：数学-除法

````java
class Solution {
    public boolean isPowerOfThree(int n) {
        while(n!=0 && n%3==0){//余数为0继续除3，不然就不是
            n/=3;
        }
        return n==1;//能整除最后结果是1
    }
}
````

### 解法：数学-约数

````java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;//2的31次方内1162261467是最大的2的幂，所以数可能是它的因子
    }
}
````