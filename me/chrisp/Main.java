package me.chrisp;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        // Variables for name information
        int year = 2010;
        char gender = 'M';
        String name;

        // Had an error with user input
        boolean error = false;

        try
        {
            System.out.print("Enter the year: ");
            year = input.nextInt();

            // If the year is between 1880 and 2010 (inclusive) AND is divisible by 10, it's a good number. Otherwise throw an exception
            if (year < 1880 || year > 2010 || year % 10 != 0)
            {
                throw new IllegalArgumentException("The year must be a decade between 1880 and 2010 (inclusive)");
            }
        } catch (IllegalArgumentException e)
        {
            // Set the error bool to true and display the error message
            error = true;
            System.out.printf("Error! %s%n", e.getMessage());
        }

        try
        {
            // Get the character from the user
            System.out.print("Enter the gender (M/F): ");
            gender = input.next().charAt(0);

            // Convert to uppercase
            gender = java.lang.Character.toUpperCase(gender);

            // If gender is M or F it's good. Otherwise throw an exception
            if (gender != 'M' && gender != 'F')
            {
                throw new IllegalArgumentException("The gender must be M or F");
            }
        } catch (IllegalArgumentException e)
        {
            // Set the error bool to true and display the error message
            error = true;
            System.out.printf("Error! %s%n", e.getMessage());
        }

        // Prompt for name
        System.out.print("Enter the name: ");
        name = input.next();

        // If there was an error, stop executing the program
        if (error)
        {
            return;
        }

        // New baby names class that loads data based on the year passed in
        BabyNames names = new BabyNames(year);

        // Find the rank based on the name and gender
        int rank = names.findName(name, gender);

        // The function returns 0 if the name was not found that year
        if (rank != 0)
        {
            System.out.printf("%n%s is ranked #%d in %d%n", name, rank, year);
        }
        else
        {
            System.out.printf("%nThe name %s is not ranked in %d%n", name, year);
        }
    }
}
