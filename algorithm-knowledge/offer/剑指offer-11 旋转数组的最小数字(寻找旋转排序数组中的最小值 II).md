## [11. 旋转数组的最小数字](https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)
同[154. 寻找旋转排序数组中的最小值 II](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/description/)
### 题目描述

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。

输入一个升序的数组的一个旋转，输出旋转数组的最小元素。

例如数组 {3,4,5,1,2} 为 {1,2,3,4,5} 的一个旋转，该数组的最小值为 1。

数组可能包含重复项。

**注意**：数组内所含元素非负，若数组大小为 0，请返回 -1。

**样例**

```
输入：nums=[2,2,2,0,1]

输出：0
```

### 解法一：遍历

直接遍历数组找最小值，时间复杂度 `O(n)`，不推荐。

```java
class Solution {

    /**
     * 获取旋转数组的最小元素
     *
     * @param nums 旋转数组
     * @return 数组中的最小值
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int min = nums[0];
        int n = nums.length;
        if (min < nums[n - 1]) {
            return min;
        }
        for (int i = 1; i < n; ++i) {
            min = Math.min(min, nums[i]);
        }
        return min;
    }
}
```

### （记住）解法二：双指针（二分查找）

利用指针 `start`,`end` 指向数组的首尾，如果 `nums[start] < nums[end]`，说明数组是递增数组，直接返回 `nums[start]`。否则进行如下讨论。

计算中间指针 `mid`：

- 如果此时 `nums[start]`, `nums[end]`, `nums[mid]` 两两相等，此时无法采用二分方式，只能通过遍历区间 `[start,end)` 获取最小值；
- 如果此时 `start`,`end` 相邻，说明此时 `end` 指向的元素是最小值，返回 `nums[end]`；
- 如果此时 `nums[mid] >= nums[start]`，说明 `mid` 位于左边的递增数组中，最小值在右边，因此，把 `start` 指向 `mid`，此时保持了 `start` 指向左边递增子数组；
- 如果此时 `nums[mid] <= nums[end]`，说明 `mid` 位于右边的递增数组中，最小值在左边，因此，把 `end` 指向 `mid`，此时保持了 `end` 指向右边递增子数组。

时间复杂度$O(logn)-O(n)$
```java
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (nums[l] < nums[r]) {//l r内单调递增，就可以跳出。
                break;
            }
            int m = (l + r) >>> 1;
            if (nums[m] > nums[l]) {
                l = m + 1;
            } else if (nums[m] < nums[l]) {
                r = m;//不能m-1，因为m本身可能是旋转点
            } else {
                ++l;//往右移动，也可以认为剪枝。不能直接l=m+1跨过去，因为 2 1 2 2 2
            }
        }
        return nums[l];
    }
}
```