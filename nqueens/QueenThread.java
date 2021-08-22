package nqueens;

public class QueenThread extends Thread {
	
	private void generar() {
		s[nivel]++;
	}
	
	private boolean solucion() {
		if(nivel != _N)return false;
		for (int i = 0; i < _N; i++) {
            if (s[i] == s[_N])             return false;
            if ((s[i] - s[_N]) == (_N - i)) return false;
            if ((s[_N] - s[i]) == (_N - i)) return false;
        }
        return true;
	}
	
	private boolean criterio() {
		if(nivel == _N)return false;
		for (int i = 0; i < nivel; i++) {
            if (s[i] == s[nivel])             return false;
            if ((s[i] - s[nivel]) == (nivel - i)) return false;
            if ((s[nivel] - s[i]) == (nivel - i)) return false;
        }
		return true;
	}
	
	private boolean masHermanos() {
		return s[nivel] < _N;
	}
	
	private void retroceder() {
		s[nivel] = -1;
		nivel--;
	}
	
	private int N;
	private int _N;
	private int nivel;
	private int semilla;
	private int[] s;
	private long soluciones;
	private Seed servidor;
	
	public QueenThread(int semilla,int N,Seed servidor) {
		this.N = N;
		this._N = this.N-1;
    	this.s = new int[N];
    	this.nivel = 1;
		for(int i=0;i<this.N;i++)this.s[i] = -1;
		this.semilla = semilla;
		this.servidor = servidor;
	}
	
	public void run() {
		do {
			
			this.s[0] = this.semilla;
			this.nivel = 1;

			do {
				generar();
				if(solucion()) { this.soluciones++; }
				if(criterio())nivel++;
				else {
					while(nivel > 0 && !masHermanos())retroceder();
				}
			} while(nivel > 0);
			
			this.semilla = this.servidor.getSiguienteSemilla();
			
		} while(this.semilla != -1);
		
	}
	
	public long getSoluciones() {
		return this.soluciones;
	}
	
}
