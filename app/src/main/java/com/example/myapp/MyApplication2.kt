package com.example.myapp

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.common.KakaoSdk

class MyApplication2 : MultiDexApplication() {
    companion object{
        lateinit var auth: FirebaseAuth
        var email: String? = null
        //lateinit var db : FirebaseFirestore
        //lateinit var storage : FirebaseStorage

        fun checkAuth(): Boolean{
            var currentUser = auth.currentUser
            return currentUser?.let {
                email = currentUser.email
                currentUser.isEmailVerified
            }?: let{
                false
            }
        }
    }

    override fun onCreate(){
        super.onCreate()
        auth = Firebase.auth

        //db = FirebaseFirestore.getInstance()
        //storage = Firebase.storage

        KakaoSdk.init(this, "4a2f50e85daf54224e3d46802235380a")
    }
}