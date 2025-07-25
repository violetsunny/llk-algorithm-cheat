## [58 - I. 翻转单词顺序](https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/)
同：[151. 反转字符串中的单词](https://leetcode.cn/problems/reverse-words-in-a-string/description/)

### 题目描述

输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。

为简单起见，标点符号和普通字母一样处理。

例如输入字符串 `"I am a student."`，则输出 `"student. a am I"`。

**样例**

```
输入："I am a student."

输出："student. a am I"
```

### 解法:split join 函数API

先对字符串按空格切割成数组，再逆序数组后，最后将元素拼接并返回。

```java
class Solution {
    /**
     * 翻转单词
     *
     * @param s 字符串
     * @return 翻转后的字符串
     */
    public String reverseWords(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        String[] arr = s.split(" ");
        int p = 0, q = arr.length - 1;
        while (p < q) {
            swap(arr, p++, q--);
        }
        return String.join(" ", arr);
    }

    private void swap(String[] arr, int p, int q) {
        String t = arr[p];
        arr[p] = arr[q];
        arr[q] = t;
    }
}
```

### （记住）解法: 双指针

*时间复杂度：$O(n)$，空间复杂度：$O(n)$*
```java
class Solution {
    /**
     * 翻转单词
     *
     * @param s 字符串
     * @return 翻转后的字符串
     */
    public String reverseWords(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int i = s.length() - 1;
        int j = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        while(i >= 0) {
            while(i >= 0 && s.charAt(i) == ' '){//去除空格
                i--;
            }
            j = i;//挪动j
            while(i >= 0 && s.charAt(i) != ' '){//找到非空格
                i--;
            }
            sb.append(s.substring(i+1,j+1) + " ");
            j = i;//挪动j
        }

        return sb.toString().trim();//去除append造成的空格
    }
}
```