import java.util.Scanner;

public class RemovalGame {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        long result = new RemovalGame().findPlayer1Max(n,arr);
        System.out.println(result);
    }

    long findPlayer1Max(int n, int[] arr){
        if(n==1) return arr[n-1];
        long[][] dp = new long[n][n];

        for(int i=1;i<n;i++){
            for(int j=0;j<n-i;j++){
                if(i==1){
                    dp[j][j+i] = Math.max(arr[j],arr[j+1]);
                }
                else if(i==2){
                    dp[j][j+i] = Math.max(arr[j]+Math.min(arr[j+1],arr[j+2]),arr[j+i]+Math.min(arr[j],arr[j+1]));
                }
                else{
                    dp[j][j+i] = Math.max(arr[j]+Math.min(dp[j+2][j+i],dp[j+1][j+i-1]),arr[j+i]+Math.min(dp[j][j+i-2],dp[j+1][j+i-1]));
                }
            }
        }

        return dp[0][n-1];
    }
}


