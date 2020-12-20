package api;

public class Edge implements edge_data {

    private node_data src;
    private node_data dst;
    private double weight;
    private int tag = 0;
    private String info;

    public Edge(node_data s, node_data d,double w) {
        this.weight = w;
        this.src = s;
        this.dst = d;
    }


    /**
     * The id of the source node of this edge.
     *
     * @return int src
     */
    @Override
    public int getSrc() {
        return this.src.getKey();
    }

    /**
     * The id of the destination node of this edge
     *
     * @return int dest
     */
    @Override
    public int getDest() {
        return this.dst.getKey();
    }

    /**
     * @return the weight of this edge (positive value).
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * Returns the remark (meta data) associated with this edge.
     *
     * @return String info
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * Allows changing the remark (meta data) associated with this edge.
     *
     * @param s - the info of the edge
     */
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     *
     * @return int tag
     */
    @Override
    public int getTag() {
        return this.tag;
    }

    /**
     * This method allows setting the "tag" value for temporal marking an edge - common
     * practice for marking by algorithms.
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
