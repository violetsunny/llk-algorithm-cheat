## [53 - I. 在排序数组中查找数字 I](https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/)
同：[34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/)

### 题目描述

统计一个数字在排序数组中出现的次数。

例如输入排序数组 `[1, 2, 3, 3, 3, 3, 4, 5]` 和数字 3，由于 3 在这个数组中出现了 4 次，因此输出 4。

**样例**

```
输入：[1, 2, 3, 3, 3, 3, 4, 5] ,  3

输出：4
```

### （记住）解法：找数模板

找出第一个 k 和最后一个 k 出现的位置。

找第一个 k 时，利用二分法，如果 `nums[m] == k`，判断它的前一个位置是不是也是 k，如果不是，说明这是第一个 k，直接返回。如果是，那么递归在左边查找第一个 k。

找最后一个 k 也同理。

*时间复杂度 $O(log n)$*
```java
class Solution {
    /**
     * 求数字k在排序数组中出现的次数
     *
     * @param nums 数组
     * @param target 数字k
     * @return k在数组中出现的次数
     */
    public int getNumberOfK(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0, end = nums.length - 1;
        int first = getFirstK(nums, start, end, target);
        int last = getLastK(nums, start, end, target);
        if (first > -1 && last > -1) {
            return last - first + 1;
        }
        return 0;
    }

    private int getFirstK(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int result = -1; // 初始化结果为 -1，若未找到则返回该值
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private int getLastK(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int result = -1; // 初始化结果为 -1，若未找到则返回该值

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;

    }
}
```
