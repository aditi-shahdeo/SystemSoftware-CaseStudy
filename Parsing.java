import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Parsing {
	private static String READ_FILE_FROM_PATH ="C:\\Users\\Bolt\\Desktop\\sam.c" ;
    /*public static String Declaration="[datatype variable_list;]";
    public static String datatype= "[int]|[float]|[double]|[char]";
    public static String variable_list="[variable|variable,variable_list|variable=value]";*/
    public static String variable="[A-Za-z][A-Za-z0-9_]*";
    public static String value="[0-9]*|[0-9.]*|[A-Za-z]*";
	  public static String[] datatypes= {"int","float","double","char"};
	public static void main(String[] args)throws Exception {
		String contentFromFile = readFile(READ_FILE_FROM_PATH);
		//parse(contentFromFile); 
	}
	 private static String readFile(String path) throws Exception{
	        String st,data = " ";
	        File file = new File(path);
	        BufferedReader br = new BufferedReader(new FileReader(file));

	        while ((st = br.readLine()) != null) {
	            data = st;
	            String parse_data=data.replaceAll("\\s","");
		       // System.out.println(parse_data);
		        parse(parse_data,data); 
	        }
	        return data;
	    }
	 private static void parse (String pdata,String data) throws Exception {
		 
		 if(pdata.startsWith("int")||pdata.startsWith("double")||pdata.startsWith("char")||pdata.startsWith("float")) {
			// System.out.println(pdata);
			 String a=pdata;
			 int len=pdata.length();
			 String iden="";
			 if(a.charAt(len-1)!=';')
				{
					System.out.println(data+"Semicolon Missing");
				}
			 else {
			 for(int i=data.indexOf(" ");((a.charAt(i)!=';'));i++)
			 {
				 iden=iden+a.charAt(i);
				 if(iden.matches(variable))
				 {
					 if(a.charAt(i+1)=='=') {
						 int l=(len-1)-(i+2);
						char[] val=new char[l];
					 a.getChars(i+2,len-1,val, 0);
					 String values=String.copyValueOf(val);
					 if(values.matches(""))
						 System.out.println(data+"value missing");
					 }
					 else if(a.charAt(i+1)==',') {
						 int l=(len-1)-(i+2);
							char[] val=new char[l];
						 a.getChars(i+2,len-1,val, 0);
						 String values=String.copyValueOf(val);
						 if(values.matches(""))
							 System.out.println(data+"identifier missing or extra character");
					 }
				 }
			 }
			
			 
		 }
			
			
				 
		 }
			
		 }
} 
