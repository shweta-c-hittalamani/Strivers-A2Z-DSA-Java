class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];
        int p = 0;
        int ne = 1;

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                temp[p] = nums[i];
                p += 2;
            } else {
                temp[ne] = nums[i];
                ne += 2;
            }
        }

        return temp;
    }
}
