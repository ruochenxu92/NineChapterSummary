package leetcode;

import java.util.HashMap;

public class FourClass {
	/**
	 * LinkedList
	 * 
	    Swap Nodes in Pairs
		Rotate List
		Reverse Nodes in k-Group
		Reverse Linked List II
		Reorder List
		Remove Nth Node From End of List
		Remove Duplicates from Sorted List II
		Remove Duplicates from Sorted List
		Partition List
		Linked List Cycle II
		Linked List Cycle
		Copy List with Random Pointer
		Add Two Numbers
		Merge Two Sorted Lists
		Sort List
		Insertion Sort List
	 */
	
	
	
	
	
	
	
	
//-------------------------------------------------------------------------------------------------------------------	
//linkedList 基本操作题
	
	/**
	 * linkedList 基本操作题
	 *  Rotate List 
	 *  Remove Nth Node From End of List
		Remove Duplicates from Sorted List II
		Remove Duplicates from Sorted List
		Partition List
	 */
	
	/**
	 * rotate List. 1 2 3 4 5    rotated 2 elements it will be 
	 * 
	 */
	public ListNode rotate(ListNode head, int k) {
		if (head == null || head.next != null) {
			return head;
		}
		int length = 0;
		for(ListNode cur = head; cur != null; cur = cur.next) {
			length++;
		}
		
		k = k % length;
		if (k == 0) {
			return head;
		}
		
		ListNode cur = head;
		ListNode newhead = null;
		for (int i = 1; cur.next != null; i++) {
			if (i == length - k) {
				newhead = cur;
			}
			cur = cur.next;
		}
		ListNode tmp = newhead.next;
		newhead.next = null;
		newhead = tmp;
		cur.next = head;
		return newhead;
	}
	
	
	
	/**
	 * Remove Nth Node From End of List
	 */
	
	
	public ListNode removeNth(ListNode head, int k) {
		if (head == null) {
			return head;
		}
		
		int length = 0;
		ListNode cur = head;
		
		for(;cur != null;cur = cur.next){
			length++;
		}
		
		if (k > length) {
			return head;
		}
		
		int index = k - length;
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		for (int i = 0; i != index; i++) {
			pre = pre.next;
		}
		pre.next = pre.next.next;
		return dummy.next;
	}
	
	
	
	
	
	/**
	 *  remove Duplicates from sorted list ii
	 *  require must remove all nodes that are duplicates
	 *  1. 1 -> 1 ->1       null;
	 *  2. 1 -> 1 -> 1 -> 2      2;
	 */
	public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                int cur = head.next.val;
                while (head.next != null && head.next.val == cur) {
                    head.next = head.next.next;
                }
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
	
	
	
	/**
	 * rotated 
	 * @param head
	 * @param n
	 * @return
	 */
	
	
	public ListNode rotateRight(ListNode head, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        int length = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            length++;
        }
        n = n % length;
        if (n == 0) {
            return head;
        }
        int k = length - n;
        ListNode pre = null;
        
        ListNode cur = head;
        for(int i = 1; i < length; i++) {
            if (i == k) {
                pre = cur;
            }
            cur = cur.next;
        }
        ListNode newhead = pre.next;
        pre.next = null;
        cur.next = head;
        return newhead;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//----------------------------------------------------------------------------------------------------------------
//reverse
	/***
	 * 跟reverse 相关的题
	 * 1. Reverse Nodes in k-Group
	   2. Reverse Linked List II
	   3. Reorder List
	   4. Swap Nodes in Pairs
	 */
	
	
	
	
	/**
	 * 1. reverse Nodes in k-Group 
	 */
	
	public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int count = 1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode aft = null;
        
