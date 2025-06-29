## [56 - I. 数组中数字出现的次数](https://leetcode.cn/problems/single-number-iii/description/)
同：[260. 只出现一次的数字 III](https://leetcode.cn/problems/single-number-iii/description/)
### 题目描述

一个整型数组里除了两个数字之外，其他的数字都出现了两次。

请写程序找出这两个只出现一次的数字。

你可以假设这两个数字一定存在。

**样例**

```
输入：[1,2,3,3,4,4]

输出：[1,2]
```

### 解法：位运算

如果数组有一个数字出现一次，其它数字都出现两次。那么我们很容易通过异或 `^` 运算求出来。

而现在是有两个数字出现一次，那么我们考虑一下怎么将这两个数字隔开，之后我们对隔开的数组分别进行异或，不就求出来了？

我们先异或，求得的结果是两个不相同的数字异或的结果，结果一定不为 0。那么它的二进制表示中一定有 1。我们根据这个 1 在二进制中出现的位置。将数组划分，这样，两个只出现一次的数字就会被隔开，之后求异或即可。
````
比如数组中异或 ^ 后的结果 z = 1010,只需要 & 操作后找到最低位不同的。
就是 0010 即可。通过 0010 和数组中其他数组 & 操作。结果为0和为1分开两个数组。
这样两个数组再异或后就是两个不同的数。
````

时间复杂度：$O(n)$
```java
class Solution {
    /**
     * 求数组中只出现一次的两个数字
     *
     * @param nums 数字
     * @return 两个数字组成的数组
     */
    public int[] findNumsAppearOnce(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int xorRes = 0;
        for (int e : nums) {
            xorRes ^= e;//异或后剩两个不同的数异或
        }
        int[] res = new int[2];
        int index = indexOf1(xorRes);//找下标
        for (int e : nums) {
            if (isBit1(e, index)) {
                res[0] ^= e;//拆开计算
            } else {
                res[1] ^= e;
            }
        }
        return res;
    }

    private int indexOf1(int val) {//int m = val & -val;//x 的二进制表示中最低位那个 1
        int index = 0;
        while ((val & 1) == 0) {//找到不同位数下标，就是第几位不同
            val = val >> 1;
            ++index;
        }
        return index;
    }

    private boolean isBit1(int val, int index) {
        for (int i = 0; i < index; ++i) {
            val = val >> 1;//当前数找到位数下标
        }
        return (val & 1) == 1;
    }
}

```
### （记住）解法：位运算 - 帅地的简单写法
拆开计算

时间复杂度：$O(n)$
````java
class Solution {

    public int[] findNumsAppearOnce(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int xorRes = 0;
        for (int e : nums) {
            xorRes ^= e;//异或后剩两个不同的数异或
        }
        int[] res = new int[2];
        int m = indexOfM(xorRes);//找下标
        for (int e : nums) {
            if ((e & m) == 1) {//然后进行与&操作看是否为1，然后就可以拆开成两个数组进行^操作，相同的会消掉，留下不同的，两个数组不同的就是两个不同的数
                res[0] ^= e;//拆开计算
            } else {
                res[1] ^= e;//也可以：res[1] = res[0] ^ xorRes,因为xorRes本来就是e0 ^ e1来的
            }
        }
        return res;


    }

    private int indexOfM(int val) {
        int m = 1;
        while ((m & val) == 0) {
            m = m << 1; //左移找到第一个不同位的位置的数，是最低位不相等的位置
        }
        return m;

        // 防止溢出  x 的二进制表示中最低位那个 1
        //return (val == Integer.MIN_VALUE ? val : val & (-val));
    }

}
````
