/**
 * llkang.com Inc.
 * Copyright (c) 2010-2022 All Rights Reserved.
 */
package top.kexcellent.algorithm.code.sort;

/**
 * 归并排序,时间复杂度O(nlogn)，空间复杂度O(n) out-place 稳定性排序
 *
 * @author kanglele
 * @version $Id: Merge, v 0.1 2022/6/9 10:50 kanglele Exp $
 */
public class MergeSort {

    /**
     * mergeSort 递归 对两个有序节点序列进行合并来实现排序，分治思想
     *
     * @param arr
     * @return
     */
    public void mergeSort(int[] arr) {
        if (arr == null) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;

        // 对左半部分数组进行归并排序
        mergeSort(arr, left, mid);

        // 对右半部分数组进行归并排序
        mergeSort(arr, mid + 1, right);

        // 合并已经排好序的左半部分和右半部分数组
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // 创建临时数组
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        // 比较左右子数组的元素，将较小的元素依次放入原数组中
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        // 如果左子数组还有剩余元素，将其全部放入原数组中
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        // 如果右子数组还有剩余元素，将其全部放入原数组中
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 将临时数组 t 中的元素复制回原始数组 nums 的相应位置
        k = 0;
        for (int m = left; m <= right; m++) {
            arr[m] = temp[k++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 34, 5, 2, 38, 45, 6, 23, 4, 67, 3, 8, 56};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(arr);
        System.out.println("Sorted array: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
