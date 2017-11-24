package dataStructures;                                         

/**
 * AVL node implementation of the BSTNode interface
 * 
 * @author AED team
 * @version 1.0
 *
 * @param <K> Generic type Key
 * @param <V> Generic type Value 
 */
class AVLNode<K,V> extends BSTNode<K,V>
{                                                                   

	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;


    /**
     * The balance factor of the tree rooted at the node,
     * which is:
     * 'E'  iff  height( node.getLeft() ) = height( node.getRight() );
     * 'L'  iff  height( node.getLeft() ) = height( node.getRight() ) + 1;
     * 'R'  iff  height( node.getLeft() ) = height( node.getRight() ) - 1.
     */
    private char balanceFactor;


    /**
     * Constructor for AVL nodes
     * 
     * @param key to be stored in this AVL tree node
     * @param value to be stored in this AVL tree node
     * @param balance is Left, Right or Equally balanced
     * @param left sub-tree of this node
     * @param right sub-tree of this node
     */
    public AVLNode( K key, V value, char balance, 
        AVLNode<K,V> left, AVLNode<K,V> right )
    {                                                                
        super(key, value, left, right);
        balanceFactor = balance;                                      
    }
    

    /**
     * Constructor for AVL nodes
     * 
     * @param key to be stored in this AVL tree node
     * @param value to be stored in this AVL tree node
     */
    public AVLNode( K key, V value )
    {    
        this(key, value, 'E', null, null);
    }


    /**
     * Returns the balance of the sub-tree below this node.
     * 
     * @return balanceFactor - the balance factor of the sub-tree
     */
    public char getBalance( )                           
    {   
        return balanceFactor;
    }


    /**
     * Sets the new balance of the sub-tree below this node.
     * 
     * @param newBalance - the new balance factor of the sub-tree
     */
    public void setBalance( char newBalance )
    {    
        balanceFactor = newBalance;
    }


}






