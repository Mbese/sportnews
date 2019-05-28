package com.example.sportnews

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.sportnews.model.FullArticleModel
import com.example.sportnews.viewmodel.FullArticleViewModel
import com.squareup.picasso.Picasso


class FullArticleActivity : AppCompatActivity() {

    private var siteName: String? = null
    private var urlName: String? = null
    private var urlFriendlyDate: String? = null
    private var urlFriendlyHeadLine: String? = null
    private var headline: String? = null

    private var headlineTextView: TextView? = null
    private var dateTextView: TextView? = null
    private var bodyTextView: TextView? = null
    private var imageView: ImageView? = null
    private var progressBar: ProgressDialog? = null

    private val viewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(FullArticleViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_article)

        progressBar = ProgressDialog(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent

        if(intent != null && intent.extras != null) {
            siteName = intent.getStringExtra("site_name")
            urlName = intent.getStringExtra("url_name")
            urlFriendlyDate = intent.getStringExtra("url_friendly_date")
            urlFriendlyHeadLine = intent.getStringExtra("url_friendly_headline")
            headline = intent.getStringExtra("headline")
        }

        headlineTextView = findViewById(R.id.blurb)
        dateTextView = findViewById(R.id.date)
        bodyTextView = findViewById(R.id.body)
        imageView = findViewById(R.id.large_imageView)

        progressBar!!.setTitle("Please wait")
        progressBar!!.setMessage("Loading full article.........")

        progressBar!!.window!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFD4D9D0")))

        progressBar!!.isIndeterminate = false
        progressBar!!.setCancelable(false)
    }

    override fun onResume() {
        super.onResume()
        progressBar!!.show()
        viewModel.getFullArticle(siteName, urlName, urlFriendlyDate, urlFriendlyHeadLine)
        observeResponseData()
    }

    private fun observeResponseData() {
        viewModel.liveData.observe(this, Observer { data ->
            progressBar!!.dismiss()
            if (data != null) {
                displayArticle(data)
            }else {
                showRetryDialog()
            }
        })
    }

    private fun showRetryDialog() {
        val alertDialog = AlertDialog.Builder(this@FullArticleActivity).create()
        alertDialog.setTitle("FAILURE")
        alertDialog.setMessage("Sorry, something went wrong, please retry")
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "RETRY"
        ) { dialog, which ->
            progressBar!!.show()
            viewModel.getFullArticle(siteName, urlName, urlFriendlyDate, urlFriendlyHeadLine)
            observeResponseData()}
        alertDialog.show()
    }

    private fun displayArticle(data: FullArticleModel) {
        headlineTextView!!.text = headline
        dateTextView!!.text = data.UrlFriendlyDate
        bodyTextView!!.text = Html.fromHtml(data.Body)

        Picasso.with(this)
            .load(data.ImageUrlLocal + data.LargeImageName)
            .into(imageView)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
