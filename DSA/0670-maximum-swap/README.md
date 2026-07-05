# [Maximum Swap](https://leetcode.com/problems/maximum-swap/)

🟡 Medium

**Topics:** Math, Greedy

## Problem Summary

Given an integer, the goal is to obtain the largest possible number by performing at most one swap of two of its digits. The task is to return this maximum valued number. If no swap can improve the number, it should remain unchanged.

## Approach

The core idea is to find the *first* digit from the left that can be swapped with a *larger* digit to its right to maximize the number. To achieve the largest possible number, we prioritize swaps at more significant (leftmost) positions.

The strategy involves two main steps:

1.  **Pre-computation of Suffix Maximums:** We first convert the integer into a character array for easier manipulation. Then, we create an auxiliary array, `maxValIdx`, to efficiently determine the index of the largest digit within any given suffix of the number. Specifically, `maxValIdx[i]` stores the index `j` such that `s[j]` is the largest digit in `s[i...n-1]`, and critically, if there are multiple occurrences of the largest digit, `j` should be the *rightmost* index. This array is built by iterating from right to left.
2.  **Greedy Swap Search:** We then iterate through the digits of the number from left to right. For each digit at index `i`, we use `maxValIdx[i]` to find the index `j` of the largest digit in the suffix starting from `i`. If the digit at `s[i]` is smaller than the digit at `s[j]` (meaning a profitable swap is possible), we perform this swap. This is the first and optimal swap because it affects the most significant position possible and swaps with the largest available digit to its right (preferring the rightmost one to ensure maximum value). After performing the swap, we convert the modified character array back to an integer and return it. If no such swap is found after iterating through all digits (e.g., the digits are already in non-increasing order), it means the number is already in its maximally swapped form, so we return the original number.

## Algorithm Walkthrough

Let's walk through the example `num = 2736`.

1.  **Convert to character array**:
    `s = ['2', '7', '3', '6']`
    `n = 4` (length of the array)

2.  **Build `maxValIdx` array**: This array will store the index of the rightmost maximum digit for each suffix.
    -   `maxValIdx` is initialized as `[?, ?, ?, ?]`
    -   For `i = 3` (last digit '6'): `maxValIdx[3] = 3`. (The maximum in `s[3...3]` is '6' at index 3).
    -   For `i = 2` (digit '3'):
        -   Compare `s[2]` ('3') with `s[maxValIdx[3]]` (`s[3]` which is '6').
        -   Since `'3' < '6'`, the maximum in `s[2...3]` is still `s[3]`. So, `maxValIdx[2] = maxValIdx[3] = 3`.
    -   For `i = 1` (digit '7'):
        -   Compare `s[1]` ('7') with `s[maxValIdx[2]]` (`s[3]` which is '6').
        -   Since `'7' > '6'`, the maximum in `s[1...3]` is `s[1]`. So, `maxValIdx[1] = 1`.
    -   For `i = 0` (digit '2'):
        -   Compare `s[0]` ('2') with `s[maxValIdx[1]]` (`s[1]` which is '7').
        -   Since `'2' < '7'`, the maximum in `s[0...3]` is still `s[1]`. So, `maxValIdx[0] = maxValIdx[1] = 1`.

    After this step, `maxValIdx = [1, 1, 3, 3]`.

3.  **Iterate and Swap**:
    -   **`i = 0`**:
        -   Current digit `s[0]` is '2'.
        -   `j = maxValIdx[0]` is `1`.
        -   The largest digit to the right (including `s[0]`) is `s[j]` (`s[1]`) which is '7'.
        -   Is `s[0] < s[j]`? Is `'2' < '7'`? Yes.
        -   Perform the swap between `s[0]` and `s[1]`.
        -   `s` becomes `['7', '2', '3', '6']`.
        -   A profitable swap has been performed. Convert `s` back to an integer and return.
        -   Result: `7236`.

## Complexity Analysis

*   **Time Complexity**:
    The conversion of the integer to a character array takes `O(L)` time, where `L` is the number of digits in `num`. Building the `maxValIdx` array involves a single pass from right to left, taking `O(L)` time. The subsequent search for a swap also involves a single pass from left to right, taking `O(L)` time. Finally, converting the character array back to an integer takes `O(L)` time. Thus, the overall time complexity is **`O(L)`**. Given that `num <= 10^8`, `L` is at most 9-10, making this approach extremely efficient.

*   **Space Complexity**:
    We use a character array `s` of size `L` to store the digits and an integer array `maxValIdx` of size `L` to store the indices of suffix maximums. This results in an overall space complexity of **`O(L)`**.