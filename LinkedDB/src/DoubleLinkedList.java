public class DoubleLinkedList {

	private DoubleNode start;
	private DoubleNode end;
	private int largo;

	public DoubleLinkedList(){
		this.start=null;
		this.end=null;
		largo = 0;
	}
	public void add(int valor){

		DoubleNode newDoubleNode = new DoubleNode();
		newDoubleNode.setDato(valor);

		if ((start==null) && (end==null)){
			start=newDoubleNode;
			end = start;
		}
		else{
			DoubleNode current = end;
			current.setNext(newDoubleNode);
			newDoubleNode.setPrev(current);
			end = newDoubleNode;

		}

		largo++;

	}

	public void insert(int valor, int pos){

		DoubleNode newDoubleNode = new DoubleNode();
		newDoubleNode.setDato(valor);

		if (pos>(largo)){
			System.out.println("Error: Excedió el largo de la lista");
		}
		else if (pos==0){
			newDoubleNode.setNext(this.start);
			this.start.setPrev(newDoubleNode);
	        this.start = newDoubleNode;
		}
		else if (pos==this.largo){
			this.end.setNext(newDoubleNode);
			newDoubleNode.setPrev(this.end);
	        this.end = newDoubleNode;
		}
		else{
			DoubleNode current = start;
			for (int i = 0; i < pos-1; i++){
				current = current.getNext();
			}
			DoubleNode next = current.getNext();
			newDoubleNode.setNext(next);
			next.setPrev(newDoubleNode);
			current.setNext(newDoubleNode);
			newDoubleNode.setPrev(current);
		}
		largo++;

	}

	public int getLargo() {
		return largo;
	}

	public void delete(int valor, int pos){
		if (pos>=(largo)){
			System.out.println("Error: Excedió el largo de la lista");
		}
		else if (pos == 0) {
			DoubleNode exStart = start;
			exStart.setNext(null);
			start = start.getNext();
			start.setPrev(null);
		}
		else if (pos == this.largo-1) {
			DoubleNode newEnd = end.getPrev();
			newEnd.setNext(null);
			end.setPrev(null);
			end=newEnd;
		}
		else {
			DoubleNode current = start;
			for (int i = 0; i < pos-1; i++){
				current = current.getNext();
			}
			DoubleNode next = current.getNext();
			DoubleNode nextNext = next.getNext();
			current.setNext(nextNext);
			nextNext.setPrev(current);
			next.setNext(null);
			next.setPrev(null);
		}
		largo--;
	}

	public void display() {
		if((this.start == null)&&(this.end == null)){
			return;
		}
        DoubleNode current = start;
        while (current != null) {
            System.out.println(current.getDato());
            current = current.getNext();
        }

	}

    public DoubleNode find(int valor) {
    	DoubleNode current = start;
        while (current != null) {
            if (current.getDato()==valor) {
                return current;
            } else {
                current = current.getNext();
            }
        }
        return null;

	}

}
