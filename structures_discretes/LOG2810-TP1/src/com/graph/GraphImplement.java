package com.graph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import com.sections.Section;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GraphImplement<VertexFactory, vFactory> {

    private WeightedMultigraph<Integer, DefaultWeightedEdge> graph;

    /**
     * Constructor of an oriented graph of the read sections.
     * @param sectionsInFile containing the different sections read from the file
     */
    public GraphImplement (HashSet<Section> sectionsInFile) throws IOException {
        graph = new  WeightedMultigraph<>(DefaultWeightedEdge.class);

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

    /**
     * Function to generate a frame containing the graph
     */
    public void generate()
    {
        JFrame frame = new JFrame("Graph Visualisation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter =
                new JGraphXAdapter<String, DefaultWeightedEdge>(new DefaultListenableGraph(graph));

        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        frameDesign(frame, graphAdapter);
    }

    /**
     * Function to add all the edges with their respective weights to the graph
     * @param frame frame (window) containing a graph
     * @param graphAdapter adapter containing the graph to be shown on the window
     */
    private void frameDesign(JFrame frame, JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter)
    {
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        frame.setContentPane(contentPanel);
        frame.add(new mxGraphComponent(graphAdapter));
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

}
