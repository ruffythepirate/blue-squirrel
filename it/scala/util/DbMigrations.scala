package util

import play.api.db.evolutions.Evolutions
import play.api.db.{Database, Databases}

object DbMigrations {

  private val inMemoryDb = Databases.inMemory()

  private var migrationDone = false

  def getMigratedDb(): Database = {
    if(! migrationDone) {
      Evolutions.applyEvolutions(inMemoryDb)
      migrationDone = true
    }
    inMemoryDb
  }

}
