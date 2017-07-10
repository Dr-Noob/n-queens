package reinas_concurrente;

public class Semilla {

	private int N;
	private int semillasUsadas;
	
	public Semilla(int N, int ncores) {
		this.N = N;
		this.semillasUsadas = ncores;
	}
	
	public synchronized int getSiguienteSemilla() {
		if(this.N % 2 == 0 && this.semillasUsadas < this.N/2 || this.N % 2 == 1 && this.semillasUsadas < this.N/2+1) {
			this.semillasUsadas++;
			return this.semillasUsadas-1;
		}
		return -1;
	}
	
}

