/**
 * 下午02:29:44
 */
package org.adaikiss.kay.trys.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

/**
 * hlw
 * 
 */
public class AnalyzerTry {

	/**
	 * WhitespaceAnalyzer(),
	 * 
	 * SimpleAnalyzer(),
	 * 
	 * StopAnalyzer(),
	 * 
	 * StandardAnalyzer(),
	 * 
	 * MIK_CAnalyzer(), //需要引入IKAnalyzer.jar
	 * 
	 * ChineseAnalyzer(),
	 * 
	 * CJKAnalyzer(),
	 * 
	 * ThesaurusAnalyzer() //需要引入Thesaurus.jar
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Analyzer analyzer = new ChineseAnalyzer();
		TokenStream tokenStream = analyzer.reusableTokenStream(null,
				new StringReader("中华人民共和国公民"));
		  TermAttribute termAtt = (TermAttribute)tokenStream.getAttribute(TermAttribute.class);
		  //TypeAttribute typeAtt = (TypeAttribute)tokenStream.getAttribute(TypeAttribute.class);
		  while (tokenStream.incrementToken())
		  {
		      System.out.println(termAtt.term());
		      //System.out.println(typeAtt.type());
		  }
	}

}
