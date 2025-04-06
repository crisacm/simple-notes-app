package com.github.crisacm.domain.useCases.tag

import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow

class GetAllTagsFlowUseCase(
  private val tagRepository: TagRepository,
) {
  operator fun invoke(): Flow<List<Tag>> = tagRepository.getAllFlow()
}
