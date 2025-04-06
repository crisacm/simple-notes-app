package com.github.crisacm.domain.model

data class Tag(
  val id: Long?,
  val name: String,
  var createdAt: Long,
  var updatedAt: Long,
)
