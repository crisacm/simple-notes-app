package com.github.crisacm.domain.useCases.tag

import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.repository.TagRepository

class AddTagUseCase(
  private val tagRepository: TagRepository,
) {
  suspend operator fun invoke(tag: Tag): Long = tagRepository.add(tag)
}
