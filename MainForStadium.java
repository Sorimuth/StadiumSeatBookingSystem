import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Stadium{

	static int constantRow = 6;
	static int constantColumn = 42;
	
	ArrayList<String> standArrList = new ArrayList<String>();
	ArrayList<String> seatNoArr = new ArrayList<String>();
	
	static String allStands[][] = {{"Rishab Pant", "David Warner", "Axar Patel", "Ravichandran Ashwin"},{"Naveen Ul Haq", "KL Rahul", "Krunal Pandya", "Avesh Khan"},{"Gautam Gambir", "Andre Russell", "Rinku Singh", "Dinesh Karthick"},{"Virat Kohli", "ABD Villiers", "Mitchell Starc", "Chris Gayle"},{"MS Dhoni", "Ravindra Jadeja", "Shane Watson", "Dwayne Bravo"},{"Shikhar Dhawan", "Mayank Agarwal", "Arshdeep Singh", "Sam Curran"},{"Bhuvaneshwar Kumar", "Aiden Markram", "Kane Williamson", "Hary Brook"},{"Jaishwal", "Trend Boult", "Ravichandra Ashwin", "Yuzvendra Chahal"},{"Hardik Pandya", "Sachin Tendulkar", "Rohit Sharma", "Kieron Pollard"},{"Subhman Gill", "Rashid Khan", "Mohammed Shami", "David Miller"}};
	
	String getStand(int stadiumNo, int standNo){
		return allStands[stadiumNo - 1][standNo - 1];
	}
	
	static MainForStadium mainObj = new MainForStadium();
	String cancelSeat;
	Scanner getData = new Scanner(System.in);
	String seatNo;
	String standSeatArr[][] = new String[constantRow][constantColumn];
	int countForMatches = 0; 
	
	void displaySeat(int stadiumNo, int standNo, String fileName){
		
		try{
			File seatFile = new File(fileName);
			Scanner getData = new Scanner(seatFile);
			int z = 0;
			while(getData.hasNextLine()){
				String arr[] = getData.nextLine().split(" ");
				for(int j = 0;j<constantColumn;j++){
					if(z == 0 && j == constantColumn - 1){
						standSeatArr[z][j] = " ";
					}
					else{
						standSeatArr[z][j] = arr[j];
					}
				}
				z++;
			}
			System.out.println("\n");
			for(int i = 0;i<constantRow;i++){
				for(int j = 0;j<constantColumn;j++){
					System.out.print(standSeatArr[i][j]);
					if(i == 0 && j < 10){
					   	System.out.print(" ");
					}
					if(i == 0 && j == 0){
						System.out.print(" ");
					}
					
					if(j == constantColumn - 1){
						System.out.print("\n\n");
					}
					else{
						System.out.print(" ");
					}
				}
			}
			
			
		}
		catch(FileNotFoundException E){
			System.out.println("The file is not found");
		}
	}
	
	
	void bookSeat(String seatNo, String fileName) {
	
	loop: while(true){
		int temp = 0;
    		String arr[];
    		String x;
    		int y;
    		while (true) {
    			
        		if (seatNo.contains("-")) {
            			arr = seatNo.split("-");
            			x = arr[0];
            			y = Integer.parseInt(arr[1]);
            			break;
        		} 
        		else {
            			System.out.println("\nInvalid Input");
            			seatNo = getSeatNo(); 
        		}
    		}

		if(x.equals("A")){
			temp = 1;
		}
		else if(x.equals("B")){
			temp = 2;
		}
		else if(x.equals("C")){
			temp = 3;
		}
		else if(x.equals("D")){
			temp = 4;
		}
		else if(x.equals("E")){
			temp = 5;
		}

		
			if (standSeatArr[temp][y+1].equals("🟩")) { 
    				standSeatArr[temp][y+1] = "⬛";
    				break loop;
    			} 
    			else {
        			System.out.println("It is already booked.");
        			System.out.println("Enter the correct Seat Number:");
        			seatNo = getData.next();
    			}
		
    		
	}
	
	try{
			
		File file = new File(fileName);
		File fileToDelete = new File(fileName);
		fileToDelete.delete();
		FileWriter fileWrite = new FileWriter(fileName);
		for(int i = 0;i<constantRow;i++){
			for(int j = 0;j<constantColumn;j++){
				fileWrite.write(standSeatArr[i][j] + " ");
				if(j == constantColumn - 1){
		   			fileWrite.write("\n");
				}
			}
				
		}
		fileWrite.close();
		
	}
	catch(FileNotFoundException e){
		System.out.println("The file is not found");
	}
	}


	void showSeats(){
	
		System.out.println("\n");
		
		for(int i = 0;i<constantRow;i++){
			for(int j = 0;j<constantColumn;j++){
				System.out.print(standSeatArr[i][j]);
				
				if(i == 0 && j < 10){
					System.out.print(" ");
				}
				if(i == 0 && j == 0){
					System.out.print(" ");
				}
					
				if(j == constantColumn - 1){
					System.out.print("\n\n");
				}
				else{
					System.out.print(" ");
				}
			}
		}
		
		
		
	}
	
	void storeMatchesInArray(){
		try{
			File teamFile = new File("teams.txt");
			Scanner getData = new Scanner(teamFile);
		
		
			while(getData.hasNextLine()){
				getData.nextLine();
				countForMatches++;
			}
		
			String matchesArr[] = new String[countForMatches];
		
			for(int i=0;i<countForMatches;i++){
				matchesArr[i] = getData.nextLine();
			}
		}
		catch(Exception e){
			throw new IllegalArgumentException("Error");
		}
		
	}
	
	
	
	
	static String matchesArr[] = {"DC VS SRH", "LSG VS RCB", "KKR VS MI", "RCB VS GT", "CSK VS RR", "PK VS LSG", "SRH VS KKR", "RR VS PK", "MI VS CSK", "GT VS DC"};
	
	String getSeatNo(){
		Scanner getData = new Scanner(System.in);
		System.out.println("Enter the seat number with space like A-14:");
		return getData.next();
	}
	
}

