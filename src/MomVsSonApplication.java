import java.util.*;

public class MomVsSonApplication {
	static Queue<Cake> shelf = new ArrayDeque<>(); // max size =7
	public static void main(String[] args) throws InterruptedException{
		MomCakeFactory mom = new MomCakeFactory();
		SonCakeConsumer son = new SonCakeConsumer();
		
		mom.start();
		Thread.sleep(1000);
		son.start();
		
		mom.join();
		son.join();
		
		System.out.println(shelf);
		
		
	}

}

////////////////////
//Producer / Publisher
class MomCakeFactory extends Thread{
	public Cake getCake() {
		return new Cake();
	}
	
	public void run() {
		while(MomVsSonApplication.shelf.size() < 7) { 
			MomVsSonApplication.shelf.add(getCake());
		}
	}
}

//Consumer / Subscriber
class SonCakeConsumer extends Thread{
	public void consumeCake(Cake cake) {
		System.out.println("I've just eatten\n\t"+ cake);
	}
	
	public void run() {
		while(MomVsSonApplication.shelf.size() > 0) { 
			consumeCake(MomVsSonApplication.shelf.poll());
		}
	}
}


// Resource
class Cake {
	static int count = 0;  // общее количество созданных пирогов
	private int weight;
	private int order;

	public Cake() {
		this.weight = 90 + new Random().nextInt(20);  //100g +-10
		this.order = ++count;
	}
	
	@Override
	public String toString() {
		return String.format("Cake [%d, %3d g]\n", order,weight);
	}	
}