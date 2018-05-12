package leetcode.longestpalindromicsubstring;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 */
class Solution
{
    public static void main(String args[])
    {
        Solution solution = new Solution();
        String s = "bb";
        System.out.println(solution.longestPalindrome(s));
    }
    /**
     * The most brute-force way to do this would be to go through all O(n^2) pairings and for each
     * of them check if its a palindrome or not. This would be O(n^3), this however could be
     * optimized to O(n^2) by deducing if a palindrome with middle point at index i and lenght l
     * is a palindrome or not by looking up index i and length l-1.
     * Note total number of middle point candidates are is i + i-1(imaginary middle points for event
     * lengthed palindromes)
     * @param s String of interest.
     * @return Length of longest palindromic substring in s.
     */
    public String longestPalindrome(String s)
    {
        int length = s.length();
        boolean isPalindromeFromIWithLengthJ[][] = new boolean[length][length];
        int maxLengthPalindrome = 1;
        int mid = 0;
        int len = 0;
        boolean isOddLength = false;
        //Base Case
        if (length == 0 || length == 1)
        {
            return s;
        }

        // Odd length palindrome
        for (int i = 0; i < length; i++)
        {
            isPalindromeFromIWithLengthJ[i][0] = true;
        }
        for (int i = 0 ; i < length; i++)
        {
            int maxLengthFromI = getMaxLengthFromI(i, length, true);
            int j;
            for (j = 1; j <= maxLengthFromI; j++)
            {
                isPalindromeFromIWithLengthJ[i][j] = s.charAt(i - j) == s.charAt(i + j)
                        && isPalindromeFromIWithLengthJ[i][j - 1];
                if(!isPalindromeFromIWithLengthJ[i][j])
                {
                    break;
                }
            }
            if (2*j - 1 > maxLengthPalindrome)
            {
                maxLengthPalindrome = 2*j -1;
                mid = i;
                len = j-1;
                isOddLength = true;
            }
        }

        // Even length palindrome
        for (int i = 0; i < length; i++)
        {
            isPalindromeFromIWithLengthJ[i][0] = true;
        }
        for (int i = 0 ; i < length - 1; i++)
        {
            int maxLengthFromI = getMaxLengthFromI(i, length, false);
            int j;
            for (j = 1; j <= maxLengthFromI; j++)
            {
                isPalindromeFromIWithLengthJ[i][j] = s.charAt(i - j + 1) == s.charAt(i + j)
                        && isPalindromeFromIWithLengthJ[i][j - 1];
                if(!isPalindromeFromIWithLengthJ[i][j])
                {
                    break;
                }
            }
            if (2*j - 2 > maxLengthPalindrome)
            {
                maxLengthPalindrome = 2*j -2;
                mid = i;
                len = j-1;
                isOddLength = false;
            }
        }
        if (maxLengthPalindrome == 1)
        {
            return s.substring(0,1);
        }
        if(isOddLength)
        {
            return s.substring(mid - len , mid + len + 1);
        }
        else
        {
            return s.substring(mid - len + 1, mid + len + 1);
        }
    }

    private int getMaxLengthFromI(int i, int length, boolean isOddLengthPalindrome)
    {
        if (isOddLengthPalindrome)
        {
            return i < length - (i+1)
                    ? i
                    : length - (i + 1);
        }
        else
        {
            return  i + 1 < length - (i + 1)
                  ? i + 1
                  : length - (i + 1);
        }
    }
}