package ex1.tests;

import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import org.junit.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest {


    @Test
    void getNode() {
        weighted_graph t = Gcreator(50, 100);
        int ed = t.edgeSize();
        int ver = t.nodeSize();
        assertEquals(50, ver);
        assertEquals(100, ed);

    }

    @org.junit.jupiter.api.Test
    void hasEdge() {
        weighted_graph a = new WGraph_DS();
        a.addNode(0);
        a.addNode(8);
        a.connect(0, 8, 3.5);
        boolean flag = a.hasEdge(0, 8);
        assertEquals(true, flag);//has edge test

        double geted = a.getEdge(0, 8);
        assertEquals(3.5, geted);//get edge test
    }

    @org.junit.jupiter.api.Test
    void getEdge() {

    }

    @org.junit.jupiter.api.Test
    void addNode() {
        weighted_graph t = new WGraph_DS();
        t.addNode(5);
        t.addNode(5);
        assertEquals(1, t.nodeSize());
    }

    @org.junit.jupiter.api.Test
    void connect() {
        weighted_graph t = new WGraph_DS();
        t.addNode(1);
        t.connect(0, 1, 7.5);
        assertEquals(0, t.edgeSize());
        t.addNode(0);
        t.connect(0, 1, 8.0);
        assertEquals(1, t.edgeSize());

    }


    @org.junit.jupiter.api.Test
    void removeNode() {
        weighted_graph t = new WGraph_DS();
        t.addNode(0);
        t.addNode(2);
        assertEquals(2, t.nodeSize());
        t.removeNode(0);
        assertEquals(1, t.nodeSize());
    }


    @org.junit.jupiter.api.Test
    void getMC() {
        int v_size =100000;
        int e_size=1000000;
        weighted_graph t = Gcreator(v_size,e_size-100);
        assertEquals(v_size+e_size-100, t.getMC());

    }

    public static weighted_graph Gcreator(int v_size, int e_size) {
        weighted_graph a = new WGraph_DS();
        for (int i = 0; i < v_size; i++) {
            a.addNode(i);
        }
        edgenotRandom(a,e_size);
        return a;
    }

    public static void edgenotRandom(weighted_graph g, int e_size) {
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
}