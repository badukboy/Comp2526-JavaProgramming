package examples.collections;

public class InstanceClassExample {
	public static void main(String[] args) {
		System.out.println("a = " + OuterClass.a);

		OuterClass oc = new OuterClass();
		// OuterClass.MemberClass ocmc = oc.new MemberClass();
		// System.out.println("a + b + c = " + ocmc.add());
	}
}