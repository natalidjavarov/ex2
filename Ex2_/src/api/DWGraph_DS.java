package api;

import scala.Int;

import java.util.*;

public class DWGraph_DS implements directed_weighted_graph {

    private Map<Integer, node_data> nodes = new HashMap<>();
    private Map<Integer, Map<Integer, edge_data>> edges = new HashMap<>();//edges out
    private Map<Integer, HashSet<Integer>> edgesi = new HashMap<>();// edges in
    private int modeCount = 0;
    private int edgesSize = 0;

    /**
     * returns the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        if (nodes.containsKey(key)) {
            return nodes.get(key);
        }
        return null;
    }

    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method should run in O(1) time.
     *
     * @param src
     * @param dest
     * @return
     */
    @Override
    public edge_data getEdge(int src, int dest) {
        if(nodes.containsKey(src) && nodes.containsKey(dest)) {
            if (this.edges.get(src).containsKey(dest)) {
                return this.edges.get(src).get(dest);
            }
        }
        return null;
    }

    /**
     * adds a new node to the graph with the given node_data.
     * Note: this method should run in O(1) time.
     *
     * @param n
     */
    @Override
    public void addNode(node_data n) {
        if(!this.nodes.containsKey(n.getKey())) {
            this.nodes.put(n.getKey(), n);
            this.edges.put(n.getKey(),new HashMap<Integer, edge_data>());
            this.edgesi.put(n.getKey(),new HashSet<Integer>());
        }
    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * * Note: this method should run in O(1) time.
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if(w < 0){
            System.err.println("weight < 0");
            return;
        }
        // invalid nodes
        if (!this.nodes.containsKey(src) || !this.nodes.containsKey(dest)) {
            System.err.println("one of the does not exist in the graph");
            return;
        }
        //exist edge
        else {
            if (getEdge(src, dest) == null) {
                edgesSize++;
            } else {
                edgesi.get(dest).remove(src);
            }
            edges.get(src).put(dest, new Edge(nodes.get(src), nodes.get(dest), w));
            edgesi.get(dest).add(src);
            modeCount++;
        }
//
//
//
//         else { // does not exist
//            node_data s = this.nodes.get(src);
//            node_data d = this.nodes.get(dest);
//            edge_data e = new Edge(w, s, d);
//            Map<Integer, edge_data> edgeMap = new HashMap<>();
//            edgeMap.put(dest, e);
//            this.edges.put(src, edgeMap);
//            edgesSize++;
//            modeCount++;

        }


    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV() {
        return this.nodes.values();

    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the edges getting out of
     * the given node (all the edges starting (source) at the given node).
     * Note: this method should run in O(k) time, k being the collection size.
     *
     * @param node_id
     * @return Collection<edge_data>
     */
    @Override
    public Collection<edge_data> getE(int node_id) {
        Collection<edge_data> e = new HashSet<edge_data>();
        for (edge_data Edge: edges.get(node_id).values()){
            e.add(Edge);
        }
        return e;
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(k), V.degree=k, as all the edges should be removed.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_data removeNode(int key) {
        if (!this.nodes.containsKey(key)) {
            return null;
        }
        node_data v = this.getNode(key);
        for (edge_data ni : this.getE(key)) {
            this.edges.get(key).remove(ni.getDest());
        }
        for (Integer i : this.edgesi.get(key)){
            this.edges.get(i).remove(key);
        }
        this.edgesi.remove(key);
        return this.nodes.remove(key);
    }

    /**
     * Deletes the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param src
     * @param dest
     * @return the data of the removed edge (null if none).
     */
    @Override
    public edge_data removeEdge(int src, int dest) {
        if (!(getEdge(src, dest) == null)) {
            this.edgesi.get(dest).remove(src);
            this.edgesSize--;
            this.modeCount++;
        }
        return this.edges.get(src).remove(dest);
    }

    /**
     * Returns the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    /**
     * Returns the number of edges (assume directional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return edgesSize;
    }

    /**
     * Returns the Mode Count - for testing changes in the graph.
     *
     * @return
     */
    @Override
    public int getMC() {
        return 0;
    }

    public class Vertex implements node_data {

        private geo_location geo;
        private Set<node_data> vertices = new HashSet<>();
        private double weight;
        private int tag = 0;
        private int key;
        String info;

        public Vertex(GeoLocation g, int k) {
            this.geo = g;
            this.key = k;
        }


        /**
         * Returns the key (id) associated with this node.
         *
         * @return
         */
        @Override
        public int getKey() {
            return this.key;
        }

        /**
         * Returns the location of this node, if
         * none return null.
         *
         * @return
         */
        @Override
        public geo_location getLocation() {
            return this.geo;
        }

        /**
         * Allows changing this node's location.
         *
         * @param p - new new location  (position) of this node.
         */
        @Override
        public void setLocation(geo_location p) {
            this.geo = p;
        }

        /**
         * Returns the weight associated with this node.
         *
         * @return
         */
        @Override
        public double getWeight() {
            return weight;
        }

        /**
         * Allows changing this node's weight.
         *
         * @param w - the new weight
         */
        @Override
        public void setWeight(double w) {
            weight = w;
        }

        /**
         * Returns the remark (meta data) associated with this node.
         *
         * @return
         */
        @Override
        public String getInfo() {
            return info;
        }

        /**
         * Allows changing the remark (meta data) associated with this node.
         *
         * @param s
         */
        @Override
        public void setInfo(String s) {
        this.info = s;
        }

        /**
         * Temporal data (aka color: e,g, white, gray, black)
         * which can be used be algorithms
         *
         * @return
         */
        @Override
        public int getTag() {
            return this.tag;
        }

        /**
         * Allows setting the "tag" value for temporal marking an node - common
         * practice for marking by algorithms.
         *
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(int t) {

        }


    }
}
