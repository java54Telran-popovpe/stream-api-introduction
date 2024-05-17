package telran.streams;

import java.util.Arrays;
import java.util.Random;

class LotoModel {
	private int from;
	private int to;
	private int numberOfSeeds;
	
	private LotoModel(int from, int to, int numberOfSeeds) {
		this.from = from;
		this.to = to;
		this.numberOfSeeds = numberOfSeeds;
	}

	public static LotoModel newInstance( int from, int to, int numberOfSeeds ) throws LotoModelException {
		if ( !(to > from) )
			throw new LotoModelException( "to", to, "Should be greater then argument <from>.");
		if ( !( numberOfSeeds <= Math.abs(to - from) && numberOfSeeds > 0 ) )
			throw new LotoModelException( "numberOfSeeds", numberOfSeeds, 
					String.format("For a given range [%d, %d] should be between 1 and %d.", from, to, to - from ) );
		return new LotoModel( from, to, numberOfSeeds );
	}
	
	public int[] getSeeds() {
		return new Random().ints(from, to + 1).distinct().limit(numberOfSeeds).toArray();
	}
	

	
	public int[] getSeedsWithoutStreams() {
		int[] result = {};
		int rangeLength = to - from + 1;
		int[] gotUniqueIntFromRange = {};
		while ( gotUniqueIntFromRange.length < numberOfSeeds ) {
			int randomIntegerFromRange = (int)Math.round( Math.random() * rangeLength ) + from;
			int i = Arrays.binarySearch(gotUniqueIntFromRange,randomIntegerFromRange);
			if ( i < 0 ) {
				int indexForAdding = Math.abs( i + 1 );
				int[] updatedArray = new int[ gotUniqueIntFromRange.length + 1];
				System.arraycopy(gotUniqueIntFromRange, 0, updatedArray, 0, indexForAdding );
				updatedArray[ indexForAdding ] = randomIntegerFromRange;
				System.arraycopy(gotUniqueIntFromRange, indexForAdding, updatedArray, indexForAdding + 1, gotUniqueIntFromRange.length - indexForAdding);
				gotUniqueIntFromRange = updatedArray;
				result = Arrays.copyOf(result, result.length + 1);
				result[ result.length - 1 ] = randomIntegerFromRange;
			}
		}
		return result;
	}
}
