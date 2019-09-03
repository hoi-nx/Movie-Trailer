package com.sun.moviedb.view.detail

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.sun.moviedb.R
import com.sun.moviedb.base.ViewModelBaseFragment
import com.sun.moviedb.databinding.FragmentDetailBinding
import com.sun.moviedb.utils.PlayManager
import com.sun.moviedb.utils.StringUtils
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by nguyenxuanhoi on 2019-08-22.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class DetailMovieFragment : ViewModelBaseFragment<DetailMovieViewModel, FragmentDetailBinding>() {
    private val movie by lazy {
        arguments?.let {
            DetailMovieFragmentArgs.fromBundle(it).movie
        }
    }
    private lateinit var playManager: PlayManager
    override val viewModel: DetailMovieViewModel by viewModel()

    override val getContentViewId = com.sun.moviedb.R.layout.fragment_detail

    override fun initializeView(savedInstanceState: Bundle?) {
        movie?.let {
            viewDataBinding.item = movie
        }
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewModel = viewModel
//        lifecycle.addObserver(details_backdrop_scrim)
//        details_backdrop_scrim.addYouTubePlayerListener(object:AbstractYouTubePlayerListener(){
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                val videoId = "S0Q4gqBUs7c"
//                youTubePlayer.loadVideo(videoId)
//                youTubePlayer.cueVideo(videoId,0f)
//            }
//        })


    }

    private fun extractYoutubeUrl(url: String) {
        @SuppressLint("StaticFieldLeak") val mExtractor = object : YouTubeExtractor(context!!) {
            override fun onExtractionComplete(
                sparseArray: SparseArray<YtFile>?,
                videoMeta: VideoMeta
            ) {
                sparseArray?.let {
                    if (::playManager.isInitialized) {
                        playManager.release()
                        playManager.init(context!!, details_backdrop_scrim, sparseArray.get(22).url)
                    }
                }
            }
        }
        mExtractor.extract(url, true, true)
    }

    override fun initializeComponents() {
        playManager = PlayManager()
        val adapter = ViewpagerFragmentAdapter(context!!, childFragmentManager)
        viewDataBinding.viewPager.adapter = adapter
        viewDataBinding.viewPager.offscreenPageLimit = 2
        viewDataBinding.tabLayout.setupWithViewPager(viewDataBinding.viewPager)
        initAdapter(adapter)
        movie?.let {
            viewModel.initData(it.id)
            viewModel.getMovieDetailDto()
        }
        viewModel.error.observe(this, Observer {
            handleBusinessException(it)
        })
    }

    private fun initAdapter(adapter: ViewpagerFragmentAdapter) {
        val fragmens = ArrayList<Fragment>()
        val fragmentTrailer = TrailerFragment.newInstance()
        fragmentTrailer.setonTrailerClickListener {
            extractYoutubeUrl(StringUtils.getUrlYoutube(it.key))
        }
        fragmentTrailer.setOnFirstTrailerListener {
            extractYoutubeUrl(StringUtils.getUrlYoutube(it))
        }
        val fragmentCast=CastFragment.newInstance()
        fragmentCast.setOnCasterClickListener {
            val directions=DetailMovieFragmentDirections.actDetailToActorInfo(it)
            findNavController().navigate(directions)

        }
        fragmens.add(fragmentTrailer)
        fragmens.add(fragmentCast)
        fragmens.add(ProductFragment.newInstance())
        adapter.submitData(fragmens)
    }

    override fun onPause() {
        super.onPause()
        if (::playManager.isInitialized) playManager.reset()

    }

    override fun onDestroy() {
        if (::playManager.isInitialized) playManager.release()
        super.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val currentOrientation = resources.configuration.orientation
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideSystemUiFullScreen()
        } else {
            hideSystemUi()
        }
    }

    private fun hideSystemUiFullScreen() {
        details_backdrop_scrim?.let {
            it.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }

    }

    private fun hideSystemUi() {
        details_backdrop_scrim?.let {
            it.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }

    }
}
