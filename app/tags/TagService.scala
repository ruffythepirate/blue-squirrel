package tags

import com.google.inject.Inject

class TagService @Inject() (tagRepository: TagRepository) {

  def getOrCreateTagIds(tagTexts: Seq[String]) : Seq[Long] = {

     tagTexts
       .map(tagRepository.getOrInsert(_))
       .map(_.id)

  }
}
