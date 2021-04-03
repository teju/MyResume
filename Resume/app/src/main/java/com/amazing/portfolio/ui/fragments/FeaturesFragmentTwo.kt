package com.amazing.portfolio.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.model.featuresContent.KeyNotes
import com.amazing.portfolio.model.featuresContent.KeyNotesICons
import com.amazing.portfolio.ui.adapters.FeaturesAdapter
import com.amazing.portfolio.ui.adapters.FeaturesIconsAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_features_two.*


class FeaturesFragmentTwo : Fragment() {
    private var recyclerViewAdapter: FeaturesAdapter? = null
    private var recyclerViewBottomAdapter: FeaturesIconsAdapter? = null
    var databaseReference: DatabaseReference? = null
    val keynotesList = ArrayList<KeyNotes>()
    val keynotesIConsList = ArrayList<KeyNotesICons>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_features_two, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity!!,RecyclerView.HORIZONTAL,false)
        val linearLayoutManager_ = LinearLayoutManager(activity!!,RecyclerView.HORIZONTAL,false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView_bottom.layoutManager = linearLayoutManager_

        recyclerViewAdapter = FeaturesAdapter(activity!!)
        recyclerViewAdapter?.keynotesList = keynotesList
        recyclerViewBottomAdapter = FeaturesIconsAdapter(activity!!)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView_bottom.adapter = recyclerViewBottomAdapter

        recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstVisibleItemCount = linearLayoutManager.findFirstVisibleItemPosition()
                recyclerViewBottomAdapter?.selectedPosition = firstVisibleItemCount
                recyclerViewBottomAdapter?.notifyDataSetChanged()

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        ld.showLoadingV2()
        fetchIcons()
    }

    fun fetchData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("features/keynotes")
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                keynotesList.clear()
                ld.hide()
                for (postSnapshot in snapshot.children) {
                    val products : KeyNotes = postSnapshot.getValue(KeyNotes::class.java)!!
                    keynotesList.add(products)
                }
                recyclerViewAdapter?.keynotesList = keynotesList
                recyclerViewAdapter?.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }
    fun fetchIcons() {
        databaseReference = FirebaseDatabase.getInstance().getReference("features/keynotes_icons")
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                keynotesList.clear()
                for (postSnapshot in snapshot.children) {
                    val products : KeyNotesICons = postSnapshot.getValue(KeyNotesICons::class.java)!!
                    keynotesIConsList.add(products)
                }

                ld.hide()
                recyclerViewBottomAdapter?.keynotesList = keynotesIConsList
                recyclerViewBottomAdapter?.notifyDataSetChanged()
                ld.showLoadingV2()
                fetchData()
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

}