package algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class BinaryTree {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	/*
	 * 一、二叉树遍历
	 * 
	 * 1、先序遍历,递归与非递归
	 * 2、中序遍历,递归与非递归
	 * 3、后序遍历,递归与非递归
	 * 4、层序遍历,递归与非递归
	 */
	
	//1.1.1、先序遍历 递归
	private void preorder(TreeNode root, List<Integer> re){
		if(root!=null){
			re.add(root.val);
			preorder(root.left, re);
			preorder(root.right, re);
		}
	}
	
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> re=new ArrayList<Integer>();
        preorder(root, re);   
        return re;
    }
	
    //1.1.2、非递归
    public List<Integer> preorderTraversal2(TreeNode root) {
    	List<Integer> re=new ArrayList<Integer>();
    	if(root==null){
    		return re;
    	}
    	Stack<TreeNode> stack=new Stack<TreeNode>();
    	TreeNode node=root;
    	while(node!=null||!stack.isEmpty()){
    		while(node!=null){
    			re.add(node.val);
    			stack.push(node);
    			node=node.left;
    		}
    		if(!stack.isEmpty()){
    			node=stack.pop().right;
    		}
    	}
    	return re;
    }
    
    //1.2.1、中序遍历 递归
    private void inorder(TreeNode root, List<Integer> re){
    	if(root!=null){
    		inorder(root.left, re);
    		re.add(root.val);
    		inorder(root.right, re);
    	}
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> re=new ArrayList<Integer>();
        inorder(root, re);
        return re;
    }
	
    //1.2.2、非递归
    public List<Integer> inorderTraversal2(TreeNode root) {
    	List<Integer> re=new ArrayList<Integer>();
    	Stack<TreeNode> stack=new Stack<TreeNode>();
    	TreeNode node=root;
    	while(node!=null||!stack.isEmpty()){
    		while(node!=null){
    			stack.push(node);
    			node=node.left;
    		}
    		
    		if(!stack.isEmpty()){
    			node=stack.pop();
    			re.add(node.val);
    			node=node.right;
    		}    		
    	}    	
    	return re;
    }
    
    //1.3.1、后序遍历 递归
    private void postorder(TreeNode root, List<Integer> re){
    	if(root!=null){
    		postorder(root.left, re);
    		postorder(root.right, re);
    		re.add(root.val);
    	}
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> re=new ArrayList<Integer>();
        postorder(root, re);        
        return re;
    }
    
    //1.3.2、非递归 改变二叉树结构
    public List<Integer> postorderTraversal2(TreeNode root) {
    	List<Integer> re=new ArrayList<Integer>();
    	Stack<TreeNode> stack=new Stack<TreeNode>();
    	TreeNode node=root;
    	while(node!=null||!stack.isEmpty()){
    		while(node!=null){
    			stack.push(node);
    			node=node.left;
    		}    		
    		if(!stack.isEmpty()){
    			if(stack.peek().right!=null){
    				node=stack.peek().right;
    				stack.peek().right=null;
    			}else{
    				re.add(stack.pop().val);
    			}
    		}
    	}
    	return re;
    }
    
    //1.3.3、非递归 不改变二叉树结构
    public List<Integer> postorderTraversal3(TreeNode root) {
    	List<Integer> re=new ArrayList<Integer>();
    	if(root==null){
    		return re;
    	}
    	Stack<TreeNode> stack=new Stack<TreeNode>();
    	stack.push(root);
    	TreeNode pre=null;
    	while(!stack.isEmpty()){
    		TreeNode peek=stack.peek();
    		if((peek.left==null&&peek.right==null)||(pre!=null&&(pre==peek.left||pre==peek.right))){
    			peek=stack.pop();
    			re.add(peek.val);
    			pre=peek;
    		}else{
    			if(peek.right!=null){
    				stack.push(peek.right);
    			}
    			if(peek.left!=null){
    				stack.push(peek.left);
    			}
    		}
    	}
    	return re;
    }

    //1.4.1、层序遍历 递归与非递归
    private void levelorder(Queue<TreeNode> p, Queue<TreeNode> q, List<List<Integer>> re){
    	List<Integer> list=new ArrayList<Integer>();
    	while(!p.isEmpty()){
    		TreeNode node=p.poll();
    		list.add(node.val);
    		if(node.left!=null){
    			q.offer(node.left);
    		}
    		if(node.right!=null){
    			q.offer(node.right);
    		}
    	}
    	re.add(list);
    	if(!q.isEmpty()){
    		levelorder(q, p, re);
    	}
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        Queue<TreeNode> p=new LinkedList<TreeNode>();
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        if(root!=null){
        	p.offer(root);
        	levelorder(p, q, re);
        }        
        return re;
    }
    
    //1.4.2 非递归
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        if(root==null){
        	return re;
        }
        List<TreeNode> list=new ArrayList<TreeNode>();
        list.add(root);
        int cur=0, last=list.size();
        while(cur<list.size()){
        	List<Integer> l=new ArrayList<Integer>();
        	for(int i=cur; i<last; i++){
        		TreeNode node=list.get(i);
        		l.add(node.val);
        		if(node.left!=null){
        			list.add(node.left);
        		}
        		if(node.right!=null){
        			list.add(node.right);
        		}	
        	}
        	re.add(l);
        	cur=last;
        	last=list.size();
        }           
        return re;
    }
    
    /*
     * 二、重建二叉树
     * 
     * 1、先序与中序遍历重建
     * 2、后序与中序遍历重建
     * 3、二叉树的序列化与反序列化
     */
    
    //2.1、先序与中序遍历重建
    private int find(int[] nums, int start, int end, int tar){
    	for(int i=start; i<=end; i++){
    		if(nums[i]==tar){
    			return i;
    		}
    	}
    	return -1;
    }
    
    private TreeNode build(int[] preorder, int index, int[] inorder, int start, int end){
    	TreeNode root=null;
    	if(start<=end){
    		root=new TreeNode(preorder[index]);
    		if(start!=end){
    			int mid=find(inorder, start, end, preorder[index]);
        		root.left=build(preorder, index+1, inorder, start, mid-1);
        		root.right=build(preorder, index+mid-start, inorder, mid+1, end);
    		}    		
    	}    	
    	return root;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	return build(preorder, 0, inorder, 0, inorder.length-1);
    }
    
    //2.2、后序与中序遍历重建
    private TreeNode build2(int[] postorder, int index, int[] inorder, int start, int end){
    	TreeNode root=null;
    	if(start<=end){
    		root=new TreeNode(postorder[index]);
    		if(start!=end){
    			int mid=find(inorder, start, end, postorder[index]);
    			root.left=build2(postorder, index-end+mid-1, inorder, start, mid-1);
    			root.right=build2(postorder, index-1, inorder, mid+1, end);
    		}
    	}
    	return root;
    }   
    
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return build2(postorder, postorder.length-1, inorder, 0, inorder.length-1);
    }
    
    //2.3、二叉树的序列化与反序列化 先序遍历
    static class Codec {
    	private void seria(StringBuilder s, TreeNode root){
    		if(root==null){
    			s.append("*,");
    			return ;
    		}else{
    			s.append(root.val).append(',');
    			seria(s, root.left);
    			seria(s, root.right);
    		}
    	}
    	
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder s=new StringBuilder();
            seria(s, root);
            return s.toString();
        }
        
        private int index;
        
        private TreeNode deseria(String s){
        	if(index>=s.length()){
        		return null;
        	}
        	TreeNode root;
        	if(s.charAt(index)=='*'){
        		root=null;
        		index+=2;
        	}else{
        		int end=index+1;
        		while(end<s.length()&&s.charAt(end)!=','){
        			end++;
        		}
        		root=new TreeNode(Integer.valueOf(s.substring(index, end)));
        		index=end+1;   
        		root.left=deseria(s);
        		root.right=deseria(s);
        	}
        	return root;
        }
        
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            index=0;
            return deseria(data);
        }
    }
    
    /*
     * 三、二叉树的基本操作
     * 
     * 1、反转二叉树 ,递归与非递归
     * 2、二叉树最大深度与最小深度
     * 3、判断平衡二叉树,后序遍历优化
     * 4、判断两棵树相同
     * 5、判断对称二叉树 递归与非递归
     * 6、两个节点的最低公共祖先 
     */
    
    //3.1.1、反转二叉树 递归
    public TreeNode invertTree(TreeNode root) {
        if(root!=null){
        	TreeNode temp=invertTree(root.right);        	
        	root.right=invertTree(root.left);
        	root.left=temp;
        }
        return root;
    }
    
    //3.1.2、 非递归
    public TreeNode invertTree2(TreeNode root) {
    	if(root!=null){
    		Queue<TreeNode> q=new LinkedList<TreeNode>();
    		q.offer(root);
    		while(!q.isEmpty()){
    			TreeNode node=q.poll();
    			
    			TreeNode temp=node.left;
    			node.left=node.right;
    			node.right=temp;
    			
    			if(node.left!=null){
    				q.offer(node.left);
    			}
    			if(node.right!=null){
    				q.offer(node.right);
    			}
    		}
    	}
    	return root;
    }
    
    //3.2.1、二叉树最大深度
    public int maxDepth(TreeNode root) {
        if(root!=null){
        	return 1+Math.max(maxDepth(root.left), maxDepth(root.right));
        }
        return 0;
    }
    
    //3.2.2、二叉树最小深度
    public int minDepth(TreeNode root) {
        if(root==null){
        	return 0;
        }else if(root.left==null&&root.right==null){
        	return 1;
        }else if(root.left==null){
        	return minDepth(root.right)+1;
        }else if(root.right==null){
        	return minDepth(root.left)+1;
        }else{
        	return 1+Math.min(minDepth(root.left), minDepth(root.right));
        }
    }
    
    //3.3.1、判断平衡二叉树 
    public boolean isBalanced(TreeNode root) {
    	if(root==null||(root.left==null&&root.right==null)){
    		return true;
    	}
    	if(Math.abs(maxDepth(root.left)-maxDepth(root.right))>1){
    		return false;
    	}
    	return isBalanced(root.left)&&isBalanced(root.right);
    }
    
    //3.3.2、优化 后序遍历		编程之美 
    private int deep;
    public boolean isBalanced2(TreeNode root) {
    	if(root==null){
    		deep=0;
    		return true;
    	}
    	int left, right;
    	if(isBalanced2(root.left)){
    		left=deep;
    	}else{
    		return false;
    	}
    	if(isBalanced2(root.right)){
    		right=deep;
    	}else{
    		return false;
    	}
    	
    	if(Math.abs(left-right)<=1){
    		deep=Math.max(left, right)+1;
    		return true;
    	}else{
    		return false;
    	}    	
    }
    
    //3.4、判断两棵树相同
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
        	return true;
        }
        if(p==null||q==null){
        	return false;
        }
        
        if(p.val!=q.val){
        	return false;
        }else{
        	return isSameTree(p.left, q.left)&&isSameTree(p.right, q.right);
        }
    }
    
    //3.5.1、判断对称二叉树		递归
    private boolean isSym(TreeNode l, TreeNode r){
    	if(l==null&&r==null){
    		return true;
    	}
    	if(l==null||r==null){
    		return false;
    	}    	
    	if(l.val==r.val){
    		return isSym(l.right, r.left)&&isSym(l.left, r.right);    		
    	}
    	return false;
    }
    
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
        	return true;
        }
        return isSym(root.left, root.right);
    } 
    
    //3.5.2、非递归
    public boolean isSymmetric2(TreeNode root) {
    	List<TreeNode> list=new ArrayList<TreeNode>();
    	list.add(root);
    	List<Integer> dataList = new ArrayList<Integer>(Arrays.asList(10,20,30,null));
    	int cur=0, last;
    	while(cur<list.size()){
    		last=list.size();
    		int i=cur, j=last-1;
    		while(i<j){
    			if((list.get(i)==null&&list.get(j)==null)||
    					(list.get(i)!=null&&list.get(j)!=null&&list.get(i).val==list.get(j).val)){
    				i++;
    				j--;
    				continue;
    			}
    			return false;
    		}
    		for(i=cur; i<last; i++){
    			if(list.get(i)!=null){
    				TreeNode node=list.get(i);
    				list.add(node.left);
    				list.add(node.right);
    			}
    		}
    		cur=last;
    	}    	
    	return true;
    }    
    
    //3.6.1、两个节点的最低公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
        	return null;
        }
        if(root==p||root==q){
        	return root;
        }
        TreeNode l=lowestCommonAncestor(root.left, p, q);
        TreeNode r=lowestCommonAncestor(root.right, p, q);
        if(l!=null&&r!=null){
        	return root;
        }
        return l==null? r: l;
    }
    
    //3.6.2、两个节点的最低公共祖先		优化 剑指offer
    private boolean getPath(List<TreeNode> list, TreeNode root, TreeNode node){
    	if(root==null){
    		return false;
    	}   	
    	list.add(root);
    	if(root==node){
    		return true;
    	}
    	
    	if(root.left!=null){
    		if(getPath(list, root.left, node)){
        		return true;
        	}else{
        		list.remove(list.size()-1);
        	}
    	}
    	if(root.right!=null){
    		if(getPath(list, root.right, node)){
    			return true;
    		}else{
    			list.remove(list.size()-1);
    		}
    	}
    	return false;
    }
    
    private TreeNode getNode(List<TreeNode> l1, List<TreeNode> l2){
    	int i=0;
    	for(i=0; i<l1.size()&&i<l2.size(); i++){
    		if(l1.get(i)!=l2.get(i)){
    			break;
    		}
    	}
    	return l1.get(i-1); 
    }
    
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    	List<TreeNode> l1=new LinkedList<TreeNode>();
    	List<TreeNode> l2=new LinkedList<TreeNode>();
    	getPath(l1, root, p);
    	getPath(l2, root, q);
    	return getNode(l1, l2);
    }
    
    //3.6.3、二叉搜索树
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
    	if(root.val>Math.max(p.val, q.val)){
    		return lowestCommonAncestor3(root.left, p, q);
    	}else if(root.val<Math.min(p.val, q.val)){
    		return lowestCommonAncestor3(root.right, p, q);
    	}else{
    		return root;
    	}
    }
    

	public static void main(String[] args) {

	}

}
