package com.github.crisacm.domain.useCases.tag

import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.repository.TagRepository

class GetByTagUseCase(
  private val tagRepository: TagRepository
) {
  suspend operator fun invoke(name: String): Tag? = tagRepository.getBy(name)
}
