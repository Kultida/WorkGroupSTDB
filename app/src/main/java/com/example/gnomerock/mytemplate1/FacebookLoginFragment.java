package com.example.gnomerock.mytemplate1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by gnomerock on 9/17/15.
 */
public class FacebookLoginFragment extends Fragment {

    CallbackManager callbackManager;
    TextView name;
    TextView email;
    ImageView imageView;
    public static FacebookLoginFragment newInstance() {
        FacebookLoginFragment fragment = new FacebookLoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Bundle params = new Bundle();
                params.putString("fields", "name,id,email,picture");
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse response) {
                        // Application code

                        try {
                            name.setText(jsonObject.get("name").toString());
                            email.setText(jsonObject.get("email").toString());
                            Glide.with(FacebookLoginFragment.this)
                                    .load("https://goo.gl/6ztiFn")
                                            // .load("https://goo.gl/5RXS5s") PicSmall
                                    .into(imageView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
                request.setParameters(params);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }

        });
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.facebook_login_fragment, container, false);


        name = (TextView) view.findViewById(R.id.fb_name);
        email  = (TextView) view.findViewById(R.id.fb_email);
        imageView  = (ImageView) view.findViewById(R.id.imageView);
        Button btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(FacebookLoginFragment.this, Arrays.asList("public_profile", "user_friends"));
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
