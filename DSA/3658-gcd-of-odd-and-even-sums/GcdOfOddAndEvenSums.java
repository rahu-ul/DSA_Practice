class Solution {
    public int gcdOfOddEvenSums(int n) {
        int so = 0;
        int se =0;
      
       for(int i=1; i<=2*n; i++){
        if(i%2 !=0)so+=i;
        else se+=i;
       }

       return gcd(so, se);
    }
    public static int gcd(int a, int b){
     if(b ==0)return a;
     return gcd(b, a%b);
    }
}
