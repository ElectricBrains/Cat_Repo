package com.example.myapplication.model.server_dto

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class ServerCat (
	@SerializedName("breeds") val breeds : List<Breeds>,
	@SerializedName("id") val id : String,
	@SerializedName("url") val url : String,
	@SerializedName("width") val width : Int,
	@SerializedName("height") val height : Int
)
data class Breeds (
	@SerializedName("weight") val weight : Weight?,
	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String,
	@SerializedName("cfa_url") val cfa_url : String,
	@SerializedName("vetstreet_url") val vetstreet_url : String,
	@SerializedName("vcahospitals_url") val vcahospitals_url : String,
	@SerializedName("temperament") val temperament : String,
	@SerializedName("origin") val origin : String,
	@SerializedName("country_codes") val country_codes : String,
	@SerializedName("country_code") val country_code : String,
	@SerializedName("description") val description : String,
	@SerializedName("life_span") val life_span : String,
	@SerializedName("indoor") val indoor : Int,
	@SerializedName("lap") val lap : Int,
	@SerializedName("alt_names") val alt_names : String,
	@SerializedName("adaptability") val adaptability : Int,
	@SerializedName("affection_level") val affection_level : Int,
	@SerializedName("child_friendly") val child_friendly : Int,
	@SerializedName("dog_friendly") val dog_friendly : Int,
	@SerializedName("energy_level") val energy_level : Int,
	@SerializedName("grooming") val grooming : Int,
	@SerializedName("health_issues") val health_issues : Int,
	@SerializedName("intelligence") val intelligence : Int,
	@SerializedName("shedding_level") val shedding_level : Int,
	@SerializedName("social_needs") val social_needs : Int,
	@SerializedName("stranger_friendly") val stranger_friendly : Int,
	@SerializedName("vocalisation") val vocalisation : Int,
	@SerializedName("experimental") val experimental : Int,
	@SerializedName("hairless") val hairless : Int,
	@SerializedName("natural") val natural : Int,
	@SerializedName("rare") val rare : Int,
	@SerializedName("rex") val rex : Int,
	@SerializedName("suppressed_tail") val suppressed_tail : Int,
	@SerializedName("short_legs") val short_legs : Int,
	@SerializedName("wikipedia_url") val wikipedia_url : String,
	@SerializedName("hypoallergenic") val hypoallergenic : Int
)

data class Weight (

	@SerializedName("imperial") val imperial : String,
	@SerializedName("metric") val metric : String
)
