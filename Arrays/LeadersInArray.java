import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
class Solution {
    public List<Integer> leaders(int[] nums) {
        List <Integer> res=new ArrayList<>();
        int n=nums.length;
        int max=nums[n-1];
        res.add(max);
        for(int i=n-2;i>=0;i--){
            if(nums[i]>max){
                res.add(nums[i]);
                max=nums[i];
            }
        }
        Collections.reverse(res);
        return res;
    }
}
