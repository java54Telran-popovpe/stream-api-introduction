package telran.streams.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import telran.streams.Trader;
import telran.streams.Transaction;

class TradersTransactionTest {
	Trader raoul = new Trader("Raoul", "Cambridge");
	Trader mario = new Trader("Mario","Milan");
	Trader alan = new Trader("Alan","Cambridge");
	Trader brian = new Trader("Brian","Cambridge");
	List<Transaction> transactions = Arrays.asList(
	new Transaction(brian, 2011, 300),
	new Transaction(raoul, 2012, 1000),
	new Transaction(raoul, 2011, 400),
	new Transaction(mario, 2012, 710),
	new Transaction(mario, 2012, 700),
	new Transaction(alan, 2012, 950));

	@Test
	void test() {
		transactions.stream()
			.filter( t -> t.year() == 2011)
			.sorted(Comparator.comparing(Transaction::value))
			.forEach(System.out::println);
		
		transactions.stream()
			.map( t -> t.trader().city() )
			.distinct()
			.forEach(System.out::println);
		transactions.stream()
			.map(Transaction::trader)
			.filter( t -> t.city().equals( "Cambridge" ))
			.distinct()
			.sorted(Comparator.comparing(Trader::name))
			.forEach(System.out::println);
		
		
		System.out.println( transactions.stream()
			.anyMatch( t -> t.trader().city().equals("Milan")) ? "True" : "False");
		
		transactions.stream()
			.filter(t -> t.trader().city().equals("Cambridge"))
			.forEach( t -> System.out.println(t.value()));
		
		transactions.stream()
			.map(Transaction::value)
			.max(Integer::compare)
			.ifPresent(System.out::println);
		
		transactions.stream()
			.max(Comparator.comparing(Transaction::value)).
			ifPresent( t -> System.out.println(t.value()));
			
		
		transactions.stream()
			.min(Comparator.comparing(Transaction::value))
			.ifPresent(System.out::println);
			
			
	}
		

}
