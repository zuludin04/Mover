package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class Videos(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("results")
	val results: List<VideoResult>? = null
)