package siuu.projekt.siuuklient.connection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpConnector {
    private final String USER_AGENT = "Mozilla/5.0";


    public String doGet(String url) {

        URL obj = null;
        try {
            obj = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) obj.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // optional default is GET
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        }

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = 0;
        StringBuilder response = new StringBuilder();
        try {
            responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return response.toString();

    }

    // HTTP POST request
    public String doPost(String url, String request, String param) throws IOException {
        URL obj = new URL(url+request);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(param);
        wr.flush();
        wr.close();
        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url+request);
        System.out.println("Post parameters : " + param);
        System.out.println("Response Code : " + responseCode);

        // Send post request
        BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();

    }

}
