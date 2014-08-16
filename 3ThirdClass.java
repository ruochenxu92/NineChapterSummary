package leetcode;

import static org.junit.Assert.*;

/**
 *  @author Xiaomin
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class ThirdClass {
	
	/**
	 * 大部分保留leetcode原名， 少部分太长或冲突，略加修改。 All finished now! Aug 9:02pm CST
	 */
//	DFS Template
//	Binary Tree DFS template
	/**
	 *
	 * 0.0 template
	 * Copyright: NineChapter
	 * - Algorithm Course, Mock Interview, Interview Questions
	 * - More details on: http://www.ninechapter.com/
	 */

	//Template 1: Traverse

	public class Solution {
	    public void traverse(TreeNode root) {
	        if (root == null) {
	            return;
	        }
	        // do something with root
	        traverse(root.left);
	        // do something with root
	        traverse(root.right);
	        // do something with root
	    }
	}


	//Tempate 2: Divide & Conquer

	
   class Solution1 {
	   
	    public ResultType traversal(TreeNode root) {
	        // null or leaf
	        if (root == null) {
	            // do something and return;
	        }
	        
	        // Divide
	        ResultType left = traversal(root.left);
	        ResultType right = traversal(root.right);
	        
	        // Conquer
	        ResultType result = Merge from left and right.
	        return result;
	    
	    }
	}
	
	
	
	
	@Test
	public void testpreorder(){
		TreeNode root = new TreeNode(5);
		root.left  = new TreeNode(3);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		List<Integer> res = preorder1(root);
		System.out.println(res);
		
		List<Integer> res1 = dacpreorder(root);
		System.out.println(res);
		/*
		 * [5, 3, 1, 4, 7, 6, 9]
		   [5, 3, 1, 4, 7, 6, 9]
		 */
	}
	
	
	
	/**
	 * 1.0 preorder recursion
	 * preorder Traversal(DFS)
	 * 
	 */
	
	public void preorder(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.println(root.val);
		preorder(root.left);
		preorder(root.right);
	}
	
	/**
	 * 1.1 stack preorder
	 * use stack to memorize
	 */
	
	public List<Integer> preorder1(TreeNode root) {
		List res = new ArrayList();
		if (root == null) {
			return res;
		}
		Stack<TreeNode> stack = new Stack();
		stack.push(root);
		while(!stack.empty()){
			TreeNode cur = stack.pop();
			res.add(cur.val);
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
		return res;
	}
	
	
	/**
	 * 1.2 divide and conquer(dac)
	 *      5
	 *   3    7
	 * 1  4  6  9
	 * 
	 * 1 3 4  left
	 * 5      root
	 * 6 7 9  right
	 */
	
	public List<Integer> dacpreorder(TreeNode root) {
		List res = new ArrayList();
		if (root == null) {
			return res;
		}
		
		//1.divide
		List<Integer> left = dacpreorder(root.left);
		List<Integer> right = dacpreorder(root.right);
		
		//2.conquer
		res.addAll(left);
		res.add(root.val);
		res.addAll(right);
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//-------------------------------------------------------------------------------------------------------------------
//merge sort and quick sort
	
	/**
	 * 2.0
	 * merge sort an array, this is not easy, using iterative method is painful.
	 * merge sort has two steps, partition and merge, partition is automatically
	 * finish, but how to merge
	 * I start from len = 1, 
	 * so many details, I need to find another good implementations, anyway, understand the thoughts of merge sort 
	 * is important.
	 * I think merge sort > quick sort since the performance can be improved by using multi-thread
	 */
	
	
	@Test
	public void testmerge(){
		int[] num = new int[] {5, 1, 2};
		mergesort(num);
		assertArrayEquals(new int[]{1,2,5}, num); 

	}
	
	public void mergesort(int[] num) {
		if (num == null) {
			return;
		}
		
		for(int len = 1; len <= num.length; len *= 2) {
			for (int i = 0; i + len <= num.length; i += len * 2) {
			     merge(num, i, len, i + len, len);
			}
		}
	}
	
	private void merge(int[] num, int Astart, int ALen, int Bstart, int BLen) {
		int[] res = new int[num.length];
		
		int start = Astart;
		
		if (Bstart + BLen > num.length) {
			BLen = num.length - Bstart;
		}
		int len = ALen + BLen;
		int index  = Astart;
		while(ALen > 0 || BLen > 0) {
			if (ALen > 0 && BLen > 0) {
				if (num[Astart]  < num[Bstart]) {
					res[index++] = num[Astart++];
					ALen--;
				} else{
					res[index++] = num[Bstart++];
					BLen--;
				}
			} else if(ALen > 0) {
				res[index++] = num[Astart++];
				ALen--;
			} else {
				res[index++] = num[Bstart++];
				BLen--;
			}
		}
		
		for(int i = 0; i < len; i++) {
			num[start + i] = res[start + i];
		}
	}
	
	
	
	
	/**
	 * 2.1 merge sort easy version by using recursive call
	 * refer information google merge sort algorithm
	 * 
	 */
	
	@Test
	public void testmergeSort() {
		int[] num = new int[]{3, 4, 1, 2, 5, 6, 7};
		int[] res = merge_sort(num);
		assertArrayEquals(res, new int[]{1,2,3,4,5,6, 7});
	}
	
	

	public int[] merge_sort(int[] num) {
		if (num.length <= 1) {
			return num;
		}
		int middle = (num.length - 1) / 2;
		int[] left = new int[middle + 1];
		int[] right = new int[num.length - left.length];
		
		for(int i = 0; i < middle + 1; i++) {
			left[i] = num[i];
		}
		
		int len = left.length;
		for(int i = 0; i + len< num.length; i++) {
			right[i] = num[i + len];
		}
		
		left = merge_sort(left);
		right = merge_sort(right);
		
		return merge(left, right);
	}
	
	@Test
	public void testmerge_(){
		int[] left = new int[] {3};
		int[] right = new int[] {};
		int[] res = merge(left,right);
		assertArrayEquals(res, new int[]{3});
	}
	
	
	
	public int[] merge(int[] left, int[] right) {
		int[] res = new int[left.length + right.length];
		int m = left.length;
		int n = right.length;
		int index = m + n;
		
		//给面试官展示从后往前的玩法
		while( m > 0 || n > 0) {
			if (m > 0 && n > 0) {
				if (left[m - 1] > right[n - 1]) {
					res[--index] = left[--m];
				} else {
					res[--index] = right[--n];
				}
			} else if (m > 0) {
				res[--index] = left[--m];
			} else {
				res[--index] = right[--n];
			}
		}
		return res;
	}
	
	
	/**
	 * 2.2 merge Sort easiest merge sort
	 * from professor top-secret notes. this is version that I like most. Easy.
	 */
	
	
	@Test
	public void testmergeSort__() {
		int[] data = new int[] {3, 4, 5, 7, 8, 1, 2, 0};
		mergeSort(data, 0, data.length - 1);
		assertArrayEquals(data, new int[]{0,1,2,3,4,5,7,8});
	}
	
	public void mergeSort(int[] data, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		
		int mid = ( lo + hi ) / 2;   //int mid = lo + (hi - lo) / 2;
		mergeSort(data, lo, mid);
		mergeSort(data, mid + 1, hi);
		
		int size = hi + 1 - lo; // whole size from lo - mid - hi
		int[] tmp = new int[size];
		merge_(data, lo, mid, mid + 1, hi, tmp);
		
		for(int i = 0; i < tmp.length; i++) {
			data[i + lo] = tmp[i];
		}
	}
	
	private void merge_(int[] data, int Astart, int Alast, int Bstart, int Blast, int[] tmp){
		int index = 0;
		while(Astart <= Alast || Bstart <= Blast) {
			if (Astart <= Alast  && Bstart <= Blast) {
				if (data[Astart] < data[Bstart]) {
					tmp[index++] = data[Astart++];
				} else {
					tmp[index++] = data[Bstart++];
				}
			} else if (Astart <= Alast) {
				tmp[index++] = data[Astart++];
			} else {
				tmp[index++] = data[Bstart++];
			}
		}
	}
	
	
	
	/**
	 * 3.0 quick sort
	 * O(nlgn) O(1)
	 * in-place partitioning algorithm
	 * from professor top-secret notes, please see my notes in github for details.
	 * It takes a while to understand quicksort.
	 */
	@Test
	public void testquickSort() {
		int[] data = new int[] {3, 4, 5, 7, 8, 1, 2, 0};
		quickSort(data, 0, data.length - 1);
		assertArrayEquals(data, new int[]{0,1,2,3,4,5,7,8});
	}
	
	public void quickSort(int[] data, int lo, int hi) {
		if (hi > lo) {
			int pivot = (lo + hi) / 2;
			int newPivotIndex = partition(data, lo, hi, pivot);
			
			quickSort(data, lo, newPivotIndex - 1);
			quickSort(data, newPivotIndex + 1, hi);
		}
	}
	
	
	//this is most important part of quick sort
	public int partition(int[] data, int lo, int hi, int pivotIndex) {
		int pivotValue = data[pivotIndex];
		swap(data, pivotIndex, lo);
		//start working in, from both ends of the list
		int left = lo + 1, right = hi;
		while (left < right) {
			if (data[left] >= pivotValue) {
				swap(data, left, right);
				right--;
			} else {
				left++;
			}
		}
		
		//the pivot will need to go to the left of the final boundary if the last
		//value is larger than the pivot value
		if (data[left] > pivotValue) {
			left--;
		}
		swap(data, lo, left);
		return left;
	}
	
	void swap(int[] data, int posA, int posB) {
		int tmp = data[posA];
		data[posA] = data[posB];
		data[posB] = tmp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 4. binary Tree Maximum path sum
	 * 记住， 一个 Node can be 整条通路
	 * 
			92 / 92 test cases passed.
			Status: Accepted
			Runtime: 512 ms
			Submitted: 0 minutes ago

	 */
	
	class ResultType{
		int singlePath,maxPath;
		ResultType(int singlePath, int maxPath){
			this.singlePath = singlePath;
			this.maxPath = maxPath;
		}
	}
	
	public ResultType maxpath(TreeNode root) {
		if (root == null) {
			return new ResultType(0, Integer.MIN_VALUE);//at least one node, if only have one node like -3, we also need to pick it
		}
		
		//divide
		ResultType left = maxpath(root.left);
		ResultType right = maxpath(root.right);
		
		int singlepath = Math.max(left.singlePath, right.singlePath) + root.val; //if not add root, we cannot continue to climb up
		singlepath = Math.max(singlepath, 0);
		
		int maxpath = Math.max(left.maxPath, right.maxPath);
		maxpath = Math.max(maxpath, left.singlePath + right.singlePath + root.val); //whole path >= singlepath for root
		
		
		return new ResultType(singlepath, maxpath);
	}
	
	
	public int maxPathSum(TreeNode root) {
        ResultType result = maxpath(root);
        return result.maxPath;
    }
	
	/**
	 * 5.0   return all the keys in the giveRange in BST    Divide and Conquer
	 *      5
	 *   3    7
	 * 1  4  6  9
	 */
	
	@Test
	public void testRange(){
		TreeNode root = new TreeNode(5);
		root.left  = new TreeNode(3);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		Interval interval = new Interval(4,7);
		List<Integer> res = Range(interval, root);
		assertEquals(res, Arrays.asList(4,5,6,7));
	}
	
	class Interval {
		int start;
		int end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	
	public List<Integer> Range(Interval interval, TreeNode root) {
		if (interval.start > interval.end || root == null) {
			return new ArrayList();
		}
		
		if (root.val > interval.end) {
			return Range(interval, root.left);
		}
		
		if (root.val < interval.start) {
			return Range(interval, root.right);
		}
		
		//divide
		List<Integer> left = Range(interval, root.left);
		List<Integer> right = Range(interval, root.right);
		
		//conquer
		List<Integer> res = new ArrayList();
		res.addAll(left);
		res.add(root.val);
		res.addAll(right);
		return res;
	}
	
	
	/**
	 * 6.0 implement an iterator in BST
	 * http://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html
	 * only need to implement three methods for iterator interface
	 * boolean has Next()
	 * Object next();     
	 * void remove(); //optional operation
	 * 
	 *     1. ascending order
	 *     2. next, hasNext, O(1).
	 *     use queue
	 *     3. O(1) space, waiting for St.Huang to solve it.
	 */
	
	class treeIterator {
		Deque<TreeNode> queue;
		
		//init
		treeIterator(TreeNode root) {
			queue = new ArrayDeque();
			
			Stack<TreeNode> stack = new Stack();
			if (root == null) {
				return;
			}
			stack.push(root);
			while( !stack.isEmpty() ) {
				TreeNode cur = stack.pop();
				queue.offer(cur);
				if (cur.right != null) {
					stack.push(cur.right);
				}
				
				if (cur.left != null) {
					stack.push(cur.left);
				}
			}
		}
		
		
		public boolean hasNext(){
			return queue.peek() != null;
		}
		
		public Object next() {
			TreeNode cur = queue.poll();
			return (Object) cur;
		}
		
	}
	
	/**
	 * 6.1  from stackOverFlow 
	 * do not need to traverse which spend O(h) time at the beginning
	 */
	public class Iterator {
	    private Stack<TNode> stack = new Stack<>();
	    private TNode cur;

	    public Iterator(TNode root) {  
	        cur = root;
	    }

	    public TNode next() {
	        while (cur != null) {
	            stack.push(cur);
	            cur = cur.left;
	        }

	        cur = stack.pop();
	        TNode node = cur;
	        cur = cur.right;

	        return node;
	    }

	    public boolean hasNext() {
	        return (!stack.isEmpty() || cur != null);
	    }

	}
	
	
	/**
	 * 6.2 LRU Cache
	 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

		get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
		set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
	 * 
	 * 
	 * O(1) get and set operation using hashMap
	 */
	
	class LRUCache {
		
        class DoubleListNode {
    		int key, val;
    		DoubleListNode prev, next;
    		DoubleListNode(int key, int val) {
    		    this.prev = null;
    		    this.next = null;
    			this.key = key;
    			this.val = val;
    		}
    	}
	
		DoubleListNode head = new DoubleListNode(-1, -1);
		DoubleListNode tail = new DoubleListNode(-1, -1);
		HashMap<Integer, DoubleListNode> dict;
		private int capacity; 
				
		LRUCache(int capacity){
			this.capacity= capacity;
			dict = new HashMap();
			head.next = tail;  //1.fixed a bug here, do not init head and tail pointers.
			tail.prev = head;
		}
		
		public int get(int key) {
			if (!dict.containsKey(key)) {
				return -1;
			}
			
			DoubleListNode query = dict.get(key);
			query.prev.next = query.next;
			query.next.prev = query.prev;
			
			move_to_tail(query);
			return query.val;
		}
		
		public void set(int key, int value) {
			if( get(key) != -1 ) {
				dict.get(key).val = value;
				return;   //2.fixed a bug here, do not return
			}
			
			DoubleListNode newnode = new DoubleListNode(key, value);
			if (capacity == dict.size()) {
				DoubleListNode del = head.next;
				head.next = del.next;
				del.next.prev = head;
				
				dict.remove(del.key); //DoubleListNode must store both key and value, for delete operations
			}
			dict.put(key, newnode);
			move_to_tail(newnode);
		}
		
		private void move_to_tail(DoubleListNode node) {
			tail.prev.next = node;
			node.next = tail;
			
			node.prev = tail.prev;
			tail.prev = node;
		}
			
	}	  
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//-------------------------------------------------------------------------------------------------------------------
//BFS
	
	
	
	
	
	
	/**
	 * 
	 * 7.0 level order traversal
	 * BST template (level Order traversal)
	 * BFS questions in leetcode
	 * Zagzig traversal 
	 * Clone Graph
	   Word Ladder II
	   Word Ladder
	   Surrounded Regions
	 */
	
	public List<List<Integer>> levelOrder(TreeNode root) {
		List res = new ArrayList();
		if (root == null) {
			return res;
		}
		Deque<TreeNode> queue = new ArrayDeque();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			List<Integer> level = new ArrayList();
			
			int size = queue.size();//St.Huang remind, do not directly put queue.size() inside loop
			
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				level.add(cur.val);
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
			
		}
		return res;
	}
	
	
	/**
	 * 7.1 Zagzig traversal
	 */
	
	public List<List<Integer>> ZagzigTraversal(TreeNode root) {
		List res = new ArrayList();
		if (root == null) {
			return res;
		}
		Deque<TreeNode> queue = new ArrayDeque();
		queue.offer(root);
		boolean normalOrder = true;
		
		while(!queue.isEmpty()) {
			List level = new ArrayList();
			int size = queue.size();
			
			for(int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				level.add(cur);
				if( cur.left !=  null) {
					queue.offer(cur.left);
				}
				if( cur.right != null) {
					queue.offer(cur.right);
				}
			}
			
			if(!normalOrder) {
				Collections.reverse(level);
			}
			res.add(level);
			normalOrder = !normalOrder;
		}
		return res;
	}
	
	/**
	 * 7.2 
	 * Clone Graph
	 * 
	 */
	/*
	Clone Graph Total Accepted: 14170 Total Submissions: 62868 My Submissions
	Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


	OJ's undirected graph serialization:
	Nodes are labeled uniquely.

	We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
	As an example, consider the serialized graph {0,1,2#1,2#2,2}.

	The graph has a total of three nodes, and therefore contains three parts as separated by #.

	First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
	Second node is labeled as 1. Connect node 1 to node 2.
	Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
	Visually, the graph looks like the following:

	       1
	      / \
	     /   \
	    0 --- 2
	         / \
	         \_/
	         
		12 / 12 test cases passed.
		Status: Accepted
		Runtime: 624 ms
		Submitted: 2 minutes ago
		O(Edges * 2) Time

	*/
	class GraphNode{
		int label;
		List<GraphNode> neighbors = new ArrayList();
		GraphNode(int label) {
			this.label = label;
			neighbors = new ArrayList();
		}
	}
	public GraphNode cloneGraph(GraphNode node) {
		if (node == null) {
			return node;
		}
		GraphNode clonehead = new GraphNode(node.label);
		HashMap<GraphNode, GraphNode> dict = new HashMap();
		
		dict.put(node, clonehead);
		Deque<GraphNode> queue = new ArrayDeque();
		
		queue.offer(node);
		HashSet<GraphNode> visited = new HashSet();  //as global variable
		visited.add(node);

		while( !queue.isEmpty() ) {
			GraphNode cur = queue.poll();
			GraphNode clonecur = dict.get(cur);
			
			for (GraphNode neighbor : cur.neighbors){
				if ( !visited.contains(neighbor) ) {
					visited.add(neighbor);   
					GraphNode newnode = new GraphNode(neighbor.label);
					queue.offer(neighbor);
					clonecur.neighbors.add(newnode);
					dict.put(neighbor, newnode);
				} else {
					clonecur.neighbors.add(dict.get(neighbor));
				}
			}
		}
		return clonehead;
	}
	
	/**
	 * 7.3.0
	 * Word Ladder I
	 * 
	 * 
	 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

		Only one letter can be changed at a time
		Each intermediate word must exist in the dictionary
		For example,
		
		Given:
		start = "hit"
		end = "cog"
		dict = ["hot","dot","dog","lot","log"]
		As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
		return its length 5.
		
		Note:
		Return 0 if there is no such transformation sequence.
		All words have the same length.
		All words contain only lowercase alphabetic characters.
		37 / 37 test cases passed.
		Status: Accepted
		Runtime: 828 ms
		Submitted: 1 minute ago
	 */
	
	public int ladderLength(String start, String last, Set<String> dict) {
		 if(start == null || last == null || start.length() != last.length() || start.length() ==0)
			 return 0;
		 Deque<String> queue = new ArrayDeque();
		 queue.offer(start);
		 HashSet<String> visited = new HashSet();
		 int depth = 1;
		 while(!queue.isEmpty()) {
		     
		     int size = queue.size();
		     for (int i = 0; i < size; i++) {
		         String tmp = queue.poll();
		         // 以下two loops进行brute force猜， 其实是看两个Nodes 之间是否connected 
		         for (int k = 0; k < tmp.length(); k++) {
		             char[] cur = tmp.toCharArray();
		             for (char c = 'a'; c <= 'z'; c++) {
		                 cur[k] = c;
		                 String now = String.copyValueOf(cur);
		                 if (now.equals(last)) {
		                     return depth + 1;
		                 }
		                 if (dict.contains(now) && !visited.contains(now)) {
		                     visited.add(now);
		                     queue.offer(now);
		                 }
		             }
		                 
		         }
		         
		         
		     }
		     depth++;
		 }
		 return 0;
	 }

	
	
	/**
	 * 7.3.1
	 * Word Ladder II  BFS + DFS
	 */
	/*
		Word Ladder II Total Accepted: 8944 Total Submissions: 80092 My Submissions
		Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
	
		Only one letter can be changed at a time
		Each intermediate word must exist in the dictionary
		For example,
	
		Given:
		start = "hit"
		end = "cog"
		dict = ["hot","dot","dog","lot","log"]
		Return
		  [
		    ["hit","hot","dot","dog","cog"],
		    ["hit","hot","lot","log","cog"]
		  ]
		  
		  
		  
		
		37 / 37 test cases passed.
		Status: Accepted
		Runtime: 1020 ms
		Submitted: 0 minutes ago

		
		bug summary: Null Pointer Exception ->forget map.put(last, lastone) 

	 */
	@Test
	public void testfindLadders() {
		String start = "hot";
		String last = "dog";
		HashSet dict = new HashSet();
		String[] strs = new String[]{"hot","cog","dog","tot","hog","hop","pot","dot"};
		for (int i = 0; i < strs.length; i++) {
			dict.add(strs[i]);
		}
		findLadders(start, last, dict);
	}
	
	
	
	class Node{
        String label;
        List<Node> neighbors;
        Node (String label) {
            this.label = label;
            this.neighbors = new ArrayList();
        }
    }
    public List<List<String>> findLadders(String start, String last, Set<String> dict) {
        List res = new ArrayList();
        if (start == null || last == null || start.length() == 0 
                || last.length() == 0 || dict.size() == 0) {
            return res;
        }
        int depth = 1;
        Deque<String> queue = new ArrayDeque();
        queue.offer(start);
        HashSet<String> visited = new HashSet();
        boolean lastLevel = false;
        HashMap<String, Node> map = new HashMap();
        Node head = new Node(start);
        map.put(start, head);
        visited.add(start);//fixed a bug, start should not be visited again
        
        while (!queue.isEmpty()) {
            HashSet<String> passport = new HashSet();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String tmp = queue.poll();
                Node node = map.get(tmp);
                
                for (int k = 0; k < tmp.length(); k++) {
                    char[] cur = tmp.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        cur[k] = c;
                        String now = String.copyValueOf(cur);
                        if (now.equals(last)){
                        	if (!lastLevel) {
                                Node lastone = new Node(last);
                                depth++;
                                lastLevel = true;
                                node.neighbors.add(lastone);
                                visited.add(now);
                                map.put(last, lastone);//fixed a bug
                            } else{
                            	Node lastone = map.get(last);
                                node.neighbors.add(lastone);
                            }
                        } 
                        //!now.equals(tmp) is not necessary
                        if (dict.contains(now) && !now.equals(tmp) && (!visited.contains(now) || passport.contains(now))) {
                            if (!visited.contains(now)) {
                                Node newnode = new Node(now);
                                queue.offer(now);
                                map.put(now, newnode);
                                visited.add(now);
                                passport.add(now);
                                node.neighbors.add(newnode);
                            } else {
                                Node oldnode = map.get(now);
                                node.neighbors.add(oldnode);
                            }
                        }
                    }
                }
            }
            
            //after one level is finish
            if (lastLevel == true) {
                break;
            }
            depth++;
        }      
        List<String> sol = new ArrayList();
        sol.add(start);
        dfs(head, depth, last, sol, res);
        return res;
    }
    
    public void dfs(Node head, int depth, String last, 
                                    List<String> sol, List<List<String>> res) {
        if (depth == 1) {
            if (head.label.equals(last)) {
                List<String> tmp = new ArrayList(sol);
                res.add(tmp);
            } 
            return;
        }
        
        for (Node next : head.neighbors) {
            sol.add(next.label);
            dfs(next, depth - 1, last, sol, res);
            sol.remove(sol.size() - 1);
        }
    }


	
	
	
	/**
	 * 7.4 
	 * Surrounded Regions
	 */
	/*	Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
	
		A region is captured by flipping all 'O's into 'X's in that surrounded region.
	
		For example,
		X X X X
		X O O X
		X X O X
		X O X X
		After running your function, the board should be:
	
		X X X X
		X X X X
		X X X X
		X O X X
		58 / 58 test cases passed.
		Status: Accepted
		Runtime: 536 ms
		Submitted: 0 minutes ago

	*/
    
    public void solve(char[][] board) {
    	if (board == null || board.length == 0 || board[0].length == 0) {
    		return;
    	}
    	Deque<Integer> idx = new ArrayDeque();
    	Deque<Integer> idy = new ArrayDeque();
    	int m = board.length;
    	int n = board[0].length;
    	
    	for (int i = 0; i < m; i++) {
    		if (board[i][0] == 'O') {
    			idx.offer(i);
    			idy.offer(0);
    		}
    		if (board[i][n - 1] == 'O') {
    			idx.offer(i);
    			idy.offer(n - 1);
    		}
    	}
    	
    	
    	for (int i = 0; i < n; i++) {
    		if (board[0][i] == 'O') {
    			idx.offer(0);
    			idy.offer(i);
    		} 
    		if (board[m- 1][i] == 'O') {
    			idx.offer(m - 1);
    			idy.offer(i);
    		}
    	}
    	
    	while(!idx.isEmpty()) {
    		int x = idx.poll();
    		int y = idy.poll();
    		
    		board[x][y] = 'I';
    		if (x - 1 >= 0 && board[x - 1][y] == 'O') {  //'I' has already explore and no need to add again
    			idx.offer(x - 1);
    			idy.offer(y);
    		}
    		if (x + 1 < m && board[x + 1][y] == 'O') {
    			idx.offer(x + 1);
    			idy.offer(y);
    		}
    		if (y - 1 >= 0 && board[x][y - 1] == 'O') {
    			idx.offer(x);
    			idy.offer(y - 1);
    		}
    		if (y + 1 < n && board[x][y + 1] == 'O') {
    			idx.offer(x);
    			idy.offer(y + 1);
    		}
    	}
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (board[i][j] == 'I') {
    				board[i][j] = 'O';
    			} else {
    				board[i][j] = 'X';
    			}
    		}
    	}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//-------------------------------------------------------------------------------------------------------------------
