## [15. 二进制中 1 的个数](https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)
同：[191.位1的个数](https://leetcode.cn/problems/number-of-1-bits/description/)

### 题目描述

输入一个 32 位整数，输出该数二进制表示中 1 的个数。

**注意**：

- 负数在计算机中用其绝对值的补码来表示。

**样例 1**

```
输入：9
输出：2
解释：9的二进制表示是1001，一共有2个1。
```

**样例 2**

```
输入：-2
输出：31
解释：-2在计算机里会被表示成11111111111111111111111111111110，
      一共有31个1。
```

### 解法一：位运算-左移

利用整数 1，依次左移每次与 n 进行与运算，若结果不为 0，说明这一位上数字为 1，++cnt。

此解法 i 需要左移 32 次。

**不要用 n 去右移并与 1 进行与运算，因为 n 可能为负数，右移时会陷入死循环**。

```java
class Solution {

    /**
     * 求二进制中1的个数
     *
     * @param n 整数
     * @return 该整数的二进制中1的个数
     */
    public int NumberOf1(int n) {
        int i = 1;
        int cnt = 0;
        while (i != 0) {
            if ((n & i) != 0) {
                ++cnt;
            }
            i <<= 1;//左移1位替换
        }
        return cnt;
    }
}
```

### （记住）解法二：位运算-与运算

运算 `(n - 1) & n`，直至 n 为 0。运算的次数即为 n 的二进制中 1 的个数。

因为 n-1 会将 n 的最右边一位 1 改为 0，如果右边还有 0，则所有 0 都会变成 1。结果与 n 进行与运算，会去除掉最右边的一个 1。

举个栗子：

```
若 n = 1100，
n - 1 = 1011
n & (n - 1) = 1000

即：把最右边的 1 变成了 0。
```

> 把一个整数减去 1 之后再和原来的整数做位与运算，得到的结果相当于把整数的二进制表示中最右边的 1 变成 0。很多二进制的问题都可以用这种思路解决。

```java
class Solution {

    /**
     * 求二进制中1的个数
     *
     * @param n 整数
     * @return 该整数的二进制中1的个数
     */
    public int NumberOf1(int n) {
        int cnt = 0;
        while (n != 0) {
            ++cnt;
            n = n & (n - 1);//这是消除二进制中最后一位1的方法
        }
        return cnt;
    }
}
```


### 解法三

利用 Java API。

```java
class Solution {

    /**
     * 求二进制中1的个数
     *
     * @param n 整数
     * @return 该整数的二进制中1的个数
     */
    public int NumberOf1(int n) {
        return Integer.bitCount(n);
    }
}
```
