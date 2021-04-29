package Util;

import java.util.HashMap;

public class MetodosFeature {

    public static HashMap<Integer,String> scenarios = new HashMap<Integer,String>();;

    public MetodosFeature()
    {
        /*if(scenarios == null)
            scenarios = new HashMap<Integer,String>();*/
        System.out.println("llego metodos feature");
    }

    public static void addScenario(String scenario){
        Thread currentThread = Thread.currentThread();
        int threadID = currentThread.hashCode();
        scenarios.put(threadID,scenario);
    }

    public static synchronized String getScenario(){
        Thread currentThread = Thread.currentThread();
        int threadID = currentThread.hashCode();
        //System.out.println("CP ->"+scenarios.get(threadID));
        return scenarios.get(threadID);
    }
}
