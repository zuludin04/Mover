package com.app.zuludin.common

import androidx.lifecycle.ViewModelProvider
import com.app.zuludin.data.MoverRepository

class ViewModelFactor constructor(private val repository: MoverRepository) :
    ViewModelProvider.NewInstanceFactory() {

}