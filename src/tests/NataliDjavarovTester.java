package tests;

import api.*;

public class NataliDjavarovTester {
    public static void main(String[] args) {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex(1);
        node_data n2 = new Vertex(2);
        node_data n3 = new Vertex(3);
        node_data n4 = new Vertex(4);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.connect(1,2,3.5);
        g.connect(1,2,3.5);
        g.connect(2,1,3.5);
        g.connect(1,2,4.0);
        g.connect(1,3,3.5);
        g.connect(3,4,3.5);

        for (node_data n : g.getV()){
            System.out.println();
            System.out.print("key is:  "+n.getKey()+"->");
            for (edge_data edge: g.getE(n.getKey())){
                System.out.print(edge.getDest()+",");
            }
        }
        System.out.println(g.edgeSize());
    }



}
