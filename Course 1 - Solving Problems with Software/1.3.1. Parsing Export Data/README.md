# Programming Exercise: Parsing Export Data

The CSV file exportdata.csv​ has information on the export products of countries. In particular it has
three column headers labeled Country​, Exports​, and Value (dollars).  
The Country​column represents a country from the world, the Exports​column is a list of export items for a country, and
the Value (dollars)​column is the dollar amount in millions of their exports in the format of a dollar sign, followed by an integer number with a comma separator every three digits from the right. An example of such a number might be “$400,000,000”.  
The CSV file exportdatasmall.csv is a smaller version of the file above with the same columns that you may find helpful in testing your program.

Write the following program.  

1. Write a method named tester that will create your CSVParser and call each of the methods below. You would start your code with:

        FileResourcefr=newFileResource();  
        CSVParserparser=fr.getCSVParser();  

    Each time you want to use the parser with another method, you will need to reset the parser with this statement before calling that method.  

        parser=fr.getCSVParser();  

2. Write a method named countryInfo​ that has two parameters, parser ​is a CSVParser ​and country ​is a String.  
This method returns a string of information about the country or returns “NOT FOUND” if there is no information about the country.  
The format of the string returned is the country, followed by “: “, followed by a list of the countries exports, followed by “: “, followed by the countries export value.  
For example, using the file exportdatasmall.csv and the country Germany, the program returns the string:

        Germany:motor vehicles,machinery,chemicals:$1,547,000,000,000

3. Write a void method named listExportersTwoProducts​ that has three parameters, parser ​is a CSVParser​, exportItem1 ​is a String and exportItem2 ​is a String.  
This method prints the names of all the countries that have both exportItem1 ​and exportItem2 ​as export items. For example, usingthe file exportdatasmall.csv, this method called with the items “gold” and “diamonds” would print
the countries Namibia and SouthAfrica.

4. Write a method named numberOfExporters​, which has two parameters, parser ​is a CSVParser​,and exportItem ​is a String.  
This method returns the number of countries that export export Item​. For example, using the file exportdatasmall.csv, this method called with the item “gold” would return 3.

5. Write a void method named bigExporters​ that has two parameters, parser​ is a CSVParser​, and amount ​is a String in the format of a dollar sign, followed by an integer number with a comma separator every three digits from the right.  
An example of such a string might be “$400,000,000”.  
This method prints the names of countries and their Value amount for all countries whose Value (dollars) string is larger than the amount string. For example, if bigExporters ​is called with the file exportdatasmall.csv ​and amount with the string $999,999,999, then this method would print eight countries and their export values shown here:  
    - Germany $1,547,000,000,000
    - Macedonia $3,421,000,000
    - Malawi $1,332,000,000
    - Malaysia $231,300,000,000
    - Namibia $4,597,000,000
    - Peru $36,430,000,000
    - SouthAfrica $97,900,000,000
    - UnitedStates $1,610,000,000,00