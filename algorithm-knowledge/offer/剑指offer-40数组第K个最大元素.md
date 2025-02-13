## [数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/description/)
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。


````
示例 1:
输入: [3,2,1,5,6,4], k = 2
输出: 5
示例 2:
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4
````
````
提示：
1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
````

````java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> max = new PriorityQueue<Integer>((x, y) -> y - x);
        for (int num : nums) {
            max.add(num);
        }
        int res = 0;
        for (int i = 0; i < k; i++) {
            res = max.poll();
        }
        return res;
    }
}
````
````java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> min = new PriorityQueue<>();
        for (int num : nums) {
            min.add(num);
            if (min.size() > k) {
                min.poll();
            }
        }
        return min.peek();
    }
}
````

自己实现堆排序
````java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        } 
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        } 
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
````
