class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String master = "123456789";
        
        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();
        
        
        for (int len = minLen; len <= maxLen; len++) {
         
            for (int i = 0; i <= 9 - len; i++) {
                String sub = master.substring(i, i + len);
                int num = Integer.parseInt(sub);
                
                
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}
