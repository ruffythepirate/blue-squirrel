package util

import play.api.db.evolutions.Evolutions
import play.api.db.{Database, Databases}

import scala.util.Try

object DbMigrations {

  private var inMemoryDb: Option[Database] = None

  private var deliveredDbs = 0

  def getMigratedDb(): Database = {
    if(inMemoryDb == None) {
      inMemoryDb = Some(Databases.inMemory())
      Try {
        Evolutions.applyEvolutions(inMemoryDb.get)
      }
    }
    deliveredDbs += 1
    inMemoryDb.get
  }

  def cleanUpDb() = {
    deliveredDbs -= 1

    if(deliveredDbs == 0 && inMemoryDb != None) {
      Evolutions.cleanupEvolutions(inMemoryDb.get)
      inMemoryDb = None
    }
  }
}
