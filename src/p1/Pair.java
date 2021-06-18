package p1;

public class Pair<F, S> {
	public F first; // declare first element
	public S second; // declare second element

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public Pair() {
	}

	static <F, S> Pair<F, S> of(F first, S second) {
		return new Pair<F, S>(first, second); // allocation
	}
}