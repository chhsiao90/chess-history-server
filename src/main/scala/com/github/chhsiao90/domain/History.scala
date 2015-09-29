package com.github.chhsiao90.domain

import slick.driver.H2Driver.api._
import spray.json._

case class History(id: Option[Long], playerBlack: String, playerWhite: String, gameDate: String, winner: String)

object HistoryJsonProtocol extends DefaultJsonProtocol {
  implicit val historyFormat = jsonFormat5(History)
}

class Histories(tag: Tag) extends Table[History](tag, "HISTORIES") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def playerBlack = column[String]("PLAYER_BLACK", O.NotNull)
  def playerWhite = column[String]("PLAYER_WHITE", O.NotNull)
  def gameDate = column[String]("GAME_DATE", O.NotNull)
  def winner = column[String]("WINNER", O.NotNull)

  def * = (id.?, playerBlack, playerWhite, gameDate, winner) <> (History.tupled, History.unapply _)
}
