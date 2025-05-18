## [最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/description/)

给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的
子序列
。

````
示例 1：

输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
示例 2：

输入：nums = [0,1,0,3,2,3]
输出：4
示例 3：

输入：nums = [7,7,7,7,7,7,7]
输出：1
````

提示：

- 1 <= nums.length <= 2500
- -104 <= nums[i] <= 104

进阶：

- 你能将算法的时间复杂度降低到 O(n log(n)) 吗?

### 解法：动态规划 + 双指针

$O(n^2)$

1. 定义 dp[i] 为考虑前 i 个元素，以第i个数字结尾的最长上升子序列的长度。
2. 如果 $nums[i]>nums[j]$ , $dp[i]=Max(d[j])+1$ , 否则 $dp[i]=1$ 。
    - 状态方程：$dp[i]=max(dp[j])+1$ ,其中 0≤j<i 且 num[j]<num[i] ;
    - $length = Max(dp[i])$ ;最长序列长度就是dp[i]中最大值
3. 边界$dp[i]=1$;含义是每个元素都至少可以单独成为子序列，此时长度都为1。

````java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 0; i < nums.length; i++) {//填充dp[i]0-n之间的数据
            dp[i] = 1;//每次重新移动要重置为1.
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {//如果nums[j]<nums[i]，就是dp[j]+1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
````

### （记住）解法：贪心+二分查找

$O(n logn)$

整个算法流程为：

设当前已求出的最长上升子序列的长度为 len（初始时为 1），从前往后遍历数组 nums，在遍历到 nums[i] 时：

如果 nums[i]>d[len] ，则直接加入到 d 数组末尾，并更新 len=len+1；

否则，在 d 数组中二分查找，找到第一个比 nums[i] 小的数 d[k] ，并更新 d[k+1]=nums[i]。

以输入序列 [0,8,4,12,2] 为例：

第一步插入 0，d=[0]；

第二步插入 8，d=[0,8]；

第三步插入 4，d=[0,4]；

第四步插入 12，d=[0,4,12]；

第五步插入 2，d=[0,2,12]。

最终得到最大递增子序列长度为 3。

````java
class Solution {
    public int lengthOfLIS(int[] nums) {
       int len = 1, n = nums.length;
       if (n == 0) {
          return 0;
       }
       int[] d = new int[n + 1];
       d[1] = nums[0];//必须从1开始，nums[0]放在第一个
       for (int i = 1; i < n; ++i) {
          if (nums[i] > d[len]) {//所以这里就是i在和前一个数在比
             d[++len] = nums[i];
          } else {
             int l = 1;
             int r = len;
             int pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0，所以dp要从1开始
             while (l <= r) {//dp中找
                int mid = (l + r) >> 1;
                if (d[mid] < nums[i]) {//找第一个比nums[i]小的数
                   pos = mid;
                   l = mid + 1;
                } else {
                   r = mid - 1;
                }
             }
             d[pos + 1] = nums[i];//更新第一个比nums[i]小的数的后面位置的数。如果都找不到就是d[1],所以dp从1开始方便
          }
       }
       return len;
    }
}
````