package com.omkar.cavistademo.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.omkar.cavistademo.R
import com.omkar.cavistademo.data.model.Image
import com.omkar.cavistademo.databinding.SearchImageListFragmentBinding
import com.omkar.cavistademo.ui.custom.EndlessRecyclerOnScrollListener
import com.omkar.cavistademo.ui.viewmodel.ImageListViewModel
import com.omkar.cavistademo.ui.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class ImageListFragment : Fragment() {

    private lateinit var binding: SearchImageListFragmentBinding

    private var imageList = mutableListOf<Image>()

    private var activateEndScrolling = false


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ImageListViewModel

    companion object {
        fun newInstance() = ImageListFragment()
    }

    private var page: Int = 1
    lateinit var layoutManager: GridLayoutManager

    private var searchedQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.search_image_list_fragment,
                container,
                false
            )
        layoutManager = GridLayoutManager(context, 3)
        binding.imageListRecyclerView.layoutManager = layoutManager
        binding.imageListRecyclerView.adapter =
            ImageListAdapter(imageList, this)

        val endlessRecyclerOnScrollListener: EndlessRecyclerOnScrollListener =
            object : EndlessRecyclerOnScrollListener(layoutManager) {
                override fun onLoadMore(current_page: Int) {
                    if (activateEndScrolling) {
                        loadNextPage(current_page, searchedQuery)
                    }
                }
            }

        binding.imageListRecyclerView.addOnScrollListener(endlessRecyclerOnScrollListener)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelFactory.create(ImageListViewModel::class.java)
        observeViewModel()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    /**
     *  Observe the model changes once user search for the image or in case of infinite scroll
     *  Hide/show the progress bar while data loading ongoing
     */
    private fun observeViewModel() {
        viewModel.articleLiveData.observe(viewLifecycleOwner, Observer {
            activateEndScrolling = it.isNotEmpty() && it.size >= 50
            for (t in it) {
                if (t.images != null) {
                    for (img in t.images!!) {
                        img.imageTitle = t.title
                        imageList.add(img)
                    }
                }
            }
            if (imageList.isEmpty()) {
                binding.noDataTextView.visibility = View.VISIBLE
            } else
                binding.noDataTextView.visibility = View.GONE

            binding.imageListRecyclerView.adapter?.notifyDataSetChanged()
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else
                binding.progressBar.visibility = View.GONE

        })
    }


    /**
     *  Load next page of the image data when user scroll the page
     */
    private fun loadNextPage(currentPage: Int, searchTerm: String) {
        viewModel.fetchImageList(currentPage, searchTerm)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_main, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(
            ImageListViewModel.DebouncingQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let {
                    searchedQuery = it
                    imageList.clear()
                    (binding.imageListRecyclerView.adapter as ImageListAdapter).notifyDataSetChanged()
                    if (it.isNotEmpty()) {
                        viewModel.fetchImageList(page, it)
                    }
                }
            }
        )

        super.onCreateOptionsMenu(menu, inflater)

    }


    /**
     *  Navigate user to image details screen once user click on a particular image
     */
    fun navigateToImageDetailsScreen(image: Image) {
        val intent = Intent(activity, ImageDetailsActivity::class.java)
        intent.putExtra(getString(R.string.key_image_title), image.imageTitle)
        intent.putExtra(getString(R.string.key_image_id), image.id)
        intent.putExtra(getString(R.string.key_image_link), image.link)
        activity?.startActivity(intent)
    }


}
