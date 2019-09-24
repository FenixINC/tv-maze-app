package com.example.tv_maze_app.presentation.fragments

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.os.bundleOf
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
import com.example.tv_maze_app.data.implementations.TvShowDetailsRepositoryImpl
import com.example.tv_maze_app.data.models.Cast
import com.example.tv_maze_app.data.models.ResultState
import com.example.tv_maze_app.data.models.TvShow
import com.example.tv_maze_app.databinding.FragmentListBinding
import com.example.tv_maze_app.presentation.listeners.TvShowCastListener
import com.example.tv_maze_app.presentation.viewmodels.TvShowDetailsViewModel
import com.example.tv_maze_app.utils.Constants
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

class TvShowCastFragment : Fragment(), TvShowCastListener {

    private lateinit var mBinding: FragmentListBinding
    private lateinit var mViewModel: TvShowDetailsViewModel
    private lateinit var mAdapter: TvShowCastAdapter

    private var mModel: TvShow? = null

    private val mViewModelFactory by lazy {
        TvShowDetailsViewModel.Factory(TvShowDetailsRepositoryImpl())
    }

    companion object {
        fun newInstance(model: TvShow?) = TvShowCastFragment().apply {
            arguments = bundleOf(Constants.KEY_MODEL to model)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            mModel = getParcelable(Constants.KEY_MODEL)
        }

        mViewModel = ViewModelProviders.of(this@TvShowCastFragment, mViewModelFactory)
                .get(TvShowDetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentListBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapterWithRecyclerView()

        lifecycleScope.launchWhenStarted {
            doLoadTvShowCastList(this, mModel?.id)
        }
    }

    override fun onItemClick(cast: Cast) {
        cast.person.url?.let { url ->
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
    }

    private fun setAdapterWithRecyclerView() {
        mAdapter = TvShowCastAdapter(this)
        val rv = mBinding.recyclerView
        rv.layoutManager = LinearLayoutManager(activity)
        rv.setHasFixedSize(true)
        rv.adapter = mAdapter
    }

    private fun doLoadTvShowCastList(scope: CoroutineScope, id: Long?) {
        mViewModel.loadTvShowCastList(scope, id)?.observe(this@TvShowCastFragment, Observer { result ->
            result?.let {
                handleResult(result)
            }
        })
    }

    private fun handleResult(result: ResultState<List<Cast>>) {
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
//        mBinding.swipeRefresh.isRefreshing = false
//        mBinding.swipeRefresh.isEnabled = false
        if (showProgress) mBinding.isEmpty = false
        if (!showProgress) {
            mBinding.isEmpty = mAdapter.getList().isEmpty()
//            mBinding.swipeRefresh.isEnabled = true
        }
    }


    //------ TvShowCastAdapter:
    private class TvShowCastAdapter(listener: TvShowCastListener) : RecyclerView.Adapter<TvShowCastAdapter.ViewHolder>() {

        private var mListener: TvShowCastListener = listener
        private var mList: ArrayList<Cast> = ArrayList()

        fun setList(list: List<Cast>) {
            mList.clear()
            mList.addAll(list)
            notifyDataSetChanged()
        }

        fun getItem(position: Int): Cast = mList[position]

        fun getList(): List<Cast> = mList

        override fun getItemCount(): Int = mList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val holder: ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.item_cast, parent, false)
            return ViewHolder(holder)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position), mListener)
        }

        class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(data: Any, listener: TvShowCastListener) {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.clickListener, listener)
            }
        }
    }
}