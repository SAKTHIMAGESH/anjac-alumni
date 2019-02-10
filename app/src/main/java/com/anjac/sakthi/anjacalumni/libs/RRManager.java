package com.anjac.sakthi.anjacalumni.libs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

/**
 * Created by Rajesh Developer on 3/3/2018.
 */

public class RRManager extends AsyncTask<JSONObject, Void, String> {
    private ProgressDialog progressDialog;
    private JSONParser jsonParser;
    private String URL,REQUEST_METHOD;
    private JSONObject params;
    private Context context;
    private static String LOADING_MSG = "Please wait... Process is going on";
    private ResponseListner responseListner;
    private boolean isProgressDialogNeed = true;

    public RRManager isProgressDialogNeed() {
        return this;
    }

    public RRManager setProgressDialogNeed(boolean progressDialogNeed) {
        isProgressDialogNeed = progressDialogNeed;
        return this;
    }

    public RRManager(Context context) {
        this.context = context;
        this.progressDialog = new ProgressDialog(context);
        this.jsonParser = new JSONParser();
    }

    public RRManager(Context context, String URL, String METHOD, JSONObject params, String Loading_Msg) {
        this.URL = URL;
        this.REQUEST_METHOD = METHOD;
        this.params = params;
        this.context = context;
        this.LOADING_MSG = Loading_Msg;
        this.progressDialog = new ProgressDialog(context);
        this.jsonParser = new JSONParser();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (isProgressDialogNeed) {
            progressDialog.setMessage(LOADING_MSG);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    protected String doInBackground(JSONObject... jsonObjects) {
        try {
            return jsonParser.makeHttpRequest(this.URL, this.REQUEST_METHOD, this.params);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String json) {
        super.onPostExecute(json);
        if (isProgressDialogNeed && progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        if (this.responseListner != null){
            try {
                responseListner.onResponse(this.params,json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public RRManager setResponseListner(ResponseListner responseListner){
        this.responseListner = responseListner;
        return this;
    }

    public RRManager setURL(String URL) {
        this.URL = URL;
        return this;
    }

    public RRManager setRequestMethod(String REQUEST_METHOD) {
        this.REQUEST_METHOD = REQUEST_METHOD;
        return this;
    }

    public RRManager setParams(JSONObject params) {
        this.params = params;
        Log.i(">>>>JSON",params.toString());
        return this;
    }

    public RRManager setLoadingMsg(String LOADING_MSG) {
        this.LOADING_MSG = LOADING_MSG;
        return this;
    }

    public interface ResponseListner{
        void onResponse(JSONObject params, String response) throws JSONException;
    }
}

/*
    instruction to use:
                        RRManager rrManager = new RRManager(<context of current instance>);
                        JSONObject params = new JSONObject();
                        try {
                            params.put("key", "value");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        rrManager.setURL(Constant.URL)
                                .setParams(params)
                                .setRequestMethod(Constant.METHOD_POST)
                                .setLoadingMsg("Process is Going on.. Please be patient...")
                                .setResponseListner(this)
                                .execute();



 */
