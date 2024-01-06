import java.util.ArrayList;

public class L2939 {
    public static void main(String[] args) {
		// example test-case
        long a = 12,b = 5;
        int n = 4;
        int result = new L2939().maximumXorProduct(a,b,n);
        System.out.println(result);
    }

    public int maximumXorProduct(long a, long b, int n) {
        int MOD = (int)1e9+7;

        ArrayList<Integer> pos = new ArrayList<>();
        long a_mod = a, b_mod = b;

        for(int i=0; i<n; i++){
            int first = (int)(a>>i) & 1;
            int second = (int)(b>>i) & 1;

            if(first == second && first==0){
                a_mod+=(long) Math.pow(2,i);
                b_mod+=(long) Math.pow(2,i);
            }
            else if(first!=second){
                pos.add(i);
            }
        }

        int listSize = pos.size();

        for(int i=listSize-1;i>=0;i--){
            if(a_mod<=b_mod){
                if(!checkBit(a_mod,pos.get(i))){
                    a_mod+=(long) Math.pow(2,pos.get(i));
                    b_mod-=(long) Math.pow(2,pos.get(i));
                }
            }
            else{
                if(!checkBit(b_mod,pos.get(i))){
                    a_mod-=(long) Math.pow(2,pos.get(i));
                    b_mod+=(long) Math.pow(2,pos.get(i));
                }
            }
        }

        int result = calculate(a_mod,b_mod,MOD);

        return result;
    }

	// copied this piece of code from the internet 
    int calculate(long a, long b, int mod){
        // Initialize result 
        long res = 0;   
  
        // Update a if it is more than  
        // or equal to mod  
        a %= mod; 
  
        while (b > 0)  
        { 
              
            // If b is odd, add a with result  
            if ((b & 1) > 0)  
            { 
                res = (res + a) % mod; 
            } 
  
            // Here we assume that doing 2*a  
            // doesn't cause overflow  
            a = (2 * a) % mod; 
  
            b >>= 1; // b = b / 2  
        } 
        return (int)res; 
    }

	// Method to check if a particular bit is 0 or 1. Returns true if 1, false if 0
    boolean checkBit(long num, int bit){
        if(((num>>bit) & 1) == 1) return true;
        return false;
    }
}
