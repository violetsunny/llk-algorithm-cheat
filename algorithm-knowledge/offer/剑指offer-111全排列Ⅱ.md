## [全排列 II](https://leetcode.cn/problems/permutations-ii/description/)
给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。


````
示例 1：

输入：nums = [1,1,2]
输出：
[[1,1,2],
[1,2,1],
[2,1,1]]
示例 2：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
````

提示：

- 1 <= nums.length <= 8
- -10 <= nums[i] <= 10


````java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length + 1]; // visited[j] 表示 nums[j] 是否已经填入排列
        backtrack(0, nums, visited);
        return ans;
    }

    // i 表示当前要填排列的第几个数
    private void backtrack(int i, int[] nums, boolean[] visited) {
        if (i == nums.length) { // 填完了
            ans.add(new ArrayList<>(path));
            return;
        }
        // 枚举 nums[j] 填入 path[i]
        for (int j = 0; j < nums.length; j++) {
            // 如果 nums[j] 已填入排列，continue
            // 如果 nums[j] 和前一个数 nums[j-1] 相等，且 nums[j-1] 没填入排列，continue
            if (visited[j] || (j > 0 && nums[j] == nums[j - 1] && !visited[j - 1])) {
                continue;
            }
            path.add(nums[j]); // 填入排列
            visited[j] = true; // nums[j] 已填入排列（注意标记的是下标，不是值）
            backtrack(i + 1, nums, visited); // 填排列的下一个数
            visited[j] = false; // 恢复现场
            path.remove(i);// 还原
        }
    }
}
````