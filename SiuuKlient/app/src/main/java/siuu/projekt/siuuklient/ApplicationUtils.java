package siuu.projekt.siuuklient;

import java.util.LinkedList;

/**
 * Created by domin4815 on 24.05.16.
 */
public class ApplicationUtils {
    public static final String SERV_ADDR = "http://10.0.2.2:8080"; //"http://10.22.112.73:8080";
    public static final long UPDATES_INTERVAL = 10000;
    public static User user = new User("abcd1234", "abcd1234", new Location(), new LinkedList<PreferedActivity>());
}
