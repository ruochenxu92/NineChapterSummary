package leetcode;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;



/**
 * @author Xiaomin
 */
public class FirstClass {
	
	/**
	 * 1. strStr leetcode implement strStr
	 */
	public String strStr(String haystack, String needle) {
		if (haystack == null || needle == null || needle.length() > haystack.length()) {
			return null;
		}
		for (int i = 0; i + needle.length() <= haystack.length(); i++) {
			String tmp = haystack.substring(i, i + needle.length());
			if (tmp.equals(needle)) {
				return haystack.substring(i);
			}
		}
		return null;
	}
	
	
	
	/**
	 *  2.0
	 *  subsets I template
	 * 
		10 / 10 test cases passed.
		Status: Accepted
		Runtime: 432 ms
		Submitted: 0 minutes ago

	 */
	
	public ArrayList<ArrayList<Integer>> subsets(int[] num) {
		ArrayList res= new ArrayList();
		if (num == null || num.length == 0) {
			return res;
		}
		Arrays.sort(num);
		ArrayList sol = new ArrayList();
		subsetsdfs(0, num, sol, res);
		return res;
	}
	
	private void subsetsdfs(int pos, int[] num, ArrayList sol, ArrayList res) {
		res.add(sol.clone());
		
		for(int i = pos; i < num.length; i++) {
			sol.add(num[i]);
			subsetsdfs(i + 1, num, sol, res);
			sol.remove(sol.size() - 1);
		}
	}
	
	
	/**
	 * 2.1
	 * subset II template
	 * the array may have duplicates
	 */
	/*
	19 / 19 test cases passed.
	Status: Accepted
	Runtime: 488 ms
	Submitted: 0 minutes ago
	*/
	
	
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
		ArrayList res = new ArrayList();
		if (num == null || num.length == 0) {
			return res;
		}
		Arrays.sort(num);
		ArrayList sol = new ArrayList();
		dfs(0, num, sol, res);
		return res;
	}
	
	
	
	// i == pos 表示第一次走，i != pos 表示recursion back 时，i继续走， 这时num[i] == num[i - 1]， 既然num[i - 1] 走过了， num[i] 就不用走了，跳过。
	private void dfs(int pos, int[] num, ArrayList sol, ArrayList res) {
		res.add(sol.clone());
		
		for(int i = pos; i < num.length; i++) {
			if (i != pos && i > 0 && num[i] == num[i - 1]) {
				continue;
			}
			sol.add(num[i]);
			dfs(i + 1, num, sol, res);
			sol.remove(sol.size() - 1);
		}
	}
	
	
	
	
	
