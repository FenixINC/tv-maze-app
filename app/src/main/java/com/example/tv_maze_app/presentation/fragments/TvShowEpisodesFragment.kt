package com.example.tv_maze_app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.tv_maze_app.BR
import com.example.tv_maze_app.R
import com.example.tv_maze_app.data.implementations.TvShowDetailsRepositoryImpl
import com.example.tv_maze_app.data.models.Episode
import com.example.tv_maze_app.data.models.ResultState
import com.example.tv_maze_app.data.models.Season
import com.example.tv_maze_app.data.models.TvShow
import com.example.tv_maze_app.databinding.FragmentTvShowEpisodeBinding
import com.example.tv_maze_app.databinding.ItemEpisodeBinding
import com.example.tv_maze_app.databinding.ItemSeasonBinding
import com.example.tv_maze_app.presentation.viewmodels.TvShowDetailsViewModel
import com.example.tv_maze_app.utils.Constants
import kotlinx.coroutines.CoroutineScope

class TvShowEpisodesFragment : Fragment() {

    private lateinit var mBinding: FragmentTvShowEpisodeBinding
    private lateinit var mViewModel: TvShowDetailsViewModel
    private lateinit var mAdapter: TvShowEpisodeAdapter

    private var mModel: TvShow? = null

    private val mViewModelFactory by lazy {
        TvShowDetailsViewModel.Factory(TvShowDetailsRepositoryImpl())
    }

    companion object {
        fun newInstance(model: TvShow?) = TvShowEpisodesFragment().apply {
            arguments = bundleOf(Constants.KEY_MODEL to model)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            mModel = getParcelable(Constants.KEY_MODEL)
        }

        mViewModel = ViewModelProviders.of(this@TvShowEpisodesFragment, mViewModelFactory)
                .get(TvShowDetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentTvShowEpisodeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapterWithRecyclerView()

        lifecycleScope.launchWhenStarted {
            doLoadTvShowEpisodeList(this, mModel?.id)
        }
    }

//    override fun onSeasonClick(showSeasonGroup: Boolean) {
//        mAdapter.setShowSeasonGroup(showSeasonGroup)
//    }

    private fun setAdapterWithRecyclerView() {
        mAdapter = TvShowEpisodeAdapter()
        val rv = mBinding.recyclerView
        rv.layoutManager = LinearLayoutManager(activity)
        rv.setHasFixedSize(true)
        rv.adapter = mAdapter
        (rv.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    private fun doLoadTvShowEpisodeList(scope: CoroutineScope, id: Long?) {
        mViewModel.loadTvShowEpisodeList(scope, id)
                ?.observe(this@TvShowEpisodesFragment, Observer { result ->
                    result?.let {
                        handleResult(result)
                    }
                })
    }

    private fun handleResult(result: ResultState<List<Episode>>) {
        when (result) {
            is ResultState.Loading -> updateViewState(true)
            is ResultState.Failure -> updateViewState(false)
            is ResultState.Error -> updateViewState(false)
            is ResultState.Success -> {
                val mapSeasonEpisode = result.data.asReversed().groupBy(keySelector = { it.season })
                for (key in mapSeasonEpisode.keys) {
                    if (mapSeasonEpisode.containsKey(key)) {
                        mapSeasonEpisode[key]?.let { list ->
                            val season = Season(key.toLong(), key)
                            mAdapter.addItem(season)
                            mAdapter.setList(list, false)
                        }
                    }
                }
                mAdapter.notifyDataChanged()
                updateViewState(false)
            }
        }
    }

    private fun updateViewState(showProgress: Boolean) {
        mBinding.showProgress = showProgress
//        mBinding.swipeRefresh.isRefreshing = false
//        mBinding.swipeRefresh.isEnabled = false
//        if (showProgress) mBinding.isEmpty = false
        if (!showProgress) {
            mBinding.isEmpty = mAdapter.getList().isEmpty()
//            mBinding.swipeRefresh.isEnabled = true
        }
    }


    //------ TvShowEpisodeAdapter:
    private class TvShowEpisodeAdapter : RecyclerView.Adapter<TvShowEpisodeAdapter.ViewHolder>() {

        private var mList = ArrayList<Any>()
        private var mShowSeasonGroup: Boolean = true

        fun addItem(model: Any) {
            mList.add(model)
        }

        fun setList(list: List<Any>, clear: Boolean) {
            if (clear) {
                mList.clear()
            }
            mList.addAll(list)
        }

        fun setShowSeasonGroup(showSeasonGroup: Boolean) {
            mShowSeasonGroup = showSeasonGroup
//            notifyDataChanged()
        }

        fun notifyDataChanged() {
            notifyDataSetChanged()
        }

        fun getItem(position: Int): Any = mList[position]

        fun getList(): List<Any> = mList

        override fun getItemCount(): Int = mList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return when (viewType) {
                R.layout.item_season -> ViewHolder(ItemSeasonBinding.inflate(inflater, parent, false))
                else -> ViewHolder(ItemEpisodeBinding.inflate(inflater, parent, false))
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position), mShowSeasonGroup)
        }

        override fun getItemViewType(position: Int): Int =
                when (getItem(position)) {
                    is Season -> R.layout.item_season
                    else -> R.layout.item_episode
                }

        private open class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(data: Any, showSeasonGroup: Boolean) {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.showSeasonGroup, showSeasonGroup)
            }
        }
    }
}