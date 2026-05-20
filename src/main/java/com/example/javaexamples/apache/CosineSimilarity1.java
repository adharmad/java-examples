package com.example.javaexamples.apache;

import org.apache.commons.text.similarity.CosineSimilarity;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CosineSimilarity1 {
    public static void main(String[] args) {
        CosineSimilarity documentsSimilarity = new CosineSimilarity();

        String text1 = "How was the Soteria risk score calculated?";
        String text2 = "How is the Soteria risk score calculated?";

        Map<CharSequence, Integer> vectorA = Arrays.stream(text1.split(" ")).collect(Collectors.toMap(
                character -> character, character -> 1, Integer::sum));
        Map<CharSequence, Integer> vectorB = Arrays.stream(text2.split(" ")).collect(Collectors.toMap(
                character -> character, character -> 1, Integer::sum));

        Double docABCosSimilarity = documentsSimilarity.cosineSimilarity(vectorA, vectorB);

        System.out.printf("%4.3f\n", docABCosSimilarity);
    }
}
