package ex1.tests;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {

    @Test
    void init() {
    }

    @Test
    void getGraph() {

        weighted_graph a = GcreatorNotRandom(1000,998);
        weighted_graph_algorithms v = new WGraph_Algo();
        v.init(a);
        weighted_graph g2 = v.getGraph();
        assertEquals(a.edgeSize(),g2.edgeSize());
    }


    @Test
    void copy() {
        weighted_graph a1 = GcreatorNotRandom(1000,35);
        weighted_graph_algorithms v = new WGraph_Algo();
        v.init(a1);
        weighted_graph a2 = v.copy();
        assertEquals(a1,a2);
    }

    @Test
    void isConnected() {
        weighted_graph a = GcreatorRandom(5,8);
        weighted_graph_algorithms v = new WGraph_Algo();
        v.init(a);
        assertEquals(true ,v.isConnected());

    }

    @Test
    void shortestPathDist() {
        weighted_graph a = GcreatorNotRandom(8,0);
        weighted_graph_algorithms v = new WGraph_Algo();
        a.connect(0,1,4.0);
        a.connect(0,7,8);
        a.connect(7,8,7);
        a.connect(7,6,1);
        a.connect(1,2,8);
        a.connect(2,8,2);
        a.connect(2,3,7);
        a.connect(6,8,6);
        a.connect(6,5,2);
        a.connect(5,4,10);
        a.connect(5,3,14);
        a.connect(4,3,9);
        a.connect(2,4,4);
        a.connect(1,7,11);
       v.init(a);
       assertEquals(true,v.isConnected());
       assertEquals(12,v.shortestPathDist(0,2));




    }

    @Test
    void shortestPath() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
    public static weighted_graph GcreatorNotRandom(int v_size, int e_size) {
        weighted_graph a = new WGraph_DS();
        for (int i = 0; i < v_size; i++) {
            a.addNode(i);
        }
        edgeNotRandom(a,e_size);
        return a;
    }
    public static weighted_graph GcreatorRandom(int v_size, int e_size) {
        weighted_graph a = new WGraph_DS();
        for (int i = 0; i < v_size; i++) {
            a.addNode(i);
        }
        edgeRandom(a,e_size);
        return a;
    }

    public static void edgeNotRandom(weighted_graph g, int e_size) {
        while (g.edgeSize() < e_size) {
            for (int i = 0; i < g.nodeSize() - 10; i++) {
                for (int j = 1; j < 11; j++) {
                    g.connect(i, i + j,(double)i+j);
                }
            }

        }
    }

    public static void edgeRandom(weighted_graph a, int e_size) {
        while (a.edgeSize() < e_size) {
            Random rnd = new Random();
            int r1 = rnd.nextInt(a.nodeSize());
            int r2 = rnd.nextInt(a.nodeSize());
            double w = (double) r1 + r2;
            a.connect(r1, r2, w);
        }
    }
    //if null
    //if init
    //if 1 node
    //2 node
    //2 node 1 edge

}