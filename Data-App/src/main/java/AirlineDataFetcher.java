import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class AirlineDataFetcher {

    private static HttpURLConnection connection;

    public static String getAirlinesInformation(){
        String url = "https://api.instantwebtools.net/v1/airlines";
        String airinesData = "";
        try {
            URL requestURL = new URL("https://api.instantwebtools.net/v1/airlines");
            connection = (HttpURLConnection) requestURL.openConnection();
            connection.setRequestMethod("GET");

            int requestStatusCode = connection.getResponseCode();

            if(requestStatusCode == 200){
                InputStream inputStream = connection.getInputStream();

                int i;

                while ((i = inputStream.read()) != -1){
                    airinesData += (char) i;
                }

                inputStream.close();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }

        return airinesData;
    }

    public static String fetchDataById(int id){
        String url = "https://api.instantwebtools.net/v1/airlines/" + id;
        String flightData = "";
        try{
            URL requestURL = new URL(url);
            connection =  (HttpURLConnection) requestURL.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();

            if(statusCode == 200){
                InputStream inputStream = connection.getInputStream();

                int i;

                while ((i = inputStream.read()) != -1){
                    flightData += (char) i;
                }

                inputStream.close();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }

        return flightData;
    }
    public static void main(String[] args) {
//       String airlineData = getAirlinesInformation();
//       System.out.println(airlineData);

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the flight ID between 1 to 1999: ");
        int id = sc.nextInt();

        String flightInformation = fetchDataById(id);
        System.out.println(flightInformation);
    }
}
