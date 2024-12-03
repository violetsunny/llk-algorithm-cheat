## [56 - II. 数组中数字出现的次数 II](https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/)


### 题目描述

在一个数组中除了一个数字只出现一次之外，其他数字都出现了三次。

请找出那个只出现一次的数字。

你可以假设满足条件的数字一定存在。

**思考题：**

- 如果要求只使用 `O(n)` 的时间和额外 `O(1)` 的空间，该怎么做呢？

### 解法：数学，被3整除

分别累加数组中每个元素的二进制中出现的数字，那么出现三次的数字，二进制位上最后累加的结果一定能被 3 整除。不能被 3 整除的位，就属于只出现一次的数字。

```java
class Solution {
    /**
     * 找出数组中只出现一次的数字，其它数字都出现三次
     *
     * @param nums 数字
     * @return 只出现一次的数字
     */
    public int findNumberAppearingOnce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] bits = new int[32];
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int val = nums[i];
            for (int j = 0; j < 32; ++j) {
                bits[j] += (val & 1);//求出位数上1 0 相加的
                val = val >> 1;//移位
            }
        }
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            if (bits[i] % 3 != 0) {//相加后的结果 %3
                res += Math.pow(2, i);//翻译为10进制
            }
        }
        return res;
    }
}
```
帅地简单写法
```java
class Solution {
    /**
     * 找出数组中只出现一次的数字，其它数字都出现三次
     *
     * @param nums 数字
     * @return 只出现一次的数字
     */
    public int findNumberAppearingOnce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] bits = new int[32];
        int n = nums.length;
        int m = 1;
        int res = 0;
        for(int i = 0;i<32;i++){
            for(int num:nums){
                if((num & m) != 0){//不等于0加1
                    bits[i]++;
                }
            }
            bits[i] = bits[i] % 3;
            res = res + bits[i] * m;//转十进制
            m = m << 1;//左移一位比较
        }
        return res;
    }
}
```

### 解法：状态机