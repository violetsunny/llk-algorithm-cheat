## [59 - I. 滑动窗口的最大值](https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)
同:[239. 滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/description/)

### 题目描述

给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。

例如，如果输入数组 `[2, 3, 4, 2, 6, 2, 5, 1]` 及滑动窗口的大小 3,那么一共存在 6 个滑动窗口，它们的最大值分别为 `[4, 4, 6, 6, 6, 5]`。

**注意：**

- 数据保证 k 大于 0，且 k 小于等于数组长度。

**样例**

```
输入：[2, 3, 4, 2, 6, 2, 5, 1] , k=3

输出: [4, 4, 6, 6, 6, 5]
```

### 解法：双向队列

利用双向队列，保证队列头部存放的是最大值的下标，当队列头部下标过期时弹出。

细节：

- 当数组元素小于队列头部下标对应的元素时，在队列尾部中插入数组元素下标。（如果队列尾部有比该元素小的元素，先弹出，再插入。）
- 当数组元素大于或等于队列头部下标构成的元素时，弹出元素直至队列为空，再插入数组元素下标。

```java
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    /**
     * 求滑动窗口的最大值
     *
     * @param nums 数组
     * @param k 滑动窗口的大小
     * @return 最大值构成的数组
     */
    public int[] maxInWindows(int[] nums, int k) {
        if (nums == null || k < 1 || k > nums.length) {
            return null;
        }
        Deque<Integer> queue = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; ++i) {
            if (queue.isEmpty()) {
                queue.addLast(i);
            } else {
                if (nums[queue.getFirst()] < nums[i]) {
                    while (!queue.isEmpty()) {
                        queue.removeFirst();
                    }
                } else {
                    while (nums[queue.getLast()] < nums[i]) {
                        queue.removeLast();
                    }
                }
                queue.addLast(i);
            }
        }

        for (int i = k; i < nums.length; ++i) {
            res[i - k] = nums[queue.getFirst()];
            if (nums[i] < nums[queue.getFirst()]) {
                while (nums[queue.getLast()] < nums[i]) {
                    queue.removeLast();
                }
            } else {
                while (!queue.isEmpty()) {
                    queue.removeFirst();
                }
            }
            queue.addLast(i);
            if (i - queue.getFirst() == k) {
                queue.removeFirst();
            }
        }
        res[nums.length - k] = nums[queue.getFirst()];
        return res;
    }
}
```

### （记住）解法：滑动窗口-帅地写法：
<pre>
<strong>输入:</strong> <em>nums</em> = <code>[1,3,-1,-3,5,3,6,7]</code>, 和 <em>k</em> = 3
<strong>输出: </strong><code>[3,3,5,5,6,7] 
<strong>解释: 
</strong></code>
  滑动窗口的位置                最大值             队列
---------------               -----             -----
[1  3  -1] -3  5  3  6  7       3               [3 -1]
 1 [3  -1  -3] 5  3  6  7       3               [3 -1 -3]
 1  3 [-1  -3  5] 3  6  7       5               [5]
 1  3  -1 [-3  5  3] 6  7       5               [5 3]
 1  3  -1  -3 [5  3  6] 7       6               [6]
 1  3  -1  -3  5 [3  6  7]      7               [7]
</pre>

*时间复杂度：$O(n)$，空间复杂度：$O(k)$*
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];//大小为n-k+1,本身还有一个最大值，所以要多出一个
        Deque<Integer> q = new ArrayDeque<>();//为了计算大小，这个放的是下标
        for (int i = 0; i < n; ++i) {
            if (!q.isEmpty() && i - q.peek() + 1 > k) {
                q.poll();//如果i和头元素下标大于k,右移，剔除左边元素
            }
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();//从后往前，剔除小于nums[i]的数。这样当前数就是后面窗口最大值，保证头元素最大
            }
            q.offer(i);//末尾放入
            if (i + 1 - k >= 0) {//因为i从0开始
                ans[i + 1 - k] = nums[q.peek()];//头元素最大
            }
        }
        return ans;
    }
}
```
