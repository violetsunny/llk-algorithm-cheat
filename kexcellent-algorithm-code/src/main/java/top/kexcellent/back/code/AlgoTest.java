/**
 * LY.com Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package top.kexcellent.back.code;

import java.util.*;

/**
 * @author kanglele
 * @version $Id: AlgoTest, v 0.1 2025/1/13 10:31 kanglele Exp $
 */
public class AlgoTest {
    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            minprice = Math.min(minprice,prices[i]);
            maxprofit = Math.max(maxprofit,prices[i] - minprice);
        }
        return maxprofit;
    }

    public int maxProfit2(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);//卖出有利可图，就进行操作
        }
        return ans;
    }

    public int maxProfit3(int[] prices) {
        int f0 = 0;
        int f1 = Integer.MIN_VALUE;
        for (int p : prices) {
            int newF0 = Math.max(f0, f1 + p);
            int newF1 = Math.max(f1, f0 - p);
            f0 = newF0;
            f1 = newF1;
        }
        return f0;
    }

    public static void main(String[] args) {
        AlgoTest algoTest = new AlgoTest();
        int[] nums = new int[]{10,12,34,7,8,5,9,12,20,21};
        System.out.println(algoTest.maxProfit(nums));
        System.out.println(algoTest.maxProfit2(nums));
        System.out.println(algoTest.maxProfit3(nums));

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(5, (x,y)->y-x);//大根堆

        List<Integer> list = new LinkedList<>();
        for(int i:list){
            System.out.print(i);
        }

        List<String> point = Arrays.asList("1","87","88","2","67","24","25","14","43","26","18","19","20","21","22","23","94","27","7356","3","15","16","17","95","90","120","4","33","34","35","36","37","38","39","40","41","42","5","7","9","45","47","49","51","53","55","57","6","8","10","46","48","50","52","54","56","58","96","97","98","99","100","101","102","103","104","105","70","71","72","73","74","75","76","77","78","79");
        System.out.print(point.size());
    }
}