class Matches{
	static String dateArr[] = {"2023-12-13", "2023-12-14", "2023-12-15", "2023-12-16", "2023-12-16", "2023-12-18", "2023-12-18", "2023-12-19", "2023-12-20", "2023-12-21"};
	
	static int timeArr[] = {19, 19, 19, 15, 19, 15, 19, 19, 19, 19};
}

class Ticket {
	ArrayList<Integer> priceArr = new ArrayList<Integer>();

	Scanner getData = new Scanner(System.in);
	
	static int allSeatsPriceForStadium[][] = {{700, 900, 1000, 1050},{800, 1000, 700, 700},{2000, 1000, 3000, 1000},{6000, 5000, 2500, 3000},{7000, 3000, 2500, 2000},{1000, 700, 2000, 3000},{1000, 700, 3000, 800},{1000, 2000, 3000, 800},{5000, 2500, 2000, 3500},{1000, 2000, 3000, 5000}};

	int price;

	void displayPrice(int standNo, int stadiumNo){
		
	
		while(true){
			if((standNo > 0) && (standNo < 4)){
				
				price = allSeatsPriceForStadium[stadiumNo - 1][standNo - 1];
				break;
			}
				
			else{
				System.out.println("Invalid Input");
				System.out.println("Enter the stand number:");
				standNo = getData.nextInt();
			}
		}
	}
}


class MainForStadium{
	String fileName;
	String password;
	String confirmPassword;
	LocalTime currentTime = LocalTime.now();
	int currentHour = currentTime.getHour();
	LocalDate currentDate = LocalDate.now();

	int stadiumNo;
	int standNo;
	String cancelSeat;
	int countForMain = 0;
	int countForCancel = 0;
	int getCancelSeatNo = 0;
	int increase = 0; 
	int price;
	String seatNo;
	static MainForStadium mainObj = new MainForStadium();
	
	Matches matchObj = new Matches();
	
	Ticket ticObj = new Ticket();
		
	static Stadium stadiumObj = new Stadium();

	static Scanner getData = new Scanner(System.in);

	static String []stadiumName = {"Arun Jaitley", "Ekana", "Eden Gardens", "M.Chinnaswamy", "MA Chidambaram", "IS Bindra", "Rajiv Gandhi", "Sawai Mansingh", "Wankhede", "Narendra Modi"};
	static String []list = {"1.Ticket Reservation", "2.Show Details", "3.Cancellation", "4.Cancel Details", "5.Exit"};
	
