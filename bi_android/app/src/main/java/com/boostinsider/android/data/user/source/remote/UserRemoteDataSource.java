package com.boostinsider.android.data.user.source.remote;

import com.boostinsider.android.RetrofitAPI.ServerHelper;
import com.boostinsider.android.data.user.User;
import com.boostinsider.android.data.user.source.UserDataSource;
import com.google.gson.internal.LinkedTreeMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by qiongchen on 4/26/16.
 */
public class UserRemoteDataSource implements UserDataSource {

    private static UserRemoteDataSource INSTANCE = null;

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private UserRemoteDataSource() {
    }

    @Override
    public void doLogin(String username, String password, final LoginOrSignUpCallback callback) {
        ServerHelper.getInstance().userLogin(username, password, new Callback<Object>() {
            @Override
            public void success(Object object, Response response) {
                LinkedTreeMap result = (LinkedTreeMap) object;
                int status = (int) ((double) result.get("status"));
                if (status == 200) {
                    LinkedTreeMap data = (LinkedTreeMap) result.get("data");
                    LinkedTreeMap user = (LinkedTreeMap) data.get("user");
                    String email = (String) user.get("email");
                    String password = (String) user.get("password");
                    String token = (String) user.get("token");
                    String paypal = (String) user.get("paypal");
                    String name = (String) user.get("name");
                    float balance = (float) ((double) user.get("balance"));
                    int id = (int) ((double) user.get("id"));
                    String avatar = (String) user.get("avatar");

                    User userObj = new User(email, password, token, paypal, name, balance, id, avatar);

                    System.out.println("token: " + token);

                    callback.onLoginOrSignUpSuccessful(userObj);
                } else {
                    String message = (String) result.get("message");
                    callback.onLoginOrSignUpFailed(message);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onLoginOrSignUpFailed(error.getMessage());
            }
        });
    }

    @Override
    public void doSignUp(String username, String password, final LoginOrSignUpCallback callback) {
        ServerHelper.getInstance().userSignUpWithoutName(username, password, new Callback<Object>() {
            @Override
            public void success(Object object, Response response) {
                LinkedTreeMap result = (LinkedTreeMap) object;
                int status = (int) (double) result.get("status");
                if (status == 200) {
                    LinkedTreeMap data = (LinkedTreeMap) result.get("data");
                    String email = (String) data.get("email");
                    String password = (String) data.get("password");
                    String token = (String) data.get("token");
                    String paypal = (String) data.get("paypal");
                    String name = (String) data.get("name");
                    float balance = (float) ((double) data.get("balance"));
                    int id = (int) ((double) data.get("id"));
                    String avatar = (String) data.get("avatar");

                    User user = new User(email, password, token, paypal, name, balance, id, avatar);

                    callback.onLoginOrSignUpSuccessful(user);
                } else {
                    String message = (String) result.get("message");
                    callback.onLoginOrSignUpFailed(message);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onLoginOrSignUpFailed(error.getMessage());
            }
        });
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void removeUser() {

    }
}
