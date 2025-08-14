## [单词搜索](https://leetcode.cn/problems/word-search/description/)

给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。


````
示例 1：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
示例 2：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true
示例 3：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false
````

提示：

- m == board.length
- n = board[i].length
- 1 <= m, n <= 6
- 1 <= word.length <= 15
- board 和 word 仅由大小写英文字母组成


进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？

### （记住）解法：深度优先搜索(DFS)+回溯

*时间复杂度$O(3^{len}*m*n)$，空间复杂度$O(m*n)$*
````java
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        // 二维度任意字符开始
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // k 从单词第一个字符开始
                if (dfs(board, i, j, words, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int i, int j, char[] words, int k) {
        //超过范围
        if (i > board.length - 1 || j > board[0].length - 1 || i < 0 || j < 0) {
            return false;
        }
        //字符不相等跳过
        if (board[i][j] != words[k]) {
            return false;
        }
        //搜完
        if (k == words.length - 1) {
            return true;
        }

        board[i][j] = '#';//标记搜过
        //上下左右搜索
        boolean res = dfs(board, i + 1, j, words, k + 1) 
                || dfs(board, i, j + 1, words, k + 1) 
                || dfs(board, i - 1, j, words, k + 1) 
                || dfs(board, i, j - 1, words, k + 1);
        board[i][j] = words[k];//还原

        return res;
    }
}
````