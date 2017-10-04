
public class BuildingType {

	public String name;
	public int numAtributtes;
	public String[] atributtes;
	public Object[] valoresR;
	public Object[] valoresNR;
	public int pos;

	public void addName(String name){
    	this.name = name;
    }
    public void numAtributtes(int num){
    	numAtributtes = num;
    }
    public void createAtributtes(){
    	atributtes = new String[numAtributtes];
    }
    public void addAtributte(String name, Object value, String requerido){
    	if (pos<atributtes.length){
    	atributtes[pos]=name;
    	pos++;
    	Object[] valores;
    	if (((valoresR==null)&&requerido.equals("si"))||((valoresNR==null)&&requerido.equals("no"))){
    		valores=new Object[1];
    		valores[0]=value;
    		if (requerido.equals("si"))
    			valoresR=valores;
    		else
    			valoresNR=valores;
    	}
    	else {
    		int j = 0;
    		switch (requerido){
    			case "si": valores = new Object[valoresR.length+1];
    				while (j<valoresR.length){
    					valores[j]=valoresR[j];
    					j++;
    				}
    				valores[valores.length-1]=value;
    				valoresR=valores;
    				break;
    			case "no": valores = new Object[valoresNR.length+1];
    				while (j<valoresR.length){
    					valores[j]=valoresNR[j];
    					j++;
    				}
    				valores[valores.length-1]=value;
    				valoresNR=valores;
    				break;
    			default: return;
    		}
    	}}
    }
    public static void main (String[] args){
    	BuildingType lol = new BuildingType();
    	lol.addName("carros");
    	lol.numAtributtes(3);
    	lol.createAtributtes();
    	lol.addAtributte("Nombre","","si");
    	lol.addAtributte("Carnet",0,"si");
    	lol.addAtributte("Numero",0,"no");
    	System.out.println(lol.atributtes[1]);
    }
}
