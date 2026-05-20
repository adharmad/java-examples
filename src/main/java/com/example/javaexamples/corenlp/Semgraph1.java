package com.example.javaexamples.corenlp;

import edu.stanford.nlp.international.Language;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.GrammaticalRelation;
import edu.stanford.nlp.util.CoreMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Semgraph1 {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,pos,lemma,ner,parse");
        // disable fine grained ner
//        props.setProperty("ner.applyFineGrained", "false");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        String sentenceText = "Give all filetypes ordered by linecount";

        Annotation document = new Annotation(sentenceText);

        // run the pipeline
        pipeline.annotate(document);

// iterate over the sentences
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            Map<String, IndexedWord> words = new HashMap<>();

            // get the dependency graph (enhanced dependencies are often better for this)
            SemanticGraph graph = sentence.get(SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation.class);

            // Find the root verb of the sentence
            IndexedWord rootVerb = graph.getFirstRoot();

            flatten(graph, rootVerb, words);

            for (String key : words.keySet()) {
                IndexedWord val = words.get(key);
                System.out.println(val.value());
            }
        }
    }

    public static void flatten(SemanticGraph graph, IndexedWord rootVerb, Map<String, IndexedWord> words) {
        List<IndexedWord> children = graph.getChildList(rootVerb);
        for (int i=0 ; i<children.size() ; i++) {
            IndexedWord child = children.get(i);
            words.put(child.toString(), child);
            flatten(graph, child, words);
        }
    }
}
