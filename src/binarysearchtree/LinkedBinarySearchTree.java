/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package binarysearchtree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author patrick.brown
 * @param <T>
 */
public class LinkedBinarySearchTree<T extends Comparable<? super T>>
{
    private ArrayList<T> tree = new ArrayList<T>();
    int ROOT_KEY = 1;
    
    /**
     * Constructor... this may be unnecessary. 
     */
    public void LinkedBinarySearchTree()
    {
        this.tree = new ArrayList<T>();
    }
    
    /**
     * Add function. Stores node into the BST.
     * 
     * @param o element to store.
     */
    public void add(T o)
    {
        if(this.tree.isEmpty()){
            // set the object at index 0 to null
            this.tree.add(0, null); 
            this.tree.add(ROOT_KEY, o);
            growTree(ROOT_KEY);
        } else {
            addHelper(ROOT_KEY, o);
        }
        
    }
    
    /**
     * Recursive add function helper. Tests for which direction to add, 
     * following the rule:
     * Left child < Parent <= Right child.
     * 
     * Looks at the current node's child link. If null, then the current node is
     * the parent of the new element (wrapped in a BinaryTreeNode). If there is 
     * a child, then recur on that child.
     * 
     * @param node is the node we're testing.
     * @param o is the object we're trying to add.
     */
    private void addHelper(int node, T o)
    {
        T current_node = this.tree.get(node);
        int left_key = getLeft(node);
        T left_node = this.tree.get(left_key);
        int right_key = getRight(node);
        T right_node = this.tree.get(right_key);

        if (o.compareTo(current_node) < 0){
            // add to left
            if(left_node == null){
                growTree(getLeft(node));
                setLeft(node, o);
            } else {
                addHelper(left_key, o);
            }
        } else if(o.compareTo(current_node) >= 0){ 
            // add to right
            if(right_node == null){
                growTree(getRight(node));
                setRight(node, o);
            } else {
                addHelper(right_key, o);
            }
        }
    }
    
    /** 
     * Remove the element from the tree. Note that we have to find the first 
     * node containing the element (or one with the same value as determined by 
     * the compareTo method), then we remove that node. We must maintain the BST
     * property: Left child < Parent <= Right child. This method makes use of 
     * the getReplacement method to find the node that will be promoted if the 
     * root is to be removed. if not the root, then the removeElemHelper method 
     * is called. if this method returns a null, the element does not exist in 
     * the tree.
     * 
     * @param o
     * @throws java.lang.Exception
     * @return ?
     */
    public T remove (T o) throws Exception 
    {
        T to_return;
        
        // Check if root is null.
        if (this.tree.get(ROOT_KEY) == null){
            //throw EmptyCollectionException("tree is empty");
        } else if (o.compareTo(this.tree.get(ROOT_KEY)) == 0) {
            this.tree.set(ROOT_KEY, getReplacement(ROOT_KEY));
        }
        
        to_return = removeHelper(ROOT_KEY, o);
        
        if(to_return == null){
            //throw ElementNotFoundException ("not found "+target.toString());
        }
        return to_return;
    }
    
    /**
     * The current node is not equal to the target, so examine the target 
     * against the current node, choose the appropriate direction to search. 
     * examine the target against the current node's child element. If found 
     * find a replacement node by calling findReplacement. If null is returned, 
     * then the child is a leaf and is deleted by setting the current node's 
     * pointer to null. If the child is not the target, recur on the child node.
     * 
     * @param key
     * @param o
     */
   
