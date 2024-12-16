## [最长连续序列](https://leetcode.cn/problems/longest-consecutive-sequence/description/)
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

 
````
示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9
 
````
提示：

- 0 <= nums.length <= 105
- -109 <= nums[i] <= 109

### 解法： 加减
只需要对每个开头的数进行循环，直到这个序列不再连续，因此复杂度是O(n)。 以题解中的序列举例:

[100，4，200，1，3，4，2]

去重后的哈希序列为：

[100，4，200，1，3，2]

按照上面逻辑进行判断：
1. 元素100是开头,因为没有99，且以100开头的序列长度为1;
2. 元素4不是开头，因为有3存在，过;
3. 元素200是开头，因为没有199，且以200开头的序列长度为1;
4. 元素1是开头，因为没有0，且以1开头的序列长度为4，因为依次累加，2，3，4都存在;
5. 元素3不是开头，因为2存在，过;
6. 元素2不是开头，因为1存在，过。
````java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
````