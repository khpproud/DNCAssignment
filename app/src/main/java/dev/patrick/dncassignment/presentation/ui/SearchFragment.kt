package dev.patrick.dncassignment.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import dev.patrick.dncassignment.R
import dev.patrick.dncassignment.base.Resource
import dev.patrick.dncassignment.base.autoCleared
import dev.patrick.dncassignment.base.convertUiModel
import dev.patrick.dncassignment.base.queryTextChangeStateFlow
import dev.patrick.dncassignment.databinding.FragmentSearchBinding
import dev.patrick.dncassignment.domain.model.User
import dev.patrick.dncassignment.presentation.ui.model.PageType
import dev.patrick.dncassignment.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    private var binding by autoCleared<FragmentSearchBinding>()

    private val userListAdapter: UserListAdapter by lazy {
        UserListAdapter { user, isFavorite ->
            viewModel.toggleFavorite(user, isFavorite)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserver()
    }

    private fun initView() {
        binding.run {
            rvSearchResult.adapter = userListAdapter
            rvSearchResult.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                etSearch.queryTextChangeStateFlow()
                    .debounce(500)
                    .distinctUntilChanged()
                    .collect {
                        viewModel.search(it)
                    }
            }
        }
    }

    private fun initObserver() {
        when (requireArguments().getInt(KEY_TYPE)) {
            API_PAGE -> {
                viewModel.users.observe(viewLifecycleOwner, ::observeData)
            }
            FAVORITE_PAGE -> {
                viewModel.favoriteUsers.observe(viewLifecycleOwner, ::observeData)
            }
        }
    }

    private fun observeData(resource: Resource<List<User>>) {
        when (resource) {
            is Resource.Loading -> {
                binding.pbLoading.visibility = View.VISIBLE
            }
            is Resource.Success -> {
                binding.pbLoading.visibility = View.GONE
                if (resource.data.isEmpty()) {
                    binding.tvEmptyResult.visibility = View.VISIBLE
                    binding.tvEmptyResult.text = getString(R.string.empty_results)
                } else {
                    binding.tvEmptyResult.visibility = View.GONE
                }
                val uiModelData = convertUiModel(resource.data)
                userListAdapter.submitList(uiModelData)
            }

            is Resource.Failure -> {
                binding.pbLoading.visibility = View.GONE
                binding.tvEmptyResult.visibility = View.VISIBLE
                binding.tvEmptyResult.text = getString(R.string.error_results)
                Timber.e("Error: ${resource.message}")
            }
        }
    }

    companion object {
        const val KEY_TYPE = "type"

        fun getInstance(type: PageType): SearchFragment {
            val fragment = SearchFragment()
            val bundle = Bundle().apply {
                putInt(KEY_TYPE, type.ordinal)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}