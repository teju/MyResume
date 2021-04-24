package com.amazing.portfolio.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.amazing.portfolio.R
import com.amazing.portfolio.model.AboutUsData
import com.amazing.portfolio.ui.adapters.AboutUsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_about_us.*

class AboutUsFragment : BaseFragment() {
    private var aboutUsAdapter: AboutUsAdapter? = null
    val aboutusList = ArrayList<AboutUsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_about_us, container, false)
        return v
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity!!)
        recyclerView.layoutManager = linearLayoutManager
        aboutUsAdapter = AboutUsAdapter(activity!!)
        aboutUsAdapter?.aboutusList = ArrayList()
        recyclerView.adapter = aboutUsAdapter

        getAboutUSist()
        ld.showLoadingV2()
        whatapp()
    }

    fun  getAboutUSist() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("/aboutus");
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                aboutusList.clear()
                ld.hide()
                for (postSnapshot in dataSnapshot.children) {
                    val products : AboutUsData = postSnapshot.getValue(AboutUsData::class.java)!!
                    aboutusList.add(products)

                }
                aboutUsAdapter?.aboutusList = aboutusList
                aboutUsAdapter?.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
            }
        })

    }

}