# [Sequential Digits](https://leetcode.com/problems/sequential-digits/)

🟡 Medium

Topics: Enumeration

## Problem Summary

The problem asks us to identify all integers within a given inclusive range `[low, high]` that possess "sequential digits." An integer has sequential digits if each digit in the number is exactly one greater than the preceding digit (e.g., 123, 4567). The final output should be a sorted list of these sequential digit integers.

## Approach

The most effective approach for this problem is to generate all possible sequential digit numbers and then filter them based on the given `[low, high]` range. Since the maximum value for `high` is `10^9`, the largest possible sequential digit number is `123456789`. This means the total number of sequential digit integers is relatively small.

We can systematically generate these numbers using nested loops:

1.  **Outer Loop (Starting Digit):** Iterate through possible starting digits from `1` to `9`. This ensures we cover all possible sequential sequences.
2.  **Inner Loop (Building the Number):** For each starting digit, incrementally build the sequential number by appending the next digit.
    *   Initialize `current_num` with the starting digit.
    *   Initialize `next_digit` to `starting_digit + 1`.
    *   While `next_digit` is less than or equal to `9` and `current_num` doesn't exceed `high` after appending `next_digit`:
        *   Append `next_digit` to `current_num` (e.g., `current_num = current_num * 10 + next_digit`).
        *   If `current_num` falls within the `[low, high]` range, add it to our result list.
        *   Increment `next_digit`.

This generation strategy naturally produces sequential numbers in a sorted order (smaller starting digits first, then shorter numbers, then longer numbers). Finally, return the collected list of numbers.

## Algorithm Walkthrough

Let's trace the algorithm with `low = 100`, `high = 300`:

1.  Initialize an empty list `result`.

2.  **Outer loop: `starting_digit` from `1` to `9`**

    *   **`starting_digit = 1`**:
        *   `current_num = 1`, `next_digit = 2`
        *   Append `2`: `current_num = 12`. Not in `[100, 300]`.
        *   Append `3`: `current_num = 123`.
            *   Is `123` in `[100, 300]`? Yes. Add `123` to `result`. (`result = [123]`)
            *   `next_digit` becomes `4`.
        *   Append `4`: `current_num = 1234`.
            *   Is `1234` in `[100, 300]`? No, `1234 > 300`.
            *   Since `current_num` exceeded `high`, we stop building this sequence and break the inner loop.

    *   **`starting_digit = 2`**:
        *   `current_num = 2`, `next_digit = 3`
        *   Append `3`: `current_num = 23`. Not in `[100, 300]`.
        *   Append `4`: `current_num = 234`.
            *   Is `234` in `[100, 300]`? Yes. Add `234` to `result`. (`result = [123, 234]`)
            *   `next_digit` becomes `5`.
        *   Append `5`: `current_num = 2345`.
            *   Is `2345` in `[100, 300]`? No, `2345 > 300`.
            *   Break inner loop.

    *   **`starting_digit = 3`**:
        *   `current_num = 3`, `next_digit = 4`
        *   Append `4`: `current_num = 34`. Not in `[100, 300]`.
        *   Append `5`: `current_num = 345`.
            *   Is `345` in `[100, 300]`? No, `345 > 300`.
            *   Break inner loop.

    *   ... (For `starting_digit` from `4` to `9`, any sequential number generated will quickly exceed `300`).

3.  After iterating through all starting digits, the `result` list contains `[123, 234]`. This list is already sorted due to the generation order.

4.  Return `result`.

## Complexity Analysis

*   **Time Complexity**: `O(1)`
    The algorithm generates all possible sequential numbers. The maximum possible sequential number is `123456789` (9 digits). There are a fixed, small number of sequential digits (e.g., 12, 123, ..., 123456789; roughly 36 such numbers). The outer loop runs 9 times, and the inner loop runs at most 9 times. Thus, the total number of operations is constant and independent of the input `low` and `high` values within the given constraints.

*   **Space Complexity**: `O(1)`
    The space used is primarily for storing the result list. The maximum number of sequential digit integers that can exist in the range `[10, 10^9]` is 36. This is a fixed maximum size, making the space complexity constant.