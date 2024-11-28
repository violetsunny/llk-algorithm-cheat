# [数组中的逆序对](https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/)

## 题目描述

<p>在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入</strong>: [7,5,6,4]
<strong>输出</strong>: 5</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 数组长度 &lt;= 50000</code></p>

## 解法

### 方法一：归并排序

归并排序的过程中，如果左边的数大于右边的数，则右边的数与左边的数之后的数都构成逆序对。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。
````java
class Solution {
    // 原始数组
    private int[] nums;
    // 临时数组，用于归并排序过程中的合并操作
    private int[] t;

    public int reversePairs(int[] nums) {
        // 将传入的数组赋值给成员变量 nums
        this.nums = nums;
        int n = nums.length;
        // 创建与输入数组长度相同的临时数组
        this.t = new int[n];
        // 调用归并排序函数，从数组的起始位置 0 到末尾位置 n - 1，返回逆序对的数量
        return mergeSort(0, n - 1);
    }

    private int mergeSort(int l, int r) {
        // 如果左边界大于等于右边界，说明子数组只有一个元素或为空，逆序对数量为 0
        if (l >= r) {
            return 0;
        }
        // 计算中间位置
        int mid = (l + r) >> 1;
        // 递归地对左右子数组进行归并排序，并统计逆序对数量
        int ans = mergeSort(l, mid) + mergeSort(mid + 1, r);
        int i = l, j = mid + 1, k = 0;
        // 进行合并操作
        while (i <= mid && j <= r) {
            // 如果左子数组当前元素小于等于右子数组当前元素
            if (nums[i] <= nums[j]) {
                // 将左子数组当前元素放入临时数组 t
                t[k++] = nums[i++];
            } else {
                // 如果左子数组当前元素大于右子数组当前元素
                // 说明从当前左子数组元素到左子数组末尾的元素都与右子数组当前元素构成逆序对
                ans += mid - i + 1;
                // 将右子数组当前元素放入临时数组 t
                t[k++] = nums[j++];
            }
        }
        // 如果左子数组还有剩余元素，将其放入临时数组 t
        while (i <= mid) {
            t[k++] = nums[i++];
        }
        // 如果右子数组还有剩余元素，将其放入临时数组 t
        while (j <= r) {
            t[k++] = nums[j++];
        }
        // 将临时数组 t 中的元素复制回原始数组 nums 的相应位置
        for (i = l; i <= r; ++i) {
            nums[i] = t[i - l];
        }
        // 返回当前子数组中的逆序对数量
        return ans;
    }
}
````
好理解点：

`4 2 3 1` </br>
`就是 x1(42的逆序) + x2(31的逆序) + x3(整体的24对13)`

````java
class Solution {

    public int reversePairs(int[] nums) {
        if(nums == null || nums.length <= 1){
            return 0;
        }
        return mergeSort(nums,0,nums.length-1);
    }
    
    public int mergeSort(int[] nums,int left,int right){
        if(left>=right){
            return 0;
        }
        int mid = (right-left)/2+left;
        int x1 = mergeSort(nums,left,mid);
        int x2 = mergeSort(nums,mid+1,right);
        int x3 = merge(nums,left,mid,mid+1,right);
        return x1+x2+x3;//左边的+右边的+整体(左边对右边)
    }
    
    public int merge(int[] nums,int l1,int r1,int l2,int r2){
        int[] temp = new int[r2-l1+1];
        int count = 0;
        int i = l1;
        int j = l2;
        int k = 0;
        while(i<=r1 && j<=r2){
            if(nums[i] > nums[j]){
                count = count + (l2-i);//如果j小于i,说明i到r1之间位置都是比j大。
                temp[k++] = nums[j++];
            } else {
                temp[k++] = nums[i++];
            }
        }
        // 如果左子数组还有剩余元素，将其放入临时数组 t
        while (i <= r1) {
            temp[k++] = nums[i++];
        }
        // 如果右子数组还有剩余元素，将其放入临时数组 t
        while (j <= r2) {
            temp[k++] = nums[j++];
        }
        // 将临时数组 t 中的元素复制回原始数组 nums 的相应位置
        k = 0;
        for (int m = l1; m <= r2; m++) {
            nums[m] = temp[k++];
        }
        return count;
    }
}

````