import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class satest {

	public static void main(String[] args) {
		
		boolean mode = false; // Console mode
		if (args.length > 0) if (!args[0].equals("-")) mode = true; // File mode
		
		if (mode) {
			System.out.println("File mode was selected.");
			String fOutName;
			if (args.length > 1) fOutName=args[1]; else fOutName="output.txt";
			StringBuffer buffer = new StringBuffer();
			try (FileInputStream fileIn = new FileInputStream(args[0]);
				 FileOutputStream fileOut = new FileOutputStream(fOutName);
				 BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn, "UTF8"));
				 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOut, "UTF8"));){
				int ch; // code of one character
				while ((ch = reader.read()) > -1) buffer.append((char)ch);
				writer.write(parseStr(buffer.toString()));
				System.out.println("Result was successfully recorded to file!");
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		else {
			System.out.println("Console mode was selected.");
			System.out.println("Use the commands: add (or sum), mul (multiplication), ma (a*b+c), quit (for exit)");
			Scanner scan = new Scanner(System.in);
			while (true) {
				System.out.print("Input a command: ");
				String str = scan.nextLine();
				if (str.equals("quit")) {
					System.out.println("Good bye!");
					break;
				}
				System.out.println(parseStr(str));
			}
		}
		
	}
	
	private static String parseStr(String str) {
	    StringTokenizer st = new StringTokenizer(str," ");
	    String com = st.nextToken();
	    int [] arr = new int[st.countTokens()];
	    for(int i = 0; i<arr.length; i++)
	    	try{
	    		arr[i] = Integer.parseInt(st.nextToken());
	    	}catch(NumberFormatException ex) {
	    		return "Incorrect number format!";
	    	}
	    if (com.equals("add") || com.equals("sum")) return "Result: "+add(arr);
	    else if (com.equals("mul")) return "Result: "+ mul(arr);
	    else if (com.equals("ma")) if (arr.length == 3) return "Result: "+ ma(arr); else
	    	return "This command has 3 arguments!";
	    else return "Unknown command!";
	}
	
	private static long add(int[] arr) {
		long res = 0;
		for (Integer num: arr) res = res + num;
		return res;
	}
	
	private static long mul(int[] arr) {
		long res = 1;
		for (Integer num: arr) res = res * num;
		return res;
	}
	
	private static long ma(int[] arr) {
		long res = 0;
		res = arr[0]*arr[1]+arr[2];
		return res;
	}

}
