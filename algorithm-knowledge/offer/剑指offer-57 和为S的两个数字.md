## [57. 和为 s 的两个数字](https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/)


### 题目描述

输入一个数组和一个数字 s，在数组中查找两个数，使得它们的和正好是 s。

如果有多对数字的和等于 s，输出任意一对即可。

你可以认为每组输入中都至少含有一组满足条件的输出。

**样例**

```
输入：[1,2,3,4] , sum=7

输出：[3,4]
```

### 解法：哈希表

利用 set 记录元素即可。

*时间复杂度：$O(n)$*
```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * 在数组中找出和为target的两个数
     *
     * @param nums 数组
     * @param target 目标和
     * @return 满足条件的两个数构成的数组
     */
    public int[] findNumbersWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (set.contains(target - nums[i])) {
                return new int[]{target - nums[i], nums[i]};
            }
            set.add(nums[i]);
        }
        return null;
    }
}
```

### （记住）解法：双指针
左右指针移动，但是前提是必须有序。

*时间复杂度：$O(n)$*
```java

import java.util.Arrays;

class Solution {
    /**
     * 在数组中找出和为target的两个数
     *
     * @param nums 数组
     * @param target 目标和
     * @return 满足条件的两个数构成的数组
     */
    public int[] findNumbersWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        Arrays.sort(nums);//先排序
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] < target) {
                i++;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                return new int[]{nums[i], nums[j]};
            }
        }
        return null;
    }
}
```

