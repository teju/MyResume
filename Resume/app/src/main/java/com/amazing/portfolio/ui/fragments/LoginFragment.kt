package com.amazing.portfolio.ui.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.amazing.portfolio.R
import com.amazing.portfolio.model.User
import com.amazing.portfolio.model.UserArray
import com.cunoraz.continuouscrollable.ContinuousScrollableImageView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : BaseFragment() ,View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{
    var TAG = "LoginFragment"
    private val RC_SIGN_IN = 7
    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.login_fragment, container, false)
        return v
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skip.setOnClickListener(this)
        gplus_signup.setOnClickListener(this)
        Handler().postDelayed({
            val image = ContinuousScrollableImageView(activity);
            image.setResourceId(R.drawable.sign_in_page);
            image.alpha = 0.8f
            image.setDirection(ContinuousScrollableImageView.LEFT);
            image.setScaleType(ContinuousScrollableImageView.FIT_XY);
            image.setDuration(10000);
            flTop.addView(image)
        }, 3)
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(activity!!)
            .enableAutoManage(activity!!, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()


    }
    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    private fun signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
            object : ResultCallback<Status?> {
                override fun onResult(p0: Status) {
                   // updateUI(false)
                }
            })
    }

    private fun revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
            object : ResultCallback<Status?> {


                override fun onResult(p0: Status) {
                    //updateUI(false)
                }
            })
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess)
        if (result.isSuccess) {
            // Signed in successfully, show authenticated UI.
            val acct = result.signInAccount
            Log.e(TAG, "display name: " + acct!!.displayName)
            val personName = acct.displayName
            val personPhotoUrl = acct.photoUrl.toString()
            val email = acct.email
            Log.e(
                TAG, "Name: " + personName + ", email: " + email
                        + ", Image: " + personPhotoUrl
            )
            updateUI(personName!!,acct.email!!,"","",personPhotoUrl);
        }
        ld.hide()
    }

    private fun updateUI(name:String,email:String,mobile_no:String,password:String,image:String) {

        val rootRef = FirebaseDatabase.getInstance().reference
        val namesRef = rootRef.child("users")
        val map: MutableMap<String, UserArray> = HashMap()
        var products = UserArray()
        var product = User()
        product.name = name
        product.email = email
        product.mobile_no = mobile_no
        product.password = password
        product.image = image
        products.array.add(product)
        map.put("user", products);
        println("map "+ Gson().toJson(map))
        namesRef.updateChildren(map as Map<String, Any>)
        home().setFragment(MainFragment())
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.skip ->{
                val mainFragment = MainFragment()
                home().setFragment(mainFragment)
            }
            R.id.gplus_signup -> {
                ld.showLoadingV2()
                signIn()
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        ld.hide()
        Log.d(TAG,"onConnectionFailed "+p0.errorMessage)
    }
}