public class DoubleNode {

	private int dato = 0;
	private DoubleNode next = null;
	private DoubleNode prev = null;

	public DoubleNode getPrev() {
		return prev;
	}
	public void setPrev(DoubleNode prev) {
		this.prev = prev;
	}
	public int getDato() {
		return dato;
	}
	public void setDato(int dato) {
		this.dato = dato;
	}
	public DoubleNode getNext() {
		return next;
	}
	public void setNext(DoubleNode next) {
		this.next = next;
	}

}