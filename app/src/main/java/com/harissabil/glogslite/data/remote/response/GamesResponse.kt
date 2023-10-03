package com.harissabil.glogslite.data.remote.response

import com.google.gson.annotations.SerializedName

data class GamesResponse(

	@field:SerializedName("number_of_total_results")
	val numberOfTotalResults: Int? = null,

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("number_of_page_results")
	val numberOfPageResults: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>,

	@field:SerializedName("version")
	val version: String? = null
)

