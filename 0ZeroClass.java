package leetcode;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

public class ZeroClass {
	/** 
		Single Number II	整数运算	https://oj.leetcode.com/problems/single-number-ii
		Single Number		整数运算	https://oj.leetcode.com/problems/single-number
		Divide Two Integers	整数运算	https://oj.leetcode.com/problems/divide-two-integers
		Roman to Integer	整数运算	https://oj.leetcode.com/problems/roman-to-integer
		Plus One			整数运算	https://oj.leetcode.com/problems/plus-one
		Add Binary			整数运算	https://oj.leetcode.com/problems/add-binary
		Valid Number		整数运算	https://oj.leetcode.com/problems/valid-number
		Reverse Integer		整数运算	https://oj.leetcode.com/problems/reverse-integer
		String to Integer   整数运算	https://oj.leetcode.com/problems/string-to-integer-atoi
		Multiply Strings	整数运算	https://oj.leetcode.com/problems/multiply-strings
	 * 
	 */
	
	/**
	 * 1. Single Number use this format
	 * int a and int b 
	 * a ^ a = 0; a ^ b ^ a = a ^ a ^ b = b
	 */
	
	@Test
	public void testbitmanipulate() {
		int a = 123;
		int b = 456;
		assertEquals(b, a ^ b ^ a);
		assertEquals(b, a ^ a ^ b);
	}
	
	public int singleNumber1(int[] num) {
		int res = 0;
		for (int i : num) {
			res ^= i;
		}
		return res;
	}
	
	
	
