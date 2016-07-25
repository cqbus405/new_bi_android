package com.boostinsider.android.RetrofitAPI;

import com.boostinsider.android.data.campaign.Campaigns;
import com.boostinsider.android.data.comment.Comments;
import com.boostinsider.android.data.other.CommentInfo;
import com.boostinsider.android.data.other.LoginInfo;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Kevin on 6/23/2015.
 */
public interface ServerEndPointInterface {

//    @FormUrlEncoded
//    @POST("/api/app/server/info")
//    void getServerInfo(@Field("version") String version, @Field("pin") String pin, Callback<Object> callback);

    @POST("/api/v1/users/app/login")
    void login(@Body LoginInfo loginInfo, Callback<Object> callback);

//    @POST("/api/v1/users/app/vipsignup")
//    void signUpWithName(@Body SignUpInfo signUpiInfo, Callback<SignUpCallback> signUpCallback);

    @POST("/api/v1/users/app/vipsignup")
    void signUpWithoutName(@Body LoginInfo loginInfo, Callback<Object> callback);

    @GET("/api/v1/users/app/campaign/available")
    void getCampaignPool(@Query("from") int from, @Query("size") int size, @Query("from_ios") boolean from_ios, @Query("device_type") String type, Callback<Campaigns> callback);

//    @GET("/api/v1/users/app/campaign/default")
//    void getCampaignPoolDefault(@Query("from") int from, @Query("size") int size, @Query("from_ios") boolean from_ios, @Query("device_type") String type, Callback<CampaignPool> campaignPool);

//    @POST("/api/v1/users/app/campaign/like/{id}")
//    void likeCampaign(@Path("id") int campaignID, @Body Like like, Callback<PostReturn> status);
//
//    @POST("/api/v1/users/assignments/{id}/status")
//    void updateStatus(@Path("id") int assignmentID, @Body AssignmentStatus status, Callback<PostReturn> cb);

//    @POST("/api/v1/users/social/update/twitter")
//    void saveTwitterCredentials(@Body SaveTwitter saveTwitter, Callback<SocialReturn> status);
//
//    @POST("/api/v1/users/social/update/tumblr")
//    void saveTumblrCredentials(@Body SaveTumblr saveTumblr,Callback<SocialReturn> status);

//    @Multipart
//    @POST("/api/v1/users/social/share")
//    void shareCampaign(@Part("assignment_id") int assignmentId, @Part("social_list") String[] socialList,
//                       @Part("message") String message, @Part("rating") int rating, @Part("image") String image,
//                       @Part("uploadingFile") TypedFile uploadingFile, Callback<Object> shareCampaignCallback);

//    @POST("/api/v1/users/withdraw")
//    void requestWithdraw(@Body WithdrawRequestInfo withdrawRequestInfo, Callback<WithdrawRequestCallback> withdrawRequestCallback);
//
//    @GET("/api/v1/users/withdraw?status=unpaid")
//    void getWithdrawPenddingList(Callback<WithdrawListCallback> withdrawListCallback);
//
//    @GET("/api/v1/users/withdraw?status=paid")
//    void getWithdrawPreviousList(Callback<WithdrawListCallback> withdrawListCallback);
//
//    @GET("/api/v1/users/app/info")
//    void getUserInfo(Callback<Object> userBaseInfoCallback);
//
//    @POST("/api/v1/users/app/update")
//    void updateUserBaseInfo(@Body UserInfoUpdateRequest userInfoUpdateRequest, Callback<Object> userInfoUpdateCallback);
//
//    @Multipart
//    @POST("/api/v1/users/app/image/upload")
//    void uploadImagesToServerWithType(@Part("uploadingFile") TypedFile photo, @Query("type") String type, Callback<PictureUploadCallback> pictureUploadCallback);
//
//    @POST("/api/v1/users/app/performance/assignment")
//    void getAssignmentPerformance(@Body PerformanceRequestInfo performanceRequestInfo, Callback<PerformanceCallback> callback);
//
//    @POST("/api/v1/users/app/performance/summary")
//    void getOverallPerformance(Callback<Object> callback);
//
//    @GET("/api/app/users/level/info")
//    void getLevelInfo(Callback<Object> callback);

//    @GET("/api/v1/users/app/posts/latest")
//    void getLatestPost(@Query("from") int from, @Query("size") int size, Callback<Posts> callback);

