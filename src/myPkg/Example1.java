/*
 * author : Chaitya Shah
 * subject : Advanced Programming
 * Question : Prompt the user for the input file name pb.txt
-Prompt the user for the output file pbo.txt
-The Java code should do the following: Open the file pb.txt, read the string lines, extract the 5 winning numbers and the PB number and save them as integers n1, n2, n3, n4, n5, and pb.  Assign an integer variable dateIndex for the date, which starts at 1 and is incremented by one for each date.  
-Output the data file pbo.txt will look as follows:
dateIndex  n1  n2  n3  n4  n5  pb
-Note that spaces must separate the numbers, and you can use a format of your choosing.

 * */

package myPkg;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Example1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String InputFile = JOptionPane.showInputDialog(null, "Enter the name of input file:");
		String OutputFile = JOptionPane.showInputDialog(null, "Enter the name of First output file:");
		String DateOutputFile = JOptionPane.showInputDialog(null, "Enter the name of Second output file:");
		
		File f = new File(InputFile + ".txt");  //opening the pb.txt file
		Scanner sc = new Scanner(f);   //scanning the file
		int count = 1;    //counter to count all the outputs
		String line1 = null;
		
		int n = 0;   //counter for right column
		int l = 0;   //counter for left column
		int co = 1;  //counter for index
		
		String FetchedNumber = null;
		int Fetched_Number = 0;
		
		int[] SortMultiplication = new int[1000];
		int pivot = 0;
		int PivotNumber = 0;
		int[] Sorted = new int[1000];
		int SortCount = 0;
		int[][] Pivot = new int[1][3];
		
		int[][] sol = new int[1000][10];
		int[][] date = new int[1000][3];
		//String[] sub = new String[1000];
		
		
		while(sc.hasNext())    //If file has next line than execute the following code
		{
			line1 = sc.nextLine();   //storing next line
			
			//Pattern ex = Pattern.compile("[0-1][0-9]/[0-3][0-9]/[0-9][0-9]\\s*-[0-9]*\\s*[0-9]*-\\s*[0-9]*-\\s*[0-9]*-\\s*[0-9]*\\s*[P][B]\\s*[0-9]*\\s*[X][0-9]"); //-dd\\s\\sdd-\\sdd-\\sdd-\\sdd\\s\\sww\\sdd\\s\\sXd\\s\\s[0-1][0-9]/[0-3][0-9]/[0-9][0-9]\\s\\s-dd\\s\\sdd-\\sdd-\\sdd-\\sdd\\s\\sww\\sdd\\s\\sXd\\s\\s");
		
			Pattern ex = Pattern.compile("[0-1][0-9]/[0-3][0-9]/[0-9][0-9]\\s*-[0-9]*\\s*[0-9]*-\\s*[0-9]*-\\s*[0-9]*-\\s*[0-9]*\\s*[P][B]\\s*[0-9]*");   // Pattern that to be matched
			
			Matcher m = ex.matcher(line1);  //matching the line with the pattern
			
			while(m.find())   //finding the matching text
			{
				l = 0;
				String line = m.group();     //fetching the matching text
				ex = Pattern.compile("[0-9]+");  //new pattern to fetch only numbers
				Matcher m2 = ex.matcher(line);
				
				while(m2.find())
				{
					FetchedNumber = m2.group().trim();
					Fetched_Number = Integer.parseInt(FetchedNumber);
					if(l < 3)
					{
						date[n][l] = Fetched_Number;
					}
					sol[n][l] = Fetched_Number;
					l++;
				}
				
				/*if((count % 2) == 1)     //to print only the left column
				{
					sol[l] = co + " ";
					co++;
					
					while(m2.find())
					{
						sol[l] = sol[l] + m2.group() + " ";
						
						int n1 = Integer.parseInt(m2.group());
					}
					l++;
				}
				
				else    //to print only the right column
				{
					sub[n] = " ";
					while(m2.find())
					{
						sub[n] = sub[n] + m2.group() + " ";
					}
					n++;
				}*/
				
				count++;
				n++;
			}
		}
		
		/*for(int i = 0; i < n; i++)
		{
			SortMultiplication[i] = 1;
			
			for(int j = 0; j < 3; j++)
			{
				SortMultiplication[i] = SortMultiplication[i] * sol[i][j];
			}
		}*/
		
		for(int i = 0; i < n; i++)
		{
			pivot = i;
			
			for(int j = 0; j < 3; j++)
			{
				Pivot[0][j] = date[pivot][j];
			}
			
			for(int j = 0; j <= n; j++)
			{
				if(Pivot[0][2] > date[j][2])
				{
					pivot = j;
					
					Pivot[0][0] = date[pivot][0];
					Pivot[0][1] = date[pivot][1];
					Pivot[0][2] = date[pivot][2];
				}
				
				else if(Pivot[0][0] > date[j][0] && Pivot[0][2] == date[j][2])
				{
					pivot = j;
					
					Pivot[0][0] = date[pivot][0];
					Pivot[0][1] = date[pivot][1];
					Pivot[0][2] = date[pivot][2];
				}
				
				else if(Pivot[0][1] > date[j][1] && Pivot[0][0] == date[j][0] && Pivot[0][2] == date[j][2])
				{
					pivot = j;
					
					Pivot[0][0] = date[pivot][0];
					Pivot[0][1] = date[pivot][1];
					Pivot[0][2] = date[pivot][2];
				}
				
				
			}
			
			date[pivot][0] = 100;
			date[pivot][1] = 100;
			date[pivot][2] = 100;
			
			System.out.println(i+" "+pivot);
			
			//SortMultiplication[PivotNumber] = 0;
			Sorted[i] = pivot;
			//System.out.println(i+" "+PivotNumber);
		}
		
		PrintWriter writer = new PrintWriter(OutputFile + ".txt", "UTF-8");   //creating output file pbo.txt
		
		/*for(int i = 0; i < count/2; i++)   //printing the numbers from left column
		{
			writer.println(sol[i]);
		}
		for(int i = 0; i < count/2; i++)   //printing the numbers from right column
		{
			writer.println(co+sub[i]);
			co++;
		}*/
		
		for(int i = 1; i <= n; i++)
		{
			SortCount = Sorted[i];
			writer.print(i+" ");
			for(int j = 3; j < 9; j++)
			writer.print(sol[SortCount][j]+ " ");
			
			writer.println();
		}
		
		writer.close();   //file close
		
		PrintWriter writer1 = new PrintWriter(DateOutputFile + ".txt", "UTF-8");   //creating output file pbo.txt
		
		for(int i = 1; i <= n; i++)
		{
			SortCount = Sorted[i];
			writer1.print(i+" ");
			for(int j = 0; j < 3; j++)
			writer1.print(sol[SortCount][j]+ " ");
			
			writer1.println();
		}
		
		writer1.close();   //file close

		System.out.println("Power Ball data processing completed");  //showing required message
	}

}
