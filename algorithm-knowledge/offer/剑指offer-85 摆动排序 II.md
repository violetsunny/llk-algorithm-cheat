## [摆动排序 II](https://leetcode.cn/problems/wiggle-sort-ii/description/)
给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。

你可以假设所有输入数组都可以得到满足题目要求的结果。


````
示例 1：

输入：nums = [1,5,1,1,6,4]
输出：[1,6,1,5,1,4]
解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
示例 2：

输入：nums = [1,3,2,2,3,1]
输出：[2,3,1,3,1,2]
````

提示：

- 1 <= nums.length <= 5 * 104
- 0 <= nums[i] <= 5000

题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果


进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？

### 解法：排序
- n为偶数：比如序列当前序列为 [0,1,2,3,4,5]，我们可以得到序列 [3,0,4,1,5,2]，然后将其反转即为 [2,5,1,4,0,3]。
- n为奇数：比如序列当前序列为 [0,1,2,3,4]，我们可以返回序列 [0,3,1,4,2]。同理我们将上述序列进行反转后[2,4,1,3,0]。
````java
class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] clone = Arrays.copyOf(nums, n);
        Arrays.sort(clone);
        int x = (n + 1) / 2;//奇数才能执行中间
        for (int i = 0, j = x - 1, k = n - 1; i < n; i = i + 2, j--, k--) {
            nums[i] = clone[j];//第一位中间开始
            if (i + 1 < n) {
                nums[i + 1] = clone[k];//第二位最后开始
            }
        }
    }
}
````