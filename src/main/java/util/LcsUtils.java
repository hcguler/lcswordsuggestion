package util;

public class LcsUtils {

  public static int calculateLcs(String A, String B) {
    /*if one String is exhausted or is empty then
     * there can not be any matching characters in
     * string A and B*/
    if (A.length() == 0 || B.length() == 0) {
      return 0;
    }
    /*As on this state of recursion we are working
     * on first characters of strings, in next recursion
     *  we need to recur for remaining strings with first
     *  characters removed */
    String remA = A.substring(1);
    String remB = B.substring(1);
    /* if the current first characters of both strings
     * matches then we add 1 to the LCS length and then
     * recur to find answer for remaining Strings*/
    if (A.charAt(0) == B.charAt(0)) {
      int remRes = calculateLcs(remA, remB);
      return 1 + remRes;
    } else {
      /*if the current first characters of both strings
       * does not match, then maximum length of LCS of
       * current string will be the max LCS of
       * (remaining A and B) or (A and remaining B) */
      int remRes = Math.max(calculateLcs(remA, B), calculateLcs(A, remB));
      return remRes;
    }
  }


  public static int calculateLcsDynamic(String A, String B) {
    char[] X = A.toCharArray();
    char[] Y = B.toCharArray();
    int m = X.length;
    int n = Y.length;
    int L[][] = new int[m + 1][n + 1];

    /* Following steps build L[m+1][n+1] in bottom up fashion. Note
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0)
          L[i][j] = 0;
        else if (X[i - 1] == Y[j - 1])
          L[i][j] = L[i - 1][j - 1] + 1;
        else
          L[i][j] = max(L[i - 1][j], L[i][j - 1]);
      }
    }
    return L[m][n];
  }

  private static int max(int a, int b) {
    return (a > b) ? a : b;
  }

}