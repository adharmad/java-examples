package com.example.javaexamples.corenlp;

import lombok.Data;

import java.util.*;

import static com.example.javaexamples.corenlp.GraphNode.NodeTag.VERB;

@Data
public class GraphNode {
    public enum NodeTag {
        NOUN,
        PRONOUN,
        VERB,
        ADJECTIVE,
        ADVERB,
        ARTICLE,
        NUMBER,
        CONJUNCTION,
        PREPOSITION,
        PUNCTUATION,
        OTHER
        ;
    }

    private String word;
    private NodeTag nodeTag;
    private boolean isRoot = false;
    private Map<String, GraphNode> children;

    public GraphNode(String word, NodeTag nodeTag) {
        this.word = word;
        this.nodeTag = nodeTag;

        children = new LinkedHashMap<>();
    }

    public void addChild(GraphNode child) {
        children.put(child.getWord(), child);
    }

    public void prettyPrint() {
        prettyPrint(0);
    }

    public Set<String> getOtherVerbs() {
        Set<String> otherVerbs = new LinkedHashSet<>();
        accumulateOtherVerbs(otherVerbs);
        return otherVerbs;
    }

    private void accumulateOtherVerbs(Set<String> otherVerbs) {
        if (!isRoot && nodeTag == VERB) {
            otherVerbs.add(word);
        }

        children.values().forEach(child -> child.accumulateOtherVerbs(otherVerbs));
    }

    private void prettyPrint(int indent) {
        String tab = "\t".repeat(indent);
        System.out.println(tab + word + "(" + nodeTag.name() + ")");
        final int nextIndent = indent+1;
        children.values().stream().forEach(graphNode -> graphNode.prettyPrint(nextIndent));
    }
}
