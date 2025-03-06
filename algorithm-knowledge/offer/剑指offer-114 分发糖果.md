## [135. 分发糖果](https://leetcode.cn/problems/candy/description/)

n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

你需要按照以下要求，给这些孩子分发糖果：

每个孩子至少分配到 1 个糖果。
相邻两个孩子评分更高的孩子会获得更多的糖果。
请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。


````
示例 1：

输入：ratings = [1,0,2]
输出：5
解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
示例 2：

输入：ratings = [1,2,2]
输出：4
解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
````

提示：

- n == ratings.length
- 1 <= n <= 2 * 104
- 0 <= ratings[i] <= 2 * 104

### 解法：双遍历
1. 先从左至右遍历学生成绩ratings，按照以下规则给糖，并记录在left中：

   - 先给所有学生1颗糖；

   - 若$ratings[i]>ratings[i−1]$，则第i名学生糖比第i−1名学生多1个。

   - 若$ratings[i] <=ratings[i−1] $ ，则第i名学生糖数量不变。（交由从右向左遍历时处理。）

   - 经过此规则分配后，可以保证所有学生糖数量满足左规则。
2. 同理，在此规则下从右至左遍历学生成绩并记录在right中，可以保证所有学生糖数量满足右规则。

3. 最终，取以上2轮遍历left和right对应学生糖果数的最大值，这样则同时满足左规则和右规则，即得到每个同学的最少糖果数量。

````
左规则把右项大于左项的处理一遍，右规则把右项小于左项的处理一遍，取最大值就是处理结果.
会发现，最大值在左规则数组的的是右项大于左项的，最大值在右规则数组的是右项小于左项的，最大值左规则数组和右规则数组相同的，是右大左小变成右小左大的点。
````

````java
class Solution {
    public int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        // 计算左侧
        for(int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
 
        int count = left[ratings.length - 1];
        // 计算右侧
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            count += Math.max(left[i], right[i]);//两边比较最大的，因为左右两侧都会影响
        }
        return count;
    }
}
````