package context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static ThreadLocal<Map<String, Object>> scenarioContext = new ThreadLocal<>();

    public static void setContext(ScenariosKeys key, Object value) {getInstance().put(key.toString(),value);}

    public static Object getContext(ScenariosKeys key) {return getInstance().get(key.toString());}

    private static Map<String, Object> getInstance() {
        if(scenarioContext.get() == null){
            scenarioContext.set(new HashMap<>());
        }
        return scenarioContext.get();
    }
}
