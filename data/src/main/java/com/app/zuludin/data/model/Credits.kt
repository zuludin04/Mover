package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class Credits(

	@field:SerializedName("cast")
	val cast: List<CastItem>? = null,

	@field:SerializedName("crew")
	val crew: List<CrewItem>? = null
)