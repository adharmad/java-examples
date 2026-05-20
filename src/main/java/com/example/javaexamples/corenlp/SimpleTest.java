package com.example.javaexamples.corenlp;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

import java.util.List;

public class SimpleTest {
    public static void main(String[] args) {
        Document doc = new Document("Lucy is in the sky with diamonds.");

        for (Sentence sent : doc.sentences()) {
            List<String> nerTags = sent.nerTags();  // [PERSON, O, O, O, O, O, O, O]
            String firstPOSTag = sent.posTag(0);   // NNP

            System.out.println(nerTags);
        }
    }
}
