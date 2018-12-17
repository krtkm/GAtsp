package antcolonyoptimization.IO;

import antcolonyoptimization.Graph.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Helper class for reading the bays29 data set and converting it to a graph.
 */
public class Import {

    /**
     * Read the specified data set and return a graph based on the set.
     * @return          the graph representing the data set
     */
    
    
    public static Graph getGraph (double evaporationRate, int alpha, int beta) {

        String dataSetName;
        int startingLine;

        dataSetName = "hiddeninstance1ard.csv";
        startingLine = 1;

        String[] lines = read(dataSetName).split("\n");
        String[] words = lines[0].split(",");
//        String[] firstNumber = words.split(" ");
//        System.out.println(words[0]);
//        System.out.println(lines[0]);
//        System.out.println(lines[1]);
        int numOfCities = Integer.parseInt(words[0]);
        Vertex[] vertices = new Vertex[numOfCities];
        System.out.println("");
        System.out.println("Number of Cities : "+numOfCities);

        // Read each line and turn it into a Vertex.
        for (int i = startingLine; i < startingLine+numOfCities; i++) {
            String[] line = lines[i].split(",");
            int x = (int)Double.parseDouble(line[0]);
            int y = (int)Double.parseDouble(line[1]);
            Vertex v = new Vertex(String.valueOf(i), x, y);
//            System.out.println(String.valueOf(i) +" "+ lines[i]);
            vertices[i-startingLine] = v;
//            System.out.println(i-startingLine);
//            System.out.println(v);
        }

        

        Graph graph = new Graph(evaporationRate, alpha, beta);

        // Create the spine of the graph (the vertices).
        for (int i = 0; i < numOfCities; i++) {
            graph.addVertex(vertices[i]);
//            System.out.println(vertices[i]);
        }

        // Create the edges of the graph (connect every vertex to each other).
        for (Vertex v : graph) {
            for (int i = 0; i < numOfCities; i++) {
                if (vertices[i] != v) {
                    graph.addEdge(v, vertices[i]);
//                    System.out.println(v +" "+vertices[i]);
                }
            }
        }

        return graph;
    }


    
    /**
     * Removes duplicate what spaces in a String.
     * Example: "   2  3  3,2   " becomes " 2 3 3,2 "
     * @param s     the String to parse
     * @return      the String minus the duplicate white spaces
     */
    private static String removeWhiteSpace (String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && s.charAt(i-1) == ' ') {
                if (i != s.length()) {
                    s = s.substring(0, i) + s.substring(i+1, s.length());
                    i--;
                } else {
                    s = s.substring(0, i);
                    i--;
                }
            }
        }
        return s;
    }

    /**
     * Read from a file and load it to a String.
     * @param fileName  the name of the file to read (within the same root as this class)
     * @return          a String with the contents of the file
     */
    private static String read (String fileName) {
        InputStream stream = Import.class.getResourceAsStream(fileName);
        java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
