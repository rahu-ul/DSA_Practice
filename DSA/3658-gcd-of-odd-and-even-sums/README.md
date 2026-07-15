# [GCD of Odd and Even Sums](https://leetcode.com/problems/gcd-of-odd-and-even-sums/)

🟢 Easy
Topics: Math, Number Theory

## Problem Summary
The problem asks us to find the greatest common divisor (GCD) of two sums: `sumOdd`, which is the sum of the first `n` positive odd numbers, and `sumEven`, which is the sum of the first `n` positive even numbers. We are given an integer `n` as input.

## Approach
The core idea is to find closed-form mathematical expressions for `sumOdd` and `sumEven` and then compute their GCD.

1.  **Sum of the first `n` positive odd numbers (`sumOdd`):**
    The sequence of the first `n` positive odd numbers is `1, 3, 5, ..., (2n - 1)`.
    This is an arithmetic progression. The sum `sumOdd` can be calculated using the formula:
    `sumOdd = n/2 * (first_term + last_term) = n/2 * (1 + (2n - 1)) = n/2 * (2n) = n^2`.

2.  **Sum of the first `n` positive even numbers (`sumEven`):**
    The sequence of the first `n` positive even numbers is `2, 4, 6, ..., 2n`.
    This is also an arithmetic progression. The sum `sumEven` can be calculated using the formula:
    `sumEven = n/2 * (first_term + last_term) = n/2 * (2 + 2n) = n/2 * 2(1 + n) = n(n + 1)`.

3.  **Compute GCD(`sumOdd`, `sumEven`):**
    Now we need to find `GCD(n^2, n(n + 1))`.
    We can use the GCD property `GCD(a, b) = GCD(a, b - a)` (or more generally `GCD(a, b) = GCD(a, b % a)`).
    Let `a = n^2` and `b = n(n + 1) = n^2 + n`.
    `GCD(n^2, n^2 + n)`
    `= GCD(n^2, (n^2 + n) - n^2)`
    `= GCD(n^2, n)`
    Since `n` is a factor of `n^2` (i.e., `n^2 = n * n`), the greatest common divisor of `n^2` and `n` is simply `n`.

Therefore, the final result is `n`. The problem simplifies to just returning the input `n`.

## Algorithm Walkthrough
Let's use Example 1: `n = 4`.

1.  **Calculate `sumOdd`:**
    Using the derived formula `sumOdd = n^2`:
    `sumOdd = 4^2 = 16`.
    (Confirming manually: `1 + 3 + 5 + 7 = 16`).

2.  **Calculate `sumEven`:**
    Using the derived formula `sumEven = n(n + 1)`:
    `sumEven = 4 * (4 + 1) = 4 * 5 = 20`.
    (Confirming manually: `2 + 4 + 6 + 8 = 20`).

3.  **Compute `GCD(sumOdd, sumEven)`:**
    We need `GCD(16, 20)`.
    *   Factors of 16: 1, 2, 4, 8, 16
    *   Factors of 20: 1, 2, 4, 5, 10, 20
    The greatest common divisor is 4.

This matches our derived general solution: for `n = 4`, the GCD is `n`, which is `4`.

## Complexity Analysis

### Time Complexity
*   **O(1)**: The solution involves direct arithmetic operations (multiplication, addition) and returns the input `n`. These operations take constant time, regardless of the value of `n`.

### Space Complexity
*   **O(1)**: The solution uses a constant amount of extra space to store `n` and temporary variables for calculations. No data structures are created that grow with the input `n`.