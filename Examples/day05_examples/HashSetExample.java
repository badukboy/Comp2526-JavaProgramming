import java.util.*;

public class HashSetExample {
	public static void main(String[] args) {
		HashSet set = new HashSet();
		set.add("This");
		set.add("is");
		set.add("is");
		set.add("a");
		set.add("a");
		set.add("test");
		
		CollectionUtil.display(set);
	}
}
