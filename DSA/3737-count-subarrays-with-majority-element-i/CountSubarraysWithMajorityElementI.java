class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int c = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int b = 0; 
            
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == target) {
                    b++;
                }
                
                int length = j - i + 1;
                
                if (b > length / 2) {
                    c++;
                }
            }
        }
        return c;
    }
}
