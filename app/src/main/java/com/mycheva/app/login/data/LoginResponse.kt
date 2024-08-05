package com.mycheva.app.login.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean,

	@field:SerializedName("token")
	val token: String
)

data class Data(

	@field:SerializedName("fakultas")
	val fakultas: String,

	@field:SerializedName("nim")
	val nim: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("divisi")
	val divisi: String,

	@field:SerializedName("prodi")
	val prodi: String,

	@field:SerializedName("url_foto_profile")
	val urlFotoProfile: Any,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