        while(cur != null) {
            if (count % k == 0) {
                aft = cur.next;
                pre = reverse(pre, aft);
                cur = aft;
            } else{
                cur = cur.next;
            }
            count++;
        }
        return dummy.next;
    }
    
    ListNode reverse(ListNode pre, ListNode aft) {
        ListNode ret = pre.next;
        ListNode head = pre.next;
        ListNode newhead = aft;
        
        while(head != aft) {
            ListNode tmp = head.next;
            head.next = newhead;
            newhead = head;
            head = tmp;
        }
        pre.next = newhead;
        return ret;
    }
	
	
	
	
	
	/**
	 * 2. reverse Linked List II
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = null;
        ListNode cur = dummy;
        ListNode aft = null;
        for (int i = 0; i < n; i++) {
            if (i == m - 1) {
                pre = cur;
            }
            cur = cur.next;
        }
        aft = cur.next;
        
        ListNode newhead = aft;
        cur = pre.next;
        while(cur != aft) {
            ListNode tmp = cur.next;
            cur.next = newhead;
            newhead = cur;
            cur = tmp;
        }
        pre.next = newhead;
        return dummy.next;
    }
	
	
	/**
	 * 3.Reorder List
	 */
	public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode middle = findMiddle(head);
        ListNode newhead = reverse(middle.next);
        middle.next = null;
        merge(head, newhead);
    }
    
    ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    ListNode reverse(ListNode head) {
        ListNode newhead = null;
        while(head != null) {
            ListNode tmp = head.next;
            head.next = newhead;
            newhead = head;
            head = tmp;
        }
        return newhead;
    }
    
    void merge(ListNode head, ListNode newhead) {
        while(newhead != null) {
            ListNode tmp = head.next;
            head.next = newhead;
            newhead = newhead.next;
            head.next.next = tmp;
            head = tmp;
        }
    }
	
	/**
	 * 4. Swap Nodes in Pairs
	 */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;                
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.next != null) {
            ListNode aft = head.next.next.next;
            ListNode cur = head.next;
            head.next = cur.next;
            head.next.next = cur;
            cur.next = aft;
            head = cur;
        }
        return dummy.next;
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
//-------------------------------------------------------------------------------------------------------------------
//merge sort
	
	/**
	 * merge sort 相关的题
	 *  1. Merge Two Sorted Lists
		2. Sort List
		3. Insertion Sort List
	 */
    
    
    
    
    
    
    /**
     * 1. Merge Two Sorted Lists
     * @param head1
     * @param head2
     * @return
     */
	
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        ListNode newNode = null;
        while(head1 != null || head2 != null) {
            if (head1 != null && head2 != null) {
                if (head1.val < head2.val) {
                    newNode = head1;
                    head1 = head1.next;
                } else {
                    newNode = head2;
                    head2 = head2.next;
                }
            } else if (head1 != null) {
                newNode = head1;
                head1 = head1.next;
            } else {
                newNode = head2;
                head2 = head2.next;
            }
            tail.next = newNode;
            tail = tail.next;
        }
        return dummy.next;
    }
	
    
    
    /**
     * 2. Sort Lists
     */
    public ListNode sortList(ListNode head) {
	    if (head == null || head.next == null) {
	        return head;
	    }
	    
	    ListNode middle = findMiddle_(head);
	    ListNode head2 = sortList(middle.next);
	    middle.next = null;
	    head = sortList(head);
	    return merge_(head, head2);
	}
	
	ListNode findMiddle_(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;   	    
	    while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
	    }
	    return slow;
	}
	
	ListNode merge_(ListNode head, ListNode head2) {
	    ListNode dummy = new ListNode(0);
	    ListNode tail = dummy;
	    ListNode newNode = null;
	    while(head != null || head2 != null) {
	        if (head != null && head2 != null) {
	            if (head.val < head2.val) {
	                newNode = head;
	                head = head.next;
	            } else {
	                newNode = head2;
	                head2 = head2.next;
	            }
	        } else if(head != null) {
	            newNode = head;
	            head = head.next;
	        } else {
	            newNode = head2;
	            head2 = head2.next;
	        }
	        tail.next = newNode;
	        tail = tail.next;
	    }
	    return dummy.next;
	}
	
	
	
	/**
	 * insertion sort
	 * create new node and use insert sort.
	 * insert sort compare insert node with the list and try to find a fit pos to insert ins node.
	 * 
	 */
	
	
	public ListNode insertionSortList(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        while(head != null) {
            ListNode node = dummy;
            while(node.next != null && node.next.val < head.val) {
                node = node.next;
            }
            ListNode tmp = head.next;
            head.next = node.next;
            node.next = head;
            head = tmp;
        }
        return dummy.next;
    }  
	
	/**
	 *  找规律题
	 *  1. Linked List Cycle II
		2. Linked List Cycle
		3. Copy List with Random Pointer
		4. Add Two Numbers
	 */
	
	
	/**
	 * 1. Linked List Cycle II
	 */
	public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        boolean firstTime = true;
        while(true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            if (fast == slow) {
                if (firstTime) {
                    firstTime = false;
                } else {
                    break;
                }
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
	
	/**
	 * 2. Copy List with Random Pointer
	 * The question is a little difficult because we need to copy random pointer
	 * Two steps O(n) Time O(n) space
	 * 1.deep copy as normal ListNode and set up 1 - 1 mapping between old node and new node
	 * 2.use 1 - 1 mapping to get random pointer
	 * 
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode newhead = new RandomListNode(head.label);
        HashMap<RandomListNode, RandomListNode> mapping = new HashMap();
        mapping.put(head, newhead);
        RandomListNode tail = newhead;
        RandomListNode trav = head.next;
        while (trav != null) {
            RandomListNode newnode = new RandomListNode(trav.label);
            mapping.put(trav, newnode);
            tail.next = newnode;
            tail = tail.next;
            trav = trav.next;
        }
        
        trav = head;
        tail = newhead;
        while(trav != null) {
            RandomListNode value = mapping.get(trav.random);
            tail.random = value;
            tail = tail.next;
            trav = trav.next;
        }
        return newhead;
    }
	
	
	
	/**
	 * 3. add two numbers
	 */
	public ListNode addTwoNumbers(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        int carry = 0;
        while (h1 != null || h2 != null) {
            int value = 0;
            if (h1 != null && h2 != null) {
                value = h1.val + h2.val + carry;
                h1 = h1.next;
                h2 = h2.next;
            } else if (h1 != null) {
                value = h1.val + carry;
                h1 = h1.next;
            } else {
                value = h2.val + carry;
                h2 = h2.next;
            }
            carry = value / 10;
            value = value % 10;
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        if (carry != 0) {
            tail.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
