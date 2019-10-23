package com.graph;

import com.sections.Section;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.generate.SimpleWeightedBipartiteGraphMatrixGenerator;
import org.jgrapht.graph.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GraphImplement<VertexFactory, vFactory> {

    private SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> graph;

    /**
     * Constructor of an oriented graph of the read sections.
     * @param sectionsInFile containing the different sections read from the file
     */
    public GraphImplement (HashSet<Section> sectionsInFile)
    {
        graph = new  SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        addAllSections(sectionsInFile);
        addAllLinks(sectionsInFile);
    }

    /**
     * Function to add all the different sections to the graph
     * @param sectionsInFile containing the different sections read from the file
     */
    private void addAllSections(HashSet<Section> sectionsInFile)
    {
        for(Section element : sectionsInFile)
            graph.addVertex(element.getSectionNumber_());
    }

    /**
     * Function to add all the edges with their respective weights to the graph
     * @param sectionsInFile containing the different sections read from the file
     */
    private void addAllLinks(HashSet<Section> sectionsInFile)
    {
        for(Section element : sectionsInFile)
        {
            HashMap<Integer, Integer> distances = element.getDistances();
            if(!distances.isEmpty())
                for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
                    DefaultWeightedEdge link = graph.addEdge(element.getSectionNumber_(), entry.getKey());
                    Integer value = entry.getValue();
                    graph.setEdgeWeight(link, 3);
                }
            }
    }

    public void generate()
    {
        SimpleWeightedBipartiteGraphMatrixGenerator<Integer, DefaultWeightedEdge> graphGenerator = new SimpleWeightedBipartiteGraphMatrixGenerator<>();
        //graphGenerator.generateGraph(graph, );
    }

}
