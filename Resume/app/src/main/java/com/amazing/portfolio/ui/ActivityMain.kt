package com.amazing.portfolio.ui

import android.R.id.message
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.*
import android.view.animation.Animation.AnimationListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.Helper
import com.amazing.portfolio.etc.Keys
import com.amazing.portfolio.etc.UserInfoManager
import com.amazing.portfolio.model.Products
import com.amazing.portfolio.ui.adapters.MyProjectsAdapter
import com.amazing.portfolio.ui.fragments.BaseFragment
import com.amazing.portfolio.ui.fragments.LoginFragment
import com.amazing.portfolio.ui.fragments.MainFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URLEncoder
import java.util.*


class ActivityMain : AppCompatActivity() {

    companion object {
        private var MAIN_FLOW_INDEX = 0
        private val MAIN_FLOW_TAG = "MainFlowFragment"
    }
    private var mAdapter: MyProjectsAdapter? = null
    var mDatas = ArrayList<Products>()
    private val MOVE_DEFAULT_TIME: Long = 1000
    private val FADE_DEFAULT_TIME: Long = 300
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        triggerMainProcess()
        initSideView()
        contact_us.setOnClickListener {
            contactUS()
        }
    }

    fun exitApp() {
        if(getSupportFragmentManager().getBackStackEntryCount()>1){
            getSupportFragmentManager().popBackStack();
        }else if(getSupportFragmentManager().getBackStackEntryCount()==1){
                this.finish();
            }
    }
    fun contactUS() {
        try {
            val packageManager: PackageManager = getPackageManager()
            val i = Intent(Intent.ACTION_VIEW)
            val url =
                "https://api.whatsapp.com/send?phone=" + "+91 9964062237" + "&text=" + URLEncoder.encode(
                    "hi",
                    "UTF-8"
                )
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)
            if (i.resolveActivity(packageManager) != null) {
                startActivity(i)
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
    fun startDribbleAnimation(){
        splash_logo.setImageResource(R.drawable.app_logo)
        splash_logo.clearAnimation()
        val transAnim = TranslateAnimation(
            0f, 0f, 0f,
            getResources().getDisplayMetrics().heightPixels.toFloat() / 2 - 200
        )
        transAnim.startOffset = 500
        transAnim.duration = 3000
        transAnim.fillAfter = true
        transAnim.interpolator = BounceInterpolator()
        transAnim.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationRepeat(animation: Animation) {
                // TODO Auto-generated method stub
            }

            override fun onAnimationEnd(animation: Animation) {
                img_app.setImageResource(R.drawable.img_app)

                val slideAnimation = AnimationUtils.loadAnimation(this@ActivityMain, R.anim.left_enter)
                img_app.startAnimation(slideAnimation)
                gee.setImageResource(R.drawable.gee)

                val slideAnimationright = AnimationUtils.loadAnimation(this@ActivityMain, R.anim.right_enter)
                gee.startAnimation(slideAnimationright)

                Handler().postDelayed({
                    rllanding.visibility = View.GONE
                   setFragment(LoginFragment())
                }, 3000)

            }
        })
        splash_logo.startAnimation(transAnim);

    }
    fun triggerMainProcess(){
        startDribbleAnimation()

        /*Handler().postDelayed(Runnable {
            setFragment(LoginFragment())
           // setFragment(MenuScreenFragment(true, null))
            splash_logo.visibility = View.GONE

        }, 3000)*/

    }

    fun setFragment(frag: Fragment) {
        try {
            val f = getSupportFragmentManager().beginTransaction()
            val list = getSupportFragmentManager().getFragments()
            for(frag in list){
                if(frag.isVisible){
                    f.hide(frag)
                }
            }

            MAIN_FLOW_INDEX = MAIN_FLOW_INDEX + 1
            f.add(R.id.layoutFragment, frag, MAIN_FLOW_TAG + MAIN_FLOW_INDEX).
                addToBackStack(
                MAIN_FLOW_TAG)
                .commitAllowingStateLoss()
            Helper.hideKeyboard(this)
        } catch (e: Exception) {
            Helper.logException(this@ActivityMain, e)
        }
        if (frag is MainFragment) {
            arrow_right_drop_circle.visibility = View.VISIBLE
            contact_us.visibility = View.VISIBLE
        } else{
            arrow_right_drop_circle.visibility = View.GONE
            contact_us.visibility = View.GONE

        }
    }
    fun setFragment(frag: Fragment,from : Int,to : Int) {
        try {
            val f = getSupportFragmentManager().beginTransaction()
            val list = getSupportFragmentManager().getFragments()
            for(frag in list){
                if(frag.isVisible){
                    f.hide(frag)
                }
            }
            f.setCustomAnimations(from,to);
            MAIN_FLOW_INDEX = MAIN_FLOW_INDEX + 1
            f.add(R.id.layoutFragment, frag, MAIN_FLOW_TAG + MAIN_FLOW_INDEX).
                addToBackStack(
                    MAIN_FLOW_TAG)
                .commitAllowingStateLoss()
            Helper.hideKeyboard(this)
        } catch (e: Exception) {
            Helper.logException(this@ActivityMain, e)
        }

    }

    fun jumpToPreviousFlowThenGoTo(fullFragPackageNameThatStillExistInStack: String, targetFrag: Fragment){
        jumpToPreviousFragment(fullFragPackageNameThatStillExistInStack)
        setFragment(targetFrag)
    }

    fun jumpToPreviousFragment(fullFragPackageNameThatStillExistInStack: String) {
        try {
            var indexTag: String? = null
            var fragmentName: String? = null

            val f = getSupportFragmentManager().beginTransaction()
            var list = getSupportFragmentManager().getFragments()
            for(i in  0..(list.size - 1)){

                if(list.get(i).javaClass.name.equals(fullFragPackageNameThatStillExistInStack, ignoreCase = false)){
                    indexTag = list.get(i).tag
                    fragmentName = list.get(i).javaClass.name
                }

                if(list.get(i).isVisible){
                    f.hide(list.get(i))
                }
            }

            if(indexTag == null){
                onBackPressed()
            }else{

                val currentIndex = getSupportFragmentManager().getFragments().size-1
                for(i in currentIndex downTo 0){
                    try {
                        if((MAIN_FLOW_TAG + i).equals(indexTag, ignoreCase = true)) break
                        try {
                            if(fragmentName.equals(currentFragment.javaClass.name, ignoreCase = true)) break
                        } catch (e: Exception) { }
                        if((indexTag).contains(MAIN_FLOW_TAG, ignoreCase = true)) {
                            getSupportFragmentManager().popBackStackImmediate()
                            MAIN_FLOW_INDEX = MAIN_FLOW_INDEX - 1
                            if(MAIN_FLOW_INDEX < 0) MAIN_FLOW_INDEX = 0
                        }
                    } catch (e: Exception) {
                        Helper.logException(this@ActivityMain, e)
                    }
                }

                list = getSupportFragmentManager().getFragments()
                for(i in  0..(list.size - 1)){
                    if(list.get(i).tag.equals(indexTag, ignoreCase = false)){
                        f.show(list.get(i))
                        break
                    }
                }

                Helper.hideKeyboard(this)
            }

        } catch (e: Exception) {
            Helper.logException(this@ActivityMain, e)
        }
    }

    fun setFragmentByReplace(frag: Fragment) {

        try {
            val f = getSupportFragmentManager().beginTransaction()
            MAIN_FLOW_INDEX = MAIN_FLOW_INDEX + 1
            f.replace(R.id.layoutFragment, frag, MAIN_FLOW_TAG + MAIN_FLOW_INDEX).addToBackStack(
                MAIN_FLOW_TAG
            ).commit()
            Helper.hideKeyboard(this)
        } catch (e: Exception) {
            Helper.logException(this@ActivityMain, e)
        }

    }

    fun clearFragmentBackstack(tag: String) {
        getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun clearFragment() {

        getSupportFragmentManager().popBackStack(MAIN_FLOW_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        for (i in MAIN_FLOW_INDEX downTo 0) {
            try {
                val fragment = getSupportFragmentManager().findFragmentByTag(MAIN_FLOW_TAG + i)
                if (fragment != null)
                    getSupportFragmentManager().beginTransaction().remove(fragment).commitNowAllowingStateLoss()
            } catch (e: Exception) {
                Helper.logException(this@ActivityMain, e)
            }

        }

        getSupportFragmentManager().popBackStack("MAIN_TAB", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        MAIN_FLOW_INDEX = 0
    }
    val currentFragment: Fragment
        get() = getSupportFragmentManager().findFragmentById(R.id.layoutFragment)!!

    fun proceedDoOnBackPressed(){
        Helper.hideKeyboard(this@ActivityMain)

        val f = getSupportFragmentManager().beginTransaction()
        val list = getSupportFragmentManager().getFragments()

        for(frag in list){
            try {
                if(frag.tag!!.contentEquals(MAIN_FLOW_TAG + (MAIN_FLOW_INDEX - 1))){
                    f.show(frag)
                }
            } catch (e: Exception) {
                System.out.println("setOrShowExistingFragmentByTag foundVisible "+e.toString())

            }
        }

        if (getSupportFragmentManager().getBackStackEntryCount() <= 1 || (currentFragment is MainFragment)) {
            this@ActivityMain.finish()
        } else {
            try {
                (currentFragment as BaseFragment).httpScope.onPause()
            } catch (e: Exception) {
                Helper.logException(null, e)
                System.out.println("setOrShowExistingFragmentByTag foundVisible "+e.toString())
            }
            super.onBackPressed()
        }

        MAIN_FLOW_INDEX = MAIN_FLOW_INDEX - 1
        if(MAIN_FLOW_INDEX < 0) MAIN_FLOW_INDEX = 0

    }

    fun logout() {
        UserInfoManager.getInstance(this).logout()
        val intent = Intent(this, ActivityMain::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.putExtra(Keys.STATUS, true)
        startActivity(intent)
        finish()
    }

    fun backToMainScreen(){
        clearFragment()
        triggerMainProcess()
    }


    override fun onBackPressed() {
        try {
            val list = getSupportFragmentManager().getFragments()
            var foundVisible = false
            for (i in 0..(list.size - 1)) {
                if (list.get(i).isVisible) {
                    if (list.get(i) is BaseFragment) {
                        foundVisible = true
                        (list.get(i) as BaseFragment).onBackTriggered()
                    }
                }
            }

            if(!foundVisible)
            proceedDoOnBackPressed()

        } catch (e : java.lang.Exception) {
            super.onBackPressed()
        }
    }
    fun setOrShowExistingFragmentByTag(
        layoutId: Int,
        fragTag: String,
        backstackTag: String,
        newFrag: Fragment,
        listFragmentTagThatNeedToHide: ArrayList<String>
    ) {

        var foundExistingFragment = false

        val fragment = supportFragmentManager.findFragmentByTag(fragTag)
        val transaction = supportFragmentManager.beginTransaction()
        if (fragment != null) {
            for (i in 0 until supportFragmentManager.fragments.size) {

                try {
                    val f = supportFragmentManager.fragments[i]

                    for (tag in listFragmentTagThatNeedToHide) {
                        try {
                            if (f.tag.toString().toLowerCase().equals(tag.toLowerCase())) {
                                transaction.hide(f)
                            }
                        } catch (e: Exception) {
                            Helper.logException(this@ActivityMain, e)
                        }

                    }

                } catch (e: Exception) {
                    Helper.logException(this@ActivityMain, e)
                }

            }

            try {
                transaction.show(fragment).commit()
            } catch (e: Exception) {
                try {
                    transaction.show(fragment).commitAllowingStateLoss()
                } catch (e1: Exception) {
                    System.out.println("setOrShowExistingFragmentByTag Exception transaction "+e.toString())

                    Helper.logException(this@ActivityMain, e)
                }

            }

            foundExistingFragment = true

        }

        if (!foundExistingFragment) {
            //setREmoveFragment(newFrag)
            setFragmentInFragment(layoutId, newFrag, fragTag, backstackTag)
        } else {
//            setREmoveFragment(newFrag)
//            setFragmentInFragment(layoutId, newFrag, fragTag, backstackTag)

        }

    }
    fun setFragmentInFragment(fragmentLayout: Int, frag: Fragment, tag: String, backstackTag: String) {
        try {
            supportFragmentManager.beginTransaction().add(fragmentLayout, frag, tag).addToBackStack(backstackTag)
                .commit()
            Helper.hideKeyboard(this)
        } catch (e: Exception) {
            try {
                supportFragmentManager.beginTransaction().add(fragmentLayout, frag, tag).addToBackStack(backstackTag)
                    .commitAllowingStateLoss()
                Helper.hideKeyboard(this)
            } catch (e1: Exception) {
                Helper.logException(this@ActivityMain, e)
            }

        }

    }
    fun setREmoveFragment(frag: Fragment) {
        try {
            supportFragmentManager.beginTransaction().remove(frag)
                .commitAllowingStateLoss()
            Helper.hideKeyboard(this)
        } catch (e1: Exception) {
            Helper.logException(this@ActivityMain, e1)
        }

    }

    fun initSideView() {
        getAppList()
        rv.setLayoutManager(LinearLayoutManager(this))
        rv.setClipToPadding(false);
        rv.setClipChildren(false);
        sideView()
        arrow_right_drop_circle.setOnClickListener {
            toggle(left_project_list.isVisible)
        }
    }

    public fun toggle(show: Boolean) {
        if(show) {
            left_project_list.visibility = View.GONE
            arrow_right_drop_circle.animate().rotation(360f).setInterpolator(LinearInterpolator()).setDuration(500)
        } else {
            left_project_list.visibility = View.VISIBLE
            arrow_right_drop_circle.animate().rotation(-180f).setInterpolator(LinearInterpolator()).setDuration(500)
        }
    }

    fun sideView() {
        mAdapter = MyProjectsAdapter(this,rv)
        mAdapter?.mDatas = mDatas
        rv.setAdapter(mAdapter)
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                for (i in 0 until recyclerView.childCount) {
                    recyclerView.getChildAt(i).invalidate()
                }
            }
        })

    }
    fun  getAppList() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("/myApps");

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mDatas.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val products : Products = postSnapshot.getValue(Products::class.java)!!
                    mDatas.add(products)

                }
                mAdapter?.mDatas = mDatas
                mAdapter?.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

    }

}

