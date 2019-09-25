package com.example.tv_maze_app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.tv_maze_app.data.models.TvShow
import com.example.tv_maze_app.databinding.FragmentTvShowDetailsBinding
import com.example.tv_maze_app.presentation.adapters.PageAdapter
import com.example.tv_maze_app.utils.Constants
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class TvShowDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentTvShowDetailsBinding
    private lateinit var mPageAdapter: PageAdapter

    private var mModel: TvShow? = null

    companion object {
        fun newInstance(model: TvShow) = TvShowDetailsFragment().apply {
            arguments = bundleOf(Constants.KEY_MODEL to model)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = activity?.toolbar
        if (toolbar != null) run {
            toolbar.setNavigationIcon(com.example.tv_maze_app.R.drawable.ic_arrow_white)
            toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        }

        arguments?.run {
            mModel = getParcelable(Constants.KEY_MODEL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentTvShowDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = mModel?.name
        mBinding.model = mModel

        setAdapterViewPager()
    }

    private fun setAdapterViewPager() {
        mPageAdapter = PageAdapter(childFragmentManager)
        mPageAdapter
                .add("Main", TvShowMainFragment.newInstance(mModel))
                .add("Episodes", TvShowEpisodesFragment.newInstance(mModel))
                .add("Cast", TvShowCastFragment.newInstance(mModel))
                .add("Crew", TvShowCrewFragment.newInstance(mModel))
        mBinding.viewPager.adapter = mPageAdapter
        mBinding.viewPager.offscreenPageLimit = 4
        mBinding.tabLayout.tabMode = TabLayout.MODE_FIXED
        mBinding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
    }
}