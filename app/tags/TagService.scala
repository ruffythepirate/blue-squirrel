package tags

import com.google.inject.Inject

class TagService @Inject() (tagRepository: TagRepository) {

  def getOrCreateTagIds(tagTexts: Seq[String]) : Seq[Long] = {

     tagTexts
     .distinct
     .filter(! _.isEmpty)
       .map(tagRepository.getOrInsert(_))
       .map(_.id)
  }

  def getAll() : Seq[TagViewModel] = {
    tagRepository.findAll().map(TagViewModel(_))
  }
}
