package com.boostinsider.android.RetrofitAPI;

import com.boostinsider.android.data.campaign.Campaigns;
import com.boostinsider.android.data.comment.Comments;
import com.boostinsider.android.data.other.CommentInfo;
import com.boostinsider.android.data.other.LoginInfo;
import com.pixplicity.easyprefs.library.Prefs;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by TwistFate on 15/8/18.
 */
public class ServerHelper {

    public static final String ENDPOINT = "https://www.boostinsider.com";

    private static ServerHelper INSTANCE;

    private static ServerEndPointInterface serverEndPointInterface;

    private ServerHelper() {
        RequestInterceptor interceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                String authToken = Prefs.getString("token", null);
                request.addHeader("Authorization", "Bearer " + authToken);
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setRequestInterceptor(interceptor)
                .build();
        serverEndPointInterface = restAdapter.create(ServerEndPointInterface.class);
    }

    public static ServerHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServerHelper();
        }

        return INSTANCE;
    }

    public void userLogin(String username, String password, Callback<Object> callback) {
        LoginInfo loginInfo = new LoginInfo(username, password);
        serverEndPointInterface.login(loginInfo, callback);
    }

//    public static void userSignUpWithName(String username, String password, String name, Callback<SignUpCallback> callback) {
//        SignUpInfo signUpInfo = new SignUpInfo();
//        signUpInfo.setEmail(username);
//        signUpInfo.setPassword(password);
//        signUpInfo.setName(name);
//        serverEndPointInterface.signUpWithName(signUpInfo, callback);
//    }

    public void userSignUpWithoutName(String username, String password, Callback<Object> callback) {
        LoginInfo loginInfo = new LoginInfo(username, password);
        serverEndPointInterface.signUpWithoutName(loginInfo, callback);
    }

//    public static void getCampaignPoolInfo(boolean isTokenEmpty, int from, int size, Callback<CampaignPool> defaultCallback, Callback<CampaignPool> myCallback) {
//        if (!isTokenEmpty) {
//            serverEndPointInterface.getCampaignPool(from, size, false, "android", myCallback);   // get android and web campaigns
//        } else {
//            serverEndPointInterface.getCampaignPoolDefault(from, size, false, "android", defaultCallback);   // get android and web campaigns
//        }
//    }

    public void getCampaigns(int from, Callback<Campaigns> callback) {
        serverEndPointInterface.getCampaignPool(from, 10, false, null, callback);
    }

//    public static void postLikeCampaignToServer(int id, Like like, Callback<PostReturn> callback) {
//        serverEndPointInterface.likeCampaign(id, like, callback);
//    }

//    public static void updateCampaignStatus(String status, int id, Callback<PostReturn> callback) {
//        AssignmentStatus newStatus = new AssignmentStatus();
//        newStatus.setAssignmentStatus(status);
//        serverEndPointInterface.updateStatus(id, newStatus, callback);
//    }

//    public static void saveTwitterInfoToServer(SaveTwitter saveTwitter, Callback<SocialReturn> callback) {
//        serverEndPointInterface.saveTwitterCredentials(saveTwitter, callback);
//    }
//
//    public static void saveTumblrInfoToServer(SaveTumblr saveTumblr, Callback<SocialReturn> callback) {
//        serverEndPointInterface.saveTumblrCredentials(saveTumblr, callback);
//    }
//
//    public static void shareCampaign(int assignmentId, String[] socialList, String message, int rating, String image, TypedFile uploadingFile, Callback<Object> callback) {
//        serverEndPointInterface.shareCampaign(assignmentId, socialList, message, rating, image, uploadingFile, callback);
//    }

//    public static void sendWithdrawRequestToServer(WithdrawRequestInfo withdrawRequestInfo, Callback<WithdrawRequestCallback> callback) {
//        serverEndPointInterface.requestWithdraw(withdrawRequestInfo, callback);
//    }
//
//    public static void getUserWithdrawPenddingList(Callback<WithdrawListCallback> callback) {
//        serverEndPointInterface.getWithdrawPenddingList(callback);
//    }
//
//    public static void getUserWithdrawPreviousList(Callback<WithdrawListCallback> callback) {
//        serverEndPointInterface.getWithdrawPreviousList(callback);
//    }
//
//    public static void getUserInfoFormServer(Callback<Object> callback) {
//        serverEndPointInterface.getUserInfo(callback);
//    }
//
//    public static void updateUserInfoToServer(UserInfoUpdateRequest userInfoUpdateRequest, Callback<Object> callback) {
//        serverEndPointInterface.updateUserBaseInfo(userInfoUpdateRequest, callback);
//    }
//
//    public static void uploadImageToServerWithType(File targetImageFile, String uploadType, Callback<PictureUploadCallback> callback) {
//        String mimeType = "image/*";
//        TypedFile fileToSend = new TypedFile(mimeType, targetImageFile);
//        serverEndPointInterface.uploadImagesToServerWithType(fileToSend, uploadType, callback);
//    }
//
//    public static void getAssignmentPerformance(PerformanceRequestInfo performanceRequestInfo, Callback<PerformanceCallback> callback) {
//        serverEndPointInterface.getAssignmentPerformance(performanceRequestInfo, callback);
//    }
//
//    public static void getUserOverallPerformance(Callback<Object> callback) {
//        serverEndPointInterface.getOverallPerformance(callback);
//    }
//
//    public static void getLevelSystemInfo(Callback<Object> callback) {
//        serverEndPointInterface.getLevelInfo(callback);
//    }

