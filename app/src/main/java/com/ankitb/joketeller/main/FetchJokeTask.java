package com.ankitb.joketeller.main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ankitb.joketeller.backend.myApi.MyApi;
import com.ankitb.joketeller.showjoke.ShowJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;


public class FetchJokeTask extends AsyncTask<Context,Void,String>{
    private static MyApi myApi = null;
    private Context context;
    private FetchJokeTaskListener listener;
    @Override
    protected String doInBackground(Context... params) {
        if(myApi == null){
            MyApi.Builder myApiBuilder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),new AndroidJsonFactory(),null)
                    .setRootUrl("https://joketeller-1106.appspot.com/_ah/api/")
                    /*.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    })*/;
            myApi = myApiBuilder.build();
        }
        context = params[0];
        try{
            return myApi.getJoke().execute().getJoke();
        }catch(Exception e){
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        if(this.listener != null)
            this.listener.onComplete(result,null);
        Intent intent = new Intent(context, ShowJokeActivity.class);
        intent.putExtra("KEY_JOKE",result);
        context.startActivity(intent);    }

    @Override
    protected void onCancelled() {
        if(listener != null)
            listener.onComplete(null, new InterruptedException("AsyncTask cancelled"));
    }

    public void setListener(FetchJokeTaskListener listener){
        this.listener = listener;
    }

    public static interface FetchJokeTaskListener {
        public void onComplete(String result, Exception e) ;
    }
}
