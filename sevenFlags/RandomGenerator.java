/**
 * The RandomGenerator generating random numbers
 * @author Xiaomin Wu
 *
 */
public class RandomGenerator {
	/**
	 * generate random ride from input ride array with equal possibility
	 * @param rides
	 * input ride array
	 * @return Ride
	 * the ride chosen randomly
	 */
	public static Ride selectRide(Ride[] rides){
		int size =  rides.length;
		int random = (int)(size*Math.random());
		return rides[random];
	}
	/**
	 * generate random ride on input possibility
	 * @param rides
	 * input ride array
	 * @param probabilities
	 * input probabilities corresponding to each ride in position
	 * @return Ride
	 * the ride chosen randomly
	 * @throws WrongInputException
	 * indicate the input is Wrong
	 */
	public static Ride selectRide(Ride[] rides, double[] probabilities) throws WrongInputException{
		Ride chosen = null;
		double[] chances = new double[probabilities.length];
		if(rides.length != probabilities.length){
			throw new WrongInputException("Wrong input!");
		}else{
			double random = Math.random();
			for(int i = 0; i < probabilities.length;i++){
				chances[i] = addUp(probabilities,i);
			}
			
			
			for(int i = 0; i < rides.length;i++){
			
				if(i == 0){
					if(random >= 0 && random < chances[i]){
						chosen = rides[i];
					}
				}else{
					if(random >= chances[i-1] && random < chances[i]){
						chosen = rides[i];
					}
				}
				
			}
			
			return chosen;
			
		}
	}
	/**
	 * support function for adding probabilities till input index up.
	 * @param probabilities
	 * @param index
	 * @return
	 */
	public static double addUp(double[] probabilities, int index){
		double sum = 0;
		for(int i = 0; i <=index; i++){
			sum = sum + probabilities[i];
		}
		return sum;
	}
}
