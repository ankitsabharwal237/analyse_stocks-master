# analyse_stocks
The project aims at analysis of stocks from the historical data and assign a score.


AnalysisMain.java: This file contains the main function, the entry point of the code. 
                  A start and an end date is required. This would be the start and end date of analysis.
  
  We use Yahoo finance data to run this application. Currently it is configured to be used for Bombay Stock Exchange. 
  The code can be eaisly modified to be used for any Stock Exchange.
  
  Portfolio analysis Engine would work on the fundamentals of a company and return a score based on fundamental strength.
  TechAnalysisEngine on the other hand would return the score based on performance of the stock in past 5 years.
  
  After all the analysis an Excel Sheet would be generated for the user in decreasing order of good_to_invest companies.
  There are multiple columns in the sheet representing scores in multiple areas.
