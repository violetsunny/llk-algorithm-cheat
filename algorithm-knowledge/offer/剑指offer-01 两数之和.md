## [两数之和](https://leetcode.cn/problems/two-sum/description/)
同：[167. 两数之和 II - 输入有序数组](https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/)

给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

你可以按任意顺序返回答案。


<pre>
示例 1：
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

示例 2：
输入：nums = [3,2,4], target = 6
输出：[1,2]

示例 3：
输入：nums = [3,3], target = 6
输出：[0,1]
</pre>


提示：
- 2 <= nums.length <= 104
- -109 <= nums[i] <= 109
- -109 <= target <= 109

只会存在一个有效答案


进阶：你可以想出一个时间复杂度小于 O(n^2) 的算法吗？

### 解法：哈希映射

````java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int c = target - nums[i];
            if (map.containsKey(c)) {
                return new int[]{map.get(c), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}

````

### （记住）解法：双指针

````java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) {
                return new int[]{start, end};
            } else if (sum > target) {
                end--;
            } else {
                start++;
            }

        }
        return new int[0];
    }
}
````

### 解法：二分查找
更通用,非递增序列也能用
````java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int start = i + 1;
            int end = numbers.length - 1;
            while (start <= end) {//要等于，因为需要拿到中间数值
                int mid = start + (end - start) / 2;
                int sum = numbers[i] + numbers[mid];
                if (target == sum) {
                    return new int[]{i, mid};
                } else if (sum > target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return new int[0];
    }
}
````