//-----------------------------------------------------------------------------------------------------------------------------
//下面进入正题 搜索题目,排列组合问题的一般解法 DFS recursion，先回想一下template,再上！
	
	/**
	 * Main Topic: DFS
		Subsets II
		Subsets
	    N-Queens II   					
		N-Queens                        https://oj.leetcode.com/problems/n-queens/
		Combinations					https://oj.leetcode.com/problems/combinations/
		Combination Sum II
		Combination Sum					https://oj.leetcode.com/problems/combination-sum/
		Sum Root to Leaf Numbers
		Restore IP Addresses
		Sudoku Solver
		Valid Sudoku
		Word Break II
		Generate Parentheses
		Letter Combinations of a Phone Number
		Permutations II
		Permutations
		Permutation Sequence
		Next Permutation
	 */
	
	/**
	 * 3.1 N-Queens
	 */
	
	public ArrayList<String[]> solveNQueens(int n){
        ArrayList res = new ArrayList();
        if (n <= 0) {
            return res;
        }
        String[] sol = new String[n];
        dfs(0, n, sol, res);
        return res;
    }
    
    private void dfs(int stRow, int n, String[] board, ArrayList res) {
        if (stRow == n) {
            res.add(board.clone());//only need to change here,  count++; return;   N-Queens II answer
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (isValid(stRow, col, board)) {
                char[] cur = new char[n];
                Arrays.fill(cur, '.');
                cur[col] = 'Q';
                board[stRow] = String.copyValueOf(cur);
                dfs(stRow + 1, n, board, res);
            }
        }      
    }
    
    private boolean isValid(int stRow, int col, String[] board) {
        
        for (int i = 0; i < stRow; i++) {
            int Qcol = board[i].indexOf('Q');
            
            if (Qcol == col) {
                return false;
            }
            
            int colDiff = Math.abs(Qcol - col);
            int rowDiff = Math.abs(stRow - i);
            
            if (colDiff == rowDiff) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * 4.0
     * Combinations
     * n = 4, k = 2
     * 1 2
     * 1 3 
     * 1 4
     * 2 3 
     * 2 4
     * 3 4
     * 
26 / 26 test cases passed.
Status: Accepted
Runtime: 456 ms
Submitted: 0 minutes ago
	
     */
    
    public ArrayList<ArrayList<Integer>> combination(int n, int k) {
    	ArrayList res = new ArrayList();
    	if (k > n || n <= 0) {
    		return res;
    	}
    	//already sort
    	ArrayList sol = new ArrayList();
    	dfs(0, k, n, sol, res);
    	return res;
    }
	
    private void dfs(int pos, int k, int n, ArrayList sol, ArrayList res) {
    	if (k == 0) {
    		res.add(sol.clone());
    		return;
    	}
    	
    	for (int i = pos; i < n; i++) {
    		sol.add(i + 1);
    		dfs(i + 1, k - 1, n, sol, res);
    		sol.remove(sol.size() - 1);
    	}
    }
    
    
    
    /**
     * 5.0
     * combination sum II 
     */
    public ArrayList<ArrayList<Integer>> combinationSum(int[] data, int tar) {
		ArrayList res = new ArrayList();
		if (data == null || data.length == 0 || tar <= 0) {
			return res;
		}
		Arrays.sort(data);
		ArrayList sol = new ArrayList();
		dfs(0, tar, data, sol, res);
		return res;
	}
	
	void dfs(int pos, int sum, int[] data, ArrayList sol, ArrayList res) {
		if (sum == 0) {
			res.add(sol.clone());
			return;
		}
		
		if (sum < 0) {
			return;
		}
		
		for (int i = pos; i < data.length; i++) {
			if (i != pos && i > 0 && data[i] == data[i - 1]) {
				continue;
			}
			sol.add(data[i]);
			dfs(i + 1, sum - data[i], data, sol, res);
			sol.remove(sol.size() - 1);
		}
	}
    
	
	
	/**
	 * 6.0 sum from root to leave node
	 *      1
		   / \
		  2   3
		The root-to-leaf path 1->2 represents the number 12.
		The root-to-leaf path 1->3 represents the number 13.
		
		Return the sum = 12 + 13 = 25.
	 */
	
	
    private int sumNumbers = 0;
	public int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		dfs(root, 0);
		return sumNumbers;
	}
	
	void dfs(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		int cursum = sum * 10 + root.val;
		if (root.left == null && root.right == null) {
			sumNumbers += cursum;
			return;
		}
		dfs(root.left,  cursum);
		dfs(root.right, cursum);
	}
	
	
	
	@Test
	public void testpathsum(){ 
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(0);
		root.right = new TreeNode(0);
		root.left.left = new TreeNode(0);
		int res = pathsum(root).pathsum;
		int res2 = sumNumbers(root);
		assertEquals(res2, res);
	}
	
	class ResultType{
		int div;
		int pathsum;
		ResultType(int div, int pathsum) {
			this.div = div;
			this.pathsum = pathsum;
		}
	}
	
	//这题适用Divide and conquer
	public ResultType pathsum(TreeNode root) {
		if (root == null) {
			return new ResultType(1, 0);
		}
		if (root.left == null && root.right == null) {
			return new ResultType(1, root.val);
		}
		//divide
		ResultType left = pathsum(root.left);
		ResultType right = pathsum(root.right);
		
		//conquer
		int div = Math.max(left.div, right.div) * 10;
		
		int pathsum = left.pathsum  + root.val * left.div * 10 + right.pathsum + root.val * right.div * 10;
		return new ResultType(div, pathsum);
	}
	
	
	
	
	
	/**
	 * 7.0 generate Parenthesis
	 * 
	 * For example, given n = 3, a solution set is:
		"((()))", "(()())", "(())()", "()(())", "()()()"
	 * @param n
	 * @return
	 */
	public ArrayList<String> generateParenthesis(int n) {
        ArrayList res = new ArrayList();
        if(n <= 0){
            return res;
        }
        StringBuffer sol = new StringBuffer();
        dfs(0, 0, n, sol, res);
        return res;
    }
    
    void dfs(int left, int right, int n, StringBuffer sol, ArrayList res){
        if (left < n) {
            sol.append('(');
            dfs(left + 1, right, n, sol, res);
            sol.deleteCharAt(sol.length() - 1);
        }
        
        if (right < left) {
            sol.append(')');
            dfs(left, right + 1, n, sol, res);
            sol.deleteCharAt(sol.length() - 1);
        }
        
        if (left == n && right == n) {
            res.add(sol.toString());
            return;
        }
    }
	
    
    /**
     * 8.0 word break II
     * This is cannot pass the leetcode because of Time Limited Exceeded.
     * But this version should pass the 面试官. 
     * There are two ways to improve the performance:
     * 1. use dp
     * 2. use hashmap
     */
    
    @Test
    public void testwordbreak(){
    	String s = new String("catsanddog");
    	Set<String> dict = new HashSet();
    	String[] strs = {"cat", "cats", "and", "sand", "dog"};
    	for (int i = 0; i < strs.length; i++) {
    		dict.add(strs[i]);
    	}
    	ArrayList<String> res = wordBreak(s, dict);
    	System.out.println(res);
    }
    
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> res = new ArrayList();
        if (s == null || dict.size() == 0) {
            return res;
        }
        StringBuffer sol = new StringBuffer();
        dfs(0, s, dict, sol, res);
        return res;
    }
    
    void dfs(int pos, String s, Set<String> dict, StringBuffer sol, ArrayList<String> res) {
        if (pos == s.length()) {
            res.add(sol.toString());
            return;
        }        
        
        StringBuffer buf = new StringBuffer();
        for (int i = pos; i < s.length(); i++) {
            buf.append(s.charAt(i));
            String tmp = buf.toString();
            if (dict.contains(tmp)) {
                int buflen = buf.length();
                if (i != s.length() - 1) {
                    buf.append(' ');                                                
                }
                int sollen = sol.length();
                sol.append(buf);
                dfs(i + 1, s, dict, sol, res);
                buf.delete(buflen, buf.length());
                sol.delete(sollen, sol.length());
            }
        }
    }
    
    
    
    
    
    
    /**
     * 9.0 valid sudoku
     * it is sub question for solve sudoku
     */
    
    public boolean isValidSudoku(char[][] matrix) {
        if (matrix == null || matrix.length != 9 || matrix[0].length != 9) {
            return false;            
        }
        boolean[] visited = new boolean[9];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int col = 0; col < 9; col++) {
                if (!process(visited, matrix[i][col])) {
                	return false;
                }
            }
        }
        
        for (int j = 0; j < 9; j++) {
            Arrays.fill(visited, false);
            for (int row = 0; row < 9; row++) {
                if (!process(visited, matrix[row][j])){
                	return false;
                }
            }
        }
        
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Arrays.fill(visited, false);
                for (int k = 0; k < 9; k++) {
                    int x = k / 3;
                    int y = k % 3;
                    if (!process(visited, matrix[i + x][j + y])) {
                    	return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    boolean process(boolean[] visited, char c) {
    	if (c == '.') {
    		return true;
    	}
    	
    	int index = c - '1';
    	if (visited[index]) {
    		return false;
    	}
    	visited[index] = true;
    	return true;
    }
	
    
    /**
     * 9.1 solve the soduku
     * This question is similar to N-queens.
     */
    
    public void solveSudoku(char[][] board) {
        solve(board, 0);
    }
    
    boolean solve(char[][] matrix, int count) {
        if (count == 9 * 9) {
            return true;
        }
        int x = count / 9;
        int y = count % 9;
        if (matrix[x][y] != '.') {
            return solve(matrix, count + 1);
        }
        
        for (char k = '1'; k <= '9'; k++) {
            matrix[x][y] = k;
            if (isValid(matrix, x, y) && solve(matrix, count + 1)) {
                return true;   // fix a bug here
            }
            matrix[x][y] = '.';
        }
        return false;
    }
    
    boolean isValid(char[][] matrix, int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (i != x && matrix[i][y] == matrix[x][y]) {
                return false;
            }
        }   
        for (int j = 0; j < 9; j++) {
            if (j != y && matrix[x][j] == matrix[x][y]) {
                return false;
            }
        }
        int row = x / 3 * 3;
        int col = y / 3 * 3;
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if ( i != x && j != y && matrix[i][j] == matrix[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    
    /**
     * 10.0 restore IP address
     * 1. pos
     * 2. StringBuffer sol
     * 3. num < 256
     * 4. 4 parts
     */
    
    @Test
    public void testrestoreIpAddress() {
    	ArrayList<String> res = restoreIpAddresses("1111");
    	assertEquals(Arrays.asList(new String[]{"1.1.1.1"}), res);
    }
    
    
    
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList res = new ArrayList();
        if (s == null || s.length() == 0) {
            return res;
        }
        StringBuffer sol = new StringBuffer();
        dfs(0, 4, s, sol, res);
        return res;
    }
    //25525511211
    void dfs(int pos, int count, String s, StringBuffer sol, ArrayList res) {
        if (count == 0 && pos == s.length()) {
            res.add(sol.toString());
            return;
        }
        if (count == 0) {
            return;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = pos; i < s.length(); i++) {
            buf.append(s.charAt(i));
            String tmp = buf.toString();
            if (tmp.charAt(0) == '0' && tmp.length() > 1) {
                return;
            }
            if (Integer.parseInt(tmp) < 256) {
                int buflen = buf.length();
                int sollen = sol.length();
                if (i != s.length() - 1) {
                    buf.append('.');
                }
                sol.append(buf);
                dfs(i + 1, count - 1, s, sol, res);
                buf.delete(buflen, buf.length());
                sol.delete(sollen, sol.length());
            }else {
                return;
            }
        }
    }
	
	/**
	 * 11.0
	 * Letter Combinations of a Phone Number
	 * HashMap and DFS
	 */
    
    
    public List<String> letterCombinations(String num) {
        HashMap<Integer,String> keyboard = new HashMap<Integer, String>();
        keyboard.put(2,"abc");
        keyboard.put(3,"def");
        keyboard.put(4,"ghi");
        keyboard.put(5,"jkl");
        keyboard.put(6,"mno"); 
        keyboard.put(7,"pqrs");
        keyboard.put(8,"tuv");
        keyboard.put(9,"wxyz");
        List<String> res = new ArrayList();
        StringBuffer sol = new StringBuffer(); 
        dfs(0, num, keyboard, sol, res);
        return res;
    }
    
    private void dfs(int pos, String s, HashMap<Integer, String> dict, 
                        StringBuffer sol, List<String> res){
        if(pos == s.length()){
            res.add(sol.toString());
            return;
        }          
        
        String tmp = dict.get(s.charAt(pos) - '0');
        for(int i = 0; i < tmp.length(); i++){
            sol.append(tmp.charAt(i));
            dfs(pos + 1, s, dict, sol, res);
            sol.deleteCharAt(sol.length() - 1);
        }
    }
    
    
    
    
    /**
     * 12.0
     * Permutation && permutation II
     * 1. boolean[] visited
     * 2. skip dup by using i > 0 && num[i] == num[i - 1] && !visited[i]
     * 3. Arrays.sort(num);
     */
    
    @Test
    public void testpermute() {
    	int[] num = new int[]{1,2,3};
    	ArrayList res = permute(num);
    	System.out.println(res);
    }
    
    public ArrayList permute(int[] num){
        ArrayList res = new ArrayList();
        if (num == null || num.length == 0) {
            return res;
        }
        Arrays.sort(num);
        boolean[] visited = new boolean[num.length];
        ArrayList sol = new ArrayList();
        dfs(num, sol, res, visited);
        return res;
    }
    
    void dfs(int[] num, ArrayList sol, ArrayList res, boolean[] visited) {
        if (num.length == sol.size()) {
            res.add(sol.clone());
            return;
        }
        
        for (int i = 0; i < num.length; i++) {
            if (!visited[i]) {
                if (i > 0 && num[i] == num[i - 1] && !visited[i - 1]) {
                    continue;
                }
                visited[i] = true;
                sol.add(num[i]);
                dfs(num, sol, res, visited);
                sol.remove(sol.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    /**
     * 13.0 Permutation Sequence
     *  1 2 3 4
     *  这题不是典型的排列组合， 是排列组合的变种，规律就在于Divide and Conquer
     *  注意：不能重复使用数字， 用过了必须从list remove
     *  Assume k = 20
     *  index = k - 1;
     *  3! = 6
     *  0 - 5     1 开头
     *  6 - 11    2 开头
     *  12 - 17   3 开头
     *  18 - 23   4 开头
     *  
     *  然后， 是2！= 2
     *  第一次是在 4开头， list = [1 2 3] 
     *  update index = index % 6 = 19 % 6 = 1
     *  0 - 1     1st
     *  2 - 3     2nd
     *  4 - 5     3rd
     *  
     *  then, 1!
     *  update index = index % 2 = 1
     *  0
     *  1
     *  
     *  then, 0!
     *  update index = index % 1 = 0;
     *  then 1st
     */
    
    @Test
    public void testgetPermutation() {
    	String s = getPermutation(4, 24);
    	assertEquals("4321", s);
    }
    
    public String getPermutation(int n, int k){
        if (n < 1 || k < 1) {
            return "";
        }
        ArrayList list = new ArrayList();
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }
        StringBuffer sol = new StringBuffer();
        int index = k - 1;
        for (int i = n - 1; i >= 0; i--) {
            int section = factorial(i);
            int key = index / section;
            sol.append(list.get(key));
            index = index % section;
        }
        return sol.toString();
    }
    int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        int res = 1;
        for (int i = n; i >= 1; i--) {
            res *= i;
        }
        return res;
    }

    
    
   
    
    
   
    /**
     * 14. 0 nextPermutation is also permutation question, we need find the rule of Permutation
     * 1234 -> 1243
     * 1243 -> 1324
     * 1324 -> 1342
     * 1342 -> 1423
     * 1423 -> 1432
     * 1432 -> 2134
     * 
     * 1. from right to left find first num that violate the ascending rule, like in 1234, 3 > 4 is not ascending.
     * mark the first num void the rule as partition.
     * 2. from right to left find the first large num that greater than that one.
     * 3. sort from partition to right.
     * 
     * Similar question, find prevPermutations
     * @param num
     */
    
    public void nextPermutation(int[] num) {
	    if (num == null || num.length <= 1) {
	        return;
	    }	
	    int partition = -1;
	    for (int i = num.length - 2; i >= 0; i--) {
	        if (num[i] < num[i + 1]) {   //change a little bit here can be find prevPermutation
	            partition = i;
	            break;
	        }
	    }
	    
	    if (partition == -1) {
	        Arrays.sort(num);
	        return;
	    }
	    
	    for (int i = num.length - 1; i >= partition; i--) {
	        if (num[i] > num[partition]) {
	            int tmp = num[i];
	            num[i] = num[partition];
	            num[partition] = tmp;
	            break;
	        }
	    }
	    Arrays.sort(num, partition + 1, num.length);
	}
}
