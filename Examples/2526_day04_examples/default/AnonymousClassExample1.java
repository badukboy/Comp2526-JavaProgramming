abstract class animal {
	abstract void talk();
};

class dog extends animal {
	void talk() { System.out.println("Bark!"); }
};

public class AnonymousClassExample1 {
	public static void main (String[] args) {
		new dog().talk();
    
		new animal() { 
			void talk() {System.out.println("Meow!");}
		}.talk();
  }
}
