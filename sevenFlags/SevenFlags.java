import java.util.ArrayList;
import java.util.Scanner;

/**
 * program runs the simulation
 * @author Xiaomin Wu
 *
 */
public class SevenFlags {

	private static ArrayList<Person> RegularCustomers = new ArrayList<Person>();
	private static int regularCustomerAmount;
	private static ArrayList<Person> SilverCustomers = new ArrayList<Person>();
	private static int silverCustomerAmount;
	private static ArrayList<Person> GoldCustomers = new ArrayList<Person>();
	private static int goldCustomerAmount;
	
	private static Ride BSOD = new Ride("BSOD");
	private static Ride KK = new Ride("KK  ");
	private static Ride i386 = new Ride("TOT ");
	private static Ride GF = new Ride("GF  "); 
	private static Ride[] Rides = new Ride[4];
	private static double[] RP = new double[4]; 
	
	private static int simulationLength;
	private static int time = 0;
	private static Scanner input = new Scanner(System.in);
	
	/**
	 * main simulator
	 * @throws  
	 * @throws FullLineException 
	 */
	public static void main(String[] args){
		while(true){
		try{
		System.out.println("Welcome to Seven Flags!");
		System.out.println("Please enter the number of regular customers: ");
		regularCustomerAmount = input.nextInt();
		System.out.println("Please enter the number of silver customers: ");
		silverCustomerAmount = input.nextInt();
		System.out.println("Please enter the number of gold customers: ");
		goldCustomerAmount = input.nextInt();
		System.out.println("Please enter simulation length:");
		simulationLength = input.nextInt();
		

		System.out.println("Please enter the duration of Blue Scream of Death (minutes):");
		BSOD.setDuration(input.nextInt());
		System.out.println("Please enter the capacity of Blue Scream of Death:");
		BSOD.setCapacity(input.nextInt());
		System.out.println("Please enter the holding queue size for Blue Scream of Death:");
		BSOD.getHoldingQueue().setMaxSize(input.nextInt());
		

		System.out.println("Please enter the duration of Kingda Knuth (minutes):");
		KK.setDuration(input.nextInt());
		System.out.println("Please enter the capacity of Kingda Knuth:");
		KK.setCapacity(input.nextInt());
		System.out.println("Please enter the holding queue size for Kingda Knuth:");
		KK.getHoldingQueue().setMaxSize(input.nextInt());
		
		System.out.println("Please enter the duration of i386 Tower of Terror (minutes): ");
		i386.setDuration(input.nextInt());
		System.out.println("Please enter the capacity of i386 Tower of Terror:");
		i386.setCapacity(input.nextInt());
		System.out.println("Please enter the holding queue size for i386 Tower of Terror: ");
		i386.getHoldingQueue().setMaxSize(input.nextInt());
		
		System.out.println("Please enter the duration of GeForce (minutes):");
		GF.setDuration(input.nextInt());
		System.out.println("Please enter the capacity of GeForce:");
		GF.setCapacity(input.nextInt());
		System.out.println("Please enter the holding queue size for GeForce: ");
		GF.getHoldingQueue().setMaxSize(input.nextInt());
		
		System.out.println("Please input the posibility of each Ride to be chosen, sum ,must be 1 and in format:\n"
				+ "BSOD KK i386 GF");
		double sum = 0;
		for(int i = 0; i < 4; i ++){
			RP[i] = input.nextDouble();
			sum = sum+RP[i];
		}
		
		if(sum != 1){
			throw new Exception();
		}
		
		break;
		}catch(Exception e){
			input.nextLine();
	
			System.out.println("Please enter correct type of input which is Int\n"
					+ "and plese make sure the probability you entered is summing to 1\n"
					+ "please do all of them again\n");
		
		}
		}
		
		//initial objects and lines
		
			try {
				initializePerson();
			} catch (WrongInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FullLineException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		
		initializeRides();
		
		//update lines and rides
		try{
		for(Ride a: Rides){
			putHL(a);
		}
		
		for(Ride a: Rides){
			putOnRide(a);
		}
		
		for(Ride a: Rides){
			putHL(a);
		}
		}catch(Exception e){
			System.out.println("Problem");
		}
		
		//show condition as Time 0
		showStatus();
		
		while(simulationLength > 0){
			try {
				timeAdvance();
			} catch (WrongInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FullLineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EmptyLineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//show condition as time++
			showStatus();
			
		}
		
		//end of simulation 
		//show statistical data 
		//fetch counter in each objects ,persons and rides.
		double GS =0;
		for(Person a: GoldCustomers){
			GS = GS + a.getTooked();
		}
		
		double SS = 0;
		for(Person a: SilverCustomers){
			SS = SS + a.getTooked();
		}
		
		double RS = 0;
		for(Person a: RegularCustomers){
			RS = RS + a.getTooked();
		}
		
		System.out.println("On average, Gold customers have taken "+ GS/goldCustomerAmount+" rides.\n"
				+ "On average, Silver customers have taken "+SS/silverCustomerAmount+" rides.\n"
						+ "On average, regular customers have taken "+RS/regularCustomerAmount+" rides.\n\n"
								+ BSOD.getName()+" has completed rides for "+BSOD.getPersonPlayed()+" people.\n"
										+ KK.getName()+" has completed rides for "+KK.getPersonPlayed()+" people.\n"
												+ i386.getName()+" has completed rides for "+i386.getPersonPlayed()+" people.\n"
														+ GF.getName()+" has completed rides for "+GF.getPersonPlayed()+" people.\n");
		
		
		

	}
	
	/**
	 * initial Ride timeleft
	 */
	public static void initializeRides(){
		for(Ride a: Rides){
			a.setTimeLeft(a.getDuration());
		}
	}
	/**
	 * person choose ride 
	 * after that, person into rides' line
	 * @throws WrongInputException
	 * @throws FullLineException
	 */
	public static void initializePerson() throws WrongInputException, FullLineException{
		Rides[0] = BSOD;
		Rides[1] = KK;
		Rides[2] = i386;
		Rides[3] = GF;
		
		for(int i = 1; i <= regularCustomerAmount; i++){
			Person P = new Person(i);
			P.setMaxLines(1);
			P.setLevel("Regular");
			P.setStatus(Status.Available);
			P.getLines().add(RandomGenerator.selectRide(Rides,RP));
			RegularCustomers.add(P);
		}
		
		for(int i = 1; i <= silverCustomerAmount; i++){
			Person P = new Person(i);
			P.setMaxLines(2);
			P.setLevel("Silver");
			P.setStatus(Status.Available);
			P.getLines().add(RandomGenerator.selectRide(Rides,RP));
			P.getLines().add(RandomGenerator.selectRide(Rides,RP));
			SilverCustomers.add(P);
		}
		
		for(int i = 1; i <= goldCustomerAmount; i++){
			Person P = new Person(i);
			P.setMaxLines(3);
			P.setLevel("Gold");
			P.setStatus(Status.Available);
			P.getLines().add(RandomGenerator.selectRide(Rides,RP));
			P.getLines().add(RandomGenerator.selectRide(Rides,RP));
			P.getLines().add(RandomGenerator.selectRide(Rides,RP));
			GoldCustomers.add(P);
		}
		
		putPersonInVL();
		
	}
	/**
	 * deRidePerson
	 * find person and put its status to available
	 * and remove corresponding Ride 
	 * precondition:
	 * input person just finished ride
	 * @param P
	 */
	public static void deRidePerson(Person P, Ride R){
		for(int i = 0; i < regularCustomerAmount; i++){
			if(RegularCustomers.get(i).equals(P)){
				RegularCustomers.get(i).setStatus(Status.Available);
				RegularCustomers.get(i).getLines().remove(0);
				RegularCustomers.get(i).setTooked(RegularCustomers.get(i).getTooked()+1);
			}
		}
		
		for(int i = 0; i < silverCustomerAmount; i++){
			if(SilverCustomers.get(i).equals(P)){
				SilverCustomers.get(i).setStatus(Status.Available);
				if(SilverCustomers.get(i).getLines().get(0).equals(R)){
					SilverCustomers.get(i).getLines().remove(0);
					SilverCustomers.get(i).setTooked(SilverCustomers.get(i).getTooked()+1);
				}else{
					SilverCustomers.get(i).getLines().remove(1);
					SilverCustomers.get(i).setTooked(SilverCustomers.get(i).getTooked()+1);
				}
			}
		}
		
		for(int i = 0; i < goldCustomerAmount; i++){
			if(GoldCustomers.get(i).equals(P)){
				GoldCustomers.get(i).setStatus(Status.Available);
				if(GoldCustomers.get(i).getLines().get(0).equals(R)){
					GoldCustomers.get(i).getLines().remove(0);
					GoldCustomers.get(i).setTooked(GoldCustomers.get(i).getTooked()+1);
				}else if(GoldCustomers.get(i).getLines().get(1).equals(R)){
					GoldCustomers.get(i).getLines().remove(1);
					GoldCustomers.get(i).setTooked(GoldCustomers.get(i).getTooked()+1);
				}else{
					GoldCustomers.get(i).getLines().remove(2);
					GoldCustomers.get(i).setTooked(GoldCustomers.get(i).getTooked()+1);
				}
			}
		}
	}
	/**
	 * check all Person's list and make fill those person who has rides 
	 * below maxLine value
	 * and update person into those VL
	 * gold has higher priority
	 * @throws WrongInputException 
	 * @throws FullLineException 
	 */
	public static void fullFillAllPerson() throws WrongInputException, FullLineException{
		for(Person p : GoldCustomers){
			while(p.getLines().size() < p.getMaxLines()){
				p.getLines().add(RandomGenerator.selectRide(Rides,RP));
				putVL(p, p.getLines().get(p.getLines().size()-1));		//new added Ride get the Person in VL
			}
		}
		
		for(Person p : SilverCustomers){
			while(p.getLines().size() < p.getMaxLines()){
				p.getLines().add(RandomGenerator.selectRide(Rides,RP));
				putVL(p, p.getLines().get(p.getLines().size()-1));		//new added Ride get the Person in VL
			}
		}
		
		for(Person p : RegularCustomers){
			while(p.getLines().size() < p.getMaxLines()){
				p.getLines().add(RandomGenerator.selectRide(Rides,RP));
				putVL(p, p.getLines().get(p.getLines().size()-1));		//new added Ride get the Person in VL
				
			}
		}
		

		

	}
	
	/**
	 * clear input Ride's people and set their status to available
	 * @param ride
	 */
	public static void clearRide(Ride ride){
		while(true){
		if(!ride.getPeopleOnRide().isEmpty()){
			deRidePerson(ride.getPeopleOnRide().remove(0),ride);
			ride.setPersonPlayed(ride.getPersonPlayed()+1);
		}else{
			break;
		}
		}
	}
	/**
	 * put person in VL for first time
	 * gold highest priority
	 * @throws FullLineException
	 */
	public static void putPersonInVL() throws FullLineException{
		for(Person p: GoldCustomers){
			putVL(p,p.getLines().get(0));
		}
		
		for(Person p: SilverCustomers){
			putVL(p,p.getLines().get(0));
		}
		
		for(Person p: RegularCustomers){
			putVL(p,p.getLines().get(0));
		}
		
		for(Person p: SilverCustomers){
			putVL(p,p.getLines().get(1));
		}
		
		for(Person p: GoldCustomers){
			putVL(p,p.getLines().get(1));
			putVL(p,p.getLines().get(2));
		}
	}
	/**
	 * put the person into the Ride
	 * @param P
	 * @param R
	 * @throws FullLineException
	 */
	public static void putVL(Person P, Ride R) throws FullLineException{
		for(Ride r: Rides){
			if(r.equals(R)){
				r.getVirtualLine().enqueue(P);
			}
		}
	}
	/**
	 * put person from VL into HL 
	 * finish till HL full or VL empty
	 * and set person status to holding
	 * @param R
	 * @throws FullLineException
	 * @throws EmptyLineException
	 */
	public static void putHL(Ride R) throws FullLineException, EmptyLineException{ 
		Person temp = null;
		int s = R.getVirtualLine().size();
		for(int i = 0; i < s;i++){
			if(R.getHoldingQueue().size() < R.getHoldingQueue().getMaxSize() && R.getVirtualLine().size() > 0){
				temp = R.getVirtualLine().dequeue();
				if(temp.getStatus() == Status.Available){
					temp.setStatus(Status.Holding);
					R.getHoldingQueue().enqueue(temp);
				}else{
					R.getVirtualLine().enqueue(temp);
				}
			}else{
				break;
			}
		}
	}
	/**
	 * check HL, if HL person available, put on ride
	 * if not available put back of the VL dequeue enqueue
	 * if ride not full, check VL , put available on ride 
	 * and put non available to the back
	 * @throws FullLineException 
	 * @throws EmptyLineException 
	 */
	public static void putOnRide(Ride R) throws FullLineException, EmptyLineException{
		Person temp;
			while(R.getHoldingQueue().size() > 0 && R.getPeopleOnRide().size() < R.getCapacity()){
				temp = R.getHoldingQueue().dequeue();
				if(temp.getStatus() == Status.Holding){
					R.getPeopleOnRide().add(temp);
					temp.setStatus(Status.OnRide);
				}else{
					R.getVirtualLine().enqueue(temp);
				}
			}
			
			if( R.getPeopleOnRide().size() < R.getCapacity()){
				int s = R.getVirtualLine().size();
				for(int i = 0; i < s;i++){
					temp = R.getVirtualLine().dequeue();
					if(temp.getStatus() == Status.Available && R.getPeopleOnRide().size() < R.getCapacity()){
						R.getPeopleOnRide().add(temp);
						temp.setStatus(Status.OnRide);
					}else if(temp.getStatus() == Status.OnRide && R.getPeopleOnRide().size() < R.getCapacity()){
						R.getVirtualLine().enqueue(temp);
					}else if(R.getPeopleOnRide().size() == R.getCapacity()){
						break;
					}
				}
			}
			
	}



	/**
	 * make time advance 1 min
	 * what will happen and deal with them
	 * postcondition:
	 * Status fixed after time advanced 1 
	 * @throws WrongInputException
	 * @throws FullLineException
	 * @throws EmptyLineException 
	 */
	public static void timeAdvance() throws WrongInputException, FullLineException, EmptyLineException{
		time++;
		simulationLength--;
		//clear and set status clear Ridded Ride
		for(Ride a: Rides){
			a.setTimeLeft(a.getTimeLeft() - 1);
		}
		
		for(Ride a:Rides){
			if(a.getTimeLeft() == 0){
				clearRide(a);
				putHL(a);
				putOnRide(a);
				a.setTimeLeft(a.getDuration());
			}
		}
		
		for(Ride a:Rides){
			putHL(a);
		}
		
		fullFillAllPerson();	//put in new Ride each person and update VL
		
	}
	/**
	 * print status on System.out
	 */
	public static void showStatus(){
		
		String RC = "";
		for(Person a: RegularCustomers){
			RC = RC + a.toString();
		}
		
		String SC = "";
		for(Person a: SilverCustomers){
			SC = SC + a.toString();
		}
		
		String GC = "";
		for(Person a: GoldCustomers){
			GC = GC + a.toString();
		}
		
		System.out.println("At Time "+ time+":\n"
				+"\n"
				+BSOD.toString()+KK.toString()+i386.toString()+GF.toString()+"Regular Customers:+\n"
						+ "Num\t\tLine\t\tStatus\n"
						+ "-------------------\n"
						+ RC
						+ "\n\n"
						+ "Silver Customers:\n"
						+ "Num\t\tLine1\t\tLine2\t\tStatus\n"
						+ "-------------------\n"
						+ SC
						+ "Gold Customers:\n"
						+ "\n"
						+ "Num\t\tLine1\t\tLine2\t\tLine3\t\tStatus\n"
						+ "-------------------------------------------\n"
						+ GC+"\n"
								+ "-----------------------------------------------------------------------------\n"
								+ "----------------------\n"
								+ "");
	}
	
}
