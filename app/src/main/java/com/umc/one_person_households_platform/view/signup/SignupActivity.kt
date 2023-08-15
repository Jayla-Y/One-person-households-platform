package com.umc.one_person_households_platform.view.signup

import android.graphics.Color
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.umc.one_person_households_platform.R
import com.umc.one_person_households_platform.databinding.ActivitySignupBinding
import com.umc.one_person_households_platform.model.Signup
import com.umc.one_person_households_platform.model.SignupResponse
import com.umc.one_person_households_platform.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private fun <T> Call<T>.enqueue(callback: Callback<SignupResponse>) {

}

class SignupActivity : AppCompatActivity() {

    // DataBinding을 위한 바인딩 변수 추가
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // DataBinding을 초기화하고 인플레이션된 레이아웃을 얻습니다.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        // 뷰 초기화 함수 호출
        initializeViews()

        // 뒤로가기 이미지뷰 클릭 이벤트 처리
        binding.ivBack.setOnClickListener {
            goBack()
        }

        // 회원가입 버튼 클릭 이벤트 처리
        binding.btnSignup.setOnClickListener {
            val isValidPassword = validatePassword(binding.etPassword.text.toString())
            if (isValidPassword) {
                val userInput = getUserInput()
                performSignup(userInput) // getUserInput로 얻은 정보를 performSignup 함수로 전달
            } else {
                // 비밀번호 유효성 검사 에러 메시지 표시
            }
        }
    }


    // 뷰 초기화 함수
    private lateinit var passwordEditText: EditText
    private lateinit var passwordValidationText: TextView
    private lateinit var userNameEditText: EditText
    private lateinit var loginIdEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var nicknameEditText: EditText
    private lateinit var adPolicyCheckBox: CheckBox

    private fun initializeViews() {
        val binding: ActivitySignupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)

        // 바인딩 클래스의 인스턴스를 사용하여 뷰들을 초기화합니다.
        with(binding) {
            passwordEditText = etPassword
            passwordValidationText = etPasswordagain
            userNameEditText = etName
            loginIdEditText = etId
            emailEditText = etEmail
            nicknameEditText = etNickname
            adPolicyCheckBox = checkBox
        }
    }


    // 뒤로가기 기능 함수
    private fun goBack() {
        supportFragmentManager.popBackStack()
    }

    // 비밀번호 유효성 검사 함수
    private fun validatePassword(password: String): Boolean {
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val isLengthValid = password.length >= 8

        val isValid = hasUpperCase && hasLowerCase && hasDigit && isLengthValid

        // 비밀번호 유효성 검사 결과에 따라 메시지와 색상 설정
        binding.tvPasswordValidation.text = if (isValid) "사용 가능한 비밀번호입니다."
        else "대문자, 소문자, 숫자를 포함하여 8자 이상 입력바랍니다."
        binding.tvPasswordValidation.setTextColor(
            if (isValid) Color.GREEN else Color.RED
        )
        return isValid
    }

    // 사용자 입력 정보 가져오는 함수
    private fun getUserInput(): Signup {
        with(binding) {
            return Signup(
                userNameEditText.text.toString(),
                loginIdEditText.text.toString(),
                passwordEditText.text.toString(),
                emailEditText.text.toString(),
                nicknameEditText.text.toString(),
                if (adPolicyCheckBox.isChecked) 1 else 0
            )
        }
    }

        // 회원가입 처리 함수
        private fun performSignup(signupRequest: Signup) {
            val retrofit = ApiClient.create()

            val call = retrofit.signup(signupRequest)
            call.enqueue(object : Callback<SignupResponse> {
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>
                ) {
                    if (response.isSuccessful) {
                        val signupResponse = response.body()
                        if (signupResponse?.isSuccess == true) {
                            val jwt = signupResponse.signupResult.jwt
                            val userIdx = signupResponse.signupResult.userIdx
                            // 회원가입 성공 처리
                        } else {
                            val errorMessage = signupResponse?.message ?: "알 수 없는 오류 발생"
                            // 회원가입 실패 처리
                        }
                    } else {
                        val errorMessage = response.message()
                        // 통신 실패 처리
                    }
                }

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    val errorMessage = "네트워크 연결 오류: ${t.message}"
                    // 네트워크 연결 실패 처리
                }
            })


        }
    }


