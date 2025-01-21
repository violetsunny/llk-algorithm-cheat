/**
 * OYO.com Inc.
 * Copyright (c) 2017-2024 All Rights Reserved.
 */
package top.kexcellent.algorithm.code;

/**
 * 并查集，解决朋友圈个数问题
 *
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
    private int find(int x) {
        while (elements[x] != -1) {
            x = elements[x];
        }
        return x;
    }

    // 把两个数的根连起来，按秩合并优化
    public void union(int x, int y) {
        // x的根
        int rootx = find(x);
        // y的根
        int rooty = find(y);
        // 如果不是同一个根就连起来
        if (rootx != rooty) {
            // 矮树向高树合并
            if (heights[rootx] > heights[rooty]) {
                elements[rooty] = rootx;
            } else if (heights[rootx] < heights[rooty]) {
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
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == -1) {//-1就是每个树的根节点
                count++;
            }
        }
        return count;
    }

    // 打印并查集
    public void print() {
        for (int i = 0; i < elements.length; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < heights.length; i++) {
            System.out.print(heights[i] + " ");
        }
        System.out.println();
    }
}


class UnionFindUpgrade {
    private int[] parent;
    private int[] rank;  // 用于优化的秩数组
    private int count;  // 组件的数量

    // 初始化并查集，每个元素的父节点是自己
    public UnionFindUpgrade(int size) {
        parent = new int[size];
        rank = new int[size];
        count = size;  // 初始时，组件数量等于元素数量
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;  // 初始秩为 1
        }
    }

//    // 查找元素的根节点，不进行路径压缩
//    public int find(int x) {
//        while (x!= parent[x]) {
//            x = parent[x];
//        }
//        return x;
//    }
//
//    // 合并两个元素所在的集合，不进行优化
//    public void union(int x, int y) {
//        int rootX = find(x);
//        int rootY = find(y);
//        if (rootX!= rootY) {
//            parent[rootY] = rootX;
//            count--;  // 每合并一次，组件数量减 1
//        }
//    }


    // 查找元素的根节点，进行路径压缩优化
    public int findWithPathCompression(int x) {
        if (x != parent[x]) {
            parent[x] = findWithPathCompression(parent[x]);
        }
        return parent[x];
    }

    // 合并两个元素所在的集合，按秩优化
    public void unionByRank(int x, int y) {
        int rootX = findWithPathCompression(x);
        int rootY = findWithPathCompression(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;  // 秩相等时，合并到 rootX 并增加秩
            }
            count--;  // 每合并一次，组件数量减 1
        }
    }

    // 获取组件的数量
    public int getCount() {
        return count;
    }

}
