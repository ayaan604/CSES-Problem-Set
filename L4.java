class L4 {
    boolean evenFlag = false;
    int medianPos;
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length, comb = n+m;
        double result = 0;

        if(n==0 || m==0){
            if(m==0){
                handleForEmpty(nums1);
            }
            else{
                handleForEmpty(nums2);
            }   
        }

        if(comb%2==0) evenFlag = true;
        medianPos = (comb-1)/2;

        // first nums1
        result = findMedian(nums1,nums2,n,m);
        // second nums2
        result = Math.min(result,findMedian(nums2, nums1, m, n));
        return result;
    }

    double findMedian(int[] nums1,int[] nums2,int n, int m){
        double result = 0;
        boolean flag = false;
        int l = 0, r = n-1, mid, opt1, opt2,target = Integer.MAX_VALUE;

        while(l<=r){
            mid = l+(r-l)/2;
            if(checkMedian(nums1,nums2,mid,medianPos)){
                flag = true;
                r = mid;
                target = Math.min(target,mid);
            } else{
                l = mid;
            }

            if(r-l<=1){
                if(checkMedian(nums1,nums2,l,medianPos)){
                    target = Math.min(target,l);
                    flag = true;
                    break;
                }
                if(checkMedian(nums1,nums2,r,medianPos)){
                    target = Math.min(target,r);
                    flag = true;
                }
                break;
            }
        }
        if(!flag) return Double.MAX_VALUE;

        if(evenFlag){
            opt1 = opt2 = Integer.MAX_VALUE;
            if(target+1<n){
                opt1 = nums1[target+1];
            }
            if(medianPos-target<m){
                opt2 = nums2[medianPos-target];
            }
            
            result = (double)(nums1[target]+Math.min(opt1,opt2))/2;
        } else{
            result = (double) nums1[target];
        }

        return result;
    }

    boolean checkMedian(int[] nums1,int[] nums2,int idx, int medianPos){
        int idx2 = medianPos-idx-1;
        if(idx2<0){
            return true;
        }
        if(idx2>=nums2.length){
            return false;
        }
        if(nums2[idx2]<=nums1[idx]) return true;
        return false;
    }

    double handleForEmpty(int[]arr){
        int n;
        double result = 0;
        
        n = arr.length;
        if(n%2==0){
            result = (double)(arr[n/2]+arr[(n-1)/2])/2;
        }
        else{
            result = arr[n/2];
        }
        
        return result;
    }

    public static void main(String[] args){
        L4 main = new L4();
        int[] arr1 = {1};
        int[] arr2 = {2,3,4};

        double result = main.findMedianSortedArrays(arr1, arr2);
        System.out.println(result);
    }
}