package util;

public class LcsUtils {

    public static int calculateLcsDynamic(String word1, String word2) {

        char[] word1CharArray = word1.toCharArray();
        char[] word2CharArray = word2.toCharArray();
        int word1Length = word1CharArray.length;
        int word2Length = word2CharArray.length;

        int resultMatris[][] = new int[word1Length + 1][word2Length + 1];

    /* Following steps build L[m+1][n+1] in bottom up fashion. Note
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
        for (int i = 0; i <= word1Length; i++) {
            for (int j = 0; j <= word2Length; j++) {
                if (i == 0 || j == 0) {
                    resultMatris[i][j] = 0;
                } else if (word1CharArray[i - 1] == word2CharArray[j - 1]) {
                    resultMatris[i][j] = resultMatris[i - 1][j - 1] + 1;
                } else {
                    resultMatris[i][j] = max(resultMatris[i - 1][j], resultMatris[i][j - 1]);
                }
            }
        }
        return resultMatris[word1Length][word2Length];
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }

}