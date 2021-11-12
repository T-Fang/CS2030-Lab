import java.util.Scanner;

class Main6{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		Cruise[] cruises = new Cruise[num];
		String id;
		int arrivalTime;
		int len;
		int passenger;
		Loader[] loaders = new Loader[270];

		for(int i = 0; i < 270; i++){
			if((i % 3) == 2) {
				loaders[i] = new RecycledLoader(i + 1);
				// System.out.println("Recycled Created");
			}
			else loaders[i] = new Loader(i + 1);
		}
		
		
		for(int i = 0; i < num; i++){
			id = sc.next();
			arrivalTime =  sc.nextInt();
			if(id.charAt(0) == 'S') cruises[i] = new SmallCruise(id, arrivalTime);
			else {
				len = sc.nextInt();
				passenger = sc.nextInt();
				cruises[i] = new BigCruise(id, arrivalTime, len, passenger);
			}
		}
		
		for(int i = 0; i < num; i++){
			// System.out.println("Required " + cruises[i].getNumOfLoadersRequired());
			for(int k = 0; k < cruises[i].getNumOfLoadersRequired(); k++){
				for(int j = 0; j < 270; j++){
					if(loaders[j].canServe(cruises[i])){
						loaders[j] = loaders[j].serve(cruises[i]);
						System.out.println(loaders[j]);
						break;	
					}
				}

			}
		}
	}
}
