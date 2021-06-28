package com.android.theta.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.theta.R
import com.android.theta.databinding.LoginFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        initGoogle()
        return binding.root
    }

    private fun initGoogle() {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.currentUser
        updateUI(currentUser)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("539785867358-o82qpni4ekcin0abmffvc2tbscls729j.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null && currentUser.isEmailVerified) {
            Timber.i("Already logged in ${currentUser.displayName}")
            currentUser.displayName?.let { FirebaseCrashlytics.getInstance().setUserId(it) }
            findNavController().navigate(R.id.action_login_to_user_view)
        }
    }

    override fun onStart() {
        super.onStart()
        binding.loginButton.setOnClickListener {
            signIn()
        }
        binding.vendor.setOnClickListener {
            vendorLogin()
        }
    }

    private fun vendorLogin() {
        findNavController().navigate(R.id.action_loginFragment_to_VendorLoginFragment)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)
                    if (account != null) {
                        Timber.i("Logged in ${account.displayName}")
                        firebaseAuthWithGoogle(account)
                        findNavController().navigate(R.id.action_login_to_user_view)
                    }
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Timber.w("Google sign in failed $e")
                }
            }
        }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Timber.d("firebaseAuthWithGoogle: ${acct.id!!}")

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Timber.d("signInWithCredential:success")
                val user = auth.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Timber.w("signInWithCredential:failure ${task.exception}")
            }
        }
    }

}