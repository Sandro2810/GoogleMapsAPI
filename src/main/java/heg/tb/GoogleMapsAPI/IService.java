package heg.tb.GoogleMapsAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IService {

  @GET("json")
  //définition des paramètres de la requette
  public Call<ApiResponse> getData(@Query("origins") String origins, @Query("destinations") String destinations, @Query("mode") String mode ,@Query("key") String key);


}
