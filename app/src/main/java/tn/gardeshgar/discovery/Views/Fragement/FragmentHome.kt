package tn.gardeshgar.discovery.Views.Fragement

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import tn.gardeshgar.discovery.Adapter.ListCategory
import tn.gardeshgar.discovery.Models.PostsAdmin
import tn.gardeshgar.discovery.PostData
import tn.gardeshgar.discovery.R
import tn.gardeshgar.discovery.Utils.ReadyFunction
import tn.gardeshgar.discovery.Views.Activity.PrayerTimes
import tn.gardeshgar.discovery.Views.Activity.Search
import tn.gardeshgar.oneblood.DataMapList.CategoryAdapter
import tn.gardeshgar.oneblood.DataMapList.RecommendedAdapter


class FragmentHome : Fragment() {
    ///
    val ReadyFunction = ReadyFunction()
    private lateinit var searchView: SearchView
    ///
    lateinit var recylcerCategory: RecyclerView
    lateinit var recylcerAdapterCategory: CategoryAdapter
    lateinit var recylcerRecommended: RecyclerView
    lateinit var AdapterRecommended: RecommendedAdapter
    //
    val ListCategory = ListCategory()
    //val ListRecommended = ListRecommended()
    //
    var PostsModels: ArrayList<PostsAdmin> = ArrayList()
//    lateinit var mShimmerViewContainer: ShimmerFrameLayout
    lateinit var ShowMoreHome: TextView
    lateinit var txtRecomm:TextView
    lateinit var  imgMosque: ImageView
    //
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //
        initView(view);
//        OpenSearch();
        //Check()
        ///
        recylcerCategory = view.findViewById(R.id.recyclerCategory)
        ///
        recylcerRecommended = view.findViewById(R.id.recyclerRecommended)
        ///
        ListCategory.initListCategory()
        ///
        //ListRecommended.initListRecommended()
//        recylcerAdapterCategory = CategoryAdapter(requireContext(),ListCategory.ListCategory as ArrayList<Category>)
//        recylcerCategory.adapter = recylcerAdapterCategory
//        recylcerCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        ////

        recylcerRecommended.setLayoutManager(StaggeredGridLayoutManager(2, 1))
        AdapterRecommended = RecommendedAdapter(requireContext(),34)
        recylcerRecommended.adapter = AdapterRecommended


        // ShowPostsRecommended()

        ShowAllPostRecomm()
        //
//        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        ShowMoreHome.setOnClickListener{
            //
            val progressDialog = ProgressDialog(context,R.style.DialogStyle)
            // progressDialog.setTitle("Loading")
            //progressDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.GRAY));
            progressDialog.setMessage("Please wait")
            progressDialog.getWindow()?.getAttributes()?.gravity = Gravity.BOTTOM;
            //progressDialog.setProgressStyle(android.R.attr.progressBarStyleInverse);
            progressDialog.setCancelable(true)
            progressDialog.show()
            //
            recylcerRecommended.setLayoutManager(StaggeredGridLayoutManager(2, 1))
            AdapterRecommended = RecommendedAdapter(requireContext(),1000)
            recylcerRecommended.adapter = AdapterRecommended
            ShowAllPostRecomm()
            //
            val handler = Handler()
            val runnable = Runnable { //Second fragment after 1 seconds appears
                progressDialog.hide()
                //val Nb: Int = AdapterRecommended.itemCount
                //txtRecomm.text= "Recommended "+ "("+Nb.toString()+")"
            }
            handler.postDelayed(runnable, 3000)
        }

        /*        val handler = Handler()
                val runnable = Runnable { //Second fragment after 1 seconds appears
                    val Nb: Int = AdapterRecommended.getItemCount()
                    txtRecomm.text= "Recommended "+ "("+Nb.toString()+")"
                }
                handler.postDelayed(runnable, 2000)*/
        imgMosque = view.findViewById(R.id.imgMosque);
        imgMosque.setOnClickListener {
            activity?.let{
                val intent = Intent (it, PrayerTimes::class.java)
                it.startActivity(intent)
            }
        }
    }

    fun Check()
    {
        requireActivity().window.getDecorView().getViewTreeObserver()
            .addOnGlobalLayoutListener {
                val r = Rect()
                requireActivity().window.getDecorView()
                    .getWindowVisibleDisplayFrame(r)
                val screenHeight: Int =
                    requireActivity().window.getDecorView().getRootView()
                        .getHeight()
                val keypadHeight: Int = screenHeight - r.bottom

                Log.d(TAG, "keypadHeight = " + keypadHeight);
                if (keypadHeight > screenHeight * 0.15) {
                    //Keyboard is opened
                    hideSystemUIAndNavigation()
                } else {
                    // keyboard is closed
                }
            }
    }

    private fun hideSystemUIAndNavigation() {
        requireActivity().window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    fun initView(view: View) {
//        searchView = view.findViewById(R.id.searchViewHome)
        ShowMoreHome = view.findViewById(R.id.ShowMoreHome)
        txtRecomm = view.findViewById(R.id.txtRecomm)
    }

    fun OpenSearch()
    {
        searchView.setOnClickListener {
            val intent = Intent(activity, Search::class.java)
            startActivity(intent)
        }
    }
    ///

    fun ShowAllPostRecomm() {
        PostsModels = PostData.fetchPostsModels()

        AdapterRecommended.setDataList(PostsModels)
        AdapterRecommended.notifyDataSetChanged()
        // Stopping Shimmer Effect's animation after data is loaded to ListView

        //
        val Nb: Int = AdapterRecommended.itemCount
        txtRecomm.text= "اماکن گردشگری استان تهران "//+ "("+Nb.toString()+")"
    }


}