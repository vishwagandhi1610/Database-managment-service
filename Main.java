import java.util.Scanner;

public class Main {
    static String key;
	/*
	* Main Method to display options available to the user, and allow the user to run operations on the database.
	*/
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int roleChoice = -1;
		while (roleChoice != 0) {
			String opChoice = "";
			roleChoice = displayLoginAsMenu(scanner);
			switch (roleChoice) {
			case 1:
				while (!opChoice.equals("0")) {
					opChoice = displayManagmentMenu(scanner);
					switch (opChoice) {
					case "A1":
						System.out.print("Enter Artist Id:");
						String creatorsid = scanner.nextLine();
						System.out.print("Enter Artist First Name:");
						String cf_name = scanner.nextLine();
						System.out.print("Enter Artist last Name:");
						String cl_name = scanner.nextLine();
						System.out.print("Enter label name if the artist is associated with some label:");
						String labelname = scanner.nextLine();
						System.out.print("Enter artist status (active/retired):");
						String a_status = scanner.nextLine();
						System.out.print("Enter type (band,composer,musician):");
						String type = scanner.nextLine();
						System.out.print("Enter Primary genre artist has:");
						String primary_genre = scanner.nextLine();
						System.out.print("Enter the country where the artist belong");
						String a_country = scanner.nextLine();
						artistapi.insertArtist(creatorsid,cf_name,cl_name,labelname,a_status,type,primary_genre,a_country);
					break;


			case "0":
					System.out.println("Thank you for using the system!");
					break;
	
				default:
					System.out.println("Invalid Input...");
					break;
				}
			}
			scanner.close();
	}
	}
	}
	// /*
	// * Method to display Roles menu and accept user choice.
	// */
	static int displayLoginAsMenu(Scanner scanner) {
		System.out.println("Login As:");
		System.out.println("1. Managment");
		System.out.println("0. Exit");
		System.out.print("Enter your choice:");
		int roleChoice = Integer.parseInt(scanner.nextLine());
		return roleChoice;
	}

	static String displayManagmentMenu(Scanner scanner) {
		System.out.println("--------------Artist Information--------------");
		System.out.println("A1. Enter information for a new artist");
		// System.out.println("A2. Update Information of a artist");
		// System.out.println("A3. Delete information of a artist");
		System.out.println("----------------------------------------");
		System.out.println("0. Go Back");
		System.out.println("----------------------------------------");
		System.out.print("Enter your choice:");
		String mainChoice = scanner.nextLine();
		return mainChoice;
	}

}