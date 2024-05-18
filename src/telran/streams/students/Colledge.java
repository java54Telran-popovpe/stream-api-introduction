package telran.streams.students;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Colledge implements Iterable<Student> {
	Student[] students;
	public Colledge( Student[] students) {
		this.students = Arrays.copyOf(students, students.length);
	}
	
	private class ColledgeIterator implements Iterator<Student> {
		private int index = 0;
		@Override
		public boolean hasNext() {
			return index < students.length;
		}

		@Override
		public Student next() {
			if( !hasNext())
				throw new NoSuchElementException();
			return students[ index++ ];
		}
		
	}
	
	@Override
	public Iterator<Student> iterator() {
		return new ColledgeIterator();
	}

}
