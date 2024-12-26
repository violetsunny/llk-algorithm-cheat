/**
 * LY.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package top.kexcellent.algorithm.code.sort;

import java.util.*;

/**拓扑排序是针对有向无环图（Directed Acyclic Graph, DAG）的一种排序算法，
 * 它会将图中的所有顶点排成一个线性序列，使得对于任何一条有向边U -> V，顶点U都在顶点V的前面。
 *
 * 这种排序不是唯一的。拓扑排序常用于任务调度、课程规划等场景。
 *
 * 在Java中，拓扑排序通常使用深度优先搜索（DFS）或广度优先搜索（BFS）实现。
 * @author kanglele
 * @version $Id: TopoSort, v 0.1 2024/12/26 15:10 kanglele Exp $
 */
public class TopoSort {

    private int n;// 顶点数
    private List<List<Integer>> adj;// 邻接表

    // 初始化数据
    public TopoSort(int n) {
        this.n = n;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // 添加边
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public List<Integer> topoSort() {
        int[] inDegree = new int[n]; // 存储每个顶点的入度

        // 计算每个顶点的入度
        for (int i = 0; i < n; i++) {
            List<Integer> neighbors = adj.get(i);
            for (int neighbor : neighbors) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {//将入度为 0 的顶点加入队列，相同入度的顶点按照大小排序
                queue.offer(i);
            }
        }

        List<Integer> topOrder = new ArrayList<>(); // 存储拓扑排序的结果

        while (!queue.isEmpty()) {
            int u = queue.poll();
            topOrder.add(u);

            // 遍历该顶点的邻居
            for (int v : adj.get(u)) {
                // 减少邻居的入度
                if (--inDegree[v] == 0) {// 入度为0放入拓扑队列中
                    queue.offer(v);
                }
            }
        }

        if (topOrder.size() != n) {
            throw new IllegalArgumentException("图中存在环，无法进行拓扑排序");
        }
        return topOrder;
    }


    public static void main(String[] args) {
        TopoSort g = new TopoSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
//        g.addEdge(3, 0);

        System.out.println("拓扑排序结果：");
        List<Integer> result = g.topoSort();
        System.out.println(Arrays.toString(result.toArray()));
    }
}

