## [丢失的数字](https://leetcode.cn/problems/missing-number/description/)

给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。


````
示例 1：

输入：nums = [3,0,1]
输出：2
解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
示例 2：

输入：nums = [0,1]
输出：2
解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
示例 3：

输入：nums = [9,6,4,2,3,5,7,0,1]
输出：8
解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
示例 4：

输入：nums = [0]
输出：1
解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
````

提示：

- n == nums.length
- 1 <= n <= 104
- 0 <= nums[i] <= n
- nums 中的所有数字都 独一无二


进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?

### 解法：排序

````java
import java.util.Arrays;

class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }
}
````
### 解法：位运算
通过a ^ a = 0;的特性，对0 - n的数和nums的数进行异或，那最后剩下的就是单独存在的数就是缺失的数
````java
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i <= nums.length; i++) {
            res ^= i;
        }
        for (int num : nums) {
            res ^= num;//异或消除
        }
        return res;//剩下的就是缺失的数字
    }
}
````

### 解法：数学-求和
既然是0-n之间的数，那就先把0-n求和，再减去现在有的数就是缺失的数
````java
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i <= nums.length; i++) {
            res += i;
        }
        for (int num : nums) {
            res -= num;
        }
        return res;
    }
}
````

### （记住）$高斯求和公式： total = n*(n+1)/2$
````java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        for (int i = 0; i < n; i++) {
            total -= nums[i];
        }
        return total;
    }
}
````