	static String []methodName = {"Arun Jaitley", "Ekana", "Eden Gardens", "M.Chinnaswamy", "MA Chidambaram", "IS Bindra", "Rajiv Gandhi", "Sawai Mansingh", "Wankhede", "Narendra Modi"};
	
	
	ArrayList<Integer> stadiumNum = new ArrayList<Integer>();
	
	
	
	String name;
	String emailId;
	static int count = 0;
	int option;
	
	void cancelDetails(String cancelSeat, int stadiumNo, String filename){
	
		String date[] = matchObj.dateArr[stadiumNo - 1].split("-");
		int getDate = Integer.parseInt(date[2]);
		int getTime = matchObj.timeArr[stadiumNo - 1];
		int dayOfMonth = mainObj.currentDate.getDayOfMonth();
	
		if((getDate > dayOfMonth) || (getDate == dayOfMonth)){
			if(((getTime - currentHour) > 2) || ((getTime - currentHour) == 2)){
				loop: while(true){
				if(stadiumObj.seatNoArr.contains(cancelSeat)){
					int temp = 0;
    					String arr[];
    					String x;
    					int y;
    					while (true) {
    			
        					if (cancelSeat.contains("-")) {
            						arr = cancelSeat.split("-");
            						x = arr[0];
            						y = Integer.parseInt(arr[1]);
            						break;
        					} 
        					else {
            						System.out.println("\nInvalid Input");
            						seatNo = stadiumObj.getSeatNo(); 
        					}
    					}

					if(x.equals("A")){
						temp = 1;
					}
					else if(x.equals("B")){
						temp = 2;
					}
					else if(x.equals("C")){
						temp = 3;
					}
					else if(x.equals("D")){
						temp = 4;
					}
					else if(x.equals("E")){
						temp = 5;
					}

		
					if (stadiumObj.standSeatArr[temp][y+1].equals("⬛")) { 
    						stadiumObj.standSeatArr[temp][y+1] = "🟩";
    						break;
    					} 
				}
				else{
					System.out.println("Seat Number does not exist");
					System.out.println("Enter the seat number to cancel:");
					cancelSeat = getData.next();
				}
				}
				
				System.out.println("\n");
				for(int i = 0;i<stadiumObj.constantRow;i++){
					for(int j = 0;j<stadiumObj.constantColumn;j++){
						System.out.print(stadiumObj.standSeatArr[i][j]);
						if(i == 0 && j < 10){
					   		System.out.print(" ");
						}
						if(i == 0 && j == 0){
							System.out.print(" ");
						}
					
						if(j == stadiumObj.constantColumn - 1){
							System.out.print("\n\n");
						}
						else{
							System.out.print(" ");
						}
					}
				}
				
				
				try{
			
					File file = new File(fileName);
					//System.out.println(standSeatArr.toString());
					File fileToDelete = new File(fileName);
					fileToDelete.delete();
					FileWriter fileWrite = new FileWriter(fileName);
		
					for(int i = 0;i<stadiumObj.constantRow;i++){
			
						for(int j = 0;j<stadiumObj.constantColumn;j++){
							fileWrite.write(stadiumObj.standSeatArr[i][j] + " ");
				
							if(j == stadiumObj.constantColumn - 1){
								fileWrite.write("\n");
							}
						}
				
					}
					fileWrite.close();
		
				}
				catch(FileNotFoundException e){
					System.out.println("The file is not found");
				}
	
				System.out.println("\nTicket Cancelled");
				getCancelSeatNo = stadiumObj.seatNoArr.indexOf(cancelSeat);
				System.out.println("Stadium Name : " + mainObj.stadiumName[mainObj.stadiumNum.get(getCancelSeatNo)]);
				System.out.println("Stand Name   : " + stadiumObj.standArrList.get(getCancelSeatNo));
				System.out.println("Seat Number  : " + stadiumObj.seatNoArr.get(getCancelSeatNo));
				System.out.println("Price        : " + ticObj.priceArr.get(getCancelSeatNo));
				System.out.println("Date         : " + matchObj.dateArr[stadiumNo - 1]);
				System.out.println("Time         : " + matchObj.timeArr[stadiumNo - 1]);
				stadiumObj.seatNoArr.remove(getCancelSeatNo);
				mainObj.stadiumNum.remove(getCancelSeatNo);
				countForMain--;
			}
			else{
				System.out.println("You cannot cancel the ticket");
			}
	
		}
		else{
			System.out.println("You cannot cancel the ticket");
		}
	
	
	
		}
		
