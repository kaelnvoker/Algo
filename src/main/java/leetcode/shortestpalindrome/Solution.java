package leetcode.shortestpalindrome;

/**
 * https://leetcode.com/problems/shortest-palindrome/description/
 */
public class Solution
{
    public static void main(String args[])
    {
        Solution solution = new Solution();
        String string = "abcd";
        System.out.println(solution.shortestPalindrome(string));
    }
    
    /**
     * The idea here is to find the longest palindrome originating from the start of the string,
     * if we are not able to find any the best option is to reverse the string and attach in front
     * of it.
     * @param s Input string
     * @return shortestPalindrome formed by adding letters in front of it
     */
    public String shortestPalindrome(String s) {
        int middlePoint = 0;
        int maxLengthPalindrome = 1;
        boolean isOddLength = true;
        //Base case
        if (s.length() < 2) {
            return s;
        }

        // Odd length palindromes
        int center = (s.length()/2) - 1 + s.length()%2;
        while (center >= 0)
        {
            boolean isPalindrome = true;
            for (int i = 1; i <= center; i++)
            {
                if (s.charAt(center - i ) != s.charAt(center + i))
                {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome)
            {
                if (2*center + 1 > maxLengthPalindrome)
                {
                    maxLengthPalindrome = 2*center + 1;
                    middlePoint = center;
                    isOddLength = true;
                }
            }
            center--;
        }

        // Even length palindrome
        center = (s.length()/2) - 1;
        while (center >= 0)
        {
            boolean isPalindrome = true;
            for (int i = 0; i <= center; i++)
            {
                if (s.charAt(center-i ) != s.charAt(center + i + 1))
                {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome)
            {
                if (2*(center + 1) > maxLengthPalindrome)
                {
                    maxLengthPalindrome = 2*center + 2;
                    middlePoint = center;
                    isOddLength = false;
                }
            }
            center--;
        }

        //Get longest palindrome starting from index 0
        if (isOddLength)
        {
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(s,
                    2*middlePoint + 1,
                    s.length())
                    .reverse()
                    .append(s)
                    .toString();
        }
        else
        {
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(s,
                    2*(middlePoint + 1),
                    s.length())
                    .reverse()
                    .append(s)
                    .toString();
        }
    }
}
