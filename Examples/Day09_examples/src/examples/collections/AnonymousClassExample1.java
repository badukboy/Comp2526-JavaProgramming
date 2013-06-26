package examples.collections;

abstract class animal {
	abstract void talk();
};

class dog extends animal {
	@Override
	void talk() {
		System.out.println("Bark!");
	}
};

public class AnonymousClassExample1 {
	public static void main(String[] args) {
		new dog().talk();

		new animal() {
			@Override
			void talk() {
				System.out.println("Meow!");
			}
		}.talk();
	}
}
