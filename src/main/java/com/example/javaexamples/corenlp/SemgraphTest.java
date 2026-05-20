package com.example.javaexamples.corenlp;

import edu.stanford.nlp.international.Language;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.GrammaticalRelation;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.Index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SemgraphTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,pos,lemma,ner,parse");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        String sentenceText = "Does the project have COBOL programs?";

        Annotation document = new Annotation(sentenceText);

        // run the pipeline
        pipeline.annotate(document);

        // iterate over the sentences
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            // get the dependency graph (enhanced dependencies are often better for this)
            SemanticGraph graph = sentence.get(SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation.class);

            // Find the root verb of the sentence
            IndexedWord rootVerb = graph.getFirstRoot();

            if (rootVerb != null) {
                // Find the subject using the nsubj relation
                IndexedWord subject = graph.getChildWithReln(rootVerb,
                        GrammaticalRelation.valueOf(Language.UniversalEnglish, "nsubj"));

                // Find the direct object using the dobj relation
                IndexedWord object = graph.getChildWithReln(rootVerb, GrammaticalRelation.valueOf("dobj"));

                System.out.println("Sentence: " + sentence.get(CoreAnnotations.TextAnnotation.class));
                System.out.println("Root Verb: " + rootVerb.word());
                if (subject != null) {
                    System.out.println("Subject: " + subject.word());
                } else {
                    System.out.println("Subject: Not found or different relation type");
                }
                if (object != null) {
                    System.out.println("Object: " + object.word());
                } else {
                    System.out.println("Object: Not found or different relation type (e.g., in a prepositional phrase or passive voice)");
                }
            }

            graph.prettyPrint();
        }
    }

}
