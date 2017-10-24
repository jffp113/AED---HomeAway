package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;

public class LinkedListTest {

	DoublyLinkedList<Integer> toAttach;
	DoublyLinkedList<Integer> list;
	
	@Before
	public void init() {
		toAttach = new DoublyLinkedList<Integer>();
		list = new DoublyLinkedList<Integer>();
		
		toAttach.addLast(1);
		toAttach.addLast(2);
		toAttach.addLast(3);
		toAttach.addLast(4);
	}
	
	@Test
	public void testAttachEmpty() {
		list.append(toAttach);
		Iterator<Integer> it = list.iterator();
		int i = 1;
		while(it.hasNext()) {
			assertEquals(it.next(),new Integer(i++));
		}
		Iterator<Integer> finalIt = toAttach.iterator();
		
		assertFalse(finalIt.hasNext());
		
	}
	
	@Test
	public void testAttach() {
		list.addLast(-1);
		list.addLast(0);
		list.append(toAttach);
		
		Iterator<Integer> it = list.iterator();
		int i = -1;
		while(it.hasNext()) {
			assertEquals(it.next(),new Integer(i++));
		}
		Iterator<Integer> finalIt = toAttach.iterator();
		
		assertFalse(finalIt.hasNext());
		
	}
	

}
