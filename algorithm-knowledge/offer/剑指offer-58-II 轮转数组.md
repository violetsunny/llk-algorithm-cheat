## [58.3 轮转数组](https://leetcode.cn/problems/rotate-array/description/)

给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。


````
示例 1:
输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
````
````
示例 2:
输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释:
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]
````

提示：
- 1 <= nums.length <= 105
- -231 <= nums[i] <= 231 - 1
- 0 <= k <= 105


进阶：
- 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
- 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？

### （记住）解法：翻转整个数组
先翻转整个数组，在翻转k个，再翻转n-k;
我们以 n=7，k=3 为例进行如下展示：
````
操作	                          结果
原始数组          	        1 2 3 4 5 6 7
翻转所有元素	                7 6 5 4 3 2 1
翻转 [0,kmodn−1] 区间的元素	5 6 7 4 3 2 1
翻转 [kmodn,n−1] 区间的元素	5 6 7 1 2 3 4
````

````java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        swap(nums, 0, n - 1);
        swap(nums, 0, k - 1);
        swap(nums, k, n - 1);
    }

    private void swap(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
````