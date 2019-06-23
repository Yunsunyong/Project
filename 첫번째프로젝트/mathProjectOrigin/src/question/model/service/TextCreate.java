package question.model.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class TextCreate {

	public int textCreate(String savePath, String semester, String book, String chapter, ArrayList<String> arr2 ,String title) {
		 int result =0;
		  String fileName = title+".txt";
	      File file = new File(savePath + File.separator + fileName);
	      String str = savePath + File.separator + fileName;
	      System.out.println(str);
	      try(BufferedWriter bw 
	         = new BufferedWriter(
	            new FileWriter(file))){ 
	    	 bw.write("제목 : "+title);
	    	 bw.newLine();
	         bw.write("학기 명 : " + semester + ", 교재 명 : " + book + ", 챕터 명 :" + chapter);
	         bw.newLine();
	         for(int i = 0 ; i<arr2.size();i++) {
	        		bw.write(arr2.get(i)+" ");
	        		if((i+1)%4==0){
	        			bw.newLine();
	        		}
	        	}
	         bw.newLine();		         
	         bw.flush();
	         System.out.println("파일에 성공적으로 저장되었습니다.");
	         result=1;
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      return result;
	
	}


}
