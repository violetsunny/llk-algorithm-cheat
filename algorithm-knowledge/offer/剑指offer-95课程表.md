## [课程表](https://leetcode.cn/problems/course-schedule/description/)
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。


````
示例 1：

输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
示例 2：

输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
````

提示：

- 1 <= numCourses <= 2000
- 0 <= prerequisites.length <= 5000
- prerequisites[i].length == 2
- 0 <= ai, bi < numCourses
- prerequisites[i] 中的所有课程对 互不相同

### 解法：拓扑排序+BFS

````java
class Solution {
    private int n;// 顶点数
    private List<List<Integer>> adj;// 邻接表

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        this.n = numCourses;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int m = prerequisites.length;
        for(int[] pre : prerequisites){
            addEdge(pre[1],pre[0]);
        }

        return topoSort();
    }

    // 添加边
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public boolean topoSort() {
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

        while (!queue.isEmpty()) {//广度优先搜索(BFS)
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

        if (topOrder.size() != n) {//有环就是不能完成
            return false;
        }
        return true;//能排序就说明可以完成
    }
}
````

### 解法：拓扑排序+DFS

````java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
    }
}
````
