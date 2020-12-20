package api;

import java.util.HashSet;
import java.util.Set;

public class Vertex implements node_data {
    private geo_location geo;
    private double weight;
    private int tag = 0;
    private int key;
    private String info;
    private static int id = 0;

    public Vertex(){
        this.key = id++;
    }
    public Vertex(int key) {
        this.key = key;
        this.geo = new GeoLocation(0,0,0);
    }


    /**
     * Returns the key (id) associated with this node.
     *
     * @return int key
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * Returns the location of this node, if
     * none return null.
     *
     * @return geo_location
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
     * @return double weight
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * Allows changing this node's weight.
     *
     * @param w - the new weight
     */
    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    /**
     * Returns the remark (meta data) associated with this node.
     *
     * @return String info
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     *
     * @param s - the new info
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
     * Allows setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
    this.tag = t;
    }
}