    private T removeHelper(int key, T o)
    {
        T current_value = this.tree.get(key);
        
        int child_key;
        T child_value;
        T replacement;
        T to_return = null;
        
        int left_key = getLeft(key);
        T left_value = this.tree.get(left_key);
        int right_key = getRight(key);
        T right_value = this.tree.get(right_key);
        
        if(current_value != null){
            if(o.compareTo(current_value) < 0){
                child_key = left_key;
                child_value = left_value;
                
                if (child_value != null && o.compareTo(child_value) == 0){
                    to_return = child_value;
                    
                    replacement = getReplacement(child_key);
                    if(replacement == null) {
                        setLeft(key, null);
                    } else {
                        setLeft(key, replacement);
                    }
                } else {
                    to_return = removeHelper(child_key, o);
                }   
            } else if(o.compareTo(current_value) > 0) {
                child_key = right_key;
                child_value = right_value;
                
                if(child_value != null && o.compareTo(child_value) == 0) {
                    to_return = child_value;
                    
                    replacement = getReplacement(child_key);
                    if(replacement == null){
                        setRight(key, null);
                    } else {
                        setRight(key, replacement);
                    }
             
                } else {
                    to_return = removeHelper(child_key, o);
                }
            }
        }
        return to_return;
    }

    private T getReplacement(int key)
    {
        T to_return;
        
        T current_value = this.tree.get(key);
        
        int left_key = getLeft(key);
        T left_value = this.tree.get(left_key);
        int right_key = getRight(key);
        T right_value = this.tree.get(right_key);
        
        if(left_value == null && right_value == null) {
            //node is a leaf
            to_return = null;
        } else if(left_value == null && right_value != null) {
            //node has a right child only
            to_return = right_value;
        } else if(left_value != null && right_value == null) {
            //node has a left child only
            to_return = left_value;
        } else {                          
            // node has both a right and left child- find inorder sucessor
            // go right because of the equals sign in: Left child < Parent <= 
            // Right child 
            to_return = findInorderSucessor(right_key); 
            // these next two lines connect the IOS to the departing node's
            // children.
            // result.left <- node.left;  
            setLeft(key, left_value);
            
            if(to_return != right_value) {
                // if they are the same, then you do not want to make the right 
                // child reference the parent!
                setRight(key, right_value);
            }
        }
        return to_return;
    }
    
    private T findInorderSucessor(int key)
    {
        T to_return;
        
        T current_value = this.tree.get(key);
        
        int left_key = getLeft(key);
        T left_value = this.tree.get(left_key);
        int right_key = getRight(key);
        T right_value = this.tree.get(right_key);
        
        to_return = left_value;
      
        if(to_return == null) {
            // the IOS is node
            return this.tree.get(key);
        }

        /**else if(child.left is null)  // child is the IOS
            node.left <- child.right
            return child
        
        //else   /// search on
        */

        return findInorderSucessor(left_key);
    }
    
    private int getParent(int key)
    {
        // java's floor function returns a *double*, not an int.
        return (int)Math.floor(key / 2);
    }

    private void setParent(int key, T o)
    {
        // java's floor function returns a *double*, not an int.
        this.tree.set((int)Math.floor(key / 2), o);
    }

    private int getLeft(int key)
    {
        return 2 * key;
    }

    private void setLeft(int key, T o)
    {
        this.tree.set(2*key, o);
    }

    private int getRight(int key)
    {   
        return 2 * key + 1;
    }

    private void setRight(int key, T o)
    {
        this.tree.set(2 * key + 1, o);
    }

    /**
     * Because we get array IndexOutOfBounds errors when we add objects out of
     * order onto ArrayLists, we need to populate potential children with null
     * objects.
     */
    private void generatePotentialChildren(int index)
    {        
        this.tree.add(getLeft(index), null);
        this.tree.add(getRight(index), null);
    }

    /**
     * At times we'll need to grow the tree. We'll take the index at the end of
     * an add operation and see if we need to grow the tree to accommodate the
     * changes.
     *
     * @param index
     */
    private void growTree(int index)
    {
        double power = Math.ceil((Math.log(index) / Math.log(2))) + 2;
        int required_size = (int)Math.pow(2, power);
        while(required_size > this.tree.size()){
            this.tree.add(null);
        }
    }
}
