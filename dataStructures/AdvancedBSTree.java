package dataStructures;                                         

/**
 * Advanced BSTree Data Type implementation
 * @author AED team
 * @version 1.0
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value 
 */
public abstract class AdvancedBSTree<K extends Comparable<K>, V> extends BinarySearchTree<K,V>
{                                                                   

	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

    
    /**
     * Returns the node whose key is the specified key;
     * or null if no such node exists.                                
     * Moreover, stores the path into the stack.
     * @param key to be searched 
     * @param path - Stack of PathStep objects containing all ancestors of node
     * @return the found node, when the search is successful
     */
    protected BSTNode<K,V> findNode( K key, Stack<PathStep<K,V>> path )
    {
        path.push( new PathStep<K,V>(null, false) );
        BSTNode<K,V> node = root;
        while ( node != null )
        {
            int compResult = key.compareTo( node.getKey() );
            if ( compResult == 0 )
                return node;
            else if ( compResult < 0 )
            {
                path.push( new PathStep<K,V>(node, true) );
                node = node.getLeft();
            }
            else                                             
            {
                path.push( new PathStep<K,V>(node, false) );
                node = node.getRight();
            }
        }
        return null;                                                    
    }                                                   


    /**
     * Returns the node with the smallest key 
     * in the tree rooted at the specified node.
     * Moreover, stores the path into the stack.
     * Requires: theRoot != null.
     *
     * @param theRoot - node that roots the tree
     * @param path - Stack of PathStep objects containing all ancestors of the minNode
     * @return node containing the entry with the minimum key
     */
    protected BSTNode<K,V> minNode( BSTNode<K,V> theRoot, 
        Stack<PathStep<K,V>> path )
    {
        BSTNode<K,V> node = theRoot;
        while ( node.getLeft() != null )         
        {
            path.push( new PathStep<K,V>(node, true) );
            node = node.getLeft();
        }
        return node; 
    }                                                   


    /**
     * Performs a single left rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * @param theRoot - root of the rotation
     * @param leftChild - Left child of theRoot
     * @param path - Stack of PathStep objects containing all ancestors of theRoot
     */
    protected void rotateLeft( BSTNode<K,V> theRoot, BSTNode<K,V> leftChild, 
        Stack<PathStep<K,V>> path )
    {
        theRoot.setLeft( leftChild.getRight() );
        leftChild.setRight(theRoot);
        this.linkSubtree(leftChild, path.top());
    }


    /**
     * Performs a single right rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * @param theRoot - root of the rotation
     * @param rightChild - Right child of theRoot
     * @param path - Stack of PathStep objects containing all ancestors of theRoot 
     */
    protected void rotateRight( BSTNode<K,V> theRoot, BSTNode<K,V> rightChild,
        Stack<PathStep<K,V>> path )
    {
        theRoot.setRight( rightChild.getLeft() );
        rightChild.setLeft(theRoot);
        this.linkSubtree(rightChild, path.top());
    }


    /**
     * Performs a double left rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * @param theRoot - root of the rotation
     * @param leftChild - Left child of theRoot
     * @param rightGrandchild - Right child of leftChild
     * @param path - Stack of PathStep objects containing all ancestors of theRoot
     */
    protected void rotateLeft( BSTNode<K,V> theRoot, BSTNode<K,V> leftChild, 
        BSTNode<K,V> rightGrandchild, Stack<PathStep<K,V>> path )
    {
        leftChild.setRight( rightGrandchild.getLeft() );
        theRoot.setLeft( rightGrandchild.getRight() );
        rightGrandchild.setLeft(leftChild);
        rightGrandchild.setRight(theRoot);
        this.linkSubtree(rightGrandchild, path.top());
    }


    /**
     * Performs a double right rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * @param theRoot - root of the rotation
     * @param rightChild - Right child of theRoot
     * @param leftGrandchild - Left child of rightChild
     * @param path - Stack of PathStep objects containing all ancestors of theRoot
     */
    protected void rotateRight( BSTNode<K,V> theRoot, BSTNode<K,V> rightChild,
        BSTNode<K,V> leftGrandchild, Stack<PathStep<K,V>> path )
    {
        theRoot.setRight( leftGrandchild.getLeft() );
        rightChild.setLeft( leftGrandchild.getRight() );
        leftGrandchild.setLeft(theRoot);
        leftGrandchild.setRight(rightChild);
        this.linkSubtree(leftGrandchild, path.top());
    }
}

