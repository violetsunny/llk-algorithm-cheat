## [阶乘后的零](https://leetcode.cn/problems/factorial-trailing-zeroes/description/)

给定一个整数 n ，返回 n! 结果中尾随零的数量。

提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1


````
示例 1：

输入：n = 3
输出：0
解释：3! = 6 ，不含尾随 0
示例 2：

输入：n = 5
输出：1
解释：5! = 120 ，有一个尾随 0
示例 3：

输入：n = 0
输出：0
````

提示：

- 0 <= n <= 104

### （记住）解法：数学-10的质因子
一般地，n! 的尾零个数，取决于 n! 可以分解出多少个 10。由于 10=2×5，我们需要知道 n! 中质因子 2 的个数、质因子 5 的个数，二者的较小值，即为 n! 的尾零个数。

而5肯定比2少，其实就是找5的质因子数。

公式：$ n / p^k = n / p^(k-1) / p $

p就是质因子，这里就是5. 只要算出有多少k的质因子5.实际上就是计算1-n之中有多少个5的因数
````java
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        while (n != 0) {
            n /= 5;
            res += n;
        }
        return res;
    }
}
````