package com.umc.one_person_households_platform.model

import com.google.gson.annotations.SerializedName

data class Signup(
    @SerializedName("userName") val userName: String,
    @SerializedName("loginId") val loginId: String,
    @SerializedName("password") val password: String,
    @SerializedName("email") val email: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("adPolicyAgreement") val adPolicyAgreement: Int
)


    data class SignupResponse(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String,
        @SerializedName("result") val signupResult: SignupResult
    )

    data class SignupResult(
        @SerializedName("jwt") val jwt: String,
        @SerializedName("userIdx") val userIdx: Int
    )





