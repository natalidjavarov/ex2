package tests;

import api.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_AlgoTest {


    @Test
    void copy() {
    }

    @Test
    void isConnected() {
        directed_weighted_graph gA = new DWGraph_DS();
        dw_graph_algorithms gAlgo = new DWGraph_Algo();
        gAlgo.init(gA);
        node_data n1 = new Vertex(0);
        node_data n2 = new Vertex(1);
gA.addNode(n1);
gA.addNode(n2);
       gA.connect(n1.getKey(), n2.getKey(), 7.9);
       boolean b = gAlgo.isConnected();
       assertEquals(false,b,"error in isConnected");
       gA.connect(n2.getKey(),n1.getKey(),8.8);
        boolean b2 = gAlgo.isConnected();
       assertEquals(true,b2,"error in isConnected");
       directed_weighted_graph gNew = new DWGraph_DS();
        dw_graph_algorithms gAlgo2 = new DWGraph_Algo();
        gAlgo2.init(gNew);
        node_data n11 = new Vertex(0);
        node_data n22 = new Vertex(1);
        node_data n3 = new Vertex(2);
        node_data n4 = new Vertex(3);
        node_data n5 = new Vertex(4);
        node_data n6 = new Vertex(5);
        node_data n7 = new Vertex(6);
        node_data n8 = new Vertex(7);
        node_data n9 = new Vertex(8);
        node_data n10 = new Vertex(9);
        gNew.addNode(n11);
        gNew.addNode(n22);
        gNew.addNode(n3);
        gNew.addNode(n4);
        gNew.addNode(n5);
        gNew.addNode(n6);
        gNew.addNode(n7);
        gNew.addNode(n8);
        gNew.addNode(n9);
        gNew.addNode(n10);
        gNew.connect(0,2,2);
        gNew.connect(8,0,1);
        gNew.connect(8,1,5);
        gNew.connect(1,4,3);
        gNew.connect(4,7,1);
        gNew.connect(7,6,3);
        gNew.connect(6,5,2);
        gNew.connect(2,5,9);
        gNew.connect(3,2,4);
        gNew.connect(3,6,4);
        gNew.connect(3,4,2);
        gNew.connect(8,3,9);
        boolean b3 = gAlgo2.isConnected();
        assertEquals(false,b3,"error in isConnected");

    }

    @Test
    void shortestPathDist() {
        directed_weighted_graph gA = new DWGraph_DS();
        dw_graph_algorithms gAlgo = new DWGraph_Algo();
        gAlgo.init(gA);
        node_data n1 = new Vertex(0);
        node_data n2 = new Vertex(1);
        node_data n3 = new Vertex(2);
        node_data n4 = new Vertex(3);
        node_data n5 = new Vertex(4);
        node_data n6 = new Vertex(5);
        node_data n7 = new Vertex(6);
        node_data n8 = new Vertex(7);
        node_data n9 = new Vertex(8);
        node_data n10 = new Vertex(9);
        gA.addNode(n1);
        gA.addNode(n2);
        gA.addNode(n3);
        gA.addNode(n4);
        gA.addNode(n5);
        gA.addNode(n6);
        gA.addNode(n7);
        gA.addNode(n8);
        gA.addNode(n9);
        gA.addNode(n10);
        gA.connect(0,2,2);
        gA.connect(8,0,1);
        gA.connect(8,1,5);
        gA.connect(1,4,3);
        gA.connect(4,7,1);
        gA.connect(7,6,3);
        gA.connect(6,5,2);
        gA.connect(2,5,9);
        gA.connect(3,2,4);
        gA.connect(3,6,4);
        gA.connect(3,4,2);
        gA.connect(8,3,9);
      gA.connect(3,5,1000);
      double distance = gAlgo.shortestPathDist(3,5);
        System.out.println(distance);
      assertEquals(6,distance,"error in ShortestPathDist");
      gA.removeEdge(8,1);
      gA.removeEdge(3,1);
        double distance1 = gAlgo.shortestPathDist(3,1);
        assertEquals(-1,distance1,"error in ShortestPathDist after remove edge");
      double distance2 = gAlgo.shortestPathDist(5,5);
      assertEquals(0,distance2,"error in ShortestPathDist ");


    }

    @Test
    void shortestPath() {
        directed_weighted_graph gA = new DWGraph_DS();
        dw_graph_algorithms gAlgo = new DWGraph_Algo();
        gAlgo.init(gA);
        node_data n1 = new Vertex(0);
        node_data n2 = new Vertex(1);
        node_data n3 = new Vertex(2);
        node_data n4 = new Vertex(3);
        node_data n5 = new Vertex(4);
        node_data n6 = new Vertex(5);
        node_data n7 = new Vertex(6);
        node_data n8 = new Vertex(7);
        node_data n9 = new Vertex(8);
        node_data n10 = new Vertex(9);
        gA.addNode(n1);
        gA.addNode(n2);
        gA.addNode(n3);
        gA.addNode(n4);
        gA.addNode(n5);
        gA.addNode(n6);
        gA.addNode(n7);
        gA.addNode(n8);
        gA.addNode(n9);
        gA.addNode(n10);
        gA.connect(0,2,2);
        gA.connect(8,0,1);
        gA.connect(8,1,5);
        gA.connect(1,4,3);
        gA.connect(4,7,1);
        gA.connect(7,6,3);
        gA.connect(6,5,2);
        gA.connect(2,5,9);
        gA.connect(3,2,4);
        gA.connect(3,6,4);
        gA.connect(3,4,2);
        gA.connect(8,3,9);
        gA.connect(3,1,1000);
        LinkedList<node_data> list = (LinkedList<node_data>) gAlgo.shortestPath(1,7);
        assertEquals(1,list.get(0).getKey(),"error in ShortestPath");
        assertEquals(4,list.get(1).getKey(),"error in ShortestPath");
        assertEquals(7,list.get(2).getKey(),"error in ShortestPath");

    }

    @Test
    void save() {
        String s = "C:\\g1.txt";
        directed_weighted_graph gA = new DWGraph_DS();
        dw_graph_algorithms gAlgo = new DWGraph_Algo();
        gAlgo.init(gA);
        node_data n1 = new Vertex(0);
        node_data n2 = new Vertex(1);
        node_data n3 = new Vertex(2);
        node_data n4 = new Vertex(3);
        node_data n5 = new Vertex(4);
        node_data n6 = new Vertex(5);
        node_data n7 = new Vertex(6);
        node_data n8 = new Vertex(7);
        node_data n9 = new Vertex(8);
        node_data n10 = new Vertex(9);
        gA.addNode(n1);
        gA.addNode(n2);
        gA.addNode(n3);
        gA.addNode(n4);
        gA.addNode(n5);
        gA.addNode(n6);
        gA.addNode(n7);
        gA.addNode(n8);
        gA.addNode(n9);
        gA.addNode(n10);
        gA.connect(0,2,2);
        gA.connect(8,0,1);
        gA.connect(8,1,5);
        gA.connect(1,4,3);
        gA.connect(4,7,1);
        gA.connect(7,6,3);
        gA.connect(6,5,2);
        gA.connect(2,5,9);
        gA.connect(3,2,4);
        gA.connect(3,6,4);
        gA.connect(3,4,2);
        gA.connect(8,3,9);
//        boolean b = gAlgo.save(s);
       // assertEquals(true,b,"error in save");

    }

    @Test
    void load() {
    }
}