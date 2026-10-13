class Solution {
    public void setZeroes(int[][] matrix) {
        // Your code goes here
        int m=matrix.length;
        int n=matrix[0].length;
        boolean zeroes[][]=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    zeroes[i][j]=true;
                }
               else{
                    zeroes[i][j]=false;

                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(zeroes[i][j]){
                    for(int k=0;k<n;k++){
                        matrix[i][k]=0;
                    }
                    for(int l=0;l<m;l++){
                        matrix[l][j]=0;
                    }
                }

            }
        }
    }
}
