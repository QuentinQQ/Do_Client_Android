package com.example.doapp.login

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

fun firebaseAuthWithGoogle(idToken: String, auth: FirebaseAuth, onResult: (Boolean, FirebaseUser?) -> Unit) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result?.user
                onResult(true, user)
            }else{
                onResult(false, null)
            }
//            onResult(task.isSuccessful)
        }
}

fun initiateGoogleSignIn(
    googleSignInClient: GoogleSignInClient,
//    auth: FirebaseAuth,
    launcher: ActivityResultLauncher<Intent>
) {
    googleSignInClient.signOut().addOnCompleteListener {
        // 触发 Google 登录意图
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }
}

fun googleSignOut(
    googleSignInClient: GoogleSignInClient
){

}
