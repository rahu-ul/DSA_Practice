class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        
        
        String numStr = String.valueOf(n);
        
        for (char c : numStr.toCharArray()) {
            if (c != '0') {
                int digit = c - '0';
                
                x = x * 10 + digit;
                
                sum += digit;
            }
        }
        
        return x * sum;
    }
}
