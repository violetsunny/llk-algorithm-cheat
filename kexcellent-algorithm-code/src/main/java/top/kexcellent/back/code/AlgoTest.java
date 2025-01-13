/**
 * LY.com Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package top.kexcellent.back.code;

/**
 * @author kanglele
 * @version $Id: AlgoTest, v 0.1 2025/1/13 10:31 kanglele Exp $
 */
public class AlgoTest {
    public boolean isSubsequence(String s, String t) {
        if(t.length()<s.length()){
            return false;
        }
        int n = t.length()-1;
        int m = s.length()-1;
        int j = 0;
        for(int i=0;i<=n;i++,n--){
            if(j<=m && s.charAt(j)==t.charAt(i)){
                j++;
            }
            if(j<=m && s.charAt(m)==t.charAt(n)){
                m--;
            }
        }
        return (j-1)==m;
    }

    public static void main(String[] args) {
        AlgoTest algoTest = new AlgoTest();
        algoTest.isSubsequence("abc","ahbgdc");
    }
}
