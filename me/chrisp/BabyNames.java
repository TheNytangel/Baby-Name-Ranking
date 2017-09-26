package me.chrisp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BabyNames
{
    private Document doc;
    private Elements ranks;

    public BabyNames(int year)
    {
        // Try to get the baby name list for the year specified
        try
        {
            doc = Jsoup.connect("https://www.ssa.gov/oact/babynames/decades/names" + year + "s.html").get();
        }
        catch (IOException e)
        {
            System.out.println("There was an error retrieve the name list from the internet.");
        }

        // Put the ranks into an element list based on table row
        ranks = doc.select("tr[align=right]").not("[valign=bottom]");
    }

    public int findName(String name, char gender)
    {
        // Example row: <tr align="right"><td>1</td><td align="center">Jacob</td><td>273,746</td><td align="center">Emily</td> <td>223,640</td></tr>

        // Go through each table row in the rank table
        for (Element babyName : ranks)
        {
            // If the gender we're looking for is male
            if (gender == 'M')
            {
                // Check the 1st child <td> tag (the one after the rank - it contains the boy's name) and check if the text inside that tag is equal to the name we're looking for (ignoring case)
                if (babyName.child(1).text().equalsIgnoreCase(name))
                {
                    // Parse the integer in the 0th <td> tag (the very first one - it contains the rank)
                    return Integer.parseInt(babyName.child(0).text());
                }
            }
            // Else the gender is F and we're looking for female
            else
            {
                // Check the 3rd child <td> tag (the one after the boy's number of names - it contains the girl's name) and check if the text inside that tag is equal to the name we're looking for (ignoring case)
                if (babyName.child(3).text().equalsIgnoreCase(name))
                {
                    // Parse the integer in the 0th <td> tag (the very first one - it contains the rank)
                    return Integer.parseInt(babyName.child(0).text());
                }
            }
        }

        // Return 0 because no rank was found for that name
        return 0;
    }
}
