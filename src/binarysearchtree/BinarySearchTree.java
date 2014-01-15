/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package binarysearchtree;

/**
 * 
 * @author patrick.brown
 */
public class BinarySearchTree
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        /* TODO: Implement these LinkedBinarySearchTree methods
        1) addToTree(T) adds an element to the tree in the correct place using
        the BST rule:  Left child < Parent <= Right child
 
        2) removeElement(T) removes the node containing T, correctly promotes a 
        sucessor to maintain the BST rule above.
 
        3) Iterator inOrderIterator() returns an iterator that will produce all 
        tree elements in ascending order. For the test case below this would be:
        8, 10, 15, 20, 27, 30, 35, 38, 40, 47, 48, 55, 60, 65
 
        4) Iterator levelOrderIterator() returns an iterator that will produce 
        all tree elements in level order. For the test case below this would be:
        30, 20, 55, 10, 27, 48, 60, 8, 15, 40, 65, 35, 47, 38
        */
        Integer[] testData = new Integer[14];
        testData[0] = new Integer (30);
        testData[1] = new Integer (20);
        testData[2] = new Integer (55);
        testData[3] = new Integer (10);
        testData[4] = new Integer (27);
        testData[5] = new Integer (8);
        testData[6] = new Integer (15);
        testData[7] = new Integer (48);
        testData[8] = new Integer (60);
        testData[9] = new Integer (65);
        testData[10] = new Integer (40);
        testData[11] = new Integer (47);
        testData[12] = new Integer (35);
        testData[13] = new Integer (38);
      
        LinkedBinarySearchTree<Integer> myBST = new LinkedBinarySearchTree<>();
          
          
        for (int i = 0; i < testData.length; i++)
            myBST.add (testData[i]);
         
        /*  should produce this tree:
      
                                   30
                                 /    \
                                20    55
                               / \    / \
                              10 27  48  60
                             / \     /    \
                            8  15   40    65
                                   /  \
                                  35  47
                                   \
                                   38
                                   
        In the JGrasp debugger, set breakpoint after the tree has been 
        populated. In the Debug, Variables tab in the left hand pane, right 
        click on myBST and choose View Value to see a diagram of your tree.
        */
       
        //Iterator inOrderIter = myBST.inOrderIterator();

        //String treeStr = printValues(inOrderIter);
      
        System.out.println("in order");
        /**System.out.println(treeStr);
       
        Iterator levelOrderIter = myBST.levelOrderIterator();
        treeStr = printValues(levelOrderIter);

        System.out.println("level order");
        System.out.println(treeStr); 
       
        /* test cases for remove: 
        8 a left hand leaf - no promotion
        47 a right hand leaf - no promotion 
        35 has one right hand child - should promote 38
        48 has one left hand child- should promote 40
        20 internal node with two children- should promote 27
        40 internal node with two children- should promote 47
        30 internal node (also the root) with two children- should promote 35 to
        the root
        */
      
        /**---try{
            myBST.removeElement(new Integer (30));
        } catch (ElementNotFoundException enfex){
            System.out.print(enfex.getMessage());
        } catch(EmptyCollectionException ecex){
            System.out.print(ecex.getMessage());
        } catch(Throwable ex){
            System.out.print(ex.getMessage());
        }
    }
   
    public static String printValues(Iterator iter){
        StringBuffer sb = new StringBuffer();
        while(iter.hasNext())
            sb.append(iter.next().toString()).append(" ");
        return sb.toString();
    */}
    
}
