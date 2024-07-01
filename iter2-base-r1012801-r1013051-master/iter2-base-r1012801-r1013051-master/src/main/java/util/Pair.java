package util;

public class Pair<T, U> {
	
	/**
	 * @invar | first != null
	 * @invar | second != null
	 */
	private T first;
	
	private U second;
	
	/** documentation vide
	 * @post | result != null
	 */
	public T getFirst() {
		return first;
	}
	
	/** documentation vide
	 * @post | result != null
	 */
	public U getSecond() {
		return second;
	}
	
	/** documentation vide
	 * @throws IllegalArgumentException | first == null || second == null
	 * @post | getFirst() == first
	 * @post | getSecond() == second
	 */
	public Pair(T first, U second) {
		if (first == null || second == null) {throw new IllegalArgumentException(); }
		this.first = first;
		this.second = second;
	}
	/**
	 * ajout de la postcondition
	 * @mutates | this
	 * @post | getFirst() == first
	 * @post | getSecond() == old(getSecond())
	 */
	public void setFirst(T first) {
		this.first = first;
	}
	
	/**
	 * ajout de la postcondition
	 * @mutates | this
	 * @post | getFirst() == old(getFirst())
	 * @post | getSecond() == second
	 */
	public void setSecond(U second) {
		this.second = second;
	}

	
	/**
	 * methode ajoutée + docu
	 * @inspects | other
	 */
	public boolean isEqual(Pair<T, U> other) {
		return other.getFirst().equals(this.first) && other.getSecond().equals(this.second);
	}
	
		}

