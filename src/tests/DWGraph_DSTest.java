package tests;

import api.*;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_DSTest {


    @org.junit.jupiter.api.Test
    void getNode() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex();
        g.addNode(n1);
     node_data testN =  g.getNode(n1.getKey());
     node_data testN1 =  g.getNode(240);
     assertNotNull(testN, "Null");// not null
     assertNull(testN1,"Not Null");// null
    }

    @org.junit.jupiter.api.Test
    void getEdge() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex();
        node_data n2 = new Vertex();
        g.addNode(n1);
        g.addNode(n2);
        g.connect(n1.getKey(),n2.getKey(),3.0);
        g.connect(n1.getKey(),n2.getKey(),4.0);
        g.connect(n2.getKey(),n1.getKey(),4.5);
        edge_data edge1 = g.getEdge(n1.getKey(),n2.getKey());
        edge_data edge2 = g.getEdge(n2.getKey(),n1.getKey());
        assertEquals(4.0,edge1.getWeight(),"error in getEdge ");
        assertEquals(4.5,edge2.getWeight(),"error in getEdge ");

    }

    @org.junit.jupiter.api.Test
    void addNode() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex();
        g.addNode(n1);
        g.addNode(n1);
    node_data n = g.getNode(n1.getKey());
    assertEquals(n1.getKey(),n.getKey(),"error in addNode");


    }

    @org.junit.jupiter.api.Test
    void connect() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex();
        node_data n2 = new Vertex();
        g.addNode(n1);
        g.addNode(n2);
      edge_data edge1  = g.getEdge(n1.getKey(), n2.getKey());
        assertNull(edge1, "error in connect");
        g.connect(n1.getKey(), n2.getKey(), 3.5);
        edge_data edge2 = g.getEdge(n2.getKey(), n1.getKey());
        assertNull(edge2,"error in connect");
        edge_data edge3  = g.getEdge(n1.getKey(), n2.getKey());
        assertEquals(3.5,edge3.getWeight(),"error in connect");
        g.connect(n1.getKey(), n2.getKey(), 4.5);
       edge_data edge4 =g.getEdge(n1.getKey(),n2.getKey());
       assertEquals(4.5,edge4.getWeight(),"error in connect");
       g.connect(n2.getKey(), n1.getKey(), 5.5);
       edge_data edge5 = g.getEdge(n2.getKey(),n1.getKey());
       assertEquals(5.5,edge5.getWeight(),"error in connect" );

    }

    @org.junit.jupiter.api.Test
    void getV() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex(1);
        g.addNode(n1);
        for( node_data node:g.getV()){
            assertEquals(1,node.getKey(),"error in getV");
        }

    }

    @org.junit.jupiter.api.Test
    void getE() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex();
        node_data n2 = new Vertex();
        g.addNode(n1);
        g.addNode(n2);
        g.connect(n1.getKey(), n2.getKey(), 6.5);
        for(edge_data edge: g.getE(n1.getKey())){
            assertEquals(6.5,edge.getWeight(),"error in getE");

        }

    }

    @org.junit.jupiter.api.Test
    void removeNode() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex(1);
        node_data n2 = new Vertex(2);
        node_data n3 = new Vertex(3);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.connect(n1.getKey(), n2.getKey(), 10);
        g.connect(n2.getKey(), n3.getKey(), 8);
        g.getE(n1.getKey());
        for (edge_data n : g.getE(n1.getKey())) {
            assertEquals(2, n.getDest(), "error in removeNode");
        }
        assertEquals(true, g.getV().contains(n2));
        g.removeNode(n2.getKey());
        for (edge_data n : g.getE(n1.getKey())) {
            assertEquals(false, n.getDest(), "error in removeNode");
        }
        assertEquals(false, g.getV().contains(n2));
    }
    @org.junit.jupiter.api.Test
    void removeEdge() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex();
        node_data n2 = new Vertex();
        node_data n3 = new Vertex();
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.connect(n1.getKey(),n2.getKey(),7.5);
        g.connect(n2.getKey(),n1.getKey(),6.5);
        g.removeEdge(n1.getKey(),n2.getKey());
        edge_data edge1 = g.getEdge(n1.getKey(), n2.getKey());
        assertNull(edge1,"error in removeEdge");
        edge_data edge2 = g.getEdge(n2.getKey(), n1.getKey());
        assertEquals(6.5,edge2.getWeight(),"error in removeEdge");


        }


    @org.junit.jupiter.api.Test
    void nodeSize() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex();
        node_data n2 = new Vertex();
        g.addNode(n1);
        g.addNode(n1);
        g.addNode(n1);
        g.addNode(n1);
        g.addNode(n2);
        assertEquals(2,g.nodeSize(),"error in nodeSize");
        g.removeNode(n1.getKey());
        assertEquals(1,g.nodeSize(),"error in nodeSize after remove");

    }

    @org.junit.jupiter.api.Test
    void edgeSize() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex();
        node_data n2 = new Vertex();
        g.addNode(n1);
        g.addNode(n2);
        edge_data edge1 = g.getEdge(n1.getKey(),n2.getKey());
        assertNull(edge1,"error in edgeSize");
       g.connect(n1.getKey(), n2.getKey(), 8.9);
       g.connect(n1.getKey(), n2.getKey(), 7.6);
       assertEquals(1,g.edgeSize(),"error in edgeSize after connect");
       g.connect(n2.getKey(),n1.getKey(),7.9);
       assertEquals(2,g.edgeSize(),"error in edgeSize after add edge2");
       g.removeEdge(n1.getKey(), n2.getKey());
       assertEquals(1,g.edgeSize(),"error in edgeSize after removeEdge");
  g.removeEdge(n1.getKey(), n2.getKey());
        assertEquals(1,g.edgeSize(),"error in edgeSize after removeEdge");


    }

    @org.junit.jupiter.api.Test
    void getMC() {
        directed_weighted_graph g = new DWGraph_DS();
        node_data n1 = new Vertex();
        node_data n2 = new Vertex();
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n2);
        assertEquals(2,g.getMC(),"error in getMC");
        g.connect(n1.getKey(),n2.getKey(),8.5);
        g.connect(n1.getKey(),n2.getKey(),8.5);
        assertEquals(4,g.getMC(),"error in getMC");
    }
}