package com.harissabil.glogslite.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

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
	val results: Results,

	@field:SerializedName("version")
	val version: String
)

data class PublishersItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class DevelopersItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class FranchisesItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class FirstAppearanceConceptsItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class ThemesItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class FirstAppearancePeopleItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class VideosItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class ReleasesItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class ObjectsItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class FirstAppearanceCharactersItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class PeopleItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class GenresItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class FirstAppearanceObjectsItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class CharactersItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class ConceptsItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class Results(

	@field:SerializedName("aliases")
	val aliases: String,

	@field:SerializedName("developers")
	val developers: List<DevelopersItem>?,

	@field:SerializedName("deck")
	val deck: String,

	@field:SerializedName("objects")
	val objects: List<ObjectsItem>,

	@field:SerializedName("similar_games")
	val similarGames: List<SimilarGamesItem>,

	@field:SerializedName("description")
	val description: String?,

	@field:SerializedName("killed_characters")
	val killedCharacters: Any,

	@field:SerializedName("expected_release_quarter")
	val expectedReleaseQuarter: Any,

	@field:SerializedName("videos")
	val videos: List<VideosItem>,

	@field:SerializedName("expected_release_month")
	val expectedReleaseMonth: Any,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem>?,

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("characters")
	val characters: List<CharactersItem>,

	@field:SerializedName("themes")
	val themes: List<ThemesItem>,

	@field:SerializedName("genres")
	val genres: List<GenresItem>?,

	@field:SerializedName("publishers")
	val publishers: List<PublishersItem>?,

	@field:SerializedName("expected_release_year")
	val expectedReleaseYear: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("date_last_updated")
	val dateLastUpdated: String,

	@field:SerializedName("franchises")
	val franchises: List<FranchisesItem>,

	@field:SerializedName("image")
	val image: Image,

	@field:SerializedName("first_appearance_locations")
	val firstAppearanceLocations: Any,

	@field:SerializedName("images")
	val images: List<ImagesItem>?,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("expected_release_day")
	val expectedReleaseDay: Any,

	@field:SerializedName("original_release_date")
	val originalReleaseDate: String?,

	@field:SerializedName("first_appearance_objects")
	val firstAppearanceObjects: List<FirstAppearanceObjectsItem>,

	@field:SerializedName("number_of_user_reviews")
	val numberOfUserReviews: Int,

	@field:SerializedName("first_appearance_concepts")
	val firstAppearanceConcepts: List<FirstAppearanceConceptsItem>,

	@field:SerializedName("people")
	val people: List<PeopleItem>,

	@field:SerializedName("first_appearance_characters")
	val firstAppearanceCharacters: List<FirstAppearanceCharactersItem>,

	@field:SerializedName("releases")
	val releases: List<ReleasesItem>,

	@field:SerializedName("date_added")
	val dateAdded: String,

	@field:SerializedName("first_appearance_people")
	val firstAppearancePeople: List<FirstAppearancePeopleItem>,

	@field:SerializedName("concepts")
	val concepts: List<ConceptsItem>,

	@field:SerializedName("original_game_rating")
	val originalGameRating: List<OriginalGameRatingItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("guid")
	val guid: String,

	@field:SerializedName("locations")
	val locations: Any,

	@field:SerializedName("image_tags")
	val imageTags: List<ImageTagsItem>
)

data class SimilarGamesItem(

	@field:SerializedName("api_detail_url")
	val apiDetailUrl: String,

	@field:SerializedName("site_detail_url")
	val siteDetailUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class ImagesItem(

	@field:SerializedName("icon_url")
	val iconUrl: String,

	@field:SerializedName("thumb_url")
	val thumbUrl: String,

	@field:SerializedName("tiny_url")
	val tinyUrl: String,

	@field:SerializedName("small_url")
	val smallUrl: String,

	@field:SerializedName("original")
	val original: String,

	@field:SerializedName("super_url")
	val superUrl: String,

	@field:SerializedName("screen_url")
	val screenUrl: String,

	@field:SerializedName("medium_url")
	val mediumUrl: String,

	@field:SerializedName("tags")
	val tags: String
)
