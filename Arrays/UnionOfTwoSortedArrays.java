import java.util.Arrays;
class Solution {
    public int[] unionArray(int[] nums1, int[] nums2) {
        int n1=nums1.length;
        int n2=nums2.length;
        int i=0;
        int j=0;
        int temp[]=new int[n1+n2];
        int k=0;
        while (i<n1 && j<n2){
            if(nums1[i]<nums2[j]){
                if(k>0 && temp[k-1]!=nums1[i] || k==0){
                    temp[k]=nums1[i];
                    k++;
                }
                i++;
            }
            else if(nums1[i]>nums2[j]){
                if(k>0 && temp[k-1]!=nums2[j] || k==0){
                    temp[k]=nums2[j];
                    k++;
                }
                j++;
            }
            else{
                if(k>0 && temp[k-1]!=nums1[i] || k==0){
                    temp[k]=nums1[i];
                    k++;
                }
                i++;
                j++;
            }
        }
        while(i<n1){
            if(k>0 && temp[k-1]!=nums1[i] || k==0){
                temp[k]=nums1[i];
                k++;
            }
            i++;
        }
        while(j<n2){
            if(k>0 && temp[k-1]!=nums2[j] || k==0){
                temp[k]=nums2[j];
                k++;
            }
            j++;
        }

        return Arrays.copyOf(temp,k);
    }
}
