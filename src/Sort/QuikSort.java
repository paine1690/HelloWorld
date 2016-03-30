package Sort;

import java.util.Arrays;

public class QuikSort {
	/*
	 * 快速排序
	 * 基于比较的内部排序中最好的方法，当待排序元素随机分布时，快速排序的平均时间最短
	 * 
	 * 1、复杂度：最坏情况会O(n^2)，但是平均性能O(nlgn)
	 * 2、空间复杂度：O(lgn),递归栈。最坏情况下O(n)
	 * 3、不是稳定排序
	 */
	
	
	/*
	 * 数组划分
	 * 将nums数组从p到r进行原址重排，nums[p...q-1]<nums[q]<nums[q+1...r]
	 * 
	 * 过程：
	 * 选取x=nums[r]为主元
	 * 数组中p到i部分全部小于x，i+1到j部分全部大于x
	 * 
	 */
	private static int partition(int[] nums, int p, int r){
		int x=nums[r];
		int i=p-1;
		int temp;
		for(int j=p; j<r; j++){
			if(nums[j]<=x){
				i++;
				temp=nums[i];
				nums[i]=nums[j];
				nums[j]=temp;
			}
		}
		temp=nums[i+1];
		nums[i+1]=nums[r];
		nums[r]=temp;
		return i+1;
	}
	
	//循环调用
	private static void quik(int[] nums, int p, int r){
		if(p<r){
			int i=partition(nums, p, r);
			quik(nums, 0, i-1);
			quik(nums, i+1, r);
		}
	}
	
	//第一次调用，从0到len-1
	public static void quikSort(int[] nums){
		quik(nums, 0, nums.length-1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={2,8,7,1,3,5,6,4};
		quikSort(nums);
		System.out.println(Arrays.toString(nums));

	}

}