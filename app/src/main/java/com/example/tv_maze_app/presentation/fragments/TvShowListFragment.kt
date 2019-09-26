package com.example.tv_maze_app.presentation.fragments

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tv_maze_app.BR
import com.example.tv_maze_app.R
import com.example.tv_maze_app.data.implementations.TvShowRepositoryImpl
import com.example.tv_maze_app.data.entities.ResultState
import com.example.tv_maze_app.data.entities.TvShow
import com.example.tv_maze_app.databinding.FragmentTvShowListBinding
import com.example.tv_maze_app.presentation.listeners.TvShowClickListener
import com.example.tv_maze_app.presentation.viewmodels.TvShowViewModel
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

class TvShowListFragment : Fragment(), TvShowClickListener {

    private lateinit var mBinding: FragmentTvShowListBinding
    private lateinit var mViewModel: TvShowViewModel
    private lateinit var mAdapter: TvShowAdapter

    private val mViewModelFactory by lazy {
        TvShowViewModel.Factory(TvShowRepositoryImpl())
    }

    companion object {
        fun newInstance() = TvShowListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this@TvShowListFragment, mViewModelFactory)
                .get(TvShowViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentTvShowListBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.header_title_tv_show)

        setAdapterWithRecyclerView()

        lifecycleScope.launchWhenStarted {
            doLoadTvShowList(this)
        }
        mBinding.swipeRefresh.setOnRefreshListener {
            lifecycleScope.launchWhenStarted {
                doRefreshTvShowList(this)
            }
        }
    }

    override fun onItemClick(tvShow: TvShow) {
        parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, TvShowDetailsFragment.newInstance(tvShow))
                .commit()
    }

    override fun onFavoriteClick(tvShow: TvShow) {

    }

    override fun onWebsiteClick(url: String) {
        if (!TextUtils.isEmpty(url)) {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                CustomTabsIntent.Builder().build().launchUrl(activity, Uri.parse("http://$url"))
            } else {
                CustomTabsIntent.Builder().build().launchUrl(activity, Uri.parse(url))
            }
        } else {
            Timber.d("Empty url")
        }
    }

    private fun setAdapterWithRecyclerView() {
        mAdapter = TvShowAdapter(this)
        val rv = mBinding.recyclerView
        rv.layoutManager = LinearLayoutManager(activity)
        rv.setHasFixedSize(true)
        rv.adapter = mAdapter
    }

    private fun doLoadTvShowList(scope: CoroutineScope) {
        mViewModel.loadTvShowList(scope)?.observe(this@TvShowListFragment, Observer { result ->
            result?.let {
                handleResult(result)
            }
        })
    }

    private fun doRefreshTvShowList(scope: CoroutineScope) {
        mViewModel.refreshTvShowList(scope)?.observe(this@TvShowListFragment, Observer { result ->
            result?.let {
                handleResult(result)
            }
        })
    }

    private fun handleResult(result: ResultState<List<TvShow>>) {
        when (result) {
            is ResultState.Loading -> updateViewState(true)
            is ResultState.Failure -> updateViewState(false)
            is ResultState.Error -> updateViewState(false)
            is ResultState.Success -> {
                mAdapter.setList(result.data)
                updateViewState(false)
            }
        }
    }

    private fun updateViewState(showProgress: Boolean) {
        mBinding.showProgress = showProgress
        mBinding.swipeRefresh.isRefreshing = false
        mBinding.swipeRefresh.isEnabled = false
        if (showProgress) mBinding.isEmpty = false
        if (!showProgress) {
            mBinding.isEmpty = mAdapter.getList().isEmpty()
            mBinding.swipeRefresh.isEnabled = true
        }
    }


    //------ TvShowAdapter:
    private class TvShowAdapter(listener: TvShowClickListener) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

        private var mListener: TvShowClickListener = listener
        private var mList: ArrayList<TvShow> = ArrayList()

        fun setList(list: List<TvShow>) {
            mList.clear()
            mList.addAll(list)
            notifyDataSetChanged()
        }

        fun getItem(position: Int): TvShow = mList[position]

        fun getList(): List<TvShow> = mList

        override fun getItemCount(): Int = mList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val holder: ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.item_tv_show, parent, false)
            return ViewHolder(holder)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position), mListener)
        }

        class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(data: Any, listener: TvShowClickListener) {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.clickListener, listener)
            }
        }
    }
}