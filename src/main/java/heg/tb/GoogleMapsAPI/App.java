package heg.tb.GoogleMapsAPI;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*
        OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
        Request request = new Request.Builder()
            .url("https://maps.googleapis.com/maps/api/distancematrix/json?origins=Grand-rue 72,Péry&destinations=Biel/Bienne&key=AIzaSyBtpcUMs7e8RkwRuLy9K-0aoXiztwAGobU")
            .method("GET", null)
            .build();
        try {
            okhttp3.Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        //création Builder okHttpClient
        OkHttpClient.Builder httpClient = new Builder();

        //requette de base + transformation de reponse en Gson
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/distancematrix/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

        //appel à l'interface
        IService service = retrofit.create(IService.class);

        // Requettes avec les paramètres
        //conduite
        Call<ApiResponse> callSyncVoiture = service.getData("Grand-Rue 72, Péry", "Rue de l'hopital 2,Biel/Bienne","driving", "AIzaSyBtpcUMs7e8RkwRuLy9K-0aoXiztwAGobU");

        //marche
        Call<ApiResponse> callSyncMarche = service.getData("Grand-Rue 72, Péry", "Rue de l'hopital 2,Biel/Bienne","walking", "AIzaSyBtpcUMs7e8RkwRuLy9K-0aoXiztwAGobU");

        //velo
        Call<ApiResponse> callSyncVelo = service.getData("Grand-Rue 72, Péry", "Rue de l'hopital 2,Biel/Bienne","bicycling", "AIzaSyBtpcUMs7e8RkwRuLy9K-0aoXiztwAGobU");


        try {
            //Conduite
            Response<ApiResponse> responseConduite = callSyncVoiture.execute();
            ApiResponse apiResponseConduite = responseConduite.body();
            System.out.println("Voiture : " + apiResponseConduite);
            //Marche
            Response<ApiResponse> responseMarche = callSyncMarche.execute();
            ApiResponse apiResponseMarche = responseMarche.body();
            System.out.println("A pied : " + apiResponseMarche);
            //Velo
            Response<ApiResponse> responseVelo = callSyncVelo.execute();
            ApiResponse apiResponseVelo = responseVelo.body();
            System.out.println("Velo : " + apiResponseVelo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
