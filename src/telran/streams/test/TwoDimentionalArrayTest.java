package telran.streams.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class TwoDimentionalArrayTest {
	int[][] array = {
			{ 10, 5, 6 },
			{ 20, 20, 20 },
			{ 10, 4 }
			
	};

	@Test
	void sortTest() {
		int[][]	expected = {
				{ 10, 4 },
				{ 10, 5, 6 },
				{ 20, 20, 20 }
		};
		assertArrayEquals(expected, arraysSort( array ));
	}
	@Test
	void sumTest() {
		assertEquals( 95, sumArrays(array));
		assertEquals(95, sumArrays2(array));
		assertEquals(95, sumArrays3(array));
		
	}
	
	@Test
	void summaryStatisticsTest() {
		IntSummaryStatistics iss = Arrays.stream(array).flatMapToInt( a -> Arrays.stream(a)).summaryStatistics();
	}

	private static int sumArrays3(int[][] ar) {
		return Arrays.stream(ar).flatMapToInt( a -> Arrays.stream(a)).sum();
	}
	private static  int sumArrays2(int[][] ar) {
		return Arrays.stream(ar).mapToInt(a-> Arrays.stream(a).sum()).sum();
	}
	
	private static int sumArrays(int[][] ar) {
		return Arrays.stream(ar).collect(Collectors.summingInt( a-> Arrays.stream(a).sum()));
	}
	private static int[][] arraysSort(int[][] ar) {
		return Arrays.stream( ar )
				.sorted((a ,b ) -> Integer.compare(Arrays.stream(a).sum(), Arrays.stream(b).sum()))
				.toArray( int[][]::new);
	}

}
