## [44. 数字序列中某一位的数字](https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/)
同：[400. 第 N 位数字](https://leetcode.cn/problems/nth-digit/description/)

### 题目描述

数字以 `123456789101112131415…` 的格式序列化到一个字符序列中。

在这个序列中，第 5 位（从 1 开始计数）是 5，第 13 位是 1，第 19 位是 4，等等。

请写一个函数求任意位对应的数字。

### 解法

举个栗子，求序列第 1000 位。

序列的前 10 位是 `1~9`， 这 10 个只有一位的数字。显然第 1000 位在这 9 个数字之后，因此这 `9` 个数字可以直接跳过。再从后面序列中找第 `991（991=1000-9）` 位的数字。接下来有 `90` 个两位数，共 `180` 位，由于 `991>180`，所以继续跳过。从 `881` 找...最后可以找到对应的数字以及数字的某一位。

````java
public class Solution {
    /**
     * 求数字序列中某一位的数字
     *
     * @param n 第n位
     * @return 第n位的数字
     */
    public int digitAtIndex(int n) {
        if (n < 0) {
            return -1;
        }
        int digits = 1;
        while (true) {
            long numbers = countOfIntegers(digits);
            if (n < digits * numbers) {
                break;
            }
            n -= numbers * digits;
            ++digits;
        }
        return digitAtIndex(digits, n);

    }

    private long countOfIntegers(int digits) {
        return digits == 1
                ? 10
                : (int) (9 * Math.pow(10, digits - 1));
    }

    private int digitAtIndex(int digits, int n) {
        int beginNumber = getBeginNumber(digits);
        int val =  beginNumber + n / digits;
        int indexFromRight = digits - n % digits;
        for (int i = 1; i < indexFromRight; ++i) {
            val /= 10;
        }
        return val % 10;
    }

    private int getBeginNumber(int digits) {
        return digits == 1
                ? 0
                : (int) Math.pow(10, digits - 1);
    }
}
````

### （记住）解法二: 数学
位数为 $k$ 的最小整数和最大整数分别为 $10^{k-1}$ 和 $10^k-1$，因此 $k$ 位数的总位数为 $k \times 9 \times 10^{k-1}$。

我们用 $k$ 表示当前数字的位数，用 $cnt$ 表示当前位数的数字的总数，初始时 $k=1$, $cnt=9$。

每次将 $n$ 减去 $cnt \times k$，当 $n$ 小于等于 $cnt \times k$ 时，说明 $n$ 对应的数字在当前位数的数字范围内，此时可以计算出对应的数字。

具体做法是，首先计算出 $n$ 对应的是当前位数的哪一个数字，然后计算出是该数字的第几位，从而得到该位上的数字。

时间复杂度 $O(\log_{10} n)$，空间复杂度 $O(1)$。其中 $n$ 为给定的数字。
````
例如：n = 1000位; 1-9是9个，10-99是90个*2=180，100-999是900个*3=2700....类推
1000 > 9 => n-9=991;
991 > (90*2) => 991-(90*2)=811;
811 < (900*3) => 811;

num = 100 + (811-1) / 3
    = 100 + 270 
    = 370
    
index = (811-1) % 3 + 1 = 1; 

res = 3;
````
公式： 

找到`i(第几位)`和`n(减去后剩下的n)`后

$num = 10^{i-1} + (n - 1) / i;$ 

$index = (n - 1) \% i + 1;$

$res = String(num).charAt(index-1); $

````java
class Solution {
    public int findNthDigit(int n) {
        long bit = 1;
        int i = 1;
        long count = 9;
        while (count < n) {
            n = (int) (n - count);//找到减去后剩下的n
            bit = bit * 10;// 1 10 100 1000 ...
            i++;//第几位 十位是2位 百位是3位 千位是4位
            count = bit * i * 9;//当前位多少数，比如十位：10-99 10*2*9=180个
        }
        long num = bit + (n - 1) / i;//找到哪个数字，因为从0开始所以当前n-1，i表示当前几位数字：3位，每个数字长度3，所以要/3。就是从第几位的数字往后数
        int idx = (n - 1) % i + 1;//找到下标，比如3位，肯定是3的余数,+1标识第几个数
        int res = String.valueOf(num).charAt(idx - 1) - '0';//比如num=370，idx=1，就是3
//        int res = (int)(num / Math.pow(10, i - idx)) % 10;
        return res;
    }
}
````