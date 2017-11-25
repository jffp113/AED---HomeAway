package tests;

import static org.junit.Assert.*;

import java.util.Random;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataStructures.AVLTree;

import dataStructures.BinarySearchTree;
import dataStructures.Entry;
import dataStructures.Iterator;

import dataStructures.OrderedDoubleList;

public class BSTIteratorTest {

	BinarySearchTree<Integer,Integer> ea;
	
	@Before
	public void setUp() {
		ea = new AVLTree<Integer,Integer>();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test//(timeout = 1000)
	public void ramdomTestTest() {
		Iterator<Entry<Integer,Integer>> it;
		Iterator<Entry<Integer,Integer>> it2;
		OrderedDoubleList<Integer, Integer> st = new OrderedDoubleList<Integer,Integer>();
		Random rd = new Random();
		int addNumber = 1000;
		int remNumber = 100;
		int vect[] = new int[addNumber];
		int rdInt;
		for(int i = 0; i < addNumber ; i++) {
			rdInt = rd.nextInt();
			st.insert(rdInt, rdInt); ea.insert(rdInt, rdInt);
			vect[i] = rdInt;
		}
		
		it = ea.iterator();
		it2 = st.iterator();
		
		for(int i = 0; i < remNumber; i++) {
			rdInt = rd.nextInt();
			if(rdInt < 0)
				rdInt = rdInt*-1;
			
			st.remove(vect[rdInt % addNumber]);ea.remove(vect[rdInt % addNumber]);
			System.out.println(vect[rdInt % addNumber]);
		}
		
		while(it2.hasNext()) {
			assertEquals(it2.next().getValue(),it.next().getValue());
		}
		assertFalse(it.hasNext());
	}
	
}
