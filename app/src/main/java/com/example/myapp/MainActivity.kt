package com.example.myapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.myapp.MyApplication2.Companion.email
import com.example.myapp.databinding.ActivityMainBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApi
import com.kakao.sdk.user.UserApiClient


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val keyHash = Utility.getKeyHash(this)
        Log.d("mobileApp", keyHash)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)

            val email = binding.authEmailEditView.text.toString()
            val password = binding.authPasswordEditView.text.toString()
            MyApplication2.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    if(task.isSuccessful){
                        if(MyApplication2.checkAuth()){
                            MyApplication2.email = email
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(baseContext, "이메일 인증이 되지 않았습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }


        }

        binding.goSignInBtn.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        binding.btnKakaoLogin.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            // 토큰 정보 보기
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                if (error != null) {
                    Log.e("mobileApp", "토큰 정보 보기 실패", error)
                }
                else if (tokenInfo != null) {
                    Log.i("mobileApp", "토큰 정보 보기 성공")
                    val intent = Intent(this, FragmentActivity::class.java)
                    startActivity(intent)
                }
            }
            // 카카오계정으로 로그인 공통 callback 구성
            // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("mobileApp", "카카오계정으로 로그인 실패", error)
                } else if (token != null) {
                    Log.i("mobileApp", "카카오계정으로 로그인 성공 ${token.accessToken}")
                    // 사용자 정보 요청 (기본)
                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.e("mobileApp", "사용자 정보 요청 실패", error)
                        }
                        else if (user != null) {
                            Log.i("mobileApp", "사용자 정보 요청 성공 ${user.kakaoAccount?.email}")
                            var scopes = mutableListOf<String>()
                            if(user.kakaoAccount?.email != null) {
                                MyApplication2.email = user.kakaoAccount?.email

                                startActivity(intent)
                            }
                            else if(user.kakaoAccount?.emailNeedsAgreement == true){
                                Log.i("mobileApp", "사용자에게 추가 동의 필요")
                                scopes.add("account_email")
                                UserApiClient.instance.loginWithNewScopes(this, scopes) { token, error ->
                                    if(error != null) {
                                        Log.e("mobileApp", "추가 동의 실패", error)
                                    } else {
                                        // 사용자의 정보 재요청
                                        UserApiClient.instance.me { user, error ->
                                            if(error != null) {
                                                Log.e("mobileApp", "사용자 정보 요청 실패", error)
                                            }
                                            else if (user != null){
                                                MyApplication2.email = user.kakaoAccount?.email.toString()
                                                startActivity(intent)
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                Log.e("mobileApp", "이메일 획득 불가", error)
                            }
                        }
                    }
                }
            }
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }

        }


    }
}