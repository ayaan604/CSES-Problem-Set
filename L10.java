public class L10 {
	public static final char WILDCARD = '.';
    public static final char QUANTIFIER = '*';
    int n = 0,m = 0;
    boolean[][] dp = null;
    String s,p;

	public static void main(String[] args) {
		L10 main = new L10();
		String s = "abcaaaaaaabaabcabac";
		String p = ".*ab.a.*a*a*.*b*b*";
		long startTime = System.currentTimeMillis();
		System.out.println(main.isMatch(s, p));
	}
	
	public boolean isMatch(String s_, String p_) {
        s = s_;
        p = p_;
        n = s.length();
        m = p.length();
        dp = new boolean[n][m];

        return process(0,0);
    }

    boolean process(int i,int j){
        // if both the string and pattern indexes are out of bounds, then there is no pattern to match. Hence return true
        if(i>=n && j>=m){
            return true;
        }
        // if either string or the pattern index has gone beyond bounds, that implies either the pattern is not adequate or the string. Hence return false
        if(i>=n || j>=m){
        	if(j<m) {
        		while(j<m && j+1<m && p.charAt(j+1)== QUANTIFIER) {
        			j+=2;
        		}
        		if(j>=m) {
        			return true;
        		}
        	}
            return false;
        }

        // Check if value already exists in table
        if(dp[i][j]){
            return false;
        }

        // Check if quantifier is present at j+1 index. This helps us in deciding strategy
        boolean quantifierFlag = false;
        if(j+1<m && p.charAt(j+1) == QUANTIFIER){
            quantifierFlag = true;
        } 
        
        // Strategy in case of wildcard
        if(p.charAt(j) == WILDCARD){
            if(quantifierFlag){
            	if(process(i,j+2)) {
            		return true;
            	}
                for(int l = i;l<n;l++){
                    if(process(l+1,j+2)){
                        return true;
                    }
                }
            } else{
                if(process(i+1,j+1)){
                    return true;
                }
            }
        } else{ // strategy in case of literal character
            if(p.charAt(j)!=s.charAt(i)){
            	if(quantifierFlag && process(i,j+2)) {
            		return true;
            	}
                dp[i][j] = true;
                return false;
            } else{
                if(quantifierFlag){
                	if(process(i,j+2)) {
                		return true;
                	}
                    for(int l=i;l<n;l++){
                        if(s.charAt(l)!=p.charAt(j)){
                            break;
                        }
                        if(process(l+1,j+2)){
                            return true;
                        }
                    }
                } else{
                    if(process(i+1,j+1)){
                        return true;
                    }
                }
            }
        }
        
        // Mark this combination of indices as true, so that upon revisit computation is saved
        dp[i][j] = true;
        return false;
    }
}
