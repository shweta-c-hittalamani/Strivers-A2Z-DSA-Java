class Solution {
    public int romanToInt(String s) {
        // Your code goes here
        int i=0;
        int res=0;
        while(i!=s.length()-1){
            int current = getval(s.charAt(i));
            int next = getval(s.charAt(i + 1));
            if(current<next){
                res=res-current;
                i++;
            }
            else{
                res=res+current;
                i++;
            }
        }
        res=res+getval(s.charAt(s.length()-1));
        return res;
    }    
    public int getval(char ch){
        if(ch=='I'){
            return 1;
        }
        if(ch=='V'){
            return 5;
        }
        if(ch=='X'){
            return 10;
        }
        if(ch=='L'){
            return 50;
        }
        if(ch=='C'){
            return 100;
        }
        if(ch=='D'){
            return 500;
        }
        if(ch=='M'){
            return 1000;
        }
        return 0;
    }
    
}
