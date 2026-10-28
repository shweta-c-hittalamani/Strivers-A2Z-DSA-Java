class Solution {
    public int maxDepth(String s) {
        // Your code goes here
        int max=0;
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                count++;
                max=Math.max(count,max);
                
            }
            else if(s.charAt(i)==')'){
                count--;
                
            }
            else{
                continue;
            }
        }
        return max;
    }
}
