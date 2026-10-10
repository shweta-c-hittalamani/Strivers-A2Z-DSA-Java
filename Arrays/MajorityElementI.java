class Solution {
    public int majorityElement(int[] nums) {
       HashMap <Integer,Integer> map=new HashMap<>();
       int n=nums.length;
       int maj=n/2;
       for(int num:nums){
        map.put(num,map.getOrDefault(num,0)+1);
        if(map.get(num)>maj){
            return num;
        }
       }
       return 0;
    }
}
