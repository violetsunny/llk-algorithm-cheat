/**
 * OYO.com Inc.
 * Copyright (c) 2017-2024 All Rights Reserved.
 */
package top.kexcellent.back.code;

/**
 * 并查集，解决朋友圈个数问题
 * @author kanglele
 * @version $Id: UnionFind, v 0.1 2024/11/6 23:48 user Exp $
 */
public class UnionFind {
    // 存储并查集
    private int[] elements;
    // 存储树的高度
    private int[] heights;

    UnionFind(int n) {
        elements = new int[n];
        heights = new int[n];
        for (int i = 0; i < n; i++) {
            // 初始都为-1
            elements[i] = -1;
            // 初始高度1
            heights[i] = 1;
        }
    }

    // 找到一个数的根
    public int find(int x) {
        while(elements[x] != -1) {
            x = elements[x];
        }
        return x;
    }

    // 把两个数的根连起来
    public void union(int x, int y) {
        // x的根
        int rootx = find(x);
        // y的根
        int rooty = find(y);
        // 如果不是同一个根就连起来
        if(rootx != rooty) {
            // 矮树向高树合并
            if(heights[rootx] > heights[rooty]) {
                elements[rooty] = rootx;
            } else if(heights[rootx] < heights[rooty]) {
                elements[rootx] = rooty;
            } else {
                // 如果高度相同，随便合并
                elements[rootx] = rooty;
                // 但是记得合并后高度加一
                heights[rooty]++;
            }

        }
    }

    // 计算形成了多少颗树
    public int count() {
        int count = 0;
        for(int i=0; i<elements.length; i++) {
            if(elements[i] == -1) {//-1就是每个树的根节点
                count++;
            }
        }
        return count;
    }

    // 打印并查集
    public void print() {
        for(int i=0; i<elements.length; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
        for(int i=0; i<heights.length; i++) {
            System.out.print(heights[i] + " ");
        }
        System.out.println();
    }
}
