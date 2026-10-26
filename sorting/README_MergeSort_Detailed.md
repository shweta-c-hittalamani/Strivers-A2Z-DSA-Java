# Merge Sort in Java — Detailed Notes on My Mistakes and Learnings

## 1. Problem Statement

Implement Merge Sort to sort an integer array in ascending order.

Example:

**Input**
```text
[4, 2, 3, 1]
```

**Expected output**
```text
[1, 2, 3, 4]
```

## 2. What Is Merge Sort?

Merge Sort is a **divide-and-conquer** sorting algorithm.

It works in three main stages:

1. **Divide:** Split the array into two smaller halves.
2. **Conquer:** Recursively sort each half.
3. **Merge:** Combine the two sorted halves into one sorted section.

The `divide()` method handles the recursive splitting. The `merge()` method combines two already-sorted sections; it does not need recursion.

## 3. How the Recursion Works

For the input `[4, 2, 3, 1]`, the splitting process looks like this:

```text
                 [4, 2, 3, 1]
                  /         \
              [4, 2]       [3, 1]
              /   \         /   \
            [4]   [2]     [3]   [1]
```

A single-element array is already sorted, so recursion stops when `l >= h`.

After the recursive calls return, the merge operations happen in reverse order:

```text
            [4] + [2]  -> [2, 4]
            [3] + [1]  -> [1, 3]
          [2, 4] + [1, 3] -> [1, 2, 3, 4]
```

This is why `merge(arr, l, m, h)` belongs **after both recursive calls** inside `divide()`.

## 4. Mistake 1 — Dividing the Array Incorrectly in `mergeSort()`

### What I did
In an earlier version, I calculated `mid` inside `mergeSort()` and called `divide()` on only part of the array. I also tried calling `divide()` separately on the left and right halves.

### Why it was wrong
`divide()` already knows how to split its assigned section into halves recursively. If `mergeSort()` calls it on only part of the array, some elements may never be sorted. Calling it separately on the halves is also unnecessary.

### Correct approach
Call `divide()` once with the complete array range:

```java
public int[] mergeSort(int[] nums) {
    divide(nums, 0, nums.length - 1);
    return nums;
}
```

**Lesson:** The top-level method starts the recursion. The recursive method handles all further divisions and merging.

---

## 5. Mistake 2 — Moving Both Pointers at Once

### What I did
I initially wrote logic like this:

```java
if (arr[i] < arr[j]) {
    i++;
    j++;
}
```

### Why it was wrong
Suppose the sorted halves are:

```text
Left half:  [2, 5]
Right half: [3, 4]
             ^  ^
             i  j
```

The first comparison is `2` versus `3`. Since `2` is smaller, I should copy `2` into the temporary array and advance only `i`. The value `3` has not been copied yet, so `j` must stay at its current position.

If both pointers move, the element `3` can be skipped and the output can become incorrect.

### Correct rule
- If `arr[i] <= arr[j]`, copy `arr[i]`, then advance `i` and `k`.
- Otherwise, copy `arr[j]`, then advance `j` and `k`.

Here, `k` is the position in the temporary array where the next selected element will be stored.

---

## 6. Mistake 3 — Swapping Elements Instead of Merging

### What I did
I tried swapping elements directly in the original array:

```java
int temp = arr[i];
arr[i] = arr[j];
arr[i + 1] = temp;
```

### Why it was wrong
Merge Sort does not sort by swapping the current elements. It assumes the left half and right half are already sorted and repeatedly chooses the smaller of their first remaining elements.

For example:

```text
Left:  [2, 5]
Right: [3, 4]
```

The correct merged order is `[2, 3, 4, 5]`.

A direct swap does not reliably place every element into its correct position, especially when either half has multiple elements.

### Correct approach
Use a temporary array. Compare the current elements in both halves and append the smaller element to the temporary array.

**Lesson:** In Merge Sort, use a temporary array to build the merged result instead of trying to fix the original array through swaps.

---

## 7. Mistake 4 — Not Creating and Filling the Temporary Array Properly

### What I did
I later created a temporary array:

```java
int[] temp = new int[h - l + 1];
```

This was correct, but the merging process was incomplete.

### Why this size is correct
The section being merged begins at index `l` and ends at index `h`, including both endpoints. Its size is:

```text
h - l + 1
```

For example, if `l = 2` and `h = 5`, the section contains indices `2, 3, 4, 5`, which is four elements. Therefore, the temporary array needs size `5 - 2 + 1 = 4`.

### What the pointers mean
- `i = l`: current position in the left half.
- `j = m + 1`: current position in the right half.
- `k = 0`: next free position in `temp`.

The temporary array starts at index `0`, even if the section being merged starts at a later index in `arr`.

---

## 8. Mistake 5 — Forgetting That One Half May Have Remaining Elements

### What I did
I used only this loop:

```java
while (i <= m && j <= h) {
    // compare and copy an element
}
```

### Why it was incomplete
The loop stops as soon as either half runs out of elements. The other half may still contain elements that have not been copied.

Example:

```text
Left half:  [1, 3]
Right half: [2, 4, 5]
```

