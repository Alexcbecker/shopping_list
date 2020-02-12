package com.shopping_list.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.presentation.extension.toBrazilString
import com.presentation.viewModel.CreateShoppingListViewModel
import com.shopping_list.R
import com.shopping_list.databinding.FragmentCreateShoppingListBinding
import com.shopping_list.ui.adapter.GroceryItemListAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.Calendar

class CreateShoppingListFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var _viewDateBinding: FragmentCreateShoppingListBinding
    private val _createShoppingListViewModel: CreateShoppingListViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(
            R.id.nav_main
        )
    })
    private val _groceryItemsAdapter by lazy {
        GroceryItemListAdapter()
    }
    private val calendar = Calendar.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewDateBinding = FragmentCreateShoppingListBinding.inflate(inflater, container, false)
        _viewDateBinding.run {
            lifecycleOwner = this@CreateShoppingListFragment.viewLifecycleOwner
            viewModel = this@CreateShoppingListFragment._createShoppingListViewModel
            init()
        }
        return _viewDateBinding.root
    }

    private fun init() {
        subscribeToSuccessEvent()
        setupRecyclerView()
        setupListeners()
    }

    private fun subscribeToSuccessEvent() {
        _createShoppingListViewModel.successEvent.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                findNavController().popBackStack()
            }
        })
    }

    private fun setupRecyclerView() {
        val recyclerView = _viewDateBinding.recyclerViewItemsList
        recyclerView.adapter = _groceryItemsAdapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }

    private fun setupListeners() {
        _viewDateBinding.fabCreateAddGroceryItem.setOnClickListener {
            findNavController().navigate(R.id.action_createShoppingListFragment_to_groceryItemsListFragment)
        }

        _viewDateBinding.viewCalendar.setOnClickListener {
            showDatePickerDialog()
        }

        _viewDateBinding.buttonSaveShoppingList.setOnClickListener {
            if (!_viewDateBinding.edittextName.text.isNullOrBlank()) {
                _createShoppingListViewModel.createShoppingList()
            } else {
                showValidationError()
            }
        }

        _viewDateBinding.edittextName.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) clearFieldErrors()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerDialog(
            context!!,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        if (view?.isShown!!) {
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            _viewDateBinding.edittextDate.setText(calendar.time.toBrazilString())
            _createShoppingListViewModel.shoppingList.value?.date = calendar.time
        }
    }

    private fun showValidationError() {
        _viewDateBinding.textInputLayoutName.isErrorEnabled = true
        _viewDateBinding.textInputLayoutName.error = getString(R.string.validation_mandatory_field)
    }

    private fun clearFieldErrors() {
        _viewDateBinding.textInputLayoutName.isErrorEnabled = false
    }
}
