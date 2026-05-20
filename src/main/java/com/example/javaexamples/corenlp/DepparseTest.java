package com.example.javaexamples.corenlp;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.simple.Sentence;

import java.util.Properties;
import java.util.stream.Collectors;

public class DepparseTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,pos,lemma,ner,depparse");
        // disable fine grained ner
//        props.setProperty("ner.applyFineGrained", "false");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument doc = pipeline.processToCoreDocument("Does the legacy project have COBOL programs and Copybooks?");
        CoreSentence sentence = doc.sentences().get(0);
        SemanticGraph semanticGraph = sentence.dependencyParse();
        System.out.println(semanticGraph);

//
//        for (CoreLabel tok : document.tokens()) {
//            System.out.println(String.format("%s\t%s", tok.word(), tok.tag()));
//        }

//        for (CoreEntityMention em : doc.entityMentions())
//            System.out.println("\tdetected entity: \t"+em.text()+"\t"+em.entityType());
//        System.out.println("---");
//        System.out.println("tokens and ner tags");
//        String tokensAndNERTags = doc.tokens().stream().map(token -> "("+token.word()+","+token.ner()+")").collect(
//                Collectors.joining(" "));
//        System.out.println(tokensAndNERTags);



    }
}
