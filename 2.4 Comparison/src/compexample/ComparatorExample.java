package compexample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * A GUI for a book of names, emails, phone numbers, and favorite colors.
 * 
 * @author rayhe
 * 
 */
public class ComparatorExample {

	// constants
	private final int COLUMN_WIDTH = 150;
	public static final String ASCENDING = "ascending";
	public static final String DESCENDING = "descending";
	// class variables - note that some declarations are made here for simplicity
	private JFrame mainFrame = new JFrame("Comparator Example GUI - Tests public 'class Person implements comparable'") {
		private static final long serialVersionUID = 60920320061L;

		// anti-aliasing code
		public void paint(Graphics someG) {
			Graphics2D myG = (Graphics2D) someG;
			myG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
					RenderingHints.VALUE_ANTIALIAS_ON);
			super.paint(myG);
		}
	};
	private JTable dataTable;
	private JScrollPane scrollPane;
	private ArrayList<Person> people = new ArrayList<Person>();
	private PersonTableModel personTableModel = new PersonTableModel(people);
	private JPanel controlPanel = new JPanel();
	private JButton addPerson = new AntiAliasJButton("Add Person");
	private JButton removePerson = new AntiAliasJButton("Remove Person");
	private JButton sort = new AntiAliasJButton("Sort Table");
	private JDialog ccDialog = new JDialog() {
		private static final long serialVersionUID = 60920320064L;

		// anti-aliasing code
		public void paint(Graphics someG) {
			Graphics2D myG = (Graphics2D) someG;
			myG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
					RenderingHints.VALUE_ANTIALIAS_ON);
			super.paint(myG);
		}
	};
	private JComboBox columnSelect = new JComboBox(Person.getAllFields().toArray());
	private String[] sortOrders = { ASCENDING, DESCENDING };
	private JComboBox sortOrder = new JComboBox(sortOrders);

	/**
	 * Constructor creates the GUI, loading the default table data, and creates all the containers/components contained
	 * by the GUI.
	 */
	public ComparatorExample() {
		controlPanel.add(addPerson);
		controlPanel.add(removePerson);
		controlPanel.add(new AntiAliasJLabel("sort by:"));
		controlPanel.add(columnSelect);
		controlPanel.add(new AntiAliasJLabel("order:"));
		controlPanel.add(sortOrder);
		controlPanel.add(sort);
		// make the buttons execute methods in the class when clicked
		addPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPerson();
			}
		});
		removePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePerson();
			}
		});
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort();
			}
		});

		dataTable = new JTable(personTableModel);
		// sets a specific renderer for JButtons, which is what is used to display the colored Color cells
		TableCellRenderer defaultRenderer = dataTable.getDefaultRenderer(JButton.class);
		dataTable.setDefaultRenderer(JButton.class, new ExtendedTableRenderer(defaultRenderer));
		// add the pass-through table-to-underlying-buttons listener, which is in
		// ExtendedTableRender.java
		dataTable.addMouseListener(new JTableButtonMouseListener(dataTable));
		// make all the string fields wider, so there's less overlap
		for (int i = 0; i < Person.NUM_FIELDS; i++) {
			if (i == Person.COL_COLOR)
				continue;
			dataTable.getColumnModel().getColumn(i).setPreferredWidth(COLUMN_WIDTH);
		}
		// loads the example people directly so we have something to sort from the getgo
		loadExamplePeople();
		// makes the table scrollable, in case we overflow
		scrollPane = new JScrollPane(dataTable);
		// use an easy-to-use/understand layoutmanager known as borderlayout
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(controlPanel, BorderLayout.NORTH);
		mainFrame.add(scrollPane, BorderLayout.CENTER);
		try {
			// make the GUI look like the operating system that it is being run on
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(ccDialog);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		mainFrame.setSize(800, 500);
		// actually close the dialog and the frame when a close command is entered
		// prevents large programs from persisting in memory
		ccDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
		// mainFrame.pack();
	}

	/**
	 * loadExamplePeople loads people into the database directly, with method calls
	 * 
	 */
	private void loadExamplePeople() {
		people.add(new Person("Quiche", "Lorraine", "617-253-1000", "quiche@scotty.mit.edu", Color.RED, 25, this));
		people.add(new Person("Alice", "Whacker", "617-253-7788", "aw@scotty.mit.edu", Color.RED.darker().darker(),
				32, this));
		people.add(new Person("Zygorthian", "Space-Raiders", "617-253-1541", "zsr@scotty.mit.edu",
				new Color(50, 70, 90), 23, this));
		people.add(new Person("Opus", "", "617-253-1000", "opus@scotty.mit.edu", Color.RED.darker(), 27, this));
		people.add(new Person("Rick", "Chang", "617-232-3257", "rick@mit.edu", Color.RED.darker().darker().darker(),
				51, this));
		people.add(new Person("Ben", "Bitdiddle", "303-494-4774", "bitdiddle@scotty.mit.edu", new Color(56, 50, 50),
				47, this));

	}

	/**
	 * Adds a person, called by the add button, basically adds a "blank" person, who can then be editted
	 * 
	 */
	private void addPerson() {
		people.add(new Person(this));
		// fire the listeners so the screen updates
		// when addPerson is called (by the button click)
		personTableModel.fireTableRowsInserted(0, 0);
	}

	/**
	 * Pretty much the same as addPerson, but it removes the person
	 */
	private void removePerson() {
		// can't remove a value if there's nothing there
		if (dataTable.getSelectedRow() == -1)
			return;
		people.remove(dataTable.getSelectedRow());
		personTableModel.fireTableRowsDeleted(dataTable.getSelectedRow(), dataTable.getSelectedRow());
	}

	/**
	 * sorts the list using Collections.sort, the list of people, and the PeopleComparator written by you!
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void sort() {
		Collections.sort(people,
				new PeopleComparator(columnSelect.getSelectedIndex(), (String) sortOrder.getSelectedItem()));
		personTableModel.fireTableDataChanged();
	}

	/**
	 * Used by person (taking a callback and the default dialog color) to ask the user for a new color, which it then
	 * sets by calling back person
	 * 
	 * @param A
	 *            valid object of class Person
	 * @param A
	 *            valid color
	 */
	void setColor(Person p, Color c) {
		Color newc = JColorChooser.showDialog(ccDialog, "Choose a color", c);
		if (newc != null)
			p.setColor(newc);
	}

	/**
	 * The data model for the table. This class controls how things are displayed, set, etc.
	 * 
	 * @author rayhe
	 * 
	 */
	private class PersonTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 60920320065L;

		private List<Person> people;

		/**
		 * 
		 * @param people
		 *            - the list from ComparatorExample for inquiries/to keep in sync
		 */
		public PersonTableModel(List<Person> people) {
			this.people = people;
		}

		public int getRowCount() {
			return people.size();
		}

		public int getColumnCount() {
			return Person.NUM_FIELDS;
		}

		/**
		 * relies on Person class to determine number of fields, what they say and what they do when edited.
		 * 
		 * @param the
		 *            column, to match up with Person.getFieldColumn(the index)
		 * @return either the string that matches the column in person.getFieldColumn or an empty string (shouldn't
		 *         happen).
		 */
		public String getColumnName(int columnIndex) {
			if (Person.getFieldColumn(columnIndex) != null)
				return Person.getFieldColumn(columnIndex);
			return "";
		}

		@SuppressWarnings({ "unchecked" })
		public Class getColumnClass(int columnIndex) {
			return Person.getClass(columnIndex);
		}

		/**
		 * Evertying except the color, which is a special button
		 * 
		 * @return whether a !cell is the color cell or not
		 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex != Person.COL_COLOR)
				return true;
			return false;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			return people.get(rowIndex).getField(columnIndex);
		}

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if (columnIndex != Person.COL_COLOR) {
				people.get(rowIndex).setField(columnIndex, (String) aValue);
			}
		}

	}

	public static void main(String[] args) {
		new ComparatorExample();
	}
}
