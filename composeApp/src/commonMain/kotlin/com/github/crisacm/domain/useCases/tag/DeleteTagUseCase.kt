package com.github.crisacm.domain.useCases.tag

import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.repository.TagRepository

class DeleteTagUseCase(
  private val tagRepository: TagRepository,
) {
  suspend operator fun invoke(tag: Tag) {
    tagRepository.delete(tag)
  }
}