    @POST("/api/v1/users/app/post/campaign")
    void getComments(@Body CommentInfo commentInfo, Callback<Comments> callback);

//    @GET("/api/app/vip/assignment/summary")
//    void getInsiderClub(Callback<InsiderClub> callback);

//    @POST("/api/v1/users/app/contact")
//    void sendFeedback(@Body FeedbackInfo feedbackInfo, Callback<Object> callback);

//    @GET("/api/v1/users/getreferrals")
//    void getReferrals(@Query("from") int from, Callback<Referrals> callback);

//    @GET("/api/v1/messages/getmessages/{conversationId}")
//    void getMessages(@Query("from") int from, @Query("size") int size, @Path("conversationId") String conversationId, @Query("to_id") int toId, Callback<GetMessagesReturn> callback);
//
//    @POST("/api/v1/messages/sendmessage")
//    void sendMessage(@Body DataToSend dataToSend, Callback<SendMessageReturn> callback);

//    @Multipart
//    @POST("/api/v1/assignment/{id}/selfpost")
//    void selfPost(@Path("id") int assignmentId, @Part("channel") String channel,
//                  @Part("userId") String userId, @Part("uploadingFile") TypedFile uploadingFile,
//                  Callback<Object> callback);

//    @Multipart
//    @POST("/api/v1/users/app/post/campaign")
//    void getMyPost(@Part("from") int from, @Part("size") int size, @Part("campaign_id") int id,
//                   @Part("user_id") String userId, Callback<Posts> callback);

//    @GET("/api/v1/messages/getconversationlist")
//    void getConversationList(@Query("from") int from, @Query("size") int size, Callback<GetConversationListReturn> callback);

//    @POST("/api/v1/messages/updateread/{targetId}")
//    void updateIsRead(@Path("targetId") int senderId, Callback<Object> callback);

//    @GET("/api/v1/users/insiderclub/isvip")
//    void getIsVip(Callback<GetIsVip> callback);

//    @POST("/api/v1/users/insiderclub/apply")
//    void applyVip(@Body ApplicationInfo applicationInfo, Callback<Object> callback);

//    @GET("/api/v1/users/app/posts/campaign/{campaignId}")
//    void doRedirect(@Query("device_type") String deviceType, @Path("campaignId") int campaignId, Callback<Object> callback);

//    @GET("/api/v1/users/notification")
//    void getNotificationList(@Query("from") int from, @Query("size") int size, Callback<Notifications> callback);
//
//    @GET("/api/v1/users/statistic")
//    void getUserStatistics(Callback<Object> callback);
//
//    @GET("/api/v1/users/traffic")
//    void getUserTraffics(Callback<Object> callback);

//    @GET("/api/v1/users/app/posts/allmyposts")
//    void getAllMyPosts(@Query("from") int from, @Query("size") int size, Callback<Posts> callback);

//    @POST("/api/v1/users/notification/markread")
//    void markAllNotificationsAsRead(Callback<Object> callback);
//
//    @POST("/api/v1/users/password/update")
//    void updatePassword(@Body JSONObject passwords, Callback<Object> callback);
//
//    @GET("/api/v1/users/favorites")
//    void getMyFavorites(@Query("lvalue") String lastValue, @Query("sidx") String sortIndex, @Query("sord") String sortDirection, @Query("device_type") String deviceType, @Query("size") int limit, @Query("totalcount") int totalCount, @Query("filters") String filterString, Callback<Assignments> callback);
//
//    @GET("/api/v1/users/app/campaign/{campaignId}")
//    void getCampaignDetail(@Path("campaignId") int campaignId, Callback<Response> callback);

//    @GET("/api/v1/campaign/{campaignId}/application")
//    void getLockedCampaignApplication(@Path("campaignId") int campaignId, Callback<Result> callback);

//    @POST("/api/app/user/{userId}/campaign/{campaignId}/apply")
//    void apiApplyCampaign(@Path("userId") int userId, @Path("campaignId") int campaignId, Callback<Result> callback);
}