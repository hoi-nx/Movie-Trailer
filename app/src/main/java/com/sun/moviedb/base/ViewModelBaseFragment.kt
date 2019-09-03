package com.sun.moviedb.base
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel

/**
 * Created by nguyenxuanhoi on 2019-08-14.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
abstract class ViewModelBaseFragment <VM : AndroidViewModel,VB : ViewDataBinding>: BaseFragment<VB>(){
    protected abstract val viewModel:VM
}
