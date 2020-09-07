package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {

    @FXML
    private Button search;

    @FXML
    private TextField city;

    @FXML
    private Label tempInf;

    @FXML
    private Label fillInfo;

    @FXML
    private Label prInfo;

    @FXML
    private Label rainInfo;

    @FXML
    void initialize(){
       search.setOnAction(event -> {
           String getUserCity = city.getText().trim();
           if (!getUserCity.equals("")){
               String output = getUrl("http://api.openweathermap.org/data/2.5/weather?q="+ getUserCity +"&appid=XXX=metric");
               System.out.println(output);

               if (!output.isEmpty()){
                   JSONObject object = new JSONObject(output);
                   tempInf.setText("Температура: " + object.getJSONObject("main").getDouble("temp"));
                   fillInfo.setText("Ощущается: " + object.getJSONObject("main").getDouble("feels_like"));
                   prInfo.setText("Давление: " + object.getJSONObject("main").getDouble("pressure"));
                   //rainInfo.setText("Осадки: " + object.getJSONObject("main").getDouble("clouds"));
               }
           }

       });
    }
    private static String getUrl (String urlAdress){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e){
            System.out.println(content.toString());
            System.out.println("This city don't found!");
        }

        return content.toString();
    }

}
