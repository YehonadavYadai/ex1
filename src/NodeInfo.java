package ex1.src;

import java.io.Serializable;

public class NodeInfo implements node_info, Serializable {
    //************** fields************
    private int key = 0;
    private String info = " ";
    private double tag = 0;
   // private HashMap<Integer,Double> h2= new HashMap<Integer,Double>();

    //*************constrcuturs********************
    public NodeInfo(int key) {
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
}