//    public static void getUserLatestPostInfo(int from, int size, Callback<Posts> callback) {
//        serverEndPointInterface.getLatestPost(from, size, callback);
//    }

    public void getComments(int campaignId, int from, Callback<Comments> callback) {
        CommentInfo commentInfo = new CommentInfo(campaignId, from, 10);
        serverEndPointInterface.getComments(commentInfo, callback);
    }

//    public static void getInsiderClubInfo(Callback<InsiderClub> callback) {
//        serverEndPointInterface.getInsiderClub(callback);
//    }

//    public static void sendFeedback(FeedbackInfo feedbackInfo, Callback<Object> callback) {
//        serverEndPointInterface.sendFeedback(feedbackInfo, callback);
//    }

//    public static void getReferrals(int from, Callback<Referrals> callback) {
//        serverEndPointInterface.getReferrals(from, callback);
//    }
//
//    public static void getMessages(int from, int size, String conversationId, int toId, Callback<GetMessagesReturn> callback) {
//        serverEndPointInterface.getMessages(from, size, conversationId, toId, callback);
//    }
//
//    public static void sendMessage(DataToSend dataToSend, Callback<SendMessageReturn> callback) {
//        serverEndPointInterface.sendMessage(dataToSend, callback);
//    }

//    public static void selfPost(int assignmentId, String channel, String userId, TypedFile uploadingFile, Callback<Object> callback) {
//        serverEndPointInterface.selfPost(assignmentId, channel, userId, uploadingFile, callback);
//    }

//    public static void getMyPost(int from, int size, int campaignId, String userId, Callback<Posts> callback) {
//        serverEndPointInterface.getMyPost(from, size, campaignId, userId, callback);
//    }
//
//    public static void getConversationList(int from, int size, Callback<GetConversationListReturn> callback) {
//        serverEndPointInterface.getConversationList(from, size, callback);
//    }
//
//    public static void updateIsRead(int targetId, Callback<Object> callback) {
//        serverEndPointInterface.updateIsRead(targetId, callback);
//    }
//
//    public static void getIsVip(Callback<GetIsVip> callback) {
//        serverEndPointInterface.getIsVip(callback);
//    }
//
//    public static void applyForVip(ApplicationInfo applicationInfo, Callback<Object> callback) {
//        serverEndPointInterface.applyVip(applicationInfo, callback);
//    }
//
//    public static void doRedirect(String deviceType, int campaignId, Callback<Object> callback) {
//        serverEndPointInterface.doRedirect(deviceType, campaignId, callback);
//    }

//    public static void getNotificationList(int from, int size, Callback<Notifications> callback) {
//        serverEndPointInterface.getNotificationList(from, size, callback);
//    }
//
//    public static void getUserStatistics(Callback<Object> callback) {
//        serverEndPointInterface.getUserStatistics(callback);
//    }
//
//    public static void getUserTraffics(Callback<Object> callback) {
//        serverEndPointInterface.getUserTraffics(callback);
//    }

//    public static void getAllMyPosts(@Query("from") int from, @Query("size") int size, Callback<Posts> callback) {
//        serverEndPointInterface.getAllMyPosts(from, size, callback);
//    }

//    public static void markAllNotificationsAsRead(Callback<Object> callback) {
//        serverEndPointInterface.markAllNotificationsAsRead(callback);
//    }
//
//    public static void updatePassword(JSONObject passwords, Callback<Object> callback) {
//        serverEndPointInterface.updatePassword(passwords, callback);
//    }

//     [sortIndex: "id", "earning"]
//     [deviceType: "android", "ios", null]
//     [filterString: "{"status":["live", "paused", "ended"]}"]
//     [sortDirection: "desc", "asc"]
//    public static void getMyFavorites(String lastValue, String sortIndex, String sortDirection, String deviceType, int limit, int totalCount, String filterString, Callback<Assignments> callback) {
//        serverEndPointInterface.getMyFavorites(lastValue, sortIndex, sortDirection, deviceType, limit, totalCount, filterString, callback);
//    }
//
//    public static void getCampaignDetail(int campaignId, Callback<com.boostinsider.android.RetrofitAPI.models.deeplink.Response> callback) {
//        serverEndPointInterface.getCampaignDetail(campaignId, callback);
//    }

//    public static void getLockedCampaignApplication(int campaignId, Callback<Result> callback) {
//        serverEndPointInterface.getLockedCampaignApplication(campaignId, callback);
//    }

//    public static void apiApplyCampaign(int userId, int campaignId, Callback<Result> callback) {
//        serverEndPointInterface.apiApplyCampaign(userId, campaignId, callback);
//    }
}


