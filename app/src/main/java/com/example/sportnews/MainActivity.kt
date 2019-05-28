package com.example.sportnews

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.sportnews.model.SportNewsModel
import com.example.sportnews.viewmodel.SportNewsViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(SportNewsViewModel::class.java)
    }

    private val list = ArrayList<SportNewsModel>()
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = ProgressDialog(this)
        recyclerView = findViewById(R.id.announcements_recycler_view)
        recyclerView!!.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        progressBar!!.setTitle("Please wait")
        progressBar!!.setMessage("Loading sports headlines.........")

        progressBar!!.window!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFD4D9D0")))

        progressBar!!.isIndeterminate = false
        progressBar!!.setCancelable(false)

    }

    override fun onResume() {
        super.onResume()
        progressBar!!.show()
        viewModel.getSportNews()
        observeResponseData()
    }


    private fun observeResponseData() {
        viewModel.liveData.observe(this, Observer { data ->
            progressBar!!.dismiss()
            if (data != null) {
                list.addAll(data)

                val adapter = SportNewsAdapter(list, this, object : SportNewsAdapter.ItemClickListener {
                    override fun onItemClicked(model: SportNewsModel) {
                        showFullArticle(model.SiteName, model.UrlName, model.UrlFriendlyDate, model.UrlFriendlyHeadline, model.Headline)
                    }
                })
                recyclerView!!.adapter = adapter
            } else {
                showRetryDialog()
            }
        })
    }

    private fun showRetryDialog() {
        val alertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle("FAILURE")
        alertDialog.setMessage("Sorry, something went wrong, please retry")
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "RETRY"
        ) { dialog, which ->
            progressBar!!.show()
            viewModel.getSportNews()
            observeResponseData()}
        alertDialog.show()
    }

    private fun showFullArticle(siteName: String, urlName: String, urlFriendlyDate: String, urlFriendlyHeadline: String, headline: String) {
        val intent = Intent(this, FullArticleActivity::class.java)
        intent.putExtra("site_name", siteName)
        intent.putExtra("url_name", urlName)
        intent.putExtra("url_friendly_date", urlFriendlyDate)
        intent.putExtra("url_friendly_headline", urlFriendlyHeadline)
        intent.putExtra("headline", headline)

        startActivity(intent)
    }
}
