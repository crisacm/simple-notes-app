package com.github.crisacm.domain.useCases.tag

import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.repository.TagRepository

class GetByIdTagUseCase(
  private val tagRepository: TagRepository,
) {
  suspend operator fun invoke(id: Long): Tag? = tagRepository.getById(id)
}
