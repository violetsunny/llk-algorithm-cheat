## [65. 不用加减乘除做加法](https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/)
同：[371. 两整数之和](https://leetcode.cn/problems/sum-of-two-integers/description/)

### 题目描述

写一个函数，求两个整数之和，要求在函数体内不得使用＋、－、×、÷ 四则运算符号。

**样例**

```
输入：num1 = 1 , num2 = 2

输出：3
```

### （记住）解法: 位运算

先对两数进行异或，求得相加不仅位的结果。再循环对两数进行按位与运算，并左移一位，直至进位为 0。

```java
class Solution {

    /**
     * 不用加减乘除做加法
     *
     * @param a 数1
     * @param b 数2
     * @return 两数之和
     */
    public int getSum(int a, int b) {
        // 循环，当进位为 0 时跳出
        while (b != 0) {
            int c = (a & b) << 1;  // c = 进位
            a = a ^ b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;//最后不需要再进位后的a 异或后就是结果
    }
}
```