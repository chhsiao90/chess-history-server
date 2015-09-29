package com.github.chhsiao90.dao

import com.github.chhsiao90.domain._
import slick.driver.H2Driver.api._
import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

object HistoryDao {
  private val histories = TableQuery[Histories]
  private val db = Database.forConfig("h2mem")

  db.run(histories.schema.create)

  def create(history: History) {
    db.run(histories ++= Seq(history))
  }

  def getAll() : Seq[History] = {
    Await.result(db.run(histories.map(x => x).result), Duration.Inf)
  }

  def getById(id: Long) : Option[History] = {
    Await.result(
      db.run(histories.filter(x => x.id === id).result)
        .map(seq => if (seq.isEmpty) None else Some(seq(0))),
        Duration.Inf)
  }
}
