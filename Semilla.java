package reinas_concurrente;

public class Semilla {

	private int N;
	private int semillasUsadas;
	private int a[][];
	
	public Semilla(int N, int ncores) {
		this.N = N;
		this.a = new int[N][N];
		this.semillasUsadas = ncores;
	}
	
	public synchronized int getSiguienteSemilla() {
		if(this.N % 2 == 0 && this.semillasUsadas < this.N/2 || this.N % 2 == 1 && this.semillasUsadas < this.N/2+1) {
			this.semillasUsadas++;
			return this.semillasUsadas-1;
		}
		return -1;
	}
	
	public synchronized void print(int s[]) {
		/*
		for(int i=0;i<N;i++)System.out.print("[" + s[i] + "]");
		System.out.println();
		*/
		this.a[s[0]][s[1]]++;
	}
	
	public void print2() {
		int nmedios;
		if(N % 2 == 0)nmedios = N/2;
		else nmedios = N/2 + 1;
		for(int i=0;i<nmedios;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(" " + a[i][j] + " ");
			}
			System.out.println();
		}
	}
}

