package com.sun.moviedb.view.actor

import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.Observer
import com.sun.moviedb.R
import com.sun.moviedb.base.ViewModelBaseFragment
import com.sun.moviedb.databinding.FragmentActorInfoBinding
import com.sun.moviedb.view.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_actor_info.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by nguyenxuanhoi on 2019-08-29.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class ActorFragment : ViewModelBaseFragment<ActorViewModel, FragmentActorInfoBinding>() {
    override val viewModel: ActorViewModel by viewModel()
    private val actor by lazy {
        arguments?.let {
            ActorFragmentArgs.fromBundle(it).caster
        }
    }
    override val getContentViewId: Int = R.layout.fragment_actor_info

    override fun initializeView(savedInstanceState: Bundle?) {
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewModel = viewModel
        initTooBar(toolBar)
        val adapter=MovieAdapter {

        }
        rv_more_movies.adapter=adapter
        motion_layout_poster_and_movie_info.setTransitionListener(object : MotionLayoutListener {
            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                p1: Int,
                p2: Int,
                progress: Float
            ) {
                if (motionLayout?.startState == R.id.frame_poster_top && motionLayout.endState == R.id.frame_more_movies_expanded) {
                   motion_layout_more_movies.progress = progress
                } else {
                    Log.i("Progress", progress.toString())
                    if (progress != 1f) {
                        motion_layout_movie_info.progress = progress
                    }
                }
            }

        })
        motion_layout_poster_and_movie_info.progress = 1f
        motion_layout_movie_info.progress = 1f
    }


    override fun initializeComponents() {
        actor?.let {
            viewModel.initData(it.id)
            viewModel.getProfile()
            viewModel.getMovieByCaster()
        }
        viewModel.error.observe(this, Observer {
            handleBusinessException(it)
        })

    }

}