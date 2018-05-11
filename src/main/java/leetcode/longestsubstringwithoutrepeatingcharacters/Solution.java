package leetcode.longestsubstringwithoutrepeatingcharacters;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

class Solution {
    public static void main(String args[])
    {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
    /**
     * First find the longest string starting from index 0, keep increasing the index to i
     * until you find a repeating character, this ensures that there no repeating characters
     * from 0 to i. Move the start pointer to 1 and end pointer to
     *
     * Time complexity: O(n)
     * @param s is the String for which we need to find the longest length substring without
     *         repetition.
     * @return longest length substring without repetition.
     */
    public int lengthOfLongestSubstring(String s)
    {
        int head = 0;
        int tail = 1;
        int maxLength = 1;
        final HashMap<Character, Integer> existsMap = new HashMap<>();
        //Base case
        if (s.length() == 0 || s.length() == 1)
        {
            return s.length();
        }

        //Generic case
        existsMap.put(s.charAt(0), 0);
        int length = 1;
        while (tail < s.length())
        {
            if (!existsMap.containsKey(s.charAt(tail)))
            {
               existsMap.put(s.charAt(tail), tail);
               length++;
            }
            else
            {
                maxLength = length > maxLength ? length : maxLength;
                int newHead = existsMap.get(s.charAt(tail)) + 1;
                length = tail - newHead + 1;
                while (head < newHead)
                {
                    existsMap.remove(s.charAt(head));
                    head++;
                }
                existsMap.put(s.charAt(tail), tail);
            }
            tail++;
        }
        return length > maxLength ? length : maxLength;
    }
}