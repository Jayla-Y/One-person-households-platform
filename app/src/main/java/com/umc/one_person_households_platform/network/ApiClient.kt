package com.umc.one_person_households_platform.network

import com.umc.one_person_households_platform.BuildConfig
import com.umc.one_person_households_platform.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {

    // 홈 화면 마감 임박 공구 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("app/home/grouppurchase")
    suspend fun getGroupBuyingCategories(): Response<GroupBuying>

    // 홈 화면 커뮤니티 인기글 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("app/home/community")
    suspend fun getCommunityCategories(): Response<Community>

    // 홈 화면 금주 HOT 레시피 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("app/home/recipe")
    suspend fun getHotRecipeCategory(): Response<HotRecipe>

    // 공동 구매 화면 최신 공구, 마감 임박 공구 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("app/boards/grouppurchase")
    suspend fun getGroupBuyingSort(
        @Query("sort") sort: String, @Query("startIdx") startIdx: Int, @Query("size") size: Int
    ): Response<GroupBuying>

    // 공동 구매 화면 나머지 카테고리 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("app/boards/grouppurchase")
    suspend fun getGroupBuyingCategory(
        @Query("category") category: String, @Query("startIdx") startIdx: Int, @Query("size") size: Int
    ): Response<GroupBuying>

    // 공동 구매 검색 화면 검색 결과 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("app/boards/grouppurchase/search")
    suspend fun getGroupBuyingSearch(
        @Query("query") query: String, @Query("startIdx") startIdx: Int, @Query("size") size: Int
    ): Response<GroupBuying>

    // 공동 구매 상세 화면 결과 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("app/post/get")
    suspend fun getGroupBuyingDetail(
        @Query("postIdx") postIdx: Int
    ): Response<GroupBuyingDetail>

    // 커뮤니티 리스트 출력
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/boards/community")
    fun getCommunity(
        @Query("category") query: String, @Query("limit") limit: Int // 검색어 파라미터
    ): Call<CommunityDTO>

    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/boards/community")
    fun getCommunitySort(
        @Query("sort") query: String // 검색어 파라미터
    ): Call<CommunityDTO>

    // 커뮤니티 게시글 검색
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/boards/community/search")
    fun getCommunitySearch(
        @Query("query") query: String // 검색어 파라미터
    ): Call<CommunityDTO>

    // 커뮤니티 게시글 디테일 화면
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/post/get")
    fun getPostDetail(@Query("postIdx") postId: Int): Call<CommunityDetailDTO>

    // 커뮤니티 게시글 작성
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @POST("/app/post/create")
    fun addCommunityPost(@Body postData: CommunityAddpostDTO): Call<ApiResponse>

    // 커뮤니티 댓글 불러오기
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/comment/get")
    fun getCommunityComment(
        @Query("commentIdx") commentIdx: Int // 검색어 파라미터
    ): Call<CommunityComment>

    // 커뮤니티 댓글 작성
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @POST("/app/comment/create")
    fun addCommunityComment(@Body commentData: CommentAddItems): Call<CommentAddResult>

    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @DELETE("/app/comment/delete")
    fun deleteComment(@Query("commentIdx") commentIdx: Int): Call<CommonPostResult>

    //게시글 삭제
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @DELETE("/app/post/delete")
    fun deletePost(@Query("postIdx") postIdx: Int): Call<CommonPostResult>

    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @POST("/app/post/heart")
    fun addHeartPost(@Body scrapitem: PostHeartDTO): Call<CommonPostResult>

    // 게시글 공감하기
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @POST("/app/post/heart/cancel")
    fun cancelHeartPost(@Body scrapitem: PostHeartDTO): Call<CommonPostResult>

    // 레시피 목록 가져오기
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/boards/recipe")
    fun getRecipe(
        @Query("sort") sort: String
    ): Call<RecipeDTO>

    // 레시피 북마크 추가 OR 삭제
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @POST("/app/post/scrap")
    fun addRecipeBookmark(@Body scrapitem: RecipeScrapBody): Call<RecipeScrapResponse>

    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @POST("/app/post/scrap/cancel")
    fun cancelRecipeBookmark(@Body scrapitem: RecipeScrapBody): Call<RecipeScrapResponse>

    // 레시피 검색
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/boards/recipe/search")
    fun getRecipeSearch(
        @Query("query") query: String // 검색어 파라미터
    ): Call<RecipeDTO>

    // 레시피 디테일
    @Headers("X-ACCESS-TOKEN: ${BuildConfig.JWT_KEY}")
    @GET("/app/post/get")
    fun getRecipeDetail(@Query("postIdx") postId: Int): Call<RecipeDetail>

    companion object {

        private const val baseUrl = "https://www.jachsang.shop/"

        fun create(): ApiClient {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }
    }
}