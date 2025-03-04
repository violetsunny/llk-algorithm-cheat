/**
 * OYO.com Inc.
 * Copyright (c) 2017-2024 All Rights Reserved.
 */
package top.kexcellent.algorithm.code;

/**
 * 寻找数的模板，前提是有序数组
 * 1. 寻找第一个大于等于target的数
 * 2. 寻找第一个等于target的数
 * 3. 寻找最后一个大于等于target的数
 * 4. 寻找最后一个等于target的数
 *
 * @author kanglele
 * @version $Id: SearchNum, v 0.1 2024/11/28 09:11 user Exp $
 */
public class SearchNum {

    // 寻找第一个大于等于target的数
    public static int findFirstGreaterEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = arr.length; // 初始化结果为数组长度，若未找到则返回该值

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    // 寻找第一个等于target的数
    public static int findFirstEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1; // 初始化结果为 -1，若未找到则返回该值

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // 寻找最后一个大于等于target的数
    public static int findLastGreaterEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1; // 初始化结果为 -1，若未找到则返回该值

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // 寻找最后一个等于target的数
    public static int findLastEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1; // 初始化结果为 -1，若未找到则返回该值

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 5, 5, 7, 9};
        int target = 5;

        System.out.println("寻找第一个大于等于 " + target + " 的数：" + findFirstGreaterEqual(arr, target));
        System.out.println("寻找第一个等于 " + target + " 的数：" + findFirstEqual(arr, target));
        System.out.println("寻找最后一个大于等于 " + target + " 的数：" + findLastGreaterEqual(arr, target));
        System.out.println("寻找最后一个等于 " + target + " 的数：" + findLastEqual(arr, target));
    }
}
