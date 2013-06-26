package examples.collections;

public class LocalClassExample {
	private final int a = 1;
	private int b; // can't use in local class

	public int doSomething() {
		class LocalClass {
			private final int c = 2;

			public LocalClass() {
			};

			public int localMethod() {
				return a + c;
			}
		}
		LocalClass local = new LocalClass();
		return local.localMethod() + 10;
	}

	public static void main(String[] args) {
		LocalClassExample lce = new LocalClassExample();
		System.out.println("a = " + lce.a);
		System.out.println("a+c+10 = " + lce.doSomething());
	}
}
