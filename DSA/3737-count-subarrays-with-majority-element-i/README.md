# [Count Subarrays With Majority Element I](https://leetcode.com/problems/count-subarrays-with-majority-element-i/)

🟡 Medium

**Topics**: Array, Hash Table, Divide and Conquer, Segment Tree, Merge Sort, Counting, Prefix Sum

## Problem Summary
You are given an integer array `nums` and a `target` integer. The goal is to determine the total count of all possible subarrays within `nums` where `target` is the majority element. An element is considered the majority element if it appears strictly more than half the times in that specific subarray.

## Approach
The provided Java solution uses a brute-force approach to count the valid subarrays. It iterates through every possible subarray of the input array `nums`. For each subarray identified, it keeps a running count of how many times the `target` element appears within that subarray. Subsequently, it checks if this count is strictly greater than half the length of the current subarray. If this condition holds true, the subarray is considered valid, and a global counter is incremented.

## Algorithm Walkthrough
Let's trace the execution with `nums = [1,2,2,3]` and `target = 2`.
The variable `c` stores the final count of valid subarrays, initialized to `0`.
The variable `b` counts occurrences of `target` within the current subarray.

1.  **`i = 0`**: Start of outer loop, considering subarrays beginning at index `0`.
    *   **`j = 0`**: Subarray `[1]`. `nums[0]` is `1`. `b` remains `0`. `length = 1`. `0 > 1/2` is `false`.
    *   **`j = 1`**: Subarray `[1,2]`. `nums[1]` is `2`. `b` becomes `1`. `length = 2`. `1 > 2/2` is `false`.
    *   **`j = 2`**: Subarray `[1,2,2]`. `nums[2]` is `2`. `b` becomes `2`. `length = 3`. `2 > 3/2` is `true`. Increment `c` to `1`.
    *   **`j = 3`**: Subarray `[1,2,2,3]`. `nums[3]` is `3`. `b` remains `2`. `length = 4`. `2 > 4/2` is `false`.

2.  **`i = 1`**: Start of outer loop, considering subarrays beginning at index `1`.
    *   **`j = 1`**: Subarray `[2]`. `nums[1]` is `2`. `b` becomes `1`. `length = 1`. `1 > 1/2` is `true`. Increment `c` to `2`.
    *   **`j = 2`**: Subarray `[2,2]`. `nums[2]` is `2`. `b` becomes `2`. `length = 2`. `2 > 2/2` is `true`. Increment `c` to `3`.
    *   **`j = 3`**: Subarray `[2,2,3]`. `nums[3]` is `3`. `b` remains `2`. `length = 3`. `2 > 3/2` is `true`. Increment `c` to `4`.

3.  **`i = 2`**: Start of outer loop, considering subarrays beginning at index `2`.
    *   **`j = 2`**: Subarray `[2]`. `nums[2]` is `2`. `b` becomes `1`. `length = 1`. `1 > 1/2` is `true`. Increment `c` to `5`.
    *   **`j = 3`**: Subarray `[2,3]`. `nums[3]` is `3`. `b` remains `1`. `length = 2`. `1 > 2/2` is `false`.

4.  **`i = 3`**: Start of outer loop, considering subarrays beginning at index `3`.
    *   **`j = 3`**: Subarray `[3]`. `nums[3]` is `3`. `b` remains `0`. `length = 1`. `0 > 1/2` is `false`.

After checking all possible subarrays, the final count `c` is `5`. This matches the example output.

## Complexity Analysis
*   **Time Complexity**: O(N^2)
    *   The outer loop iterates `N` times (for `i` from `0` to `nums.length - 1`).
    *   The inner loop iterates up to `N` times (for `j` from `i` to `nums.length - 1`).
    *   Inside the inner loop, operations such as checking `nums[j]`, incrementing `b`, calculating `length`, and the comparison `b > length / 2` all take constant time, O(1).
    *   Therefore, the total time complexity is proportional to `N * N`, which simplifies to O(N^2).
*   **Space Complexity**: O(1)
    *   The solution uses a fixed number of auxiliary variables (`c`, `b`, `length`) regardless of the input array size. No additional data structures are allocated that scale with `N`.

## Java Solution

```java
class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int c = 0; // Global counter for valid subarrays
        
        // Outer loop: iterates through all possible starting indices 'i'
        for (int i = 0; i < nums.length; i++) {
            int b = 0; // Counter for 'target' occurrences in the current subarray [i...j]
            
            // Inner loop: iterates through all possible ending indices 'j' for the current 'i'
            for (int j = i; j < nums.length; j++) {
                // If the current element is the target, increment its count
                if (nums[j] == target) {
                    b++;
                }
                
                // Calculate the length of the current subarray [i...j]
                int length = j - i + 1;
                
                // Check if 'target' is the majority element (appears strictly more than half the times)
                if (b > length / 2) {
                    c++; // If it is, increment the global counter
                }
            }
        }
        return c; // Return the total count of valid subarrays
    }
}

```