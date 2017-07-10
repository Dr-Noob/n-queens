package reinas_concurrente;

public class Main {
	
	public static void main(String[] args) {
		
		if(args.length != 1) {
    		System.out.println("Por favor, especifica el valor de N");
    		return;
    	}
    	int N = 0;
    	try {
    		 N = Integer.parseInt(args[0]);
    	} catch(NumberFormatException e) {
    		System.out.println(args[0] + " no es un numero");
    		return;
    	}
		
		if(N == 1) {
			System.out.println("1");
			return;
		}
		
		int ncores = Runtime.getRuntime().availableProcessors();
		int a = 0;
		int Nmedios;
		if(N % 2 == 0)Nmedios = N/2;
		else Nmedios = N/2 + 1;
		long startTime = System.nanoTime();
		Semilla s = new Semilla(N,ncores);
		Hilo hilos[] = new Hilo[ncores];
	 
	
		while(a < ncores && a < Nmedios) {
			Hilo h = new Hilo(a,N,s);
			hilos[a] = h;
			h.start();
			a++;
		}
		
		try {
			for(int i=0; i < a; i++)hilos[i].join();
		} catch (InterruptedException e) {
			System.out.println("Error al unir los hilos");
		}
		
		int soluciones = 0;
		
		if(N % 2 == 0) {
			for(int i=0; i < a; i++)soluciones += hilos[i].getSoluciones();
			soluciones = soluciones * 2;
		}
		else {
			for(int i=0; i < a-1; i++)soluciones += hilos[i].getSoluciones();
			soluciones = soluciones * 2;
			soluciones += hilos[a-1].getSoluciones();
		}
		
		System.out.println("[N=" + N + "][" + soluciones + " soluciones]");
		long endTime = System.nanoTime();
        System.out.println("Tiempo invertido: " + (float)(endTime - startTime)/1000000000 + " s");
		
	}
}
