package telran.streams;

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
}
