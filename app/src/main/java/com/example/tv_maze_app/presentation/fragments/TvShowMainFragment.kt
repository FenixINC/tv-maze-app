package com.example.tv_maze_app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.tv_maze_app.data.models.TvShow
import com.example.tv_maze_app.databinding.FragmentTvShowMainBinding
import com.example.tv_maze_app.utils.Constants

class TvShowMainFragment : Fragment() {

    private lateinit var mBinding: FragmentTvShowMainBinding

    private var mModel: TvShow? = null

    companion object {
        fun newInstance(model: TvShow?) = TvShowMainFragment().apply {
            arguments = bundleOf(Constants.KEY_MODEL to model)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.run {
            mModel = getParcelable(Constants.KEY_MODEL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentTvShowMainBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.model = mModel
    }
}