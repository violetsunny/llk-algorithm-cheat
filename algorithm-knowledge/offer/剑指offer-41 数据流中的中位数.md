## [41. 数据流中的中位数](https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/)
同：[295. 数据流的中位数](https://leetcode.cn/problems/find-median-from-data-stream/description/)
### 题目描述

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用`Insert()`方法读取数据流，使用`GetMedian()`方法获取当前读取数据的中位数。

### 解法：大根堆 + 小根堆

利用大根堆存放较小的一半元素，小根堆存放较大的一半元素。维持大小堆的元素个数差不超过 1。

时间复杂度`O(logn)`,空间复杂度`O(n)`

```java
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x);

    /**
     * 插入一个数
     *
     * @param num 数
     */
    public void Insert(Integer num) {

        if (maxHeap.isEmpty() || num < maxHeap.peek()) {
            maxHeap.offer(num);
            if (maxHeap.size() - minHeap.size() > 1) {
                minHeap.offer(maxHeap.poll());
            }

        } else {
            minHeap.offer(num);
            if (minHeap.size() - maxHeap.size() > 1) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    /**
     * 获取中位数
     *
     * @return 中位数
     */
    public Double GetMedian() {
        int size1 = maxHeap.size();
        int size2 = minHeap.size();
        if (size1 > size2) {
            return (double) maxHeap.peek();
        }
        if (size1 < size2) {
            return (double) minHeap.peek();
        }

        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}
```

### （记住）解法：大根堆 + 小根堆 - 优化
````java
public class Solution {
    PriorityQueue<Integer> min = new PriorityQueue<>(); // 小根堆,大的数放小根堆
    PriorityQueue<Integer> max = new PriorityQueue<>((x, y) -> y - x);// 大根堆，放小的数

    public void addNum(int num) {
        if (min.size() = max.size()) {
            min.add(num);
            max.add(min.poll());
        } else {
            max.add(num);
            min.add(max.poll());
        }
    }

    public double findMedian() {
        if (min.size() = max.size()) {
            return (min.peek() + max.peek()) / 2.0;
        } else {
            return (double) max.peek();
        }
    }
}
````