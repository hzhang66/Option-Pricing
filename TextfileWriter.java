package OptionPricing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class TextfileWriter {
	OutputStreamWriter ops = null;
	BufferedWriter bw=null;
	String filename;
	String content;
	public TextfileWriter(String name){
		this.filename=name;		
    }
	
	public void writeLine(String str){
		content=content+str+'\n';
    }
	public void writeLine(String str,double num){
		String tostr = str+num;
		content=content+tostr+'\n';
    }
	public void writeLine(String str,int num){
		String tostr = str+num;
		content=content+tostr+'\n';
    }
	public void finish() {
		try {
	        File file = new File("/Users/auroracappadocian/Downloads/week1", filename + ".txt");
	        file.createNewFile();
	        ops = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
	        bw = new BufferedWriter(ops);
	        bw.write(content);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (bw != null) {
	                bw.flush();
	                bw.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } 
	}
}
