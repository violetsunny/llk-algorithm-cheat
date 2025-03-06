## [单词接龙](https://leetcode.cn/problems/word-ladder/description/)

字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：

每一对相邻的单词只差一个字母。
对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
sk == endWord
给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。

````
示例 1：

输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
输出：5
解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
示例 2：

输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
输出：0
解释：endWord "cog" 不在字典中，所以无法进行转换。
````

提示：

- 1 <= beginWord.length <= 10
- endWord.length == beginWord.length
- 1 <= wordList.length <= 5000
- wordList[i].length == beginWord.length
- beginWord、endWord 和 wordList[i] 由小写英文字母组成
- beginWord != endWord
- wordList 中的所有字符串 互不相同

### 解法： 广度优先搜索(BFS) + 优化建图 + 哈希映射
时间复杂度：$O(N×C^2)$,N 为 wordList 的长度，C 为单词的长度。
````java
class Solution {

    Map<String, List<String>> edge = new HashMap<>();//存单词对应关系

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 1;
        }
        //组装视图
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);

        //初始化需要数据
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Map<String, Integer> step = new HashMap<>();
        step.put(beginWord, 0);

        //循环查找字符,有个问题：不能避免多个方式找到结束字符次数不一致的问题
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (cur.equals(endWord)) {
                return step.get(cur) / 2 + 1;//找到字符+1，因为使用了中转所以要/2
            }

            for (String map : edge.get(cur)) {
                if (!step.containsKey(map)) {//步骤中没有的映射字符放入队列中，有的上面没有匹配到就说明不需要放了
                    step.put(map, step.get(cur) + 1);//每次中转+1
                    queue.offer(map);//通过映射字符中转
                }
            }
        }

        return 0;
    }

    private void addEdge(String word) {
        if (!edge.containsKey(word)) {
            edge.put(word, new ArrayList<>());
        } else {
            return;
        }

        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            chars[i] = '*';//通配符，方便匹配
            String map = new String(chars);//映射字符
            edge.get(word).add(map);//单词放入映射字符
            if (!edge.containsKey(map)) {
                edge.put(map, new ArrayList<>());
            }
            edge.get(map).add(word);//映射字符也放入该单词，才能通过通配符*来反向找到

            chars[i] = temp;//需要将改动的字符改回去
        }
    }
}
````

### 解法：双向广度优先搜索
头和尾都一起找，结果相加：时间复杂度：$O(N×C^2)$,N 为 wordList 的长度，C 为单词的长度。
````java
class Solution {
    Map<String, List<String>> edge = new HashMap<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 1;
        }
        //组装视图
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);

        //初始化需要数据，双向搜索
        //头开始
        Queue<String> queueB = new LinkedList<>();
        queueB.offer(beginWord);

        Map<String, Integer> stepB = new HashMap<>();
        stepB.put(beginWord, 0);

        //尾开始
        Queue<String> queueE = new LinkedList<>();
        queueE.offer(endWord);

        Map<String, Integer> stepE = new HashMap<>();
        stepE.put(endWord, 0);

        //循环查找字符,有个问题：不能避免多个方式找到结束字符次数不一致的问题
        while (!queueB.isEmpty() && !queueE.isEmpty()) {
            String cur = queueB.poll();
            if (cur.equals(endWord)) {
                return (stepB.get(cur) + stepE.get(cur)) / 2 + 1;//头和尾相加的次数，再+1
            }

            for (String map : edge.get(cur)) {
                if (!stepB.containsKey(map)) {//步骤中没有映射字符
                    stepB.put(map, stepB.get(cur) + 1);//每次中转+1
                    queueB.offer(map);//通过映射字符中转
                }
            }

            //尾开始也来一遍
            String cur2 = queueE.poll();
            if (cur2.equals(beginWord)) {
                return (stepE.get(cur2) + stepB.get(cur2)) / 2 + 1;//找到字符+1
            }

            if (edge.get(cur2) == null) {//可能尾不在列表中
                return 0;
            }
            for (String map : edge.get(cur2)) {
                if (!stepE.containsKey(map)) {//步骤中没有映射字符
                    stepE.put(map, stepE.get(cur2) + 1);//每次中转+1
                    queueE.offer(map);//通过映射字符中转
                }
            }
        }

        return 0;
    }

    private void addEdge(String word) {
        if (!edge.containsKey(word)) {
            edge.put(word, new ArrayList<>());
        } else {
            return;
        }

        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            chars[i] = '*';//通配符，方便匹配
            String map = new String(chars);//映射字符
            edge.get(word).add(map);//单词放入映射字符
            if (!edge.containsKey(map)) {
                edge.put(map, new ArrayList<>());
            }
            edge.get(map).add(word);//映射字符也放入该单词，才能通过通配符*来反向找到

            chars[i] = temp;//需要将改动的字符改回去
        }

    }
}
````