package edu.knowitall.taggers.app;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;

import com.google.common.io.Files;

import scala.util.matching.Regex;
import scala.xml.XML;

import edu.knowitall.collection.immutable.Interval;
import edu.knowitall.taggers.Type;
import edu.knowitall.taggers.tag.Tagger;
import edu.knowitall.taggers.tag.TaggerCollection;
import edu.knowitall.tool.chunk.ChunkedToken;
import edu.knowitall.tool.chunk.OpenNlpChunker;
import edu.knowitall.tool.stem.Lemmatized;
import edu.knowitall.tool.stem.MorphaStemmer;

public class TripleExtractorApp {
	
	
	
	public static void main(String[] args) throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JDOMException, IOException{
		if(args.length != 3){
			System.err.println("App takes exactly 3 arguments:\n" +
								"1. path to taggers recursive directory\n" +
								"2. path to input text file with one sentence per line\n" +
								"3. path to output file");
			return;
		}
			String taggerPath = args[0];
			String inputPath = args[1];
			String outputPath = args[2];
			
			
			TaggerCollection t = TaggerCollection.fromPath(taggerPath);
			TaggerCollection newT = new TaggerCollection();
			
			String inputFileString = FileUtils.readFileToString(new File(inputPath));
			
			//create map from subdirectory name to list of xml pattern file names
			Map<String,List<String>> FileStringMap = new HashMap<String,List<String>>();
			File taggerDirectory = new File(taggerPath);
			List<File> dirList = Arrays.asList(taggerDirectory.listFiles());
			java.util.Collections.sort(dirList);
			
			for(File d: dirList){
				String key = d.getName();
				List<String> xmlList = new ArrayList<String>();
				if(d.isDirectory()){
					for(File x: d.listFiles()){
						if(x.getName().endsWith(".xml")){
							refactorPatterns(x,newT);
							xmlList.add(x.getName());
						}
					}
					if(!xmlList.isEmpty()){
					 FileStringMap.put(key,xmlList);
					}
				}
			}
			
			//create sorted subdirectory list
			List<String> directoryList = new ArrayList<String>(FileStringMap.keySet());
			java.util.Collections.sort(directoryList);

			
			
			
			PrintWriter pw = new PrintWriter(new File(outputPath));
			
			writeHeader(pw,directoryList);
			
	        OpenNlpChunker chunker = new OpenNlpChunker();
	        MorphaStemmer morpha = new MorphaStemmer();

			
			for(String line : inputFileString.split("\n")){
				
	            List<ChunkedToken> chunkedSentence = scala.collection.JavaConverters.seqAsJavaListConverter(chunker.chunk(line)).asJava();
	            List<Lemmatized<ChunkedToken>> tokens = new ArrayList<Lemmatized<ChunkedToken>>(chunkedSentence.size());
	            for (ChunkedToken token : chunkedSentence) {
	                Lemmatized<ChunkedToken> lemma = morpha.lemmatizeToken(token);
	                tokens.add(lemma);
	            }
	            
	            pw.write(line.trim()+"\t");
	            for(Lemmatized<ChunkedToken> tok: tokens){
	            	pw.write(tok.token().postag()+" ");
	            }	            
	            List<Type> types = newT.tag(tokens);
	            Map<String,List<Type>> levelMap = new HashMap<String,List<Type>>();
	            for (Type type : types) {
	            	String level = findLevel(type,taggerDirectory);
	            	//System.out.println(type.descriptor() + " " + level);
	            	if(levelMap.containsKey(level)){
	            	    levelMap.get(level).add(type);
	            	}
	            	else{
	            		List<Type> x = new ArrayList<Type>();
	                    x.add(type);
	            		levelMap.put(level,x);
	            	}
	            }
	            
	            for(String level: directoryList){
		            pw.write("\t");
		            
		            //sort thest Types by offset
		            if(levelMap.containsKey(level)){
	            	    List<Type> levelTypes = levelMap.get(level);
		            	java.util.Collections.sort(levelTypes, new Comparator<Type> (){
		            		public int compare(Type t1, Type t2){
		            			return t1.interval().compare(t2.interval());
		            		}
		            	});
		            	for(Type type: levelTypes){

		            		pw.write(type + " ");
		            		//if we are at the highest level write group matches as well
		            		if(directoryList.get(directoryList.size()-1) == level){
			            		Map<String,String> groupMap = type.groupMap();
			            		if(!groupMap.isEmpty()){
			            			for(String k : groupMap.keySet()){
			            				pw.write("\t"+k+"-"+groupMap.get(k));
			            				
			            			}
							pw.write("\t");
			            		}
		            		}
		            	}
	            	}
	            }
	            pw.write("\n");
				
			}
			
			pw.close();
	}
	
	private static void refactorPatterns(File x, TaggerCollection newT) throws IOException, JDOMException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException {
		String patternString = FileUtils.readFileToString(x);
		Pattern typePattern = Pattern.compile("\\[type=([^\\]]+)\\]\\+");
		
		
		Matcher typePatternMatcher = typePattern.matcher(patternString);
		int start =0;
		while(start < patternString.length() && typePatternMatcher.find(start)){
			String group1 = typePatternMatcher.group(1);
			String replacementString = "([typeStart="+group1+" &amp; typeEnd="+group1+"] | ([typeStart="+group1+"] [type="+group1+"]* [typeEnd="+group1+"]))";
			patternString = patternString.substring(0, typePatternMatcher.start()) + replacementString + patternString.substring(typePatternMatcher.end());
			start = typePatternMatcher.start() + replacementString.length();
			typePatternMatcher = typePattern.matcher(patternString);
		}
		
		InputStream is = new ByteArrayInputStream(patternString.getBytes());
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(is);
		for(Tagger t : new TaggerCollection(doc).getTaggers()){
			newT.addTagger(t);
		}
	}

	private static void writeHeader(PrintWriter pw, List<String> directoryList){		 
		pw.write("sentence\tpos");
		
		for(String subDirName: directoryList){
			pw.write("\t"+subDirName+"-tags");
		}
		
		pw.write("\tPattern components\n");
	}
	
	
	private static String findLevel(Type type, File taggerDirectory) throws IOException{
		
		List<String> typeDescriptors = new ArrayList<String>();
		typeDescriptors.add(type.descriptor());
		if(type.source() != null){
			typeDescriptors.add(type.source());
		}
		
		for(File subDir : taggerDirectory.listFiles()){
			if(subDir.isDirectory()){
				String level = subDir.getName();
				for(File x: subDir.listFiles()){
					String xString = FileUtils.readFileToString(x);
					for(String descriptionString : typeDescriptors){
						if(xString.indexOf("descriptor=\""+descriptionString+"\">") != -1){
							return level;
						}
						
					}
				}
			}
		}
		
		return "-1";
	}
}
			