The main loop compares and copies `1`, `2`, `3`, and `4`. At that point, the left half is exhausted, but `5` still needs to be copied.

### Correct approach
Use two additional loops after the main comparison loop:

```java
while (i <= m) {
    temp[k++] = arr[i++];
}

while (j <= h) {
    temp[k++] = arr[j++];
}
```

Only one of these loops will usually have remaining elements to copy, but using both makes the method handle either situation.

---

## 9. Mistake 6 — Writing `arr = temp`

### What I did
I wrote:

```java
arr = temp;
```

### Why it was wrong
`arr` is a local reference inside the `merge()` method. Assigning `temp` to `arr` changes which array that local variable refers to. It does **not** copy the values into the original array object that `mergeSort()` returns.

The caller still has its original array, so the merged values must be copied back into that array.

### Correct approach
```java
for (int x = 0; x < temp.length; x++) {
    arr[l + x] = temp[x];
}
```

### Why use `l + x`?
The temporary array uses indices starting at `0`, but the merged section of the original array starts at `l`.

For example, if `l = 2`:
- `temp[0]` must go into `arr[2]`.
- `temp[1]` must go into `arr[3]`.
- `temp[2]` must go into `arr[4]`.

Therefore, the original-array index is `l + x`, not just `x`.

---

## 10. Mistake 7 — Using `<` Instead of `<=`

### What I wrote
```java
if (arr[i] < arr[j])
```

### Is this incorrect?
No. It can still sort the values correctly.

### Why use `<=`?
Using `arr[i] <= arr[j]` selects the left element first when both elements are equal. This helps preserve the relative order of equal elements, making the merge **stable**.

For a basic integer-array problem, either comparison can produce sorted output. `<=` is a useful standard choice for stable Merge Sort.

---

## 11. Correct `merge()` Method

```java
public void merge(int[] arr, int l, int m, int h) {
    int[] temp = new int[h - l + 1];

    int i = l;       // Left half pointer
    int j = m + 1;   // Right half pointer
    int k = 0;       // Temporary array pointer

    // Compare elements while both halves have items
    while (i <= m && j <= h) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
        }
    }

    // Copy any remaining elements from the left half
    while (i <= m) {
        temp[k++] = arr[i++];
    }

    // Copy any remaining elements from the right half
    while (j <= h) {
        temp[k++] = arr[j++];
    }

    // Copy the merged section back into the original array
    for (int x = 0; x < temp.length; x++) {
        arr[l + x] = temp[x];
    }
}
```

## 12. Complete Working Java Solution

```java
class Solution {
    public int[] mergeSort(int[] nums) {
        divide(nums, 0, nums.length - 1);
        return nums;
    }

    public void divide(int[] arr, int l, int h) {
        if (l >= h) {
            return;
        }

        int m = l + (h - l) / 2;

        divide(arr, l, m);
        divide(arr, m + 1, h);

        merge(arr, l, m, h);
    }

    public void merge(int[] arr, int l, int m, int h) {
        int[] temp = new int[h - l + 1];

        int i = l;
        int j = m + 1;
        int k = 0;

        while (i <= m && j <= h) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= m) {
            temp[k++] = arr[i++];
        }

        while (j <= h) {
            temp[k++] = arr[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            arr[l + x] = temp[x];
        }
    }
}
```

## 13. Dry Run

Input:

```text
[4, 2, 3, 1]
```

### Step 1 — Divide
```text
[4, 2, 3, 1]
[4, 2] and [3, 1]
[4], [2], [3], [1]
```

### Step 2 — Merge the smallest sections
```text
[4] and [2] -> [2, 4]
[3] and [1] -> [1, 3]
```

### Step 3 — Merge the sorted halves
```text
[2, 4] and [1, 3] -> [1, 2, 3, 4]
```

Output:

```text
[1, 2, 3, 4]
```

## 14. Complexity Analysis

### Time Complexity: `O(n log n)`
The array is divided into approximately `log n` levels. At each level, merging processes a total of `n` elements. Therefore, the total time complexity is `O(n log n)` in the best, average, and worst cases.

### Auxiliary Space: `O(n)`
The merge operation uses temporary arrays to store elements before copying them back. The overall auxiliary space is `O(n)` for a standard Merge Sort implementation.

## 15. Checklist for Future Merge Sort Problems

Before submitting, check:

- [ ] `mergeSort()` calls `divide()` once for the full array.
- [ ] `divide()` returns when `l >= h`.
- [ ] The middle index is calculated as `l + (h - l) / 2`.
- [ ] Both halves are recursively divided.
- [ ] `merge()` is called after both recursive calls.
- [ ] The temporary array has size `h - l + 1`.
- [ ] The left pointer starts at `l` and the right pointer at `m + 1`.
- [ ] The main loop continues while both halves have elements.
- [ ] Only the pointer corresponding to the copied element advances.
- [ ] Remaining elements from both halves are copied.
- [ ] The temporary values are copied back starting at `arr[l]`.

## Final Learning

The most important distinction is:

- `divide()` uses **recursion** to split the array.
- `merge()` uses **loops and a temporary array** to combine sorted halves.
- Copying the temporary array back into the correct part of the original array is essential; otherwise, the final result will not be updated correctly.
