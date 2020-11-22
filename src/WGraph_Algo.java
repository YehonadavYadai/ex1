package ex1.src;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms, Serializable {
    //-------------fields-------------
    weighted_graph g;

    //-----------------constructur ---------------
    public WGraph_Algo() {
        weighted_graph a = new WGraph_DS();
        this.g = a;
    }

    @Override
    public void init(weighted_graph g) {
        this.g = g;
    }

    @Override
    public weighted_graph getGraph() {
        return this.g;
    }


    @Override
    public weighted_graph copy() {
        weighted_graph copied = new WGraph_DS();
        for (node_info v : g.getV()) { // first of all i copy all the nodes.
            copied.addNode(v.getKey());
            copied.getNode(v.getKey()).setInfo(v.getInfo());
            copied.getNode(v.getKey()).setTag(v.getTag());
        }
        int i = 0;
        while (i < g.edgeSize()) {// i connect all the edges .
            for (node_info currentNode : g.getV()) {
                for (node_info sib : g.getV(currentNode.getKey())) {
                    int node1 = currentNode.getKey();
                    int node2 = sib.getKey();
                    copied.connect(node1, node2, g.getEdge(node1, node2));
                    i++;
                }

            }

        }
        return copied;
    }

    private void setAllTags(double num) {
        for (node_info thisnode : this.g.getV()) {
            thisnode.setTag(num);
        }
    }

    /**
     * to check if the graph is connected i used Bfs . With a quene .
     */

    @Override
    public boolean isConnected() {
        if (g.nodeSize() == 0) return true;
        this.setAllTags(0);// 0 means unvisited
        Queue<Integer> queforbfs = new LinkedList<>();
        int numOfVisits = 0;
        int src = g.getV().iterator().next().getKey(); // i start checking from some node in the graph.
        queforbfs.add(src);

        while (!(queforbfs.isEmpty())) {
            Integer thisNode = queforbfs.poll();
            for (node_info thisNi : g.getV(thisNode)) {
                if (thisNi.getTag() == 0 & thisNi.getKey() != src) {
                    thisNi.setTag(1);
                    queforbfs.add(thisNi.getKey());
                    numOfVisits++;

                }
            }
        }
        return (numOfVisits == (this.g.nodeSize() - 1)); // if i visited all the nodes out of the irst one it means the graph is connected.
    }


    /**
     * i used the dijkstra algorithm with a quene.
     * it helps  me to know what is the best way to get from src to dest.
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (g.getNode(src) == null || g.getNode(dest) == null) return -1;
        double inf = Double.POSITIVE_INFINITY;
        this.setAllTags((inf)); // i put in all infinity.
        node_info node1 = g.getNode(src);
        node_info node2 = g.getNode(dest);
        node1.setTag(0);
        Queue<Integer> quefodisk = new LinkedList<>();
        quefodisk.add(src);
        while (!quefodisk.isEmpty()) {
            int currentkey = quefodisk.poll();
            for (node_info current : g.getV(currentkey)) {
                double offer = g.getNode(currentkey).getTag() + g.getEdge(currentkey, current.getKey());// i check if i can "offer" you a better way. my ofer his from the nodes that i stand on and th wighte of this edge.
                if (offer < current.getTag()) { // if its a better way i will put it in the tag.
                    current.setTag(offer);
                    quefodisk.add(current.getKey());
                }
            }

        }
        double ans = Double.POSITIVE_INFINITY;
        if (g.getNode(dest).getTag() == ans) return -1;
        return g.getNode(dest).getTag();
    }

    @Override
    public List<node_info> shortestPath(int src, int dest) {
        Stack<node_info> way = new Stack<>();
        LinkedList<node_info> way2 = new LinkedList<>();
        double a = shortestPathDist(src, dest);// i activate the dijkstra algorithm
        node_info des = g.getNode(dest);
        int current = dest;
        while (current != src) { // i start from dest
            for (node_info sib : g.getV(current)) {
                double check = sib.getTag() + g.getEdge(current, sib.getKey());
                if (check == g.getNode(current).getTag()) { // if it feets it mean this is the way we shold go
                    way.push(g.getNode(current)); // i added to the way

                    current = sib.getKey();// this is the way!
                    break;
                }

            }

        }
        way.push(g.getNode(src)); // i reverse it with LinkedList.
        while (!way.empty()) {
            way2.add(way.pop());
        }
        return way2;
    }

    @Override
    public boolean save(String file) {


        try {
            FileOutputStream fileout = new FileOutputStream(file);
            ObjectOutputStream objectout = new ObjectOutputStream(fileout);

            objectout.writeObject(g);
            objectout.close();
            fileout.close();
            return true;
        } catch (IOException e) {
            return false;

        }


    }

    @Override
    public boolean load(String file) {
        try {
            FileInputStream infile = new FileInputStream(file);
            ObjectInputStream inObject = new ObjectInputStream(infile);

            this.init((weighted_graph) inObject.readObject());
            infile.close();
            inObject.close();
            return true;

        } catch (FileNotFoundException e) {

            return false;
        } catch (IOException e) {
            return false;

        } catch (ClassNotFoundException e) {
            return false;

        }

    }
}