	/**
	 * 2. Single Number II related to such questions
	 */
	public int singleNumber(int[] num) {
        int res = 0;
        int[] bit = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < num.length; j++) {
                bit[i] += (num[j] >> i) & 1;
            }
            bit[i] %= 3;
            res |= bit[i] << i;
        }
        return res;
    }
	
	
	
	/**
	 * 3. Divide 	 
	 * 1000 / 4 
	 * 1000 = 1 * 512 + 1 * 256 + 1 * 128 + 0 * 64 + ... + 1 * 2 + 0 * 0 (looks like this)
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	
	public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        int quotient = 0;
        //corner case
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
            return 1;
        } else if (divisor == Integer.MIN_VALUE) {
            return 0;
        } else if (dividend == Integer.MIN_VALUE) {
            dividend += Math.abs(divisor);
            quotient ++;
        }
        
        //start calculate
        int Dividend = Math.abs(dividend);
        int Divisor = Math.abs(divisor);
        
        int shift = 0;
        while(Dividend >> (shift + 1) >= Divisor) {
            shift++;
        }
        
        while(shift >= 0) {
            if (Dividend >= (Divisor << shift)) {
                Dividend -= Divisor << shift;
                quotient += 1 << shift;
            }
            shift--;
        }
        return ((dividend ^ divisor) >>> 31) == 0 ? quotient : -quotient;
    }
	
	/*
    given an integer in base 10, convert to a given base k
    110 -> 2 
    8 / 2 = 4
    4 / 2 = 2
    2 / 2 = 1
    shift++;
    */
	@Test
	public void testconvert() {
		int num = 2;
		int[] res = convertToBaseK(num, 2);
		for (int i : res) {
			System.out.print(i + ",");
		}
	}
	public int[] convertToBaseK(int num, int k) {
        
        int base = k;
        int div = 1;
        int pos = 0;
        while (num / div >= base) {
            div *= base;
            pos ++;
        }
        
        int[] digit = new int[pos + 1];
        
        while (pos >= 0) {
            if (num >= div) { //get times of div
                int times = num / div;
                num -= times * div;
                digit[digit.length - 1 - pos] = times;
            }
            pos--;
            div /= base;
        }
        
        return digit;
    }
	/**
	 * 4. Roman to Integer
	 * first, familiar with Roman and Integer transformation rule
	 * 1 -> I     
	 * 5 -> V
	 * 10 -> X
	 * 50 -> L
	 * 100 -> C
	 * 500 -> D
	 * 1000 -> M
	 * Roman 数中没有零
	 * 1 2 3 
	 * 4 5
	 * 6 7 8 
	 * 9 
	 */
	
	
	public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }	    
        int res = 0;
        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = convert(s.charAt(i));
            if (i > 0 && pre < cur) {
                res -= pre;
                res += cur - pre;
                pre = cur;
            } else {
                res += cur;
                pre = cur;
            }
        }
        return res;
	}
	
	int convert(char c) {
	    switch(c) {
	        case 'I':
	            return 1;
	        case 'V':
	            return 5;
	        case 'X':
	            return 10;
	        case 'L':
	            return 50;
	        case 'C':
	            return 100;
	        case 'D':
	            return 500;
	        case 'M':
	            return 1000;
	        default:
	        	return 0;
	    }
	}
	
	/**
	 * Plus One
	 * @param digits
	 * @return
	 */
			
	
	
	public int[] plusOne(int[] digits){
        boolean allNine = true;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 9) {
                allNine = false;
                break;
            }
        }
        if (allNine) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }
        int carry = 0;
        digits[digits.length - 1] ++;
        for (int i = digits.length - 1; i >= 0; i--) {
            int value = digits[i] + carry;
            carry = value / 10;
            value = value % 10;
            digits[i] = value;
        }
        return digits;
    }
	
	
	
	/**
	 * add two binary
	 * @param a
	 * @param b
	 * @return
	 */
	
	 @Test
	 public void testaddBinary() {
		 String a = "11";
		 String b = "11";
		 String res = addBinary(a, b);
		 assertEquals("110", res);
	 }
	 
	 public String addBinary(String a, String b){
	        if (a == null || b == null) {
	            return a == null ? b : a;
	        }
	        int m = a.length();
	        int n = b.length();
	        int carry = 0;
	        char[] sol =new char[m + n + 1];
	        int index = m + n - 1;
	        
	        while(m > 0 || n > 0) {
	            int value = 0;
	            if (m > 0 && n > 0) {
	                value = a.charAt(m - 1) - '0' + b.charAt(n - 1) - '0' + carry;
	                m--;
	                n--;
	            } else if(m > 0) {
	                value = a.charAt(m - 1) - '0' + carry;
	                m--;
	            } else {
	                value = b.charAt(n - 1) - '0' + carry;
	                n--;
	            }
	            carry = value / 2;
	            value = value % 2;
	            sol[index--] = (char)(value + '0');
	        }
	        if (carry != 0) {
	            sol[index--] = (char)(carry + '0');
	        }
	        index++;
	       
	        return String.copyValueOf(sol, index, sol.length - index - 1);//this is final index
	 }
	 
	 
	 
	 
	 

	 
	
	
	/**
	 * Valid Number
	 */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0 || s.trim().equals("")) {
            return false;
        }
        s = s.trim();
        
        int start = 0;
        // skip leading +/-
        if (s.charAt(start) == '+' || s.charAt(start) == '-') start++;
        
        boolean num = false; // is a digit
        boolean dot = false; // is a '.'
        boolean exp = false; // is a 'e'
        
        for(;start < s.length(); start++){
            char c = s.charAt(start);
            if (Character.isDigit(c)) {
                num = true;
            } else if (c == '.') {
                if (dot || exp) {
                    return false;
                }
                dot = true;
            } else if(c == 'e'){
                if (!num || exp) {
                    return false;
                }
                exp = true;
                num = false;
            } else if(c == '+' || c == '-') {
                if (start > 0 && s.charAt(start - 1) == 'e') {
                    continue;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return num;
    }
	 
	 
	 
    /**
     * reverse integer
     * use long type to solve the problem
     */
    public int reverse(int num){
        int sign = 1;
        if(num < 0){
            sign = -1;
            num = -num;
        }
        long res = 0;
        while(num > 0) {
            int value = num % 10;
            res = res * 10  + value;//last digit
            num /= 10;//remove last one
        }
        if (sign * res < Integer.MIN_VALUE) {
            return 0;
        } 
        if (sign * res > Integer.MAX_VALUE) {
            return 0;
        }
        return sign * (int) res;
	}
    
    
	/**
	 * String to Integer(atoi)
	 * use long to store final result and then cast to int
	 */
	 public int atoi(String str) {
	        if (str.length() == 0) {
	            return 0;
	        }
	        str = str.trim();
	        int sign = 1;
	        int index = 0;
	        if (str.charAt(0) == '+') {
	            index++;
	        } else if (str.charAt(0) == '-') {
	            sign = -1;
	            index++;
	        }
	        long num = 0;
	        for (int i = index; i < str.length(); i++) {
	            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
	                break;
	            }
	            num = num * 10 + str.charAt(i) - '0';
	            if (num > Integer.MAX_VALUE) {
	                break;
	            }
	        }
	        if (num * sign > Integer.MAX_VALUE) {
	            return Integer.MAX_VALUE;
	        }
	        if (num * sign < Integer.MIN_VALUE) {
	            return Integer.MIN_VALUE;
	        }
	        return sign * (int)  num;
	    }
	
	 
	/**
		Multiply Strings
	*/
	 @Test
	 public  void testmultiply() {
		 String res = multiply("11","11");
		 assertEquals("121", res);
	 }
	 
	 public String multiply(String A1, String A2) {
			StringBuffer num1 = new StringBuffer(A1);
			StringBuffer num2 = new StringBuffer(A2);
			num1.reverse();
			num2.reverse();
			
	        int m = num1.length();
	        int n = num2.length();
	        
	        int carrier = 0;
	        int[] res = new int[m + n + 1];
	        
	        
	        for(int k = 0; k <m ; k++){
	            int d1 = num1.charAt(k) - '0';
	            int carry = 0;
	            
	            for(int i = 0; i < n; i ++){
	                int d2 = num2.charAt(i) - '0';
	                
	                int exist = res[k + i];
	                res[k + i] = ( d1 * d2 +carry + exist )% 10 ;
	                carry = ( d1 * d2 +carry + exist ) /10 ;
	            }
	            
	            if(carry > 0){
	            	res[k + n] = carry;
	            	
	            }
	        }
	        
	        
	        StringBuffer ret = new StringBuffer();
	        for(int i = n + m; i >= 0; i--){
	        	ret.append(res[i]);
	        }
	        while(ret.length() > 1 && ret.charAt(0) == '0'){
	        	ret.deleteCharAt(0);
	        }
	        return ret.toString();
	    }
	    
	    
	 
	 
	 
	 
	 
	 
	 
	 
//----------------------------------------------------------------------------------------------------------------------

/**	 
 * 	 移动窗口
 * 	 Longest Consecutive Sequence
	 Longest Substring Without Repeating Characters
	 Substring with Concatenation of All Words
	 Minimum Window Substring 
*/ 
	    /**
	     * longestConsecutive
	     */
	    @Test
	    public void testlongest() {
	    	longestConsecutive(new int[]{1,0,-1});
	    }
	    public int longestConsecutive(int[] num) {
	        HashMap<Integer, Integer> hs = new HashMap();
	        for (int i : num) {
	            hs.put(i, 1);
	        }
	        
	        int max = 0;
	        for (int i : num) {
	            if (hs.get(i) == 0) {
	                continue;
	            }
	            hs.put(i, 0);
	            
	            int up = i + 1;
	            while (hs.containsKey(up)) {
	                hs.put(up, 0);
	                up++;
	            }
	            
	            int down = i - 1;
	            while (hs.containsKey(down)) {
	                hs.put(down, 0);
	                down--;
	            }
	            
	            max = Math.max(max, up - down - 1);
	        }
	        return max;
	    }
	    
	    
	    /**
	     * longest substring without repeating character
	     */
	    public int lengthOfLongestSubstring(String s) {
	        HashSet<Character> hs = new HashSet();
	        int start = 0;
	        int max = 0;
	        for (int i = 0;  i < s.length();  i++) {
	            char c = s.charAt(i);
	            if (hs.contains(c)) {
	                max = Math.max(max, i - start);
	                while(s.charAt(start) != c) {
	                    hs.remove(s.charAt(start));
	                    start++;
	                }
	                start = start + 1; // new start
	            } else {
	                hs.add(c);
	            }
	        }
	        max = Math.max(max, s.length() - start);
	        return max;
	    }
	    
	    
	    /**
	     * The words might be repeat.
	     */
	    
	    
	    @Test
	    public void testfindSubstring(){ 
	    	List<Integer> res = findSubstring("barfoothefoobarman", new String[]{"foo","bar"});
	    	System.out.println(res);
	    }
	    public List<Integer> findSubstring(String S, String[] L){
	        int n = L.length;
	        int w = L[0].length();
	        List<Integer> res = new ArrayList();
	        for (int i = 0; i < w; i++) {
	            for (int j = i; j + n * w <= S.length(); j += w) {
	                int end   = j + n * w;
	                int start = j;
	                boolean flag = true;
	                HashMap<String, Integer> map = new HashMap();
	                for (String str : L) {
	                    if (map.containsKey(str)) {
	                        map.put(str, map.get(str) + 1);
	                    }else {
	                        map.put(str, 1);
	                    }
	                }
	        
	                for(int k = start; k < end; k += w) {
	                    String tmp = S.substring(k, k + w);
	                    if (map.containsKey(tmp)) {
	                        if (map.get(tmp) == 0) {
	                            flag = false;
	                            break;
	                        } else {
	                           	map.put(tmp, map.get(tmp) - 1);
	                        }
	                    } else {
	                        flag = false;
	                        break;
	                    }
	                }
	                if (flag) {
	                    res.add(start);
	                }
	            }
	        }
	        return res;
	    }
	    
	    
	
}
