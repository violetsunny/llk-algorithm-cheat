/**
 * LY.com Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package top.kexcellent.algorithm.code;

import java.util.*;

/**
 * @author kanglele
 * @version $Id: PrimePartners, v 0.1 2025/3/27 15:34 kanglele Exp $
 */
public class PrimePartners {

    public static int primePartners(int[] nums) {
        // 划分奇数和偶数集合
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        for (int num : nums) {
            if (num % 2 == 1) {
                odd.add(num);
            } else {
                even.add(num);
            }
        }

        // 构建二分图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < odd.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < even.size(); j++) {
                if (isPrime(odd.get(i) + even.get(j))) {
                    graph.get(i).add(j);
                }
            }
        }

        // 匈牙利算法求解最大匹配
        int[] match = new int[even.size()];
        for (int i = 0; i < even.size(); i++) {
            match[i] = -1;
        }

        int result = 0;
        for (int i = 0; i < odd.size(); i++) {
            boolean[] used = new boolean[even.size()];
            if (find(i, used, match, graph)) {
                result++;
            }
        }

        return result;
    }

    // 判断一个数是否为素数
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // 匈牙利算法寻找增广路径
    private static boolean find(int u, boolean[] used, int[] match, List<List<Integer>> graph) {
        for (int v : graph.get(u)) {
            if (!used[v]) {
                used[v] = true;
                if (match[v] == -1 || find(match[v], used, match, graph)) {
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,5,6,13,15,17,19,21,23,29};

        System.out.println(primePartners(nums));
    }
}
