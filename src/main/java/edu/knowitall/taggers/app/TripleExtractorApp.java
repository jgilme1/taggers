package edu.knowitall.taggers.app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jdom2.JDOMException;

import edu.knowitall.taggers.Type;
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
			
			
			String inputFileString = FileUtils.readFileToString(new File(inputPath));
			
			PrintWriter pw = new PrintWriter(new File(outputPath));
			
	        OpenNlpChunker chunker = new OpenNlpChunker();
	        MorphaStemmer morpha = new MorphaStemmer();

			
			for(String line : inputFileString.split("\n")){
				
	            List<ChunkedToken> chunkedSentence = scala.collection.JavaConverters.seqAsJavaListConverter(chunker.chunk(line)).asJava();
	            List<Lemmatized<ChunkedToken>> tokens = new ArrayList<Lemmatized<ChunkedToken>>(chunkedSentence.size());
	            for (ChunkedToken token : chunkedSentence) {
	                Lemmatized<ChunkedToken> lemma = morpha.lemmatizeToken(token);
	                tokens.add(lemma);
	            }
	            
	            pw.write(line+"\n");
	            
	            List<Type> types = t.tag(tokens);
	            for (Type type : types) {
	                pw.write(type + "\n");
	            }
	            pw.write("\n");
				
			}
			
			pw.close();
	}
}
			
