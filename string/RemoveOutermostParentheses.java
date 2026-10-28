class Solution {
    public String removeOuterParentheses(String s) {
        // Your code goes here
        int count=0;
        StringBuilder result=new StringBuilder(); //string builder is used bcz it is easy and fast to append characters repeatedly and here.append meth is used not "+" conac sign.
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){ //s[i] is not allowed for strings in java
                if(count>0){
                    result.append('(');
                }
                count++;
            }
            else{
                count--;
                if(count>0){
                    result.append(')');
                }
            }
        }
        return result.toString();

    }
}
