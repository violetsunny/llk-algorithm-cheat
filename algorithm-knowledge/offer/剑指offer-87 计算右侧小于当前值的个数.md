## [315.计算右侧小于当前元素的个数](https://leetcode.cn/problems/count-of-smaller-numbers-after-self/description/)
给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。


````
示例 1：

输入：nums = [5,2,6,1]
输出：[2,1,1,0]
解释：
5 的右侧有 2 个更小的元素 (2 和 1)
2 的右侧仅有 1 个更小的元素 (1)
6 的右侧有 1 个更小的元素 (1)
1 的右侧有 0 个更小的元素
示例 2：

输入：nums = [-1]
输出：[0]
示例 3：

输入：nums = [-1,-1]
输出：[0,0]
````

提示：

- 1 <= nums.length <= 105
- -104 <= nums[i] <= 104

### （记住）解法：归并排序

````java
import java.util.*;

class Solution {

    private List<Integer> res;
    private int[] index;
    private int[] ans;

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        res = new ArrayList<>();
        ans = new int[nums.length];
        index = new int[nums.length];//存储下标，防止交换的时候失去数据位置
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        mergeSort(nums, 0, nums.length - 1);

        for (int an : ans) {
            res.add(an);
        }
        return res;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int[] tempIndex = new int[right - left + 1];//同temp，跟着交换
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k] = nums[i];
                tempIndex[k] = index[i];
                //虽然i比j小，但是i比j之前都要大，因为如果j往右挪动了而i没有，说明j之前的都要小于当前i(也就是min+1到j-1的位置)，所以就是j-mid-1
                ans[index[i]] += (j - mid - 1);
                k++;
                i++;
            } else {
                temp[k] = nums[j];
                tempIndex[k] = index[j];
                k++;
                j++;
            }
        }

        //剩余的数字 先放了右所以剩下左，说明右比左小
        while (i <= mid) {
            temp[k] = nums[i];
            tempIndex[k] = index[i];
            ans[index[i]] += (j - mid - 1);
            k++;
            i++;
        }
        while (j <= right) {
            temp[k] = nums[j];
            tempIndex[k] = index[j];
            k++;
            j++;
        }

        k = 0;
        for (int m = left; m <= right; m++) {
            index[m] = tempIndex[k];//赋给原来数组
            nums[m] = temp[k];//赋给原来数组
            k++;
        }
    }
}
````

