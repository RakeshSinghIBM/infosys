import java.util.ArrayList;
import java.util.List;

public class FishEater {
	
	
	public static final int injectedSize = 10;
	
	public static int counter = 0;
	
	
	public static void main(String[] args) {
		FishEater eater = new FishEater();
		int runs = 0;
		int moves = 0;
		boolean inProgress = true;
		System.out.println(args[0]);
		String[] fishes = args[0].split("#");
		int firstEater = Integer.valueOf(fishes[0]); 
		System.out.println("I am eating first: "+firstEater);
		List<Integer> fishesReady = eater.convertToListOfInt(fishes[1]);
		List<Integer> remainingFishes = new ArrayList<>();
		System.out.println("Step-"+counter+": "+"Initial stage "+fishesReady);
		// Start eating
		while (inProgress) {
			List<Integer> newList = new ArrayList<>();
			
			int newEater = 0;
			boolean timeForMove = false;
			if(runs == 0) {
				newList = new ArrayList(fishesReady);
				newEater = eater.eat(fishesReady, firstEater,newList); runs++;
			} else {
				newList = new ArrayList(remainingFishes);
				newEater = eater.eat(remainingFishes, firstEater,newList);
			}
			if(newEater == firstEater) {
				timeForMove = true;
			} else {
				firstEater = newEater;
			}
			// Is this time for move
			if(timeForMove) {
				// which move
				if(newList.size() == 1) {
					// Delete move
					moves++; counter++;
					System.out.println("Step-"+counter+": "+"remove fish of size "+newList.remove(0)+" "+newList +"{ Move Number : "+moves+" }");
				} else {
					// Add move
					moves++; counter++;
					int fishToBeAdded = firstEater - 1;
					System.out.println("Step-"+counter+": "+"add normal fish of size "+fishToBeAdded+" "+newList +"{ Move Number : "+moves+" }");
					newList.add(fishToBeAdded);
					
				}
			}
			if(newList.size() == 0) {
				inProgress  =false;
			} else {
				remainingFishes = newList;
			}
		}
		System.out.println("Moves taken : "+moves);
	}
	
	// Eat a fish and return the new size
	public int eat (List<Integer> remainingFishes, int whoIsEating, List<Integer> updatedList) {
		// eat smallest one first
		int smallestFish = findSmallest(remainingFishes);
		if(whoIsEating > smallestFish) {
			counter ++;
			whoIsEating = whoIsEating + smallestFish;
			updatedList.remove(Integer.valueOf(smallestFish));
			System.out.println("Step-"+counter+": "+"eats "+smallestFish+" becomes "+whoIsEating+" while others are "+updatedList);
		}
		return whoIsEating;
	}
	
	public int findSmallest (List<Integer> remainingFishes) {
		int smallestFish = 0;
		for (int i = 0; i < remainingFishes.size(); i++) {
			if(i == 0) {
				smallestFish = Integer.valueOf(remainingFishes.get(i));
			} else if(Integer.valueOf(remainingFishes.get(i)) < smallestFish) {
				smallestFish = Integer.valueOf(remainingFishes.get(i));
			}
		}
		return smallestFish;
	}
	
	public List<Integer> convertToListOfInt (String fishes){
		String[] remainingFishes = fishes.split(",");
		List<Integer> remainingFishesSizes =  new ArrayList<>();
		for (int i = 0; i < remainingFishes.length; i++) {
			String fish = remainingFishes[i];
			remainingFishesSizes.add(Integer.valueOf(fish));
		}
		return remainingFishesSizes;
	}

}
