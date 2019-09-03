package com.sun.moviedb.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb.BR

/**
 * Created by nguyenxuanhoi on 2019-08-16.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
abstract class BaseRecyclerAdapter<Item, ViewBinding : ViewDataBinding>(
    callBack: DiffUtil.ItemCallback<Item>
) : ListAdapter<Item, BaseViewHolder<ViewBinding>>(callBack) {

    override fun submitList(list: List<Item>?) {
        super.submitList(ArrayList<Item>(list ?: listOf()))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding> {
        return BaseViewHolder(
            DataBindingUtil.inflate<ViewBinding>(
                LayoutInflater.from(parent.context),
                getLayoutRes(viewType),
                parent, false
            ).apply {
                bindFirstTime(this)
            })
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        val item: Item? = currentList.getOrNull(position)
        holder.binding.setVariable(BR.item, item)
        item?.let {
            bindView(holder.binding, item, position)
        }
        holder.binding.executePendingBindings()
    }

    protected abstract fun getLayoutRes(viewType: Int): Int

    protected open fun bindFirstTime(binding: ViewBinding) {}

    protected open fun bindView(binding: ViewBinding, item: Item, position: Int) {}

}

open class BaseViewHolder<ViewBinding : ViewDataBinding>(
    val binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root)
