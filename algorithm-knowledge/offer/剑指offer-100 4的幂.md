## [4的幂](https://leetcode.cn/problems/power-of-four/description/)
给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。

整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x


````
示例 1：

输入：n = 16
输出：true
示例 2：

输入：n = 5
输出：false
示例 3：

输入：n = 1
输出：true
````

提示：

- -231 <= n <= 231 - 1


进阶：你能不使用循环或者递归来完成本题吗？

### 解法：位运算
如果是4的幂，那肯定是2的幂。所以先判断2，然后再判断4。

从低位0开始偶数为1是4的幂，所以设置一个mask标识，mask=(10101010101010101010101010101010)

我们将 n 和 mask 进行按位与运算，如果结果为 0，说明 n 二进制表示中的 1 出现在偶数的位置。
````java
class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}
````

### （记住）解法：数学-取模
如果是4的幂，那肯定是2的幂。如果按4取模可能是2的，所以可以按照3取模并余1.
````java
class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & -n) == n && (n % 3) == 1;// 2的幂中找出除3余1的数
    }
}
````