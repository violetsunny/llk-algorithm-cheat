## [课程表 II](https://leetcode.cn/problems/course-schedule-ii/description/)

现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。


````
示例 1：

输入：numCourses = 2, prerequisites = [[1,0]]
输出：[0,1]
解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
示例 2：

输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
输出：[0,2,1,3]
解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
示例 3：

输入：numCourses = 1, prerequisites = []
输出：[0]
````

提示：
- 1 <= numCourses <= 2000
- 0 <= prerequisites.length <= numCourses * (numCourses - 1)
- prerequisites[i].length == 2
- 0 <= ai, bi < numCourses
- ai != bi
- 所有[ai, bi] 互不相同

### 解法：拓扑排序 + DFS

````java
import java.util.ArrayList;

class Solution {
    private int n;// 顶点数
    private List<List<Integer>> adj;// 邻接表

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        this.n = numCourses;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int m = prerequisites.length;
        for (int[] pre : prerequisites) {
            addEdge(pre[1], pre[0]);
        }

        return topoSort();
    }

    // 添加边
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public int[] topoSort() {
        int[] visited = new int[n];// 访问标记数组 0=未搜索，1=搜索中，2=已完成

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                dfs(i, visited, stack);
            }
        }

        if (stack.size() != n) {
            return new int[0];
        }

        int[] topOrder = new int[n]; // 存储拓扑排序的结果
        int index = 0;
        while (!stack.isEmpty()) {
            topOrder[index++] = stack.pop();
        }
        return topOrder;
    }

    private void dfs(int i, int[] visited, Stack<Integer> stack) {
        visited[i] = 1;//标记搜索
        for (int neighbor : adj.get(i)) {
            if (visited[neighbor] == 0) {
                dfs(neighbor, visited, stack);
            } else if (visited[neighbor] == 1) {
                return;
            }
        }
        stack.push(i);//最后没有指向别的节点了，就是出度为0(adj.get(i)为空或者所有对应的visited[ne]==2)放入栈中
        visited[i] = 2;//标记完成
    }
}
````

### 解法：拓扑排序 + BFS

````java
class Solution {
    int n;
    List<List<Integer>> adj;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        n = numCourses;
        adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            addEdge(prerequisite[1], prerequisite[0]);
        }

        return topoSort();
    }

    private void addEdge(int k, int v) {
        adj.get(k).add(v);
    }

    public int[] topoSort() {
        int[] in = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> list = adj.get(i);
            for (int j : list) {
                in[j]++;//入度
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        //入度为0
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int num = queue.poll();
            order.add(num);

            List<Integer> list = adj.get(num);
            for (int i : list) {
                if (--in[i] == 0) {//放入节点后，指向节点入度要减去
                    queue.offer(i);
                }
            }
        }

        if (order.size() != n) {
            return new int[0];
        }
        return order.stream().mapToInt(Integer::intValue).toArray();
    }
}
````