class Solution {
    public int maxLen(int[] arr) {
        // Your code goes here
        HashMap<Integer,Integer> map=new HashMap<>();
        int sum=0;
        int max=0;
        for(int i=0;i<arr.length;i++){
            sum=sum+arr[i];
            if(sum==0){ //if index starts with 0
                max=i+1;
            }
            if(map.containsKey(sum)){ //if required index starts in between somewhere 
                int len=i-map.get(sum);
                max=Math.max(len,max);
            }
            if(!map.containsKey(sum)){  //adding elements to the map
                map.put(sum,i);
            }
            
        }
        return max;

    }
}
