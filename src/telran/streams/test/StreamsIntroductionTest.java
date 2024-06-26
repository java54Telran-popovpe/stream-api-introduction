package telran.streams.test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.streams.StreamsIntroductionMethods.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.streams.MinMaxAvg;

class StreamsIntroductionTest {

	@Test
	void test() {
		int[] ar = { 1,2,3,4,5,6 };
		
		assertEquals(12, sumIf(ar, n -> n %2 == 0 ) );

	}
	@Test
	void sumDistinctTest() {
		int[] ar = { 1, 2, 10, 1, 1 };
		assertEquals(13, sumDistinct( ar ) );

	}
	
	@Test
	void maxIfTest() {
		int[] ar = { 1, 7, -1, 23, 13, 101 };
		assertEquals(101, maxIf(ar, n -> n % 2 != 0 ) );
		assertThrowsExactly(NoSuchElementException.class, () -> maxIf(ar, n -> n % 2 == 0 ) );
		
	}
	
	@Test
	void sortDistinctTest() {
		int[] ar = { 1, 10, 1, 1, 2 };
		int[] expected = { 1, 2, 10 };
		assertArrayEquals(expected, sortDistinct(ar));
	}
	
	@Test
	void forEachIfTest() {
		int[] ar = { 1, 10, 1, 1, 2 };
		forEachIf(ar,  n -> n % 2 != 0, System.out::println);
	}
	
	@Test
	void getRandomArrayTest() {
		int ar[] = getRandomArray(10, 12, 1000);
		assertEquals(1000, ar.length);
		assertTrue( Arrays.stream(ar).allMatch(n -> n >= 10 && n < 12));
		assertEquals(21, sumDistinct(ar));
	}
	
	@Test
	void displayShuffleTest() {
		int[] arr = { -10, 45, 32, 48, 100, 32 }; 
		for ( int i = 0; i < arr.length; i++ )
			displayShuffle( arr ); 
		arr = new int[]{ 23 };
		displayShuffle( arr ); 
		arr = new int[]{ };
		displayShuffle( arr );
	}
	
	
	
	
	@Test
	void minMaxAvgTest() {
		int[] ar = {12, -45, 56, 78, 67, 32, 0, -4 , -87};
		Arrays.sort(ar);
		MinMaxAvg result = getMinMaxAvg(ar);
		assertEquals(ar[0], result.min());
		assertEquals(ar[ar.length - 1], result.max());
		assertEquals( Arrays.stream(ar).sum() / (double) ar.length, result.avg());
		
		ar = new int[0];
		result = getMinMaxAvg(ar);
		assertEquals(Integer.MAX_VALUE, result.min());
		assertEquals(Integer.MIN_VALUE, result.max());
		assertEquals( 0, result.avg());
		
		
		
	}
}
