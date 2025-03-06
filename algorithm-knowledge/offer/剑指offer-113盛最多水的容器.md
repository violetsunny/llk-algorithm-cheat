## [11. 盛最多水的容器](https://leetcode.cn/problems/container-with-most-water/description/)

给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器。


````
示例 1：



输入：[1,8,6,2,5,4,8,3,7]
输出：49
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
示例 2：

输入：height = [1,1]
输出：1
````

提示：

- n == height.length
- 2 <= n <= 105
- 0 <= height[i] <= 10^4

### 解法：双指针+贪心
双指针两边移动，求取面积。因为移动过程中底部长度会缩短，所以需要选择高度低先移动才有可能面积更大。相等也移动某一边，防止有更高的出现。
````java
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int n = height.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int area = (r - l) * Math.min(height[r], height[l]);
            max = Math.max(area, max);//比较最大的面积
            if (height[r] > height[l]) {
                l++;//因为height[r]大于height[l]，要想max更大，只能l往右移动知道更大的边
            } else {
                r--;
            }
        }

        return max;
    }
}
````