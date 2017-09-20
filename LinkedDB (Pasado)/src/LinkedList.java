public class LinkedList {

	public Node root;
	private int largo;

	public LinkedList(){
		this.root=null;
		largo = 0;
	}



	public void add(Object valor){

		Node newNode = new Node();
		newNode.setDato(valor);

		if (root==null){
			root = newNode;
		}
		else{
			Node current = root;
			while(current.getNext()!=null){
				current = current.getNext();

			}

			current.setNext(newNode);

		}

		largo++;

	}

	public void insert(Object valor, int pos){

		Node newNode = new Node();
		newNode.setDato(valor);

		if (pos>(largo)){
			System.out.println("Error: Excedió el largo de la lista");
		}

		else if (pos==0){
			newNode.setNext(this.root);
	        this.root = newNode;
		}
		else{
			Node current = root;
			for (int i = 0; i < pos - 1; i++){
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}
		largo++;

	}

	public int getLargo() {
		return largo;
	}

	public void delete(int pos){


		if (pos>=(largo)){
			System.out.println("Error: Excedió el largo de la lista");
		}

		else if (pos == 0) {
			root = root.getNext();
		}
		else{
			Node current = root;
			for (int i = 0; i < pos - 1; i++){
				current = current.getNext();

			}
			Node next = current.getNext();
			current.setNext(next.getNext());
			next.setNext(null);
		}
		largo--;

	}

	public void display() {
		if(this.root == null){
			return;
		}
        Node current = root;
        while (current != null) {
            System.out.println(current.getDato());
            current = current.getNext();
        }

	}
    public Node find(Object valor) {
        Node current = root;
        while (current != null) {
            if (current.getDato()==valor) {
                return current;
            } else {
                current = current.getNext();
            }
        }
        return null;

	}

	public static void main(String[] args){
        LinkedList l = new LinkedList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.display();
        l.delete(0);
        System.out.println();
        l.display();
        System.out.println();
        l.add(4);
        l.add(5);
        l.add(6);

        l.display();
        System.out.println();
        l.delete(4);
        l.display();
        System.out.println(l.find(6));
        System.out.println();
        System.out.println();
        l.insert(6, 4);
        l.display();
        System.out.println();
        l.delete(3);
        l.display();
	}

}
