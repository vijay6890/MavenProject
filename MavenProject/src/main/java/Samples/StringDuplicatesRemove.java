package Samples;

public class StringDuplicatesRemove {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "We learn java basics as part of java sessions in java week1";
		
		    String[] split = text.split(" ");
		    int count=0;	
		    String temp="";
		    for (int i = 0; i < split.length; i++) {
		    	 for (int j = i+1; j < split.length; j++) {
		    		 
		    		 if(split[i].equalsIgnoreCase(split[j]))
		    		 {
		    			 count++;
		    			 temp=split[j];
					
				}
		    	
		    	 }
		    	
		    	 
				
			}
		    if(count>0)
		    {
		    	System.out.println(text.replace(temp, ""));
		    
		    
		    }
		

}
}
