## [3.1 找出数组中重复的数](https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

### 题目描述

给定一个长度为 `n` 的整数数组 `nums`，数组中所有的数字都在 `0∼n−1` 的范围内。

数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。

请找出数组中任意一个重复的数字。

**注意**：如果某些数字不在 `0∼n−1` 的范围内，或数组中不包含重复数字，则返回 `-1`；

**样例**

```
给定 nums = [2, 3, 5, 4, 3, 2, 6, 7]。

返回 2 或 3。
```

### （记住）解法: 下标法

从题目我们可以知道，数组长度为 n，所有数字都在 `0~n-1` 范围内。如果元素不重复，那么数组应该就是 `[0, 1, 2, ...n-1]`（假设给数组排完了序）。也就是说，递增排序后，数组中的元素值与其对应的下标应该是相同的，即下标为 0 的元素值也是 0，以此类推。

首先，我们可以遍历数组，若存在元素不在 `0~n-1` 的范围内，直接返回 -1。

接着，再次遍历数组，若下标 `i` 与对应元素 `nums[i]` 不同，即 `nums[i] != i`，我们应该把 `nums[i]` 这个元素交换到正确的位置 `nums[i]`上。交换前，先判断 `nums[i]` 与 `nums[nums[i]]` 这两个元素是否相同，相同说明存在重复元素，直接返回，否则进行 swap 交换。交换过后，我们需要再次判断 i 位置上的元素，因此，我们使用 while 循环。

可对照下方代码实现，加深理解。

```java
class Solution {

    /**
     * 查找数组中的重复元素
     *
     * @param nums 数组
     * @return 其中一个重复的元素
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length;

        // 若存在数组元素不在[0, n-1] 的范围内，直接返回-1
        for (int num : nums) {
            if (num < 0 || num >= n) {
                return -1;
            }
        }

        for (int i = 0; i < n; ++i) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {//一定要在nums[i] != i中，因为外面判断可能就是交换后的，这样才是原来的，如果有重复的就一定会进这个判断
                    // 说明位置i与位置nums[i]上的元素相同，直接返回该重复元素
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;

    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```
