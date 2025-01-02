## [直线上最多的点数](https://leetcode.cn/problems/max-points-on-a-line/description/)

给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
````
示例 1：


输入：points = [[1,1],[2,2],[3,3]]
输出：3
示例 2：


输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出：4
````

提示：

- 1 <= points.length <= 300
- points[i].length == 2
- -104 <= xi, yi <= 104
- points 中的所有点 互不相同

### 解法：数学-向量叉积
两个向量叉积为0 ，表示共线。一般情况下，叉积用来表示一个点在向量的左侧还是右侧 。
````java
class Solution {
    public int maxPoints(int[][] points) {
                int n = points.length;
        if (n <= 2) {
            return n;
        }

        int max=0;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                int[] vec = createV(points[i],points[j]);
                int mex2 = 2;
                for(int k=0;k<n;k++){
                    if(k==i || k==j){
                        continue;
                    }
                    int[] vec1 = createV(points[i],points[k]);

                    if(cross(vec,vec1) == 0){
                        mex2++;
                    }
                }

                max = Math.max(max,mex2);

                if(max > n/2){
                    return max;
                }
            }
        }

        return max;
    }

    //构建向量
    private int[] createV(int[] points1,int[] points2){
        return new int[]{points1[0]-points2[0],points1[1]-points2[1]};
    }

    //叉积
    private int cross(int[] points1,int[] points2){
        return points1[0]*points2[1] - points2[0]*points1[1];
    }
}
````

### 解法：数学-斜率
一般情况下，斜率可以表示为 slope= Δy/Δx ​ 的形式，因此我们可以用分子和分母组成的二元组来代表斜率。但注意到存在形如  
1/2 = 2/4
这样两个二元组不同，但实际上两分数的值相同的情况，所以我们需要将分数 Δy/Δx ​ 化简为最简分数的形式。

````java
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (ret >= n - i || ret > n / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int num = entry.getValue();
                maxn = Math.max(maxn, num + 1);
            }
            ret = Math.max(ret, maxn);
        }
        return ret;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
````