		String getFileName(int stadiumNo, int standNo){
			return Stadium.allStands[stadiumNo - 1][standNo - 1];
		}
		
		
		
		

	public static void main(String[] args){
		
			 
		System.out.println("1. Signup\n2. Login\nEnter any option");
		int signLog = getData.nextInt();
		loop: while(true){
			
			if(signLog == 1){
				mainObj.signup();
				break loop;
			}
			else if(signLog == 2){
				mainObj.login();
				break loop;
			}
			else{
				System.out.println("\nInvalid Input");
				System.out.println("\n1. Signup\n2. Login\nEnter any option");
				signLog = getData.nextInt();
			}
		}
		
		
	
		
		displayFeatures();
		
		Ticket ticObj = new Ticket();
		
		Stadium stadiumObj = new Stadium();
		
		
			
			int option = mainObj.printOption();
		
			while((option > 0) && (option < 5)){
			
			
				if(option == 1){
					
					mainObj.store();
					option = mainObj.printOption();
				}
				
				else if(option == 2){
					mainObj.printDetails();
					displayFeatures();
					option = mainObj.printOption();
				}
				else if(option == 3){
					mainObj.cancelSeat = mainObj.Cancellation();
					mainObj.cancelDetails(mainObj.cancelSeat, mainObj.stadiumNo, mainObj.fileName);
					displayFeatures();
					option = mainObj.printOption();
				}
				else if(option == 4){
					System.out.println("Thankyou");
					System.exit(0);
				}
				
			}
			if((option < 0) && (option > 5)){
				System.out.println("Invalid Input");
				System.exit(0);
			}
			
				
	}
	
	
	void callMethodWhenStadiumNoIsEntered(int stadiumNo){
		
		if(stadiumNo == 1){
			System.out.println("\n========================================================================================");
			System.out.println("| S.No. |                       Stands                            |     Ticket Rates   |");
			System.out.println("========================================================================================");
			System.out.println("|   1   |Rishab Pant Stand                                        |        Rs. 700/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   2   |David Warner Stand                                       |        Rs. 900/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   3   |Axar Patel Stand                                         |       Rs. 1000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   4   |Ravichandran Ashwin Stand                                |       Rs. 1050/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
		
		else if(stadiumNo == 2){
			System.out.println("\n========================================================================================");
			System.out.println("| S.No. |                       Stands                            |     Ticket Rates   |");
			System.out.println("========================================================================================");
			System.out.println("|   1   |Naveen Ul Haq Stand                                      |        Rs. 800/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   2   |KL Rahul Stand                                           |        Rs. 1000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   3   |Krunal Pandya Stand                                      |         Rs. 700/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   4   |Avesh Khan Stand                                         |         Rs. 700/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
		
		else if(stadiumNo == 3){
			System.out.println("\n========================================================================================");
			System.out.println("| S.No. |                       Stands                            |     Ticket Rates   |");
			System.out.println("========================================================================================");
			System.out.println("|   1   |Gautam Gambir Stand                                      |        Rs. 2000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   2   |Andre Russell Stand                                      |        Rs. 1000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   3   |Rinku Singh Stand                                        |        Rs. 3000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   4   |Dinesh Karthik Stand                                     |        Rs. 1000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
		else if(stadiumNo == 4){
			System.out.println("\n========================================================================================");
			System.out.println("| S.No. |                       Stands                            |     Ticket Rates   |");
			System.out.println("========================================================================================");
			System.out.println("|   1   |Virat Kohli Stand                                        |       Rs. 6000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   2   |ABD Villiers Stand                                        |       Rs. 5000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   3   |Mitchell Starc Stand                                     |       Rs. 2500/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   4   |Chris Gayle Stand                                        |       Rs. 3000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
		else if(stadiumNo == 5){
			System.out.println("\n========================================================================================");
			System.out.println("| S.No. |                       Stands                            |     Ticket Rates   |");
			System.out.println("========================================================================================");
			System.out.println("|   1   |MS Dhoni Stand                                           |       Rs. 7000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   2   |Ravindra Jadeja Stand                                    |       Rs. 3000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   3   |Shane Watson Stand                                       |       Rs. 2500/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   4   |Dwayne Bravo Stand                                       |       Rs. 2000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
		else if(stadiumNo == 6){
			System.out.println("\n========================================================================================");
			System.out.println("| S.No. |                       Stands                            |     Ticket Rates   |");
			System.out.println("========================================================================================");
			System.out.println("|   1   |Shikhar Dhawan Stand                                     |        Rs. 1000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   2   |Mayank Agarwal Stand                                    |         Rs. 700/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   3   |Arshdeep Singh Stand                                     |        Rs. 2000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   4   |Sam Curran Stand                                         |        Rs. 3000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
		
		else if(stadiumNo == 7){
			System.out.println("\n========================================================================================");
			System.out.println("| S.No. |                       Stands                            |     Ticket Rates   |");
			System.out.println("========================================================================================");
			System.out.println("|   1   |Bhuvaneshwar Kumar Stand                                 |        Rs. 1000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   2   |Aiden Markram Stand                                      |         Rs. 700/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   3   |Kane Williamson Stand                                    |        Rs. 3000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   4   |Hary Brook Stand                                         |         Rs. 800/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
		else if(stadiumNo == 8){
			System.out.println("\n========================================================================================");
			System.out.println("| S.No. |                       Stands                            |     Ticket Rates   |");
			System.out.println("========================================================================================");
			System.out.println("|   1   |Jaishwal  Stand                                          |        Rs. 1000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   2   |Trend Boult Stand                                        |        Rs. 2000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   3   |Ravichandra Ashwin Stand                                 |        Rs. 3000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   4   |Yuzvendra Chahal Stand                                   |         Rs. 800/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
		
		else if(stadiumNo == 9){
			System.out.println("\n========================================================================================");
			System.out.println("| S.No. |                       Stands                            |     Ticket Rates   |");
			System.out.println("========================================================================================");
			System.out.println("|   1   |Hardik Pandya Stand                                      |       Rs. 5000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   2   |Sachin Tendulkar Stand                                   |       Rs. 2500/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   3   |Kieron Pollard Stand                                     |       Rs. 2000/-   |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   4   |Rohit Sharma Stand                                       |       Rs. 3500/-   |");
			System.out.println("----------------------------------------------------------------------------------------\n");
		}
		
		else if(stadiumNo == 10){
			System.out.println("\n========================================================================================");
			System.out.println("| S.No. |                       Stands                            |     Ticket Rates   |");
			System.out.println("========================================================================================");
			System.out.println("|   1   |Subhman Gill Stand                                       |        Rs. 1000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   2   |Rashid Khan Stand                                        |        Rs. 2000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   3   |Mohammed Shami Stand                                     |        Rs. 3000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("|   4   |David Miller Stand                                       |        Rs. 5000/-  |");
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
	
	}
	
	
	
	void signup(){
		try{	
			
			
			
			System.out.println("Enter your name:");
			String nameForSignup = getData.next();
			
			File f = new File(nameForSignup+".txt");
			
			if(f.createNewFile()){
				FileWriter userFileInput = new FileWriter(nameForSignup + ".txt");
			
				System.out.println("Enter your Email Id");
				String emailForSignUp = getData.next();
				String regexPattern = "^[A-Za-z0-9+_.-]+@[a-zA-Z0-9.-]+$";
				Pattern pattern = Pattern.compile(regexPattern);
				Matcher matcher = pattern.matcher(emailForSignUp);
				loop: while(true){
					if(matcher.matches()){
						userFileInput.write(nameForSignup);
						userFileInput.write("\nEmail Id is "+emailForSignUp);
						userFileInput.close();
						System.out.println("Enter the password:");
						getData.nextLine();
						password = getData.nextLine();
						
						
						System.out.println("\n1.Login\n2.Exit");
						int signLog = getData.nextInt();
						
						if(signLog == 1){
							mainObj.login();
							break loop;
						}
						else if(signLog == 2){
							System.out.println("Exit");
							System.exit(0);
						}
					}
					else{
						System.out.println("Invalid Input. Check your Email-Id");
						System.out.println("Enter your Email Id");
						emailForSignUp = getData.next();
						regexPattern = "^[A-Za-z0-9+_.-]+@[a-zA-Z0-9.-]+$";
						pattern = Pattern.compile(regexPattern);
						matcher = pattern.matcher(emailForSignUp);
					}
				}
				
				
				
			
			}
			else{
				System.out.println("Username already exist");
			}
		}
		catch(FileNotFoundException e){
			System.out.println("The file is not found");
		}
	}
	
	void login(){
		getData.nextLine();
		System.out.println("Enter your name :");
		String nameForLogin = getData.next();
		System.out.println("Enter the password :");
		confirmPassword = getData.next();
	
		loop: while(true){
		if(password.equals(confirmPassword)){
			File fileForLogin = new File(nameForLogin+".txt");
			if(fileForLogin.exists()){
				System.out.println("Login successfully");
				break loop;
			}
			else{
				System.out.println("Name does not exist.");
				System.out.println("1. Signup\n2. Login\nEnter any option");
				getData.nextLine();
				String signLog = getData.nextLine();
				if(signLog.equals("signup")){
					mainObj.signup();
				}
				else if(signLog.equals("login")){
					mainObj.login();
				}
			}
		}
		else{
			System.out.println("Invalid Input.");
			System.out.println("Enter the correct password:");
			password = getData.nextLine();
			System.out.println("Confirm Password:");
			confirmPassword = getData.nextLine();
		}
		}
		
		
	}
	
	
	
	void store(){
		listStadiums();
		stadiumNo = mainObj.getStadiumNo();
		
		loop: while(true){
			if((stadiumNo > 0) && (stadiumNo < 11)){
				
				break loop;	
			}
			else{
				
				System.out.println("Invalid Input");
				stadiumNo = mainObj.getStadiumNo();
			}
		}
		
		mainObj.callMethodWhenStadiumNoIsEntered(stadiumNo);
		mainObj.stadiumNum.add(stadiumNo - 1);
		
		standNo = mainObj.getStandNo();
		stadiumObj.standArrList.add(stadiumObj.getStand(stadiumNo, standNo));
		fileName = mainObj.getFileName(stadiumNo, standNo) + " Stand.txt";
		
		loop1: while(true){
			if(stadiumNo == 9){
				if((standNo > 0) && (standNo < 7)){
					break loop1;
				}
				else{
					System.out.println("Invalid Input");
					standNo = mainObj.getStandNo();
				}
			}
			else{
				if((standNo > 0) && (standNo < 5)){
					break loop1;
				}
				else{
					System.out.println("Invalid Input");
					standNo = mainObj.getStandNo();
				}
			}
		}
		
		
		stadiumObj.displaySeat(stadiumNo, standNo, fileName);
		
		System.out.println("Enter how many seats did you want");
		int howManySeats = getData.nextInt();
		int countingSeats = 0;
		
		while(countingSeats < howManySeats){
			countForMain++;
			String seatNo = stadiumObj.getSeatNo();
			stadiumObj.bookSeat(seatNo, fileName);
			stadiumObj.seatNoArr.add(seatNo);
			stadiumObj.standArrList.add(stadiumObj.getStand(stadiumNo, standNo));
			ticObj.displayPrice(standNo, stadiumNo);
			ticObj.priceArr.add(ticObj.price);
			
			if(countingSeats < howManySeats){
				stadiumObj.showSeats();
			}
			mainObj.stadiumNum.add(stadiumNo - 1);
			countingSeats++;
		}
		stadiumNum.remove(stadiumNum.size() - 1);
		System.out.println("\nBooked Tickets");
		countingSeats = 0;
		
		mainObj.printDetails();
		
		displayFeatures();
	}
	
	int getStandNo(){
		System.out.println("Enter the stand number :");
		return getData.nextInt();
	}
	
	String Cancellation(){
		System.out.println("Enter the seat number to cancel:");
		return getData.next();
	}
	
	void printDetails(){
		if(countForMain > 0){
			for(int i=0;i<countForMain;i++){
				System.out.println("Stadium Name : " + mainObj.stadiumName[mainObj.stadiumNum.get(increase)]);
				System.out.println("Stand Name   : " + stadiumObj.standArrList.get(increase));
				System.out.println("Seat Number  : " + stadiumObj.seatNoArr.get(increase));
				System.out.println("Price        : " + ticObj.priceArr.get(increase));
				System.out.println("Date         : " + matchObj.dateArr[stadiumNo - 1]);
				System.out.println("Time         : " + matchObj.timeArr[stadiumNo - 1]+"\n");
				increase++;
			}
			increase = 0;
		}
		else{
			System.out.println("\n\n==================");
			System.out.println("No ticket booked.");
			System.out.println("==================");
		}
		
		
	}
	
	String getName(){
		System.out.println("Enter a name:");
		return getData.next();	
	}
	
	
	String getEmailId(){
		System.out.println("Enter a E-mail:");
		return getData.next();
	}
	
	
	
	static void listStadiums(){
		
		Stadium stadiumObj = new Stadium();
		count = 0;
		
		
		System.out.println("\n==================================================");
		System.out.println("| S.No. |         Stadiums         |   Matches   |");
		System.out.println("==================================================");
		
		
		for(int i=0;i<stadiumName.length;i++){
			count++;
			if(count == 10){
				System.out.println("|  " +count+ "   |      "+stadiumName[i]+"       |   "+stadiumObj.matchesArr[i]+"  |");
				System.out.println("--------------------------------------------------");
			}
			else if(count == 1){
				System.out.println("|   " +count+ "   |       "+stadiumName[i]+"       |  "+stadiumObj.matchesArr[i]+"  |");
				System.out.println("--------------------------------------------------");
			}
			else if(count == 2){
				System.out.println("|   " +count+ "   |          "+stadiumName[i]+"           |  "+stadiumObj.matchesArr[i]+" |");
				System.out.println("--------------------------------------------------");
			}
			else if(count == 3){
				System.out.println("|   " +count+ "   |       "+stadiumName[i]+"       |  "+stadiumObj.matchesArr[i]+"  |");
				System.out.println("--------------------------------------------------");
			}
			else if(count == 4){
				System.out.println("|   " +count+ "   |      "+stadiumName[i]+"       |  "+stadiumObj.matchesArr[i]+"  |");
				System.out.println("--------------------------------------------------");
			}
			else if(count == 5){
				System.out.println("|   " +count+ "   |     "+stadiumName[i]+"       |  "+stadiumObj.matchesArr[i]+"  |");
				System.out.println("--------------------------------------------------");
			}
			else if(count == 6){
				System.out.println("|   " +count+ "   |        "+stadiumName[i]+"         |  "+stadiumObj.matchesArr[i]+"  |");
				System.out.println("--------------------------------------------------");
			}
			else if(count == 7){
				System.out.println("|   " +count+ "   |      "+stadiumName[i]+"        |  "+stadiumObj.matchesArr[i]+" |");
				System.out.println("--------------------------------------------------");
			}
			else if(count == 8){
				System.out.println("|   " +count+ "   |     "+stadiumName[i]+"       |   "+stadiumObj.matchesArr[i]+"  |");
				System.out.println("--------------------------------------------------");
			}
			else if(count == 9){
				System.out.println("|   " +count+ "   |        "+stadiumName[i]+"          |  "+stadiumObj.matchesArr[i]+"  |");
				System.out.println("--------------------------------------------------");
			}
			
		}
	}
	
	int getStadiumNo(){
		System.out.println("\nChoose the stadium number:\n");
		return getData.nextInt();
	}
	
	int printOption(){
		System.out.println("\nEnter any one of the option:");
	        return getData.nextInt();
	}
	
	static void displayFeatures(){
		System.out.println("\n============================");
		System.out.println("||     Book My Ticket     ||");
		System.out.println("============================");
		System.out.println("\nEnter your choice");
		System.out.println("\n1.Ticket Reservation");
		System.out.println("\n2.View Booked Details");
		System.out.println("\n3.Cancellation");
		System.out.println("\n4.Exit");
	}
}
