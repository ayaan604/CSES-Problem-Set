import java.util.Stack;

public class L42 {
    public static void main(String[] args) {
        int[] arr = {3,0,0,2,0,4};
        int n = 6;

        long result = new L42().trappingWater(arr, n);
        System.out.println(result);
    }

    private long trappingWater(int arr[], int n) { 
        int prev = -1;
        long result = 0;
        
        long[] prefix = new long[n];
        
        prefix[0] = arr[0];
        
        // creating prefix array which sums up area of blocks until that index
        for(int i=1;i<n;i++){
            prefix[i] = prefix[i-1]+arr[i];   
        }
        
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i<n; i++){
            while(!stack.empty() && arr[i]>arr[stack.peek()]){
                prev = stack.pop();
            }

            if(stack.empty()){
                // if stack has been emptied, then just add the blank area from leftmost block to this block in final result
                if(prev != -1 && i-prev>=2){
                    long prevArr = arr[prev];
                    result+=(prevArr*(i-prev-1))-prefix[i-1]+prefix[prev];
                }
            }
            
            stack.push(i);
        }
        
        // Find area of trapped rain water through monotonic stack
        while(stack.size()>1){
            prev = stack.pop();
            if(prev-stack.peek()>=2){
                long prevArr = arr[prev];
                result+=(prevArr*(prev-stack.peek()-1))-prefix[prev-1]+prefix[stack.peek()];
            }
        }
        
        return result;
    } 
}
