class Solution {
    public void sortZeroOneTwo(int[] nums) {
        int n=nums.length;
        int l=0;
        int mid=n/2;
        int r=n-1;
        while(l<r){
            if(nums[mid]==0){
                int temp=nums[l];
                nums[l]=nums[mid];
                nums[mid]=temp;
                l++;
                mid++;
            }
            else if(nums[mid]==2){
                int temp=nums[r];
                nums[r]=nums[mid];
                nums[mid]=temp;
                r--;
            }
            else{
                mid++;
            }
        }
    }
}
