# Concatenate Non-Zero Digits and Multiply by Sum I

🟢 Easy | [LeetCode Link](https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-i/)

## Topics
Math

## Problem Summary
Given an integer `n`, the task is to construct a new integer `x` by concatenating all non-zero digits of `n` in their original relative order. If no non-zero digits exist, `x` is 0. Subsequently, calculate `sum`, the sum of all digits in `x`. The final answer required is the product of `x` and `sum`.

## Approach
The most straightforward approach involves converting the input integer `n` into its string representation to easily process its digits sequentially. We then iterate through this string to identify and collect non-zero digits. As we collect these, we simultaneously build the integer `x` (as a string initially) and compute the sum of its digits.

1.  **String Conversion**: Convert the integer `n` into its string form. This allows easy character-by-character iteration.
2.  **Filter and Build**: Iterate through each character of the string representation of `n`. If a character is not '0':
    *   Append this non-zero digit character to a `StringBuilder` (which will form the string representation of `x`).
    *   Convert the character back to its integer value and add it to a running `sum` variable.
3.  **Handle Empty `x`**: After iterating, if the `StringBuilder` remains empty (meaning `n` contained only zeros or was 0), `x` should be 0. Otherwise, convert the content of the `StringBuilder` to a `long` to obtain the numerical value of `x`.
4.  **Final Calculation**: Return the product of `x` (the `long` value) and `sum`. It's important to use `long` for `x` and the final product, as `x * sum` can exceed the maximum value of an `int`.

## Algorithm Walkthrough

Let's use `n = 10203004` as an example.

1.  **Convert `n` to string**: `str_n = "10203004"`.
2.  **Initialize**:
    *   `xStringBuilder = ""` (empty `StringBuilder`)
    *   `sum = 0`
3.  **Iterate through `str_n`**:
    *   **'1'**:
        *   Not '0'. Append to `xStringBuilder`: `xStringBuilder = "1"`.
        *   Add `1` to `sum`: `sum = 1`.
    *   **'0'**: Skip (it's '0').
    *   **'2'**:
        *   Not '0'. Append to `xStringBuilder`: `xStringBuilder = "12"`.
        *   Add `2` to `sum`: `sum = 1 + 2 = 3`.
    *   **'0'**: Skip.
    *   **'3'**:
        *   Not '0'. Append to `xStringBuilder`: `xStringBuilder = "123"`.
        *   Add `3` to `sum`: `sum = 3 + 3 = 6`.
    *   **'0'**: Skip.
    *   **'0'**: Skip.
    *   **'4'**:
        *   Not '0'. Append to `xStringBuilder`: `xStringBuilder = "1234"`.
        *   Add `4` to `sum`: `sum = 6 + 4 = 10`.
4.  **After iteration**:
    *   `xStringBuilder` is "1234".
    *   `sum` is 10.
5.  **Convert `xStringBuilder` to `long`**: `x = Long.parseLong("1234") = 1234L`.
6.  **Calculate final result**: `x * sum = 1234L * 10 = 12340L`.

## Complexity Analysis

### Time Complexity
The time complexity is dominated by the conversion of `n` to a string and then iterating through that string.
*   Converting `n` to a string takes `O(D)` time, where `D` is the number of digits in `n` (which is roughly `log10(n)`).
*   Iterating through the string `str_n` and appending to `StringBuilder` takes `O(D)` time.
*   Converting the final `StringBuilder` to a `long` also takes `O(D_x)` time, where `D_x` is the number of digits in `x` (`D_x <= D`).
Overall, the time complexity is **O(D)**, where `D` is the number of digits in the input `n`.

### Space Complexity
The space complexity arises from storing the string representation of `n` and the `StringBuilder` used to construct `x`.
*   Storing `str_n` requires `O(D)` space.
*   The `xStringBuilder` stores at most `D` characters, requiring `O(D)` space.
Overall, the space complexity is **O(D)**, where `D` is the number of digits in the input `n`.