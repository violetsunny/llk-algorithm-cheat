## [前 K 个高频元素](https://leetcode.cn/problems/top-k-frequent-elements/description/)
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

````
示例 1:
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:
输入: nums = [1], k = 1
输出: [1]
````

提示：
- 1 <= nums.length <= 105
- k 的取值范围是 [1, 数组中不相同的元素的个数]
- 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的


进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。

### 解法：Hash + 大根堆
````java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for(int num : nums) {
            counter.merge(num, 1, Integer::sum);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]); // 最大堆
        for(Map.Entry<Integer, Integer> map : counter.entrySet()) {
            heap.offer(new int[]{map.getValue(), map.getKey()}); // 按次数排序，所以[0]传的是计数值
        }
        int[] res = new int[k];
        for(int i = 0; i<k;i++) {
            res[i] = heap.poll()[1];
        }
        return res;
    }
}
````

### 解法：Hash + 桶排序
````java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer,Integer> map=new HashMap<>();
        //统计频率
        for(int i=0;i<n;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        //建立桶，将频率作为下标，存储数字，由于频率可能重复，因此用List<Integer>
        List<Integer>[] bucket=new List[n+1];
        for(int key:map.keySet()){
            int times=map.get(key);
            if(bucket[times]==null) bucket[times]=new ArrayList<>();
            bucket[times].add(key);
        }
        //由于频率就是下标，从后向前遍历桶数组，直到遍历完或获取k个数字
        int[] ans=new int[k];
        int index=0;
        for(int i=n;i>=0&&index<k;i--){
            if(bucket[i]==null) continue;
            while(!bucket[i].isEmpty()){
                ans[index++]=bucket[i].getLast();
                bucket[i].removeLast();
            }
        }
        return ans;
    }
}
````