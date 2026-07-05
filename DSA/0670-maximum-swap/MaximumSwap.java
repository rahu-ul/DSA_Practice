class Solution {
    public int maximumSwap(int num) {
        List<Integer> arr = new ArrayList<>();
        int a = num;
        while(a!=0){
            int h = a%10;
            arr.add(0, h);
            a = a/10;
        }
      
for (int i = 0; i < arr.size(); i++) {
    
    int maxDigit = arr.get(i);
    int maxIndex = -1;
    
    for (int j = i + 1; j < arr.size(); j++) {
        
        if (arr.get(j) >= maxDigit) {
            maxDigit = arr.get(j);
            maxIndex = j;
        }
    }
    
    
    if (maxDigit > arr.get(i)) {
        int temp = arr.get(i);
        arr.set(i, arr.get(maxIndex));
        arr.set(maxIndex, temp);
        break; 
    }
}

        int b =0;
        int i=0;
        for(int j =arr.size()-1; j>=0; j--){
            b+= arr.get(j) * Math.pow(10, i);
            i++;
        }
        return b;
    }
}
