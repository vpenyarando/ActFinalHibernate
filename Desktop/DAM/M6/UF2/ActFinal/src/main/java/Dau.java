import java.util.Random;

public class Dau {
 private int cares;
 

public Dau() {
	this.cares=6;}

public int tirar() {
	Random rd= new Random();
	return rd.nextInt(cares)+1;
}

public int getCares() {
	return cares;
}

public void setCares(int cares) {
	this.cares = cares;
}
 
}
