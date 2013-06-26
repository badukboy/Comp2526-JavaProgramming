package examples.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompareSortExample {

	public static void main(String[] args) {
		new CompareSortExample();
	}

	public CompareSortExample() {
		String[] s = { "Leopard", "Deer", "Cow", "Cat", "Cougar", "Lion", "Dog" };

		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < s.length; i++) {
			list.add(s[i]);
			System.out.println(s[i]);
		}
		System.out.println("--------------------------");
		Collections.sort(list);
		CollectionUtil.display(list);

		System.out.println("--------------------------");
		CompareByLength cs = new CompareByLength();
		Collections.sort(list, cs);
		CollectionUtil.display(list);
	}

	// sorting helper
	private class CompareByLength implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			// our sorting criteria is length of string
			int len1 = s1.length();
			int len2 = s2.length();
			return (len1 - len2);
		}
	}
}
