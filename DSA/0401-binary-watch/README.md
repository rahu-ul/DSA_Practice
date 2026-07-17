# [Binary Watch](https://leetcode.com/problems/binary-watch/)

🟢 Easy

Topics: Backtracking, Bit Manipulation

## Problem Summary

Given an integer `turnedOn`, representing the total number of LEDs that are lit on a binary watch, the task is to find all possible valid times the watch could display. The watch has 4 LEDs for hours (0-11) and 6 LEDs for minutes (0-59). Special formatting rules apply: hours should not have a leading zero (except for "0"), and minutes must always be two digits (e.g., "05" instead of "5").

## Approach

The most straightforward approach for this problem leverages the small, fixed ranges for hours (0-11) and minutes (0-59). We can iterate through every possible valid hour and minute combination, calculate the total number of set bits (LEDs turned on) for that specific time, and then check if it matches the given `turnedOn` value.

1.  **Iterate Hours and Minutes**: Loop `h` from 0 to 11 (inclusive) and `m` from 0 to 59 (inclusive). These loops cover all possible valid times a binary watch can display.
2.  **Count Set Bits**: For each `(h, m)` pair, use a bit manipulation function (like `Integer.bitCount()` in Java) to count the number of set bits for `h` and `m` separately. The total number of LEDs turned on for this time is `bitCount(h) + bitCount(m)`.
3.  **Check Condition**: If the `totalSetBits` calculated in the previous step is equal to the given `turnedOn` value, then this `(h, m)` pair represents a valid time to be included in the result.
4.  **Format Time String**: Format the valid `h` and `m` into a string `"h:mm"` according to the problem's rules:
    *   `h`: Printed as is. No leading zero needed, unless `h` itself is 0.
    *   `m`: Must always be two digits. If `m < 10`, prepend a "0". For example, `5` becomes `"05"`.
5.  **Collect Results**: Add the formatted time string to a list of results.

This approach effectively brute-forces all 12 * 60 = 720 possible times and checks each against the given criteria.

## Algorithm Walkthrough

Let's walk through an example with `turnedOn = 1`.

1.  Initialize an empty list `result`.
2.  The outer loop iterates `h` from 0 to 11. The inner loop iterates `m` from 0 to 59.

    *   **`h = 0, m = 0`**:
        *   `Integer.bitCount(0)` (for hours) is 0.
        *   `Integer.bitCount(0)` (for minutes) is 0.
        *   Total set bits: `0 + 0 = 0`.
        *   Since `0 != turnedOn` (which is 1), we do not add "0:00".

    *   **`h = 0, m = 1`**:
        *   `Integer.bitCount(0)` is 0.
        *   `Integer.bitCount(1)` is 1.
        *   Total set bits: `0 + 1 = 1`.
        *   Since `1 == turnedOn`, this is a valid time. Format: `String.format("%d:%02d", 0, 1)` results in `"0:01"`. Add `"0:01"` to `result`.

    *   **`h = 0, m = 2`**:
        *   `Integer.bitCount(0)` is 0.
        *   `Integer.bitCount(2)` is 1.
        *   Total set bits: `0 + 1 = 1`.
        *   Since `1 == turnedOn`, this is a valid time. Format: `"0:02"`. Add `"0:02"` to `result`.

    *   ... (Continue for `m = 4, 8, 16, 32` for `h=0`, which all have 1 bit set for `m` and 0 for `h`)
        *   `"0:04"` added.
        *   `"0:08"` added.
        *   `"0:16"` added.
        *   `"0:32"` added.

    *   **`h = 1, m = 0`**:
        *   `Integer.bitCount(1)` is 1.
        *   `Integer.bitCount(0)` is 0.
        *   Total set bits: `1 + 0 = 1`.
        *   Since `1 == turnedOn`, this is a valid time. Format: `"1:00"`. Add `"1:00"` to `result`.

    *   **`h = 2, m = 0`**:
        *   `Integer.bitCount(2)` is 1.
        *   `Integer.bitCount(0)` is 0.
        *   Total set bits: `1 + 0 = 1`.
        *   Since `1 == turnedOn`, this is a valid time. Format: `"2:00"`. Add `"2:00"` to `result`.

    *   ... (Continue for `h = 4, 8` for `m=0`, which all have 1 bit set for `h` and 0 for `m`)
        *   `"4:00"` added.
        *   `"8:00"` added.

    *   Many other combinations will yield `totalSetBits != 1` and thus won't be added (e.g., `h=3, m=0` gives `bitCount(3) + bitCount(0) = 2 + 0 = 2`).

3.  After iterating through all `h` and `m` combinations, the `result` list will contain:
    `["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]`

## Complexity Analysis

*   **Time Complexity**:
    The algorithm involves two nested loops: one iterating 12 times for hours (0-11) and another iterating 60 times for minutes (0-59). Inside the loops, operations like `Integer.bitCount()` and string formatting are constant time for fixed-size integers and strings.
    Therefore, the total time complexity is `12 * 60 * O(1) = O(1)`, as the number of operations is fixed and does not depend on the input `turnedOn` in a scalable way.

*   **Space Complexity**:
    The space complexity is determined by the storage required for the result list. In the worst case (e.g., if `turnedOn` allowed for many possible times), the list would store all valid time strings. The maximum number of possible valid times is `12 * 60 = 720`. Each time string has a small, constant length (e.g., "10:59" is 5 characters).
    Thus, the space complexity is `O(1)` because the maximum number of elements and their individual sizes are bounded by constants.