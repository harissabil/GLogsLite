package com.harissabil.glogslite.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("number_of_total_results")
	val numberOfTotalResults: Int,

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("offset")
	val offset: Int,

	@field:SerializedName("number_of_page_results")
	val numberOfPageResults: Int,

	@field:SerializedName("limit")
	val limit: Int,

	@field:SerializedName("error")
	val error: String,

	@field:SerializedName("results")
	val results: List<ResultsItem>,

	@field:SerializedName("version")
	val version: String
)

data class ResultsItem(

	@field:SerializedName("image")
	val image: Image,

	@field:SerializedName("aliases")
	val aliases: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("expected_release_day")
	val expectedReleaseDay: Any,

	@field:SerializedName("deck")
	val deck: String,

	@field:SerializedName("resource_type")
	val resourceType: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("expected_release_quarter")
	val expectedReleaseQuarter: Any,

	@field:SerializedName("original_release_date")
	val originalReleaseDate: String,

	@field:SerializedName("number_of_user_reviews")
	val numberOfUserReviews: Int,

	@field:SerializedName("expected_release_month")
	val expectedReleaseMonth: Any,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem>,

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("date_added")
	val dateAdded: String,

	@field:SerializedName("original_game_rating")
	val originalGameRating: List<OriginalGameRatingItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("guid")
	val guid: String,

	@field:SerializedName("expected_release_year")
	val expectedReleaseYear: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_tags")
	val imageTags: List<ImageTagsItem>,

	@field:SerializedName("date_last_updated")
	val dateLastUpdated: String
)

data class OriginalGameRatingItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class ImageTagsItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("name")
	val name: String
)

data class Image(

	@field:SerializedName("icon_url")
	val iconUrl: String,

	@field:SerializedName("screen_large_url")
	val screenLargeUrl: String,

	@field:SerializedName("thumb_url")
	val thumbUrl: String,

	@field:SerializedName("tiny_url")
	val tinyUrl: String,

	@field:SerializedName("small_url")
	val smallUrl: String,

	@field:SerializedName("super_url")
	val superUrl: String,

	@field:SerializedName("original_url")
	val originalUrl: String,

	@field:SerializedName("screen_url")
	val screenUrl: String,

	@field:SerializedName("medium_url")
	val mediumUrl: String,

	@field:SerializedName("image_tags")
	val imageTags: String
)

data class PlatformsItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("abbreviation")
	val abbreviation: String
)
