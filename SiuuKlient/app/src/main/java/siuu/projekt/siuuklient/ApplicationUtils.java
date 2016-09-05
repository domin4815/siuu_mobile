package siuu.projekt.siuuklient;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import siuu.projekt.siuuklient.model.FacebookUser;
import siuu.projekt.siuuklient.repository.DataReposotory;

/**
 * Created by domin4815 on 24.05.16.
 */
public class ApplicationUtils {
    public static String SERV_ADDR = "http://10.22.106.61:8080"; //"http://10.22.112.73:8080";
    public static volatile long UPDATES_INTERVAL = 10000;
    public static User user = new User();//
    public static List<FacebookUser> friends = new LinkedList<FacebookUser>();
    public static final Set<String> allPreferencesList = new HashSet<>();
    public static final DataReposotory repository = new DataReposotory();


}
