package compexample;

import java.util.Comparator;

public class PeopleComparator implements Comparator<Person> {
	private String sortOrder;
	private int sortColumn;

	public PeopleComparator(int sortColumn, String sortOrder) {
		// Your Job (*hint* you probably want to store the two arguments)
		// The first argument takes a column that matches up with the
		// constants Person.COL_LASTNAME, Person.COL_FIRSTNAME,
		// Person.COL_PHONE, Person.COL_EMAIL, and Person.COL_COLOR
		// The second matches ComparatorExample.ASCENDING and
		// ComparatorExample.DESCENDING (two string constants).
		this.sortColumn = sortColumn;
		this.sortOrder = sortOrder;
	}

	public int compare(Person o1, Person o2) {
		// Your Job (you must cast o1 and o2 to Person, but
		// make sure to check that they are indeed instanceof Person)
		// This MUST follow the Comparator interface.
		// To compare the colours, try using getColor() in
		// class Person. To compare the other fields, note
		// that many things are already comparable, using
		// someObj1.compareTo(someObj2)

		// Sort in ascending order
		if (sortOrder == ComparatorExample.ASCENDING) {
			// Sort by the amount of red in the colour
			if (sortColumn == 4) {
				// Return 1 if there's less red
				if (o1.getColor().getRed() < o2.getColor().getRed()) {
					return 1;
				} else if (o1.getColor().getRed() == o2.getColor().getRed()) {
					return 0;
				} else {
					return -1;
				}
			} else if (sortColumn == 5) {
				if (o1.getAge() > o2.getAge()) {
					return 1;
				} else if (o1.getAge() == o2.getAge()) {
					return 0;
				} else {
					return -1;
				}
			} else {
				// Compare the strings, e.g. first name, etc.
				return ((String) o1.getField(sortColumn)).compareTo((String) o2.getField(sortColumn));
			}
		}
		// Otherwise sort in descending order
		else {			
			if (sortColumn == 4) {
				if (o1.getColor().getRed() > o2.getColor().getRed()) {
					return 1;
				} else if (o1.getColor().getRed() == o2.getColor().getRed()) {
					return 0;
				} else {
					return -1;
				}
			} else if (sortColumn == 5) {
				if (o1.getAge() < o2.getAge()) {
					return 1;
				} else if (o1.getAge() == o2.getAge()) {
					return 0;
				} else {
					return -1;
				}
			} else {
				return ((String) o2.getField(sortColumn)).compareTo((String) o1.getField(sortColumn));
			}
		}

	}

}
