import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class DeclarationStatementHandler {
	private static String READ_FILE_FROM_PATH ="C:\\Users\\Bolt\\Desktop\\sam.c" ;
    public static String variable = "[A-Za-z][A-Za-z0-9_]*";
    public static String value = "[0-9]*|[0-9.]*|[A-Za-z]*";
    public static int flag=0;
   public static int lineNo=0;
	public static void main(String[] args)throws Exception {
		String contentFromFile = readAndParseFile(READ_FILE_FROM_PATH);
		
	}
	 private static String readAndParseFile(String path) throws Exception{
	        String st,data = " ";
	        File file = new File(path);
	        BufferedReader br = new BufferedReader(new FileReader(file));  

	        while ((st = br.readLine()) != null) {
	            data = st;
	            lineNo++;
	            String parseData=data.replaceAll("\\s","");
		        parse(parseData,data,lineNo); 
	        }
	        return data;
	    }
	 private static void parse (String parseData,String data,int lineNum) throws Exception {
		 
		 if(parseData.startsWith("int")||parseData.startsWith("double")||parseData.startsWith("char")||parseData.startsWith("float")) 
		 {
			 String a = parseData;
			 int len = parseData.length();
			 String iden = "";
			 if(a.charAt(len-1)!=';')
				{
					System.out.println("Syntax error at line "+lineNum+":"+data+" Semicolon Missing");
				}
			 else {
			 for(int i=data.indexOf(" ");((a.charAt(i)!=';'));i++)
			 {
				 iden = iden+a.charAt(i);
				 if(iden.matches(variable))
				 {
					 if(a.charAt(i+1)=='=') {
						 int result = syntaxCheck(i,len,a);
						 if(result==1)
							 System.out.println("Syntax error at line "+lineNum+":"+data+" value missing");
					 }
					 else if(a.charAt(i+1)==',') {
						 int result = syntaxCheck(i,len,a);
						 if(result==1)
							 System.out.println("Syntax error at line "+lineNum+":"+data+" identifier missing or extra character");
					 }
				 }
			 }
		 }
		}
	 }
	 private static int syntaxCheck(int i,int len,String a )
	 {
		 flag=0;
		 int l = (len-1)-(i+2);
		 char[] val =  new char[l];
		 a.getChars(i+2,len-1,val,0);
		 String values=String.copyValueOf(val);
		 if(values.matches(""))
			 flag=1;
		return flag;
	 }
}
