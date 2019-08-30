import java.io.*;
import java.util.*;

public class RemoveComments {

    private static String READ_FILE_FROM_PATH ="C:\\Users\\Bolt\\Desktop\\hello1.c" ;
    private static String WRITE_TO_FILE_PATH = "C:\\Users\\Bolt\\Desktop\\sam.c";
    private static String COMMENT_PATTERN_1 = "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)";
    private static String COMMENT_PATTERN_2 = "(?m)^\\s*$[\n\r]{1,}";
    private static String[] keywords = {"auto","break","casechar","const","continue","default","do","double","else","enum","extern","float","for","goto","if",
            "int","long","register","return","short","signed","sizeof","static","struct","switch",
            "typedef","union","unsigned","void","volatile","char","while","include","main","printf"};
    private static String IDENTIFER_PATTERN="[A-Za-z][A-Za-z0-9_]*";
    public static String Regex="[0-9.]*";
    public static String[] datatypes= {"int","float","double","char"};
 
    public static void main(String[] args)throws Exception {

        String contentFromFile = readFile(READ_FILE_FROM_PATH);
        String contentToWrite = replaceComments(contentFromFile);
        writeToFile(WRITE_TO_FILE_PATH, contentToWrite);
        printKeywordsAndIdentifiers(contentToWrite);
    }

    private static String readFile(String path) throws Exception{
        String st,data = " ";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((st = br.readLine()) != null) {
            data = data+"\n"+st;
        }
        return data;
    }

    private static String replaceComments(String contentFromFile) {
        return contentFromFile.replaceAll(COMMENT_PATTERN_1,"").replaceAll(COMMENT_PATTERN_2, "");
    }

    private static void printKeywordsAndIdentifiers(String contentToWrite) {
        HashSet<String> keys = new HashSet<>(Arrays.asList(keywords));
        HashSet<String> allKeywordsPresent = new HashSet<>();
        ArrayList<String> allIdentifiersPresent = new ArrayList<>();
        HashSet<String> data = new HashSet<>(Arrays.asList(datatypes));
        ArrayList<String> allDatatypesPresent=new ArrayList<>();
        ArrayList<String> Tokens=new ArrayList<>();
        StringTokenizer st = new StringTokenizer(contentToWrite,", ,\n,<,>,=,;,(,)");
        while(st.hasMoreTokens()) {
            String curToken = st.nextToken();
           Tokens.add(curToken);
            if (keys.contains(curToken)) 
             allKeywordsPresent.add(curToken);
            else if(curToken.matches(IDENTIFER_PATTERN)) {
                allIdentifiersPresent.add(curToken);
                //allDatatypesPresent.add(previous_datatype);
            }
            if(data.contains(curToken))
          	  allDatatypesPresent.add(curToken);
        }
        ListIterator<String> values=Tokens.listIterator();
        ArrayList<String> allValuesPresent=new ArrayList<>();
        while(values.hasNext())  
        {
        	String tok=values.next();
        	if(allIdentifiersPresent.contains(tok))
        	{
        		  String value=values.next();
                  if(value.matches(Regex)||value.startsWith(""))
                	  if(!value.matches(Regex))
                			  value=value.substring(1,value.length()-1);
                	  allValuesPresent.add(value);
                  if(allKeywordsPresent.contains(value))
                  {
                	  allValuesPresent.remove(value);
                	  }
        	}
      }
        Object[] a=allDatatypesPresent.toArray();
        Object[] b=allIdentifiersPresent.toArray();
        Object[] c=allValuesPresent.toArray();
        int index=0;
        System.out.println("Index\t\tIdentifiers\t\t\tDatatType\t\t\t\tValues\n"+"");
         for(index=0;index<allDatatypesPresent.size();index++) {
         	System.out.print(index+"\t\t"+b[index]+"\t\t\t\t"+a[index]+"\t\t\t\t\t"+c[index]+"\n");
         }
         System.out.println("Keywords: " + allKeywordsPresent);
         System.out.println("Identifiers: " + allIdentifiersPresent);
     
    }
    
    private static void writeToFile(String path, String contentToWrite) throws Exception{
        BufferedWriter writer =  new BufferedWriter(new PrintWriter(path));
        writer.write(contentToWrite);
        writer.close();
    }
}