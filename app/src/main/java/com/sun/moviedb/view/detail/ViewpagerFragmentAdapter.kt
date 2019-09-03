package com.sun.moviedb.view.detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sun.moviedb.R
import com.sun.moviedb.utils.CurrentPosistion


/**
 * Created by nguyenxuanhoi on 2019-07-19.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class ViewpagerFragmentAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private var fragments = ArrayList<Fragment>()

    fun submitData(fragments:ArrayList<Fragment>){
        this.fragments = fragments
        notifyDataSetChanged()
    }


    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
    override fun getPageTitle(position: Int): CharSequence?= when(position){
        CurrentPosistion.TRAILER->context.getString(R.string.title_trailer)
        CurrentPosistion.CASTING->context.getString(R.string.title_casting)
        CurrentPosistion.PRODUCER->context.getString(R.string.title_producer)
        else->super.getPageTitle(position)

    }


}