//BST 的基本操作
	
	
	/**
	 * 8. validate BST
	 * thinking from the definition of BST
	 * left < root < right
	 */
	
	public boolean validate(TreeNode root) {
		if (root == null) {
			return true;
		}
		return dac(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean dac(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}
		if (root.val < min || root.val > max) {
			return false;
		}
		return dac(root.left, min, root.val) && dac(root.right, root.val , max);
	}
	
	/**
	 * 9. insert a node in BST, only insert at bottom
	 * if (root != null) {
	 * 1. value > root.val -> root.right
	 * 2. value < root.val -> root.left
	 * 3. value == root.val -> return false; // insert failed
	 * } 
	 * root == null
	 * root = new TreeNode(value);
	 * return true;
	 */
	
	 public boolean insert(TreeNode root, int value) {
		 if (root == null ){
			 root = new TreeNode(value);
			 return true;
		 }
		 
		 if (value == root.val) {
			 return false;
		 }
		 
		 if (value > root.val) {
			 return insert(root.right, value);
		 } else {
			 return insert(root.left, value);
		 }
	 }
	
	 
	 /**
	  * 10.delete a node in BST
	  * 
	  * there is a lot details when the delete is not leave node.
	  * 前驱节点(left child rightmost node) the node that is closest to root
	  * is useful in this case.
	  */
	 
	@Test
	public void testdelete() {
		TreeNode root = new TreeNode(5);
		root.left  = new TreeNode(3);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		root = delete(root, 5);
		assertEquals(4, root.val);
	}
	 
	 
	public TreeNode delete (TreeNode root, int tar) {
		if (root == null) {
			 return root;
		}
		if (root.val < tar) {
			 return delete(root.right, tar);
		}
		if (root.val > tar) {
			 return delete(root.left, tar);
		}
		
		//start delete
		if (root.left == null) {
			root = root.right;
			
		} else {
			TreeNode trav = root.left;
			if (trav.right == null) {
				trav.right = root.right;
				root = trav;
			} else {
				TreeNode pre = trav;
				while(trav.right != null) {
					pre = trav;
					trav = trav.right;
				}
				pre.right = trav.left;
				trav.right = root.right;
				trav.left = root.left;
				root = trav; //make a bug here, change root pointer to trav does not work in the 上层，因为root 传进来时已经指向地址id = 51 固定。 
			}
		}
		return root;
	}
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//----------------------------------------------------------------------------------------------------------------
//	以下题为 Divide and Conquer 类型
	
	
	
	/** 
	 * 
	 * 11.0 Least Common Ancester(Divide And Conquer)
	 * has a parent
	 * 
	 */
	class TNode{
		TNode left, right, parent;
	}
	
	public TNode LCA(TNode node1, TNode node2) {
		int dep1 = maxDepth(node1);
		int dep2 = maxDepth(node2);
		
		if (node1 == null || node2 == null) {
			return null; //invalid
		}
		if (node1.parent == null || node2.parent == null) {
			if (node1.parent == null) {
				return node1;
			} else {
				return node2;
			}
		}
		
		if (dep1 == dep2) {
			return LCA(node1.parent, node2.parent);
		}
		
		if (dep1 > dep2) {
			return LCA(node1.parent, node2);
		} else {
			return LCA(node1, node2.parent);
		}
		
	}
	
	private int maxDepth(TNode root){
		if (root == null) {
			return 0;
		}
		return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
	}
	
	
	/**
	 * 11.1 
	 * if we only know root, the easy way to think is from up to bottom
	 * 
	 * if return is null, means node1 and node2 is not in this subtree.
	 * 
	 */
	
	public TreeNode getAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
		if (root == null || root == node1 || root == node2) {
			return root;
		}
		TreeNode left = getAncestor(root.left, node1, node2);
		TreeNode right = getAncestor(root.right, node1, node2);
		
		if (left != null && right != null) {
			//both left and right are not null means, the two nodes are disperse in two subtrees, LCA is root
			return root;
		} else if(left != null) {
			//only left is not null, right does not contains nodes, but this does not means both nodes are in left. only know at least one in left.
			return left;
		} else if(right != null) {
			return right;  //let  上一层 去处理，他们有更多信息去judge. 
		}
		
		return null;//does not contains any of two nodes
	}
	
	
	/**
	 * leetcode (Divide and Conquer)
	 * 12.0 construct tree from preorder, inorder (DAC);
	 * Given preorder and inorder traversal of a tree, construct the binary tree.
	 * 
	 * 12.1 construct tree from inorder, postorder(DAC);
	 * Given inorder and postorder traversal of a tree, construct the binary tree.
	 * 
	 *  202 / 202 test cases passed.
		Status: Accepted
		Runtime: 488 ms
		Submitted: 0 minutes ago

	 * tips: 碰到array index recursion, java 里尽量用 start + len 组合，可以少出bugs
	 */
    public TreeNode buildTree(int[] preorder, int[] inorder){
    	if (preorder == null || inorder == null) {
    		return null;
    	}
    	if (preorder.length != inorder.length) {
    		return null;
    	}
    	return getTree(inorder, 0, preorder, 0, inorder.length);
    }
    
    public TreeNode getTree(int[] preorder, int pstart, int[] inorder, int istart, int len) {
    	if (len == 0) {
    		return null;
    	}
    	if (len == 1) {
    		return new TreeNode(preorder[pstart]);
    	}
    	//Conquer
    	int rootValue = preorder[pstart];
    	int rootIndex = -1;
    	for (int i = istart; i < istart + len; i++) {
    		if (inorder[i] == rootValue) {
    			rootIndex = i;
    			break;
    		}
    	}
    	int leftLength = rootIndex - istart;
    	int rightLength = len - 1 - leftLength;
    	TreeNode root = new TreeNode(rootValue);
    	
    	//Divide 
    	root.left = getTree(preorder, pstart + 1, inorder, istart, leftLength);
    	root.right = getTree(preorder, pstart + leftLength + 1, inorder, rootIndex + 1, rightLength);
    	return root;
    }
    
    

	/**
	 * 13.0 Divide and conquer
	 * this can be solved by divide the left, right two parts, 
	 * s1 = left1 + right1;
	 * s2 = left2 + right2;
	 * 
	 * but make sure the length should be same. and we can swicth them
	 * if (left1 == left2 && right1 == right2 || left1 == right2 && left2 == right1) 
	 * return true
	 */
    /*
     	Scramble String Total Accepted: 10943 Total Submissions: 48570 My Submissions
		Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
		
		Below is one possible representation of s1 = "great":
		
		    great
		   /    \
		  gr    eat
		 / \    /  \
		g   r  e   at
		           / \
		          a   t
		To scramble the string, we may choose any non-leaf node and swap its two children.
		
		For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
		
		    rgeat
		   /    \
		  rg    eat
		 / \    /  \
		r   g  e   at
		           / \
		          a   t
		We say that "rgeat" is a scrambled string of "great".
		
		Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
		
		    rgtae
		   /    \
		  rg    tae
		 / \    /  \
		r   g  ta  e
		       / \
		      t   a
		We say that "rgtae" is a scrambled string of "great".
		
		Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
			
		}
		
		281 / 281 test cases passed.
		Status: Accepted
		Runtime: 424 ms
		Submitted: 5 minutes ago

		*/
    public boolean isScramble(String s1, String s2){
        if (s1 == null || s2 == null) {
            return false;
        }
        return dac(s1, s2);
    }
    public boolean dac(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() == 1) {
            return s1.equals(s2);
        }
        if (!check(s1, s2)) {
            return false;
        }
        
        for (int i = 0; i < s1.length(); i++) {
            String s11 = s1.substring(0, i); // i
            String s12 = s1.substring(i, s1.length()); // s1.length - i
            
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, s2.length());  
            boolean res = dac(s11, s21) && dac(s12, s22);
            if (!res) {
                String s31 = s2.substring(0, s2.length() - i);
                String s32 = s2.substring(s2.length() - i, s2.length());
                res = dac(s11, s32) && dac(s12, s31);                
            }
            if (res) {
                return true;
            }
        }
        return false;
    }
    
    private boolean check(String s1, String s2) {
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        
        for (int i = 0; i < s1.length(); i++) {
            c1[s1.charAt(i) - 'a']++;
            c2[s2.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 14.0 Divide and Conquer + DP
     * Unique Binary Search Trees II
     *      3
     *    1   4
     * count[3] = count[0] * count[2] + count[1] * count[1] + count[2] * count[1] 
     * count[n] = All Possible Of (count[left] * count[right])
     * the total problem divide into left and right subtree.
     */
    
    @Test
    public void testUniqueTree() {
    	int res = UniqueTreeI(3);
    	assertEquals(5, res);
    }
    
    public int UniqueTreeI(int n) {
    	if (n == 0) {
    		return 1;
    	}
    	if (n == 1) { 
    		return 1;
    	}

    	int[] count = new int[n + 1]; 
    	count[0] = 1;
    	count[1] = 1;
    	for (int i = 2; i <= n; i++) {
    		int sum = 0;
    		for (int j = 0; j < i; j++) {
    			sum += count[j] * count[i - 1 - j];
    		}
    		count[i] = sum;
    	}
    	return count[n];
    }
    
    
    /**
     * unique tree II 
     * @param n
     * @return
     * 
		9 / 9 test cases passed.
		Status: Accepted
		Runtime: 460 ms
		Submitted: 1 minute ago

     */
    
    public List<TreeNode> generateTrees(int n) {
    	List res = new ArrayList();
    	if (n == 0) {
    	    res.add(null);
    		return res;
    	}
    	return buildTree(1, n);
    }
    
    private List<TreeNode> buildTree(int start, int last) {
    	List<TreeNode> res = new ArrayList();
    	if (start > last) {
    	    res.add(null);//null stands for empty tree
    		return res;
    	}
    	if (start == last) {
    		TreeNode root = new TreeNode(start);
    		res.add(root);
    		return res;
    	}
    	
    	for(int i = start; i <= last; i++) {
    		
    		//divide
    		List<TreeNode> leftsubTrees  = buildTree(start, i - 1);
    		List<TreeNode> rightsubTrees = buildTree(i + 1, last);
    		
    		//conquer
    		for (TreeNode leftnode : leftsubTrees) {  //what will happen leftsubTrees == null
    			for (TreeNode rightnode : rightsubTrees) {
    	    		TreeNode root = new TreeNode(i);
    	    		root.left = leftnode;
    	    		root.right = rightnode;
    	    		res.add(root);
    			}
    		}
    	}
    	return res;
    }
    
    
    
    /**
     * 15.0 Best Time to Buy and Sell Stock III
     *  198 / 198 test cases passed.
		Status: Accepted
		Runtime: 496 ms
		Submitted: 1 minute ago

     * We have twice choice to buy and sell,
     * how to earn the biggest money.
     * 
     * 此问题分为两个子问题, 把0 - n 分两半，一次在左半部分买， 另一次在右半部分买。 Divide and Conquer
     * 1.  1.0 solve the first time best buy from 0 to i.   use int[] to memorize the best 
     *     1.1 solve the second time best buy from i to n.  use int[] to memorize the best
     * 2. scan from 0 - n 
     *  local = first + sec;
     *  global = Math.max(local, global);
     * 
     * tips: how to solve one time best buy is in Best Time to Buy and Sell Stock I
     */
    
    public int maxProfit(int[] data) {
        //1 2 1 2
        if (data == null || data.length <= 1) {
            return 0;
        }
        
        int n = data.length;
        //divide into two dps
        int[] forward = new int[n + 1];
        forward[0] = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
            max = Math.max(max, data[i] - min);
            forward[i + 1] = max;
        }
        
        int[] back = new int[n];
        back[n] = 0;
        int maxValue = Integer.MIN_VALUE;
        max = Integer.MIN_VALUE;
        
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] > maxValue) {
                maxValue = data[i];
            }
            max = Math.max(max, maxValue - data[i]);
            back[i] = max;            
        }
        
        //conquer
        int maxProfit = Integer.MIN_VALUE;
        
        for (int i = 0; i < data.length; i++) {
            int sum = forward[i]; // buy first from 0 to i - 1
            sum += back[i];       // buy sec from i to data.length - 1
            maxProfit = Math.max(maxProfit, sum);
        }
        return maxProfit;
    }
    
    
    
    /**
     * 16.0 Divide and Conquer
     * Flatten Binary Tree to Linked List
     * 
     * 
     * 
     * 
	225 / 225 test cases passed.
	Status: Accepted
	Runtime: 416 ms
	Submitted: 0 minutes ago

     */
    
    public void flatten(TreeNode root) {
    	if (root == null) {
    		return;
    	}
        convertToList(root);
    }
    
    private TreeNode convertToList(TreeNode root) {
    	if (root == null) {
    		return root;
    	}
    	
    	TreeNode tmpRight = root.right;
    	
    	//divide 
    	root.right = convertToList(root.left);
    	root.left  = null;
    	
    	//conquer
    	TreeNode trav = root;
    	while (trav.right != null) {
    		trav = trav.right;
    	}
    	trav.right = convertToList(tmpRight);
    	return root;
    }
    
    
    
    
    
}