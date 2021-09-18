import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class DataHandler {

    private static HttpURLConnection connection;

    public static String getData(){
        String data = "";
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int requestStatus = connection.getResponseCode();
            System.out.println(requestStatus);
            if(requestStatus == 200){
                InputStream inputStream = connection.getInputStream();

                int i;
                while((i = inputStream.read())!=-1){
                    data = data + (char) i ;
                }

                inputStream.close();
            }else{
                InputStream inputStream = connection.getErrorStream();

                int i;

                while (((i = inputStream.read()) != -1)){
                    data = data + (char) i;
                }

                inputStream.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }


        return data;
    }

    public static String getDataById(int id){
        String url = "https://jsonplaceholder.typicode.com/comments?postId=" + id;
        String data = "";
        try {
            URL requestURL = new URL(url);
            connection = (HttpURLConnection) requestURL.openConnection();
            connection.setRequestMethod("GET");
            

            int requestStatus = connection.getResponseCode();

            if(requestStatus == 200){
                InputStream inputStream = connection.getInputStream();
                int i;

                while ((i = inputStream.read()) != -1){
                    data = data + (char) i;
                }

                inputStream.close();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }

        return data;

    }
    public static void main(String[] args) {
        System.out.println("Hello World");
      //  String result = getData();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Id for the Posts you want to fetch: ");
        int id = sc.nextInt();
        String apiDataByID = getDataById(id);
        System.out.println(apiDataByID);
      //  System.out.println(result);

        sc.close();
    }
}
