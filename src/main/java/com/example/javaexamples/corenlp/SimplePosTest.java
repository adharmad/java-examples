package com.example.javaexamples.corenlp;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

import java.util.List;
import java.util.Properties;

public class SimplePosTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,pos");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        //Document doc = new Document("Lucy is in the sky with diamonds.");
        //CoreDocument document = pipeline.processToCoreDocument("Does the legacy project have COBOL programs and Copybooks?");
        CoreDocument document = pipeline.processToCoreDocument("Give top 10 programs by average complexity 100");
        //CoreDocument document = pipeline.processToCoreDocument("How is the score for Soteria, composition, complexity, dependency, Vulnerability and portability calculated?");

        for (CoreLabel tok : document.tokens()) {
            System.out.println(String.format("%s\t%s", tok.word(), tok.tag()));
        }
    }
}
