## [最小基因变化](https://leetcode.cn/problems/minimum-genetic-mutation/description/)

基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。

假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。

例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）

给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。

注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。


````
示例 1：

输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
输出：1
示例 2：

输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
输出：2
示例 3：

输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
输出：3
````

提示：

- start.length == 8
- end.length == 8
- 0 <= bank.length <= 10
- bank[i].length == 8
- start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成

### 解法：广度优先搜索 + 优化建图 + 哈希映射
和单词接龙，一模一样

````java
class Solution {
    Map<String, List<String>> edge = new HashMap<>();
    public int minMutation(String beginWord, String endWord, String[] wordList) {
        if (beginWord.equals(endWord)) {
            return 0;
        }

        for (String word : wordList) {
            addEdge(word);
        }

        addEdge(beginWord);
        Map<String, Integer> step = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        step.put(beginWord, 0);
        while (!queue.isEmpty()) {
            String oldWord = queue.poll();
            if (oldWord.equals(endWord)) {
                return step.get(oldWord) / 2;//没有变化是0，所以不用+1.因为使用了中转所以要/2
            }
            for (String newWord : edge.get(oldWord)) {
                if (!step.containsKey(newWord)) {//步骤中没有的映射字符放入队列中，有的上面没有匹配到就说明不需要放了
                    step.put(newWord, step.get(oldWord) + 1);
                    queue.offer(newWord);//通过映射字符中转
                }
            }
        }

        return -1;
    }

     public void addEdge(String word) {
        if (!edge.containsKey(word)) {
            edge.put(word, new ArrayList<>());
        }
        char[] array = word.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char tmp = array[i];
            array[i] = '*';//通配符，方便匹配
            String newWord = new String(array);
            edge.get(word).add(newWord);//单词放入映射字符
            if (!edge.containsKey(newWord)) {
                edge.put(newWord, new ArrayList<>());
            }
            edge.get(newWord).add(word);//映射字符也放入该单词，才能通过通配符*来反向找到
            
            array[i] = tmp;
        }
    }
}
````