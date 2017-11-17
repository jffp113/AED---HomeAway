package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataStructures.BinarySearchTree;
import dataStructures.Entry;
import dataStructures.Iterator;

public class BSTIteratorTest {

	BinarySearchTree<Integer,Integer> ea;
	
	@Before
	public void setUp() {
		ea = new BinarySearchTree<Integer,Integer>();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void firtTest() {
		Iterator<Entry<Integer,Integer>> it;
		ea.insert(5, 5);
		ea.insert(1, 1);
		ea.insert(20, 20);
		ea.insert(21, 21);
		ea.insert(15, 15);
		ea.insert(9, 9);
		ea.insert(10, 10);
		ea.insert(3, 3);
		
		it = ea.iterator();
		
		assertEquals(new Integer(1),it.next().getKey());
		assertEquals(new Integer(3),it.next().getKey());
		assertEquals(new Integer(5),it.next().getKey());
		assertEquals(new Integer(9),it.next().getKey());
		assertEquals(new Integer(10),it.next().getKey());
		assertEquals(new Integer(15),it.next().getKey());
		assertEquals(new Integer(20),it.next().getKey());
		assertEquals(new Integer(21),it.next().getKey());
		assertFalse(it.hasNext());
	}
	
}
