package telran.streams;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SportLoto {

	public static void main(String[] args)  {
		
		try {
			int[] intArgs = getThreeIntegersFromArgs( args );
			LotoModel lotoModel = LotoModel.newInstance( intArgs[0], intArgs[1], intArgs[2] );
			int[] seeds = lotoModel.getSeedsWithoutStreams();
			printResult(seeds);
		} catch (LotoModelException e) {
			System.out.printf( "LotoModel object creation error:\n " + e.getMessage() );
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.printf( e.getMessage() );
		}

	}

	private static void printResult(int[] seeds) {
		System.out.println( "The results are:" );
		System.out.print(Arrays.stream(seeds).mapToObj( Integer::toString ).collect(Collectors.joining(", ")));
		System.out.println();
		
	}

	private static int[] getThreeIntegersFromArgs(String[] args) throws Exception {
		if ( args.length < 3 )
			throw new Exception( String.format("Given only %d command line arguments, should be 3.\n", args.length ));
		int[] result = new int[ 3 ];
		for ( int i = 0; i < 3; i++ ) {
			try {
				result[ i ] = Integer.parseInt( args[ i ] );
			} catch (NumberFormatException e) {
				throw new Exception( String.format("Error in command line argument #%d (given: %s): should be an integer number.", i + 1, args[ i ] ) );
			}
		}
		return result;
	}
}
