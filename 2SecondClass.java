package leetcode;
/**
 * @author Xiaomin
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class SecondClass {
	
	
	//1.0 First Position Search
	/*O(log n)
	 * 1 3 5 5 5 5 1 target = 5
	 * return index of value, 
	 * if not find
	 * return -1
	 */
	@Test
	public void testfindFirstPos() {
		int[] num = new int[]{1,3,5,5,5,5, 1};
		int pos = findFirstPos(num, 5);
		int lastpos = findLastPos(num, 5);
		assertEquals(pos, 2);
		assertEquals(lastpos, 2 + 4 - 1);
	}
	
	public int findFirstPos(int[] num, int tar) {
		if (num == null || num.length == 0) {
			return -1;
		}
		
		int start = 0;
		int last = num.length - 1;
		
		while (start + 1 < last) {
			int mid = start + (last - start) / 2;
			if (num[mid] == tar) {
				last = mid;
			} else if(tar < num[mid]) {
				last = mid;
			} else {
				start = mid;
			}
		}
		
		if (num[start] == tar) {
			return start;
		} 
		if (num[last] == tar) {
			return last;
		}
		return -1;
	}
	
	
	
	
	//1.1 Last Position Search
	public int findLastPos(int[] num, int tar) {
		if (num == null || num.length == 0) {
			return -1;
		}
		int start = 0; 
		int last = num.length - 1;
		
		while (start + 1 < last) {
			int mid = start + (last - start) / 2;
			if (num[mid] == tar) {
				start = mid;
			} else if (tar < num[mid]) {
				last = mid;
			} else {
				start = mid;
			}
		}
		
		if (num[last] == tar) {
			return last;
		} else if (num[start] == tar){
			return start;
		} else {
			return -1;
		}
	}
	
	
	
	
	//2.0 find first bad version
	/*O(log n)
	 * The code base version is an integer and start from 0 to n.
	 * One day, someone commit a bad version in the code vase, so 
	 * it cause itself and the following versions are all failed in
	 * unittests, You can determine whether a version is bad 
	 * by the following interface:
	 * boolean isBadVersion(int version);
	 * Find the first bad version, return the first bad index
	 * if (not find )
	 * return -1;
	 */
	
	@Test
	public void testFindtheFirstBad() {
		int[] version = new int[] {12342, 14444, 23453, 35664, 36566, 49967, 59604};
		int FirstBad = FindtheFirstBad(version);
		assertEquals(6 - 1, FirstBad);
	}
	
	public int FindtheFirstBad(int[] version){
		if (version == null || version.length == 0) {
			return -1;
		}
		int start = 0;
		int last = version.length;
		
		while (start + 1 < last) {
			int mid = start + (last - start) / 2;
			if (isBadVersion(version[mid])) {
				 last = mid;
			} else {
				start = mid;
			}
		}
		
		if (isBadVersion(version[start])) {
			return start;
		} 
		if (isBadVersion(version[last])) {
			return last;
		}
		return -1;
	}
	
	
	boolean isBadVersion(int version) {
		int badnum = 49967;
		if (version >= badnum) {
			return true;
		}
		return false;
	}
	
	/**
	 * 3.0 Find a peak.
	 * O(log n)
	 * int[] num that satisfy num[0] < num[1] && num[len - 2] > num[len - 1]
	 * return the first index peak, 
	 * if (not find) 
	 * return -1;
	 * 
	 * define peak is strictly greater than its neighbors. 
	 * (This may be in this case, 0, 1, 2, 3, 3, 2, 4, 5,0, does not work in this case, 
	 * cannot use binary search, we cannot say in which case, if you say right, how about 0, 4, 5, 3, 3, 2, 4, 5,0 );
	 * Another version define a peak is greater than or equal to its neighbors. (This is version that I like). 
	 */
	
	
	@Test
	public void testFindFirstIndexPeak() {
		int[] num = new int[]{0, 1, 0};
		int res = FindFirstIndexPeak(num);
		assertEquals(1, res);
		int[] num2 = new int[]{0, 1, 2, 3, 3, 2, 4, 5,0}; // it will return 8 - 1;
		int res2 = FindFirstIndexPeak(num2);
		assertEquals(4 - 1, res2);
	}
	
	
	public int FindFirstIndexPeak(int[] num) {
		if (num == null || num.length <= 2) {
			return -1;
		}
		
		int start = 0;
		int last = num.length - 1;
		while (start + 1 < last) {
			int mid = start + (last - start) / 2;
			
			if (num[mid] >= num[mid - 1] && num[mid] >= num[mid + 1]){
				last = mid;
			} else if (num[mid] >= num[mid - 1]) {
				start = mid;
			} else if (num[mid] >= num[mid + 1]) {
				last = mid;
			} else {
				last = mid;
			}
		}
		
		if (start - 1 >= 0 && num[start] >= num[start - 1]
				&& num[start] >= num[start + 1]) {
			return start;
		}
		if (last + 1 < num.length && num[last] >= num[last + 1]
				&& num[last] >= num[last - 1]) {
			return last;
		}
		
		return -1;
	}
	
	@Test
	public void testFindLastIndexPeak() {
		int[] num = new int[]{0, 1, 0};
		int res = FindLastIndexPeak(num);
		assertEquals(1, res);
		int[] num2 = new int[]{0, 1, 2, 3, 3, 2, 4, 5,0}; // it will return 8 - 1;
		int res2 = FindLastIndexPeak(num2);
		assertEquals(8 - 1, res2);
	}
	
	
	public int FindLastIndexPeak(int[] num) {
		if (num == null || num.length <= 2) {
			return -1;
		}
		
		int start = 0;
		int last = num.length - 1;
		while (start + 1 < last) {
			int mid = start + (last - start) / 2;
			
			if (num[mid] >= num[mid - 1] && num[mid] >= num[mid + 1]) {
				start = mid;
			} else if (num[mid] >= num[mid - 1]) {
				start = mid;
			} else if (num[mid] >= num[mid + 1]) {
				last  = mid;
			} else {
				start = mid;
			}
		}
		
		if (last + 1 < num.length && num[last] >= num[last + 1] 
						&& num[last] >= num[last - 1]){
			return last;
		}
		
		if (start - 1 >= 0 && num[start] >= num[start - 1] 
							&& num[start] >= num[start + 1]) {
			return start;
		}
		
		return -1;
	}
	
	/**
	 * find kth Element in two sorted Array 
	 * O(log k) 
	 * A : 1,2,3, Integer.MAX_VALUE
	 * B : 4,5,6, Integer.MAX_VALUE
	 * Assume k >= 1
	 *  return the value of kth element
	 */
	@Test
	public void testfindKth() {
		int[] A = new int[] {1};
		int[] B = new int[] {4,5,6};
		int res = findKth(A, 0, B, 0, 4);
		assertEquals(6, res);
	}
	
	//Assume k >= 1 && k <= A.length + B.length
	
	public int findKth(int[] A, int Astart, int[] B, int Bstart, int k) {
		if (!isValid(A, Astart)) {
			return B[Bstart + k - 1];
		}
		if (!isValid(B, Bstart)) {
			return A[Astart + k - 1];
		}
		if (k == 1) {
			return A[Astart] < B[Bstart] ? A[Astart] : B[Bstart];
		}
		int A_key = (Astart + k / 2 - 1 >= A.length) ? Integer.MAX_VALUE : A[Astart + k / 2 - 1];
		int B_key = (Bstart + k / 2 - 1 >= B.length) ? Integer.MAX_VALUE : B[Bstart + k / 2 - 1];
		
		if (A_key < B_key) {
			return findKth(A, Astart + k / 2, B , Bstart, k - k / 2);
		} else {
			return findKth(A, Astart, B, Bstart + k / 2, k - k / 2);
		}
	}
	
	
	private boolean isValid(int[] num, int start) {
		if (start >= num.length) {
			return false;
		}
		return true;
	}
	
	
	@Test
	public void testfindMedian(){ 
		int[] A = new int[]{1,2,3};
		int[] B = new int[]{4,5,6};
		Assert.assertEquals(0, Double.compare(3.5, findMedian(A,B)));
	}
	
	public double findMedian(int[] A, int[] B) {
		if (A == null || B == null) {
			return -1;
		}
		
		if ((A.length + B.length) % 2 == 1) {
			return (double)findKth(A, 0, B, 0 , (A.length + B.length) / 2 + 1);
		} else {
			int a = findKth(A, 0, B, 0, (A.length + B.length) / 2);
			int b = findKth(A, 0, B, 0, (A.length + B.length) / 2 + 1);
			return (a + b) / 2.0;
		}
	}
	
	
	/**
	 * Recover Soted Array
	 * 4 5 1 2 3 -> 1 2 3 4 5
	 * O(n + n) = O(n)
	 */
	
	@Test
	public void testrecover() {
		int[] num = new int[]{4,5,1,2,3};
		recover(num);
		assertArrayEquals(new int[]{1,2,3,4,5}, num); 
	}
	
	
	public void recover(int[] num) {
		if (num == null || num.length <= 1) {
			return;
		}
		int tail = -1;
		int head = -1;
		
		for (int i = 1; i < num.length; i++) {
			if (num[i - 1] > num[i]) {
				tail = i - 1;
				head = i;
				break;
			}
		}
		
		if (tail == -1 ){
			return;
		}
		
		reverse(num, 0, tail);
		reverse(num, head, num.length - 1);
		reverse(num, 0, num.length - 1);
	}
	
	public void reverse(int[] num, int start, int last) {
		if (start >= last) {
			return;
		}
		int len = last + 1 - start;
		for (int i = 0; i < len / 2; i++) {//fix a bug len should be len/2
			int tmp = num[start + i];
			num[start + i] = num[last - i];
			num[last - i] = tmp;
		}
	}
	
	
	
	
	
	/**
	 * 6.0 leetcode question search in a 2D matrix
	 * O(log(m * n)) = O(log m + log n)
	 * 
		134 / 134 test cases passed.
		Status: Accepted
		Runtime: 404 ms
		Submitted: 0 minutes ago
	 */
	
	
	 public boolean searchMatrix(int[][] matrix, int target) {
	        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
	            return false;
	        }
	        
	        int m = matrix.length;
	        int n = matrix[0].length;

	        int start = 0;
	        int last = m * n - 1;
	        
	        while (start + 1 < last) {
	            int mid = start + (last - start) / 2;
	            int x = mid / n; 
	            int y = mid % n; 
	            
	            if(matrix[x][y] == target) {
	                return true;
	            } else if(target < matrix[x][y]) {
	                last = mid;
	            } else {
	                start = mid;
	            }
	        }        
	        
	        if (matrix[start / n][start % n] == target) {
	            return true;
	        } else if (matrix[last / n][last % n] == target) {
	            return true;
	        } 
	        return false;
	    }
	
	 
	 
	 
	
	
	 	/**
	 	 * 7.0 search in a 2D strictly sorted matrix 
	 	 * 1 3 5 7
	 	 * 2 4 6 8
	 	 * 3 5 7 9
	 	 * 4 6 8 10
	 	 * 5 7 9 11
	 	 * O(m + n) Time
	 	 */
	 
	 	@Test
	 	public void testsearchInMatrixII(){
	 		int[][] matrix = new int[][]{{1,3,5,7}, {2,4,6,8}, {3,5,7,9}, {4,6,8,10}, {5,7,9,11}};
	 		boolean res = searchInMatrixII(matrix, 0);
	 		assertEquals(false, res);
	 	}
	 
	 	public boolean searchInMatrixII(int[][] matrix, int tar) {
	 		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
	 			return false;
	 		}
	 		int m = matrix.length;
	 		int n = matrix[0].length;
	 		int x = m - 1;
	 		int y = 0;
	 		while (x >= 0 && y < n) {
	 			int val = matrix[x][y];
	 			if (val == tar) {
	 				return true;
	 			} else if (val < tar) {
	 				y++;
	 			} else {
	 				x--;
	 			}
	 		}
	 		return false;
	 	}
	 
	 	
	 	
	 	
	 	
	 	/**
	 	 * 8.0 search in Rotated Array leetcode question 
	 	 * 
	 	 * 4 5 6 7 0 1 2
	 	 *  change the title to search to run in leetcode
			194 / 194 test cases passed.
			Status: Accepted
			Runtime: 408 ms
			Submitted: 3 minutes ago

	 	 * 
	 	 */
	 	
	 	
	 	public int searchInRotateArray(int[] num, int tar) {
	 		if (num == null || num.length == 0) {
	 			return -1;
	 		}
	 		
	 		int start = 0;
	 		int last = num.length - 1;
	 		while (start + 1 < last) {
	 			int mid = start + (last - start) / 2;
	 			
	 			if (num[mid] == tar) {
	 				return mid;
	 			}
	 			
	 			if (num[mid] >= num[start]) {
	 				if (num[mid] > tar && tar >= num[start]) {
	 					last = mid;
	 				} else {
	 					start = mid;
	 				}
	 			} else {
	 				if (num[mid] < tar && tar <= num[last]) {
	 					start = mid;
	 				} else {
	 					last = mid;
	 				}
	 			}
	 		}
	 		
	 		if (num[start] == tar) {
	 			return start;
	 		} else if (num[last] == tar) {
	 			return last;
	 		} else {
	 			return -1;
	 		}
	 	}
	 
	 	
	 	
	 	
	 	//8.1 search in rotated II
	 	/**
	 	 * 271 / 271 test cases passed.
			Status: Accepted
			Runtime: 444 ms
			Submitted: 0 minutes ago
	 	 * @param num
	 	 * @param tar
	 	 * @return
	 	 */
	 	
	 	
	 	public boolean searchInRotateArrayII(int[] num, int tar) {
	 		if (num == null || num.length == 0) {
	 			return false;
	 		}
	 		
	 		int start = 0;
	 		int last = num.length - 1;
	 		while (start + 1 < last) {
	 			int mid = start + (last - start) / 2;
	 			
	 			if (num[mid] == tar) {
	 				return true;
	 			}
	 			
	 			if (num[mid] > num[start]) {
	 				if (num[mid] > tar && tar >= num[start]) {
	 					last = mid;
	 				} else {
	 					start = mid;
	 				}
	 			} else if (num[mid] < num[start]) {
	 				if (num[mid] < tar && tar <= num[last]) {
	 					start = mid;
	 				} else {
	 					last = mid;
	 				}
	 			} else {
	 				start ++;
	 			}
	 		}
	 		
	 		if (num[start] == tar) {
	 			return true;
	 		} else if (num[last] == tar) {
	 			return true;
	 		} else {
	 			return false;
	 		}
	 	}
	 	
	 	
	 	/**
	 	 * 9. search for a range leetcode question
	 	 * 
	 	 *  81 / 81 test cases passed.
			Status: Accepted
			Runtime: 384 ms
			Submitted: 0 minutes ago

	 	 */
	 	
	 	public int[] searchRange(int[] num, int tar) {
	 		if (num == null || num.length == 0) {
	 			return new int[] {-1, -1};
	 		}
	 		int firstPos = findFirst(num, tar);
	 		if (firstPos == -1) {
	 			return new int[] {-1,-1};
	 		}
	 		int lastPos = findLast(num, tar);
	 		return new int[]{firstPos, lastPos};
	 		
	 	}
	 	
	 	
	 	private int findFirst(int[] num, int tar) {
	 		if (num == null || num.length == 0) {
	 			return -1;
	 		}
	 		
	 		int start = 0;
	 		int last = num.length - 1;
	 		
	 		while (start + 1 < last) {
	 			int mid = start + (last - start) / 2;
	 			
	 			if (num[mid] == tar) {
	 				last = mid;
	 			} else if (num[mid] < tar) {
	 				start = mid;
	 			} else {
	 				last = mid;
	 			}
	 		}
	 		
	 		if (num[start] == tar) {
	 			return start;
	 		} 
	 		if (num[last] == tar) {
	 			return last;
	 		}
	 		return -1;
	 	}
	 	
	 	private int findLast(int[] num, int tar) {
	 		if (num == null || num.length == 0) {
	 			return -1;
	 		}
	 		int start = 0;
	 		int last = num.length - 1;
	 		
	 		while (start + 1 < last) {
	 			int mid = start + (last - start) / 2;
	 			
	 			if (num[mid] == tar) {
	 				start = mid;
	 			} else if (num[mid] > tar) {
	 				last = mid;
	 			} else{
	 				start = mid;
	 			}
	 		} 
	 		
	 		if (num[last] == tar) {
	 			return last;
	 		} 
	 		if (num[start] == tar) {
	 			return start;
	 		}
	 		return -1;
	 	}
	 
	 	
	 	
	 	
	 	
	 	
	 	/**
	 	 * 10. I love you -> uoy evol I -> you love I
	 	 * three time reverse
	 	 * 
	 	 */
	 	@Test
	 	public void testreverseWords(){
	 		String res = reverseWords(new String("I Love You"));
	 		assertEquals(res, "You Love I");
	 	}
	 	
	 	private void reverse(char[] chars, int start, int last) {
	 		int len = last + 1 - start;
	 		for (int i = 0; i < len / 2; i++) {
	 			char tmp = chars[start + i];
	 			chars[start + i] = chars[last - i];
	 			chars[last - i] = tmp;
	 		}
	 	}
	
	 	public String reverseWords(String s) {
	 		if ( s == null || s.length() == 0) {
	 			return s;
	 		}
	 		s = s.trim();
	 		char[] chars = s.toCharArray();
	 		
	 		//1. reverse all
	 		reverse(chars, 0, chars.length - 1);
	 		
	 		int start = 0;
	 		
	 		for(int i = 0; i < chars.length; i++) {
	 			if (chars[i] == ' ') {
	 				while (i < chars.length && chars[i] == ' ') {
	 					i++;
	 				}
	 				i--;
	 				reverse(chars, start, i - 1);
	 				start = i + 1;
	 			}
	 		}
	 		return String.copyValueOf(chars);
	 	}
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
//----------------------------------------------------------------------------------------------------------------------------------------
//K Sum type Questions
	 	
	 	
	 	
	 	
	 	
	 	/**
	 	 * 11.0 two Sum -- leetcode
	 	 * Assume have duplicate numbers. 
	 	 * Input: numbers={2, 7, 11, 15}, target=9
						Output: index1=1, index2=2
	 	 * give an array, [-1, -1, 0, 0, 3, 4, 5] target == 4,
	 	 * 
	 	 */
	 	@Test
	 	public void testtwoSum(){
	 		int[] num = new int[]{2,7,11,15};
	 		twoSum(num, 9);
	 	}
	 	
	 	public int[] twoSum(int[] num, int tar) {
	 		int index1 = -1, index2 = -1;
	 		//System.out.println(index1);
	 		if (num == null || num.length == 0) {
	 			return new int[] {-1, -1};
	 		}
	 		List<Integer> list = new ArrayList();
	 		for (int i : num) {
	 			list.add(i);
	 		}
	 		Arrays.sort(num);
	 		int start = 0;
	 		int last = num.length - 1;
	 		while (start < last) {
	 			int sum = num[start] + num[last];
	 			if (sum == tar) {
	 				break;
	 			} else if (sum > tar) {
	 				last--;
	 			} else {
	 				start ++;
	 			}
	 		}
	 		if (start < last && num[start] + num[last] == tar) {
	 			index1 = list.indexOf(num[start]);
	 			index2 = list.lastIndexOf(num[last]);
	 		}
	 		if (index1 < index2) {
	 			return new int[] {index1, index2};
	 		} else {
	 			return new int[] {index2, index1};
	 		}
	 	}
	 	
	 	
	 	/**
	 	 * 11.1   3 Sum
	 	 * sort array,
	 	 * binary search and skip duplicate
	 	 * 3 Sum closest is similar to this question.
	 	 */
	 	/*
	 	    Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

			Note:
			Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
			The solution set must not contain duplicate triplets.
			    For example, given array S = {-1 0 1 2 -1 -4},
			
			    A solution set is:
			    (-1, 0, 1)
			    (-1, -1, 2)
		*/
	 	
	 	
	 	public List<List<Integer>> ThreeSum(int[] data, int tar) {
	 		List res = new ArrayList();
	 		if (data == null || data.length <= 2) {
	 			return res;
	 		}
	 		Arrays.sort(data);
	 		
	 		for (int i = 0; i < data.length; i++) {
	 			if (i > 0 && data[i] == data[i - 1] ) {
	 				continue;
	 			}
	 			int start = i + 1;
	 			int last = data.length - 1;
	 			
	 			while (start < last) {
	 				int sum = data[i]  + data[start] + data[last];
	 				if (sum == tar) {
	 					List<Integer> tmp = new ArrayList();
	 					tmp.add(data[i]);
	 					tmp.add(data[start]);
	 					tmp.add(data[last]);
	 					res.add(tmp);
	 					
	 					start++;
	 					last--;
	 					while (start < last && data[start] == data[start - 1]) {
	 						start ++;
	 					}
	 					while (start < last && data[last] == data[last + 1] ) {
	 						last--;
	 					}
	 				} else if (sum < tar) {
	 					start++;
	 				} else {
	 					last--;
	 				}
	 			}
	 		}
	 		return res;
	 	}
	 	
	 	
	 	
	 	
	 	/**
	 	 * 11.2   4 Sum
	 	 * sort array
	 	 * binary search and skip duplicate
	 	 */
	 	/*
	 	    Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
			Note:
			Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
			The solution set must not contain duplicate quadruplets.
			    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
			
			    A solution set is:
			    (-1,  0, 0, 1)
			    (-2, -1, 1, 2)
			    (-2,  0, 0, 2)
	 	 */
	 	
	 	
	 	
	 	public List<List<Integer>> FourSum(int[] data, int tar) {
	 		List res = new ArrayList();
	 		if (data == null || data.length <= 3) {
	 			return res;
	 		}
	 		Arrays.sort(data);
	 		
	 		for (int j = 0; j < data.length; j++) {
	 			if (j > 0 && data[j] == data[j - 1]) {
	 				continue;
	 			}
		 		for (int i = 0; i < data.length; i++) {
		 			if (i > 0 && data[i] == data[i - 1] ) {
		 				continue;
		 			}
		 			int start = i + 1;
		 			int last = data.length - 1;
		 			
		 			while (start < last) {
		 				int sum = data[j] + data[i]  + data[start] + data[last];
		 				if (sum == tar) {
		 					List<Integer> tmp = new ArrayList();
		 					
		 					tmp.add(data[j]);
		 					tmp.add(data[i]);
		 					tmp.add(data[start]);
		 					tmp.add(data[last]);
		 					res.add(tmp);
		 					
		 					start++;
		 					last--;
		 					while (start < last && data[start] == data[start - 1]) {
		 						start ++;
		 					}
		 					while (start < last && data[last] == data[last + 1] ) {
		 						last--;
		 					}
		 				} else if (sum < tar) {
		 					start++;
		 				} else {
		 					last--;
		 				}
		 			}
		 		}
		 		
		 	}
	 		return res;
	 	}
	 	
	 	
	 	
	 	
	 	
}

