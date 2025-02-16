## [组合总和 II](https://leetcode.cn/problems/combination-sum-ii/description/)

给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用 一次 。

注意：解集不能包含重复的组合。


````
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
输出:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
输出:
[
[1,2,2],
[5]
]
````

提示:

- 1 <= candidates.length <= 100
- 1 <= candidates[i] <= 50
- 1 <= target <= 30

### 解法：回溯
````java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);//排序后才能判断
        boolean[] visited = new boolean[candidates.length + 1];
        int cur = 0;//cur 表示当前要填排列的第几个数
        backtrack(candidates, target, cur, visited);
        return res;
    }

    private void backtrack(int[] candidates, int target, int cur, boolean[] visited) {
        // 存入
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 剪枝二：从 cur 开始遍历，避免生成重复子集
        // 剪枝三：从 cur 开始遍历，避免重复选择同一元素
        for (int i = cur; i < candidates.length; i++) {
            // 剪枝一：若子集和超过 target ，则直接结束循环
            // 这是因为数组已排序，后边元素更大，子集和一定超过 target
            if (target < candidates[i]) {
                continue;
            }
            // 剪枝四：如果该元素与左边元素相等，说明该搜索分支重复，直接跳过
            if (visited[i] || (i > 0 && candidates[i] == candidates[i - 1] && !visited[i - 1])) {
                continue;
            }
            //选择
            list.add(candidates[i]);
            visited[i] = true;
            //下一个
            backtrack(candidates, target - candidates[i], i + 1, visited);
            //还原
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
````