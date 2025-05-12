## [三数之和](https://leetcode.cn/problems/3sum/description/)

给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。


````
示例 1：

输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
示例 2：

输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。
示例 3：

输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。
````

提示：

- 3 <= nums.length <= 3000
- -10^5 <= nums[i] <= 10^5

### 解法-：排序+双指针

````java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);// 要先排序才能减少重复
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {//不能重复
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            int target = nums[i];

            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum + target == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    // 跳过重复的
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if (sum + target > 0) {//指针移动
                    r--;
                } else {
                    l++;
                }

            }
        }

        return res;
    }
}
````