package com.example.javaexamples.corenlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

import static com.example.javaexamples.corenlp.GraphNode.NodeTag.*;
import static java.util.Map.entry;

public class Semgraph2 {

    public static final Map<String, GraphNode.NodeTag> POS_MAP = Map.ofEntries(
            entry("CC", CONJUNCTION),
            entry("CD", NUMBER),
            entry("DT", ARTICLE),
            entry("EX", OTHER),
            entry("FW", OTHER),
            entry("IN", PREPOSITION),
            entry("JJ", ADJECTIVE),
            entry("JJR", ADJECTIVE),
            entry("JJS", ADJECTIVE),
            entry("LS", OTHER),
            entry("ND", OTHER),
            entry("NN", NOUN),
            entry("NNS", NOUN),
            entry("NNP", NOUN),
            entry("NNPS", NOUN),
            entry("PDT", OTHER),
            entry("POS", OTHER),
            entry("PRP", PRONOUN),
            entry("PRP$", PRONOUN),
            entry("RB", ADVERB),
            entry("RBR", ADVERB),
            entry("RBS", ADVERB),
            entry("RP", OTHER),
            entry("SYM", OTHER),
            entry("TO", OTHER),
            entry("UH", OTHER),
            entry("VB", VERB),
            entry("VBD", VERB),
            entry("VBG", VERB),
            entry("VBN", VERB),
            entry("VBP", VERB),
            entry("VBZ", VERB),
            entry("WDT", ARTICLE),
            entry("WP", PRONOUN),
            entry("WP$", PRONOUN),
            entry("WRB", ADVERB),
            entry(".", PUNCTUATION),
            entry("?", PUNCTUATION),
            entry(",", PUNCTUATION),
            entry(";", PUNCTUATION),
            entry(":", PUNCTUATION)
    );

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,pos,lemma,ner,parse");
        // disable fine grained ner
//        props.setProperty("ner.applyFineGrained", "false");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        //String sentenceText = "Find and return all the filetypes ordered by linecount in ascending order.";
        String sentenceText = "Find and return highest 10 the filetypes ordered by linecount in ascending order.";

        Annotation document = new Annotation(sentenceText);

        // run the pipeline
        pipeline.annotate(document);

// iterate over the sentences
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            //Map<String, IndexedWord> words = new HashMap<>();

            // get the dependency graph (enhanced dependencies are often better for this)
            SemanticGraph semanticGraph = sentence.get(SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation.class);

            // Find the root verb of the sentence
            IndexedWord rootVerb = semanticGraph.getFirstRoot();
            GraphNode graph = new GraphNode(rootVerb.value(), POS_MAP.get(rootVerb.tag()));
            graph.setRoot(true);

            flatten(semanticGraph, rootVerb, graph);

            graph.prettyPrint();
            System.out.println("");
            Set<String> otherVerbs = graph.getOtherVerbs();
            System.out.println(otherVerbs);
        }
    }

    public static void flatten(SemanticGraph semanticGraph, IndexedWord rootVerb, GraphNode graph) {
        List<IndexedWord> children = semanticGraph .getChildList(rootVerb);
        for (int i = 0; i < children.size(); i++) {
            IndexedWord child = children.get(i);
            GraphNode childGraph = new GraphNode(child.value(), POS_MAP.get(child.tag()));
            graph.addChild(childGraph);
            flatten(semanticGraph, child, childGraph);
        }
    }


}
