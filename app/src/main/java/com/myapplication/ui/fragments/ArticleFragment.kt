package com.myapplication.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.myapplication.databinding.FragmentArticleBinding
import com.myapplication.ui.NewsActivity
import com.myapplication.ui.NewsViewModel


class ArticleFragment : ViewBindingFragment<FragmentArticleBinding>(FragmentArticleBinding::inflate) {

    lateinit var viewModel: NewsViewModel
    val args:ArticleFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
            val article = args.article
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }

            binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Статья сохранена", Snackbar.LENGTH_SHORT).show()
        }
    }
}
