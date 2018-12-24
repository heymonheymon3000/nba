package nanodegree.android.nba.persistence.source.remote;


public interface RemoteResponseListener<T> {
    void onSuccess(T result);

    void onFailure(Throwable throwable);
}

