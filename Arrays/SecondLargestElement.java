class Solution {
    public int secondLargestElement(int[] nums) {
        int largest=Integer.MIN_VALUE;
        int sec=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>largest){
                sec=largest;
                largest=nums[i];
            }
            else if(nums[i]<largest && nums[i]>sec){
                sec=nums[i];
            }
            
        }
        if(sec==Integer.MIN_VALUE){
            return -1;
        }
        return sec;
    }
}
