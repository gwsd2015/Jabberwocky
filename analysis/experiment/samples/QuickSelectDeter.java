import java.util.*;
import java.io.*;

public class QuickSelectDeter{

    static int sortPartition(int[] arr, int p, int r, int[] indices){
	int pivotI = (p+r)/2;
	int pivotVal = arr[pivotI];
	//swap pivot element with A[r]
	int tmp = arr[pivotI];
	int tmpIndices = indices[pivotI];
	arr[pivotI] = arr[r];
	indices[pivotI] = indices[r];
	arr[r] =  tmp;
	indices[r] = tmpIndices;
	int storeIndex = p;
	//compare remaining elements against pivotValue
	for(int i=p; i<r; i++){
	    if(arr[i] < pivotVal){
		tmp = arr[i];
		tmpIndices = indices[i];
		arr[i] = arr[storeIndex];
		indices[i] = indices[storeIndex];
		arr[storeIndex] = tmp;
		indices[storeIndex] = tmpIndices;
		storeIndex++;
	    }
	}
	tmp = arr[storeIndex];
	tmpIndices = indices[storeIndex];
	arr[storeIndex] = arr[r];
	indices[storeIndex] = indices[r];
	arr[r] = tmp; //move pivot to final position
	indices[r] = tmpIndices;
	return storeIndex;
    }

    static void sort(int[] A, int lo, int hi, int[] indices){
	int q;
	if(lo < hi){
	    q = sortPartition(A, lo, hi, indices);
	    sort(A, lo, q-1, indices);
	    sort(A, q+1, hi, indices);
	}
    }

    static int getMedian(int[] A, int lo, int hi, int[] indices){
	int[] ACopy = Arrays.copyOfRange(A, lo, hi+1);
	sort(ACopy, 0, indices.length-1, indices);
	return indices[indices.length/2];	
    }

    static int partition(int[] A, int lo, int hi){
	int GROUP_SIZE = 5;
	int numGroups = (hi-lo)/GROUP_SIZE + 1;
	int[] medians = new int[numGroups];
	int[] mediansI = new int[numGroups];
	int counter = 0;
	for(int i=lo; i<=hi; i+=5){
	    int indices[];
	    int top;

	    if(i+4 <= hi){
		//OK to use i
		indices = new int[GROUP_SIZE];
		top = i+4;
	    }else{
		//use hi
		indices = new int[hi-i+1];
		top = hi;
	    }
	    for(int j=0; j<indices.length; j++){
		indices[j] = i+j;
	    }
	    mediansI[counter] = getMedian(A, i, top, indices);
	    medians[counter] = A[mediansI[counter]];
	    counter++;
	}
	return getMedian(medians, 0, medians.length-1, mediansI);
    }

    static void partitionAround(int[] arr, int pivotI, int p, int r){
	int pivotVal = arr[pivotI];
	//swap pivot element with A[r]
	int tmp = arr[pivotI];
	arr[pivotI] = arr[r];
	arr[r] =  tmp;
	int storeIndex = p;
	//compare remaining elements against pivotValue
	for(int i=p; i<r; i++){
	    if(arr[i] < pivotVal){
		tmp = arr[i];
		arr[i] = arr[storeIndex];
		arr[storeIndex] = tmp;
		storeIndex++;
	    }
	}
	tmp = arr[storeIndex];
	arr[storeIndex] = arr[r];
	arr[r] = tmp; //move pivot to final position
    }

    public static int Select(int[] A, int k, int lo, int hi){
	int k1 = partition(A, lo, hi);
	partitionAround(A, k1, lo, hi);
	//	System.out.println("A: " + Arrays.toString(A) + " k: " + k + " lo: " +
	//		   lo + " hi: " + hi + " k1: " + k1);
	if(k == k1){
	    return A[k1];
	}else if(k < k1){
	    return Select(A, k, lo, k1-1);
	}else{
	    return Select(A, k, k1+1, hi);
	}
    }

    public static void main(String[] args){
	int[] A = {4, 87, 2, 65, 32, 67, 132, 16, 4, 3, 8, 12};
	System.out.println("A = " + Arrays.toString(A));
	System.out.println("k = 0: " + Select(A, 0, 0, A.length-1));
	System.out.println("k = A.length-1: " + Select(A, A.length-1, 0, A.length-1));
	System.out.println("k = 6: " + Select(A, 6, 0, A.length-1));
	System.out.println("k = 2: " + Select(A, 2, 0, A.length-1));
	System.out.println("k = 9: " + Select(A, 9, 0, A.length-1));
    }
}
