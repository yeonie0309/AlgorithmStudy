import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
 
public class Main {
	
	public static int N,K;
	public static int[] arr;
	public static int[] arr_diff;
	public static int answer =0 ;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	arr = new int[N];
    	arr_diff = new int[N-1];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=0;i<N;i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    		
    	}
    	
    	for(int i=0;i<N-1;i++) {
    		arr_diff[i] = arr[i+1] - arr[i];
 
    	}
    	Arrays.sort(arr_diff);
    	
    	for(int i=0;i< (N-1) - (K-1); i++) {
    		answer += arr_diff[i];
    	}
    	System.out.println(answer);
 
    }
}