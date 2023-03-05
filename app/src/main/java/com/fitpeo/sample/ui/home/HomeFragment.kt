package com.fitpeo.sample.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.fitpeo.sample.R
import com.fitpeo.sample.databinding.FragmentHomeBinding
import com.fitpeo.sample.utils.Resource
import com.fitpeo.sample.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickData {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val viewModel: HomeViewModel by viewModels()
    private var mHomeModelList = mutableListOf<HomeBean>()
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = HomeAdapter(this)
        binding.idRecycler.adapter = adapter

        subscribeObservers()


        viewModel.setStateHomeData()
        binding.submit.setOnClickListener {}

    }

    override fun onClickEventsImage(pos: Int, data: HomeBean) {

    }

    private fun subscribeObservers() {
        viewModel.dataStateHomeRequest.observe(viewLifecycleOwner) { it ->
            when (it.status) {
                Resource.Status.LOADING -> {
                    Timber.d("loading...")
                    Utils.showProgress(
                        requireActivity().findViewById(R.id.progressBar),
                        requireActivity().window,
                        true
                    )
                }
                Resource.Status.SUCCESS -> {
                    Timber.d("success: ${it.data}")
                    mHomeModelList = mutableListOf<HomeBean>()
                    if (it.data != null) {
                        it.data.let {

                            mHomeModelList = mutableListOf<HomeBean>()
                            it.forEach { it1 ->

                                mHomeModelList.add(
                                    HomeBean(
                                        it1.albumId ?: "",
                                        it1.title ?: "",
                                        it1.url ?: "",
                                        it1.thumbnailUrl ?: ""
                                    )
                                )
                            }

                            adapter.update(mHomeModelList, requireContext())

                        }
                        Utils.showProgress(
                            requireActivity().findViewById(R.id.progressBar),
                            requireActivity().window,
                            false
                        )
                    }
                }
                Resource.Status.ERROR -> {
                    Timber.d("error: ${it.message}")
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()

                    Utils.showProgress(
                        requireActivity().findViewById(R.id.progressBar),
                        requireActivity().window,
                        false
                    )
                }
            }
        }
    }

}