public class CircularDoubleLinkedList {

	private DoubleNode start;
	private DoubleNode end;
	private int largo;

	public CircularDoubleLinkedList(){
		this.start=null;
		this.end=null;
		largo = 0;
	}
	public void add(Object valor){

		DoubleNode newDoubleNode = new DoubleNode();
		newDoubleNode.setDato(valor);

		if ((start==null) && (end==null)){
			start=newDoubleNode;
			end = start;
			start.setNext(end);
			start.setPrev(end);
			end.setNext(start);
			end.setPrev(start);
		}
		else{
			DoubleNode current = end;
			current.setNext(newDoubleNode);
			newDoubleNode.setPrev(current);
			end = newDoubleNode;
			end.setNext(start);
			start.setNext(end);

		}

		largo++;

	}

	public void insert(Object valor, int pos){

		DoubleNode newDoubleNode = new DoubleNode();
		newDoubleNode.setDato(valor);

		if (pos>(largo)){
			System.out.println("Error: Excedió el largo de la lista");
		}
		else if (pos==0){
			newDoubleNode.setNext(this.start);
			this.start.setPrev(newDoubleNode);
	        this.start = newDoubleNode;
	        this.start.setPrev(end);
	        end.setNext(this.start);
		}
		else if (pos==this.largo){
			this.end.setNext(newDoubleNode);
			newDoubleNode.setPrev(this.end);
	        this.end = newDoubleNode;
	        this.start.setPrev(end);
	        end.setNext(this.start);
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

	public void delete(Object valor, int pos){
		if (pos>=(largo)){
			System.out.println("Error: Excedió el largo de la lista");
		}
		else if (pos == 0) {
			DoubleNode exStart = start;
			start = start.getNext();
			exStart.setNext(null);
			exStart.setPrev(null);
			start.setPrev(end);
			end.setNext(start);
		}
		else if (pos == this.largo-1) {
			DoubleNode newEnd = end.getPrev();
			newEnd.setNext(start);
			end.setPrev(null);
			end.setNext(null);
			end=newEnd;
			start.setPrev(end);
			end.setNext(start);
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
        do {
            System.out.println(current.getDato());
            current = current.getNext();
        } while (current != start);

	}

    public DoubleNode find(Object valor) {
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