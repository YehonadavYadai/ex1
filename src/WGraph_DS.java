package ex1.src;

import java.io.Serializable;
import java.util.*;

/**
 *  This is the class that representin a weighted graph. i used 2 Hashmaps.
 *  1. to save the nodes of the graph.
 *  2.to save for everynode a Hashmap that keeping all his siblings and the wiighted in the "way" (edge) to them.
 *  enjoy.
 *
 */

public class WGraph_DS implements weighted_graph, Serializable {
    //-----------fields ------------------
    private HashMap<Integer, node_info> allNodes = new HashMap<Integer, node_info>(); // ALL VERICLES
    private HashMap<Integer, HashMap<Integer, Double>> allHashes = new HashMap<Integer, HashMap<Integer, Double>>(); // ALL HASHMAPS
    private int mc = 0, edges = 0;


    //--------------construtus----------
    public WGraph_DS() {
    }


    //---------------methods--------
    @Override
    /**
     *  this method return the node
     * Note: this method should run in O(1) time.
     * @param key of node
     *
     * @return the node
     */

    public node_info getNode(int key) {
        return allNodes.get(key);
    }
    /**
     * this method return true if there edge between this nodes return false if there isnt edge or one of the nodes not in the graph
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) { // should be an Hadni method.

        return allHashes.get(node1).containsKey(node2);
    }

    @Override
    /**
     * this method return the wighte if the edge if not exits return -1.
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
    public double getEdge(int node1, int node2) {
        if (hasEdge(node1, node2)) {

            return allHashes.get(node1).get(node2);
        } else return -1;
    }
    /**
     * this method add node to the graph with the specieeld key , is he is exits already nothing happens.
     * Note: this method should run in O(1) time.
     * @param key
     *
     * first of all i check if there isn't node like this . then i initalized a node , and add him to the Hashmap.
     * and i initlized for him a emptyHashmap(for now) that if he will have siblings it will be kept there.
     *
     */
    @Override
    public void addNode(int key) {
        if (!(allNodes.containsKey(key))) // if this key does not exits
        {
            node_info newnode = new NodeInfo(key);// i created a new node
            allNodes.put(key, newnode);// i added to the hashmap of nodes

            HashMap<Integer, Double> putHash = new HashMap<>();// i created for him a hashmap.
            allHashes.put(key, putHash);
            mc++;

        }
    }
    /**
     * this method connect 2 of the nodes if they exits, if the edge is already exits , the method updating the wighte .
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (allNodes.containsKey(node1) & allNodes.containsKey(node2) & (node2 != node1)) {// check if  they exits.
            if (!hasEdge(node1, node2)) {
                edges++;
                mc++;
            }

            allHashes.get(node1).put(node2, w);// i put/update the weight
            allHashes.get(node2).put(node1, w);

        }
    }


    @Override
    public Collection<node_info> getV() {
        return allNodes.values();
    }

    /**
     *  this method returns a collection of siblings of node with the key "node_id"
     *  i check in the Hashmap of nodeid what's the key of hs siblings , then i take the nodes themself from the collection of nodes of the graph .

     */
    @Override
    public Collection<node_info> getV(int node_id) {
        List<node_info> collect = new LinkedList<>();
        for (Integer sib : allHashes.get(node_id).keySet()) { // keyset give me the keys of the siblings
            collect.add(getNode(sib)); // i get the node and put him into "collect"
        }
        return collect;
    }
    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */

    @Override
    public node_info removeNode(int key) {
        if (allNodes.containsKey(key)) { // check if he is exiting in the graph.
            node_info temp = getNode(key);

            Iterator<node_info> it = getV(key).iterator();
            while (it.hasNext()) { // i pass all his siblings
                int a = it.next().getKey();// key of current sibling

                removeEdge(key, a); // removed every edge
            }
            mc++;
            allNodes.remove(key);// delete him from the map of nodes
            allHashes.remove(key);// delete his list of sibling from the map of nodes

            return temp;
        }
        return null;
    }

    @Override
    public void removeEdge(int node1, int node2) {
        if (hasEdge(node1, node2)) {
            allHashes.get(node2).remove(node1);
            allHashes.get(node1).remove(node2);
            mc++;
            edges--;
        }
    }

    @Override
    public int nodeSize() {
        return allNodes.size();
    }

    @Override
    public int edgeSize() {
        return edges;
    }

    @Override
    public int getMC() {
        return mc;
    }

    @Override
    public boolean equals(Object o) {
        weighted_graph g2 = (weighted_graph) o;
        Collection<node_info> c1 = getV();
        Collection<node_info> c2 = g2.getV();
        boolean checkNodes = c1.containsAll(c2) && c2.containsAll(c1); // i check if they got the same nodes
        if (checkNodes) { // if they got the same nodes i will check the edges.
            for (node_info v1 : allNodes.values()) {
                for (node_info v2 : g2.getV()) {
                    if (v1.getKey() == v2.getKey()) { // i want to hold the 2 collection of sbilings then to check them.
                        int a = v1.getKey();
                        if (getV(a).containsAll(g2.getV(a)) && g2.getV(a).containsAll(getV(a))) ;
                        else return false; // if some edge not right it will return false
                    }
                }
            }
        }
        return checkNodes;
    }


    class NodeInfo implements node_info, Serializable {
        //************** fields************
        private int key = 0;
        private String info = " ";
        private double tag = 0;

        //*************constrcuturs********************
        public NodeInfo(int key) {
            this.key = key;
        }

        public NodeInfo(int key, String s) {
            info = s;
            this.key = key;
        }

        //************methods****************
        @Override
        public int getKey() {
            return this.key;
        }

        @Override
        public String getInfo() {
            return this.info;
        }

        @Override
        public void setInfo(String s) {
            this.info = s;

        }

        @Override
        public double getTag() {
            return this.tag;
        }

        @Override
        public void setTag(double t) {
            this.tag = t;

        }

        @Override
        public boolean equals(Object o) {
            node_info v2 = (node_info) o;
            return (key == ((node_info) o).getKey() && info.equals(((node_info) o).getInfo()));
        }


    }


}





