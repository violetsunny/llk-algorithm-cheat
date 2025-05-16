## [45. 把数组排成最小的数](https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/)
同：[179. 最大数](https://leetcode.cn/problems/largest-number/description/)

### 题目描述

输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

例如输入数组 `[3, 32, 321]`，则打印出这 3 个数字能排成的最小数字`321323`。

### 解法: 字符比较排序
是否具有传递性，才能使用比较排序。`x + y < y + x && y + z < z + y => x + z < z + x`

我们将数组中的数字转换为字符串，然后按照字符串拼接的大小进行排序。具体地，比较两个字符串 $a$ 和 $b$，如果 $a + b < b + a$，则 $a$ 小于 $b$，否则 $a$ 大于 $b$。
```java
import java.util.Arrays;

class Solution {

    /**
     * 打印数组元素组成的最小的数字
     *
     * @param nums 数组
     * @return 最小的数字
     */
    public String printMinNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        int n = nums.length;
        String[] strNums = new String[n];
        for (int i = 0; i < n; ++i) {
            strNums[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strNums, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder sb = new StringBuilder();
        for (String str : strNums) {
            sb.append(str);
        }
        return sb.toString();
    }
}
```

#### 简单写法：
```java
class Solution {
    public String minNumber(int[] nums) {
        return Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .sorted((a, b) -> (a + b).compareTo(b + a))//比较这两个字符拼接后ASCII码比较大小
            .reduce((a, b) -> a.equals("0")?a:a + b)//拼接收集
            .orElse("");
    }
}
```

### （记住）解法：快排
代码按照快排模板。

时间复杂度：`O(logn)`，空间复杂度：`O(n)`
```java
class Solution {
    public String minNumber(int[] nums) {
        qSort(nums, 0, nums.length - 1);
        String res = "";
        for (int num : nums) {
            if (!res.equals("0")) {
                res += num;
            }
        }
        return res;
    }

    private void qSort(int[] nums, int low, int high) {
        if (low > high) {
            return;
        }
        int partition = partition(nums, low, high);
        qSort(nums, low, partition - 1);
        qSort(nums, partition + 1, high);
    }

    private int partition(int[] nums, int low, int high) {
        int base = nums[high];
        int i = low;
        int j = high;
        while (i < j) {
            while (i < j && (nums[i] + "" + base).compareTo(base + "" + nums[i]) <= 0) {
                i++;
            }
            while (i < j && (nums[j] + "" + base).compareTo(base + "" + nums[j]) >= 0) {
                j--;
            }
            swap(nums, i, j);
        }
        swap(nums, i, high);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

