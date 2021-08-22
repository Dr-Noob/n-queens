package nqueens;

public class Main {
	
	public static void main(String[] args) {
		
		if(args.length != 1) {
    		System.out.println("Please, specify the value of N");
    		return;
    	}
    	int N = 0;
    	try {
    		 N = Integer.parseInt(args[0]);
    	} catch(NumberFormatException e) {
    		System.out.println(args[0] + " is not a number");
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
		Seed s = new Seed(N,ncores);
		QueenThread hilos[] = new QueenThread[ncores];
	 
	
		while(a < ncores && a < Nmedios) {
			QueenThread h = new QueenThread(a,N,s);
			hilos[a] = h;
			h.start();
			a++;
		}
		
		try {
			for(int i=0; i < a; i++)hilos[i].join();
		} catch (InterruptedException e) {
			System.out.println("Failed joining threads");
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
		
		System.out.println("[N=" + N + "][" + soluciones + " solutions]");
		long endTime = System.nanoTime();
        	System.out.println("Elapsed time: " + (float)(endTime - startTime)/1000000000 + " s");
		
	}
}
