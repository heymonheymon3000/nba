//package nanodegree.android.nba.persistence.source.remote;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import android.support.annotation.NonNull;
//
//import nanodegree.android.nba.rest.response.dailySchedule.DailySchedule;
//import nanodegree.android.nba.rest.ApiUtils;
//import nanodegree.android.nba.rest.GameService;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import nanodegree.android.nba.BuildConfig;
//
//public class RemoteSourceImpl implements RemoteSource {
//    private GameService gameService;
//    private Context mContext;
//
//    public RemoteSourceImpl(Context context) {
//        this.mContext = context;
//        this.gameService = ApiUtils.getGameService();
//    }
//
//    @Override
//    public void getGames(final RemoteResponseListener<DailySchedule> responseListener) {
//        gameService.getDailySchedule("en", 2018, 12, 17, ".json", BuildConfig.NBA_DB_API_KEY).enqueue(new Callback<DailySchedule>() {
//            @Override
//            public void onResponse(@NonNull Call<DailySchedule> call, @NonNull Response<DailySchedule> response) {
//                if(response.isSuccessful()) {
//                    responseListener.onSuccess(response.body());
//                } else {
//                    responseListener.onFailure(new Throwable("Failed to get game schedules with a http status of " + String.valueOf(response.code())));
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<DailySchedule> call,@NonNull Throwable t) {
//                Toast.makeText(mContext,
//                        "Something went wrong, please check your internet connection and try again!",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
