package api;

import com.google.gson.*;
import gameClient.util.Point3D;
import netscape.javascript.JSObject;
import api.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class DWGraph_Algo implements dw_graph_algorithms {
    private directed_weighted_graph graph;

    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(directed_weighted_graph g) {
        this.graph = g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return directed_weighted_graph - the graph in this class
     */
    @Override
    public directed_weighted_graph getGraph() {
        return this.graph;
    }

    /**
     * Compute a deep copy of this weighted graph.
     *
     * @return directed_weighted_graph - deep copy of the graph in the class
     */
    @Override
    public directed_weighted_graph copy() {
        directed_weighted_graph graph = new DWGraph_DS();
        for (node_data n : this.graph.getV()) {
            node_data node = new Vertex(n.getKey());
            graph.addNode(node);
        }
        for (node_data n : this.graph.getV()) {
            for (edge_data e : this.graph.getE(n.getKey())) {
                graph.connect(e.getSrc(), e.getDest(), e.getWeight());
            }
        }
        return graph;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from each node to each
     * other node.
     *
     * @return true - if the graph is connected and false if the graph is not connected
     */
    @Override
    public boolean isConnected() {
//        directed_weighted_graph runOnIt = copy();
//        Collection<node_data> nodes = this.graph.getV();
//        Iterator<node_data> iter = nodes.iterator();
//        //List<node_data> queue = new ArrayList<>();
//        //bfs(this.g)
//        directed_weighted_graph newGraph = new DWGraph_DS();
//        for (node_data node : nodes) {
//            newGraph.addNode(node);
//            Collection<edge_data> edges = this.graph.getE(node.getKey());
//            for (edge_data e : edges) {
//                newGraph.connect(e.getDest(),e.getSrc(),e.getWeight());
//            }
//        }
//
//
//
//
//
//
//        return false;

        Iterator<node_data> iterator = this.graph.getV().iterator();
        if (!iterator.hasNext())
            return true;
        while(iterator.hasNext()) {
            int nodes_counter = this.BFS_Algorithm(iterator.next());
            if(nodes_counter != this.graph.nodeSize()) {
                return false;
            }
        }
        return true;


















}
    /**
     * returns the length of the shortest path between src to dest
     * @algorithm traversing the graph using BFS and updating the weight of each node
     * by its shortest distance from the src node and retrieving the weight of the dest node
     * @param src  - start node
     * @param dest - end (target) node
     * @return double - the minimal distance (by weight) from the src node to dest.
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if(this.graph.getNode(src) == null || this.graph.getNode(dest) == null){return -1;}
        Queue<node_data> q = new LinkedList<node_data>();
        for (node_data node : this.graph.getV()){
            node.setWeight(Double.MAX_VALUE);
        }
        node_data start = this.graph.getNode(src);
        start.setWeight(0);
        node_data end = this.graph.getNode(dest);

        q.add(start);
        while(q.size() != 0){
            node_data curr = q.poll();
            for (edge_data edge : this.graph.getE(curr.getKey())){
                node_data ni = graph.getNode(edge.getDest());
                double niw = ni.getWeight();
                double edgew = edge.getWeight() + curr.getWeight();
                if(niw > edgew){
                    ni.setWeight(edgew);
                    q.add(ni);
                    ni.setTag(curr.getKey());
                   // System.out.println(ni.getKey());
                }
            }

        }
        if(end.getWeight() != Double.MAX_VALUE){
            return end.getWeight();
        }


        return -1;
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * @algorithm using shortestPathDist method to mark each node tag
     * with its previous node's key and using getTheList - a private method that recursively
     * builds the list
     * @param src  - start node
     * @param dest - end (target) node
     * @return List<node_data> - list of the nodes in the path from src to dest.
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        double km = shortestPathDist(src,dest);
        if(km == -1 ){return null;}
        LinkedList<node_data> path1 = new LinkedList<node_data>();
        LinkedList<node_data> path = getTheList(this.graph.getNode(dest),src,path1);
      //  path.add(this.graph.getNode(src));

        return path;
    }
    private LinkedList<node_data> getTheList(node_data curr , int src , LinkedList<node_data> path ){
         if(curr.getKey() == src) {
             path.addFirst(curr);
         }
            else {
                path.addFirst(curr);
                return getTheList(this.graph.getNode(curr.getTag()),src,path);}

        return path;
}

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved, false if the file is unsuccessfully.
     */
    @Override
    public boolean save(String file) {
        Gson NataliJSON =new  Gson();
        JsonObject JsonedGraph = new JsonObject();
        JsonArray JsonedEdges = new JsonArray();
        JsonArray JsonedNodes = new JsonArray();
        JsonedGraph.add("Edges",JsonedEdges);
        JsonedGraph.add("Nodes",JsonedNodes);
        for (node_data node: this.graph.getV()){
            JsonObject JsonedNode = new JsonObject();
            JsonedNodes.add(JsonedNode);
            String pos = node.getLocation().x() + "," +node.getLocation().y()+","+node.getLocation().z();
            int id = node.getKey();
            JsonedNode.addProperty("pos",pos);
            JsonedNode.addProperty("id",id);
            for (edge_data edge : this.graph.getE(id)){
                JsonObject JsonedEdge = new JsonObject();
                JsonedEdges.add(JsonedEdge);
                int src = edge.getSrc();
                double w = edge.getWeight();
                int dest = edge.getDest();
                JsonedEdge.addProperty("src",src);
                JsonedEdge.addProperty("w",w);
                JsonedEdge.addProperty("dest",dest);

            }
        }
        try {
            System.out.println(NataliJSON.toJson(JsonedGraph));

            File MyFile = new File(file);
            FileWriter MyFileWriter = new FileWriter(file);
            boolean b = MyFile.createNewFile();
            if(b == true ){
                MyFileWriter.write(NataliJSON.toJson(JsonedGraph));
            }
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded, false if it was unsuccessfully loaded.
     */
    @Override
    public boolean load(String file) {
        Gson NataliGson = new Gson();
        directed_weighted_graph graph = new DWGraph_DS();
        try {
            File MyFile = new File(file);
            JsonObject js = new JsonParser().parse(new FileReader(file)).getAsJsonObject();
            // NataliGson.fromJson(MyFile.toString(),)
            for (JsonElement jsonedNode : js.getAsJsonArray("Nodes")) {
                int id = ((JsonObject) jsonedNode).get("id").getAsInt();
                node_data node = new Vertex(id);
                graph.addNode(node);
                String posString = ((JsonObject) jsonedNode).get("pos").getAsString();
                String[] locations = posString.split(",");
                double x = Double.parseDouble(locations[0]);
                double y = Double.parseDouble(locations[1]);
                double z = Double.parseDouble(locations[2]);
                geo_location pos = new Point3D(x, y, z);
                node.setLocation(pos);
            }
            for (JsonElement jsonedEdge : js.getAsJsonArray("Edges")) {
                int src = ((JsonObject) jsonedEdge).get("src").getAsInt();
                double w = ((JsonObject) jsonedEdge).get("w").getAsDouble();
                int dest = ((JsonObject) jsonedEdge).get("dest").getAsInt();
                graph.connect(src, dest, w);
            }
        }
            catch(FileNotFoundException e){
                return false;
            }

        return true;
    }


     private int BFS_Algorithm(node_data start) {
        Queue<node_data> q = new LinkedList<node_data>();
        int counter = 0;
        for (node_data node : graph.getV()){
            node.setTag(0);
        }
        q.add(start);
        counter++;
        while (!q.isEmpty()) {
            node_data node = q.poll();
            for (edge_data edge : graph.getE(node.getKey())){
                node_data ni = graph.getNode(edge.getDest());
                if(ni.getTag() == 0){
                    ni.setTag(1);
                    counter++;
                }
            }

        }


        return counter;
        }
}