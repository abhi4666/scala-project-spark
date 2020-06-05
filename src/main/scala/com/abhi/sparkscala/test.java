package com.abhi.sparkscala;

import java.util.*;

public class test {

	public static void main(String[] args) {
		
		System.out.println("How many numbers to print");
		
		Scanner sc =new Scanner(System.in);
		
		int rows = sc.nextInt();
		
		System.out.println("Here is your pattern");
		
	for(int i = 1; i <= rows; i++)
	{
		for(int j = 1; j <= i; j++)
		{
			System.out.print(j+" ");
		}
             
            System.out.println();
        }
         
        //Close the resources
         
        sc.close();
    }
}