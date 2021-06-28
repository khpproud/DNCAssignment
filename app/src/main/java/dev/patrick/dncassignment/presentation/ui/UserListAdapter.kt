package dev.patrick.dncassignment.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import dev.patrick.dncassignment.R
import dev.patrick.dncassignment.databinding.ItemGithubUserBinding
import dev.patrick.dncassignment.databinding.SeparatorInitialBinding
import dev.patrick.dncassignment.domain.model.User
import dev.patrick.dncassignment.presentation.ui.model.UiModel
import timber.log.Timber

class UserListAdapter(
    private val itemClickListener: (user: User, isFavorite: Boolean) -> Unit
) : ListAdapter<UiModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.item_github_user) {
            UserViewHolder.create(parent)
        } else {
            HeaderViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.UserItem -> R.layout.item_github_user
            is UiModel.HeaderItem -> R.layout.separator_initial
            else -> throw UnsupportedOperationException("Unknown type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val uiModel = getItem(position)) {
            is UiModel.UserItem -> (holder as UserViewHolder).bind(uiModel.user, itemClickListener)
            is UiModel.HeaderItem -> (holder as HeaderViewHolder).bind(uiModel.initialChar)
        }
    }

    class UserViewHolder private constructor(
        private val binding: ItemGithubUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, itemClickListener: (user: User, isFavorite: Boolean) -> Unit) {
            var isFavorite = user.isFavorite
            binding.run {
                root.setOnClickListener {
                    isFavorite = !isFavorite
                    itemClickListener(user, isFavorite)
                    ivFavorite.setBackgroundResource(getIconResource(isFavorite))
                }
                ivAvatar.load(user.avatarUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_loading_placeholder_24)
                    transformations(CircleCropTransformation())
                    error(R.drawable.ic_android_24)
                }
                tvUsername.text = user.name
                ivFavorite.setBackgroundResource(getIconResource(isFavorite))
            }
        }

        private fun getIconResource(isFavorite: Boolean): Int {
            return when (isFavorite) {
                true -> R.drawable.ic_star_24
                false -> R.drawable.ic_star_border_24
            }
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return UserViewHolder(
                    ItemGithubUserBinding
                        .inflate(inflater, parent, false)
                )
            }
        }
    }

    class HeaderViewHolder(
        private val binding: SeparatorInitialBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(initialChar: String) {
            binding.tvSeparator.text = initialChar
        }

        companion object {
            fun create(parent: ViewGroup): HeaderViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return HeaderViewHolder(SeparatorInitialBinding.inflate(inflater, parent, false))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
                (oldItem is UiModel.UserItem && newItem is UiModel.UserItem &&
                        oldItem.user.name == newItem.user.name)
                         || (oldItem is UiModel.HeaderItem &&
                        newItem is UiModel.HeaderItem && oldItem.initialChar == newItem.initialChar)

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
                oldItem == newItem
        }
    }
}