package siuu.projekt.siuuklient;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by domin4815 on 24.05.16.
 */
public class ApplicationUtils {
    public static final String SERV_ADDR = "http://10.22.106.61:8080"; //"http://10.22.112.73:8080";
    public static final long UPDATES_INTERVAL = 10000;
    public static User user = new User("Krowa155", "abcd1234", new Location(), new HashSet<PreferedActivity>());
    public static final Set<String> allPreferencesList = new HashSet<>();

}
