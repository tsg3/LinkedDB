public class LinkedList {

	private Node root;
	private int largo;
	
	public LinkedList(){
		this.root=new Node();
		root.setDato(0);
		largo = 1;
	}

	

	public void add(int valor){
		
		Node newNode = new Node();
		newNode.setDato(valor);
		
		if (root.getNext()==null){
			root.setNext(newNode);			
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
	
	public void insert(int valor, int pos){
		
		Node newNode = new Node();
		newNode.setDato(valor);
		
		if (pos>(largo)){ //**********************************
			System.out.println("Error: Excedió el largo de la lista");
			return;
		}
			
		Node current = root;
		for (int i = 0; i < pos; i++){
			current = current.getNext();
			
		}
		
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		
		largo++;
		
	}
	
	public int getLargo() {
		return largo;
	}
	
	public void delete(int valor, int pos){
		
		
		if (pos>(largo)){
			System.out.println("Error: Excedió el largo de la lista");
			return;
		}
			
		Node current = root;
		for (int i = 0; i < pos; i++){
			current = current.getNext();
			
		}
		if (current.getNext() == null) {
			return;
		}
		Node next = current.getNext();
		current.setNext(next.getNext());
		next.setNext(null);
		
		largo--;
		
	}
	
}
