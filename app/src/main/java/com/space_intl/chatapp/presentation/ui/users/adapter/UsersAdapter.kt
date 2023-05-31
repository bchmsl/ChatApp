package com.space_intl.chatapp.presentation.ui.users.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space_intl.chatapp.R
import com.space_intl.chatapp.databinding.ItemUserBinding
import com.space_intl.chatapp.presentation.base.adapter.CustomItemCallback
import com.space_intl.chatapp.presentation.base.adapter.OnClickListener
import com.space_intl.chatapp.presentation.ui.users.adapter.UsersAdapter.UsersViewHolder
import com.space_intl.chatapp.presentation.ui.users.fragment.UsersFragment
import com.space_intl.chatapp.presentation.ui.users.model.UserUIModel

/**
 * RecyclerView Adapter for [UsersFragment].
 * @see ListAdapter
 * @see UsersViewHolder
 * @see CustomItemCallback
 */
class UsersAdapter :
    ListAdapter<UserUIModel, UsersViewHolder>(CustomItemCallback<UserUIModel>()) {

    private var listener: OnClickListener<UserUIModel>? = null

    fun setListener(listener: OnClickListener<UserUIModel>) {
        this.listener = listener
    }

    /**
     * Function to create the view holder.
     * @param parent as a parameter to create the view holder.
     * @param viewType as a parameter to create the view holder.
     * @return the created view holder.
     * @see ListAdapter.onCreateViewHolder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder =
        UsersViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    /**
     * Function to bind the data to the view.
     * @param holder as a parameter for the view holder.
     * @param position as a parameter for the position of the item.
     */
    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.onBind(getItem(position), listener)
    }

    /**
     * View holder for [UsersAdapter].
     * @param binding as a parameter to get the UI elements.
     * @see RecyclerView.ViewHolder
     */
    class UsersViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: UserUIModel, listener: OnClickListener<UserUIModel>?) {
            with(binding) {
                userNameTextView.text = item.userName
                setChosenItem(item.isOpened, this)
                root.setOnClickListener { listener?.onClick(item, adapterPosition) }
            }
        }

        private fun setChosenItem(isOpened: Boolean, binding: ItemUserBinding) {
            with(binding.userNameTextView) {
                if (isOpened) {
                    setTextColor(
                        resources.getColor(
                            R.color.purple_default,
                            null
                        )
                    )
                    typeface = Typeface.DEFAULT_BOLD
                } else {
                    setTextColor(
                        resources.getColor(
                            R.color.neutral_02_dark_grey,
                            null
                        )
                    )
                    typeface = Typeface.DEFAULT
                }
            }
        }
    }
}
