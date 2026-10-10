 import java.util.Arrays;
class Solution {
    public int[] intersectionArray(int[] nums1, int[] nums2) {
        int n1=nums1.length;
        int n2=nums2.length;
        int result[]=new int[n1+n2];
        int i=0;
        int j=0;
        int k=0;
        while(i<n1 && j<n2){
            if(nums1[i]>nums2[j]){
                j++;
            }
            else if(nums1[i]<nums2[j]){
                i++;
            }
            else{
                result[k]=nums1[i];
                i++;
                j++;
                k++;
            }
        }
        return Arrays.copyOf(result,k);
    }
}
