package com.app.zuludin.common.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showErrorSnackbar(view)
    }

    private fun showErrorSnackbar(view: View) {
        getViewModel().snackBarError.observe(this, Observer {
            it?.let { event ->
                event.getContentIfNotHandled()?.let { message ->
                    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    abstract fun getViewModel(): BaseViewModel
}