class Solution {
    public int[] mergeSort(int[] nums) {
        int n=nums.length;
        int low=0;
        int high=n-1;
        divide(nums,low,high);
        return nums;
        
    }
    public void divide(int arr[],int l,int h){
        if(l>=h){
            return;
        }
        int m=l+(h-l)/2;
        divide(arr,l,m);
        divide(arr,m+1,h);
        merge(arr,l,m,h);
    }
    public void merge(int arr[],int l,int m,int h){
        int temp[]=new int[h-l+1];
        int i=l;
        int j=m+1;
        int k=0;
        while(i<=m && j<=h){
            if(arr[i]<=arr[j]){
                temp[k]=arr[i];
                i++;
                k++;
        
            }
            else{
                temp[k]=arr[j];
                j++;
                k++;
            }
        }
        while(i<=m){
            temp[k]=arr[i];
            k++;
            i++;
        }
        while(j<=h){
            temp[k]=arr[j];
            k++;
            j++;
        }
        for(int x=0;x<temp.length;x++){
            arr[l+x]=temp[x];
        }
    }
    
}
