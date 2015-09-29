package com.github.chhsiao90.rest

import akka.actor.Actor
import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

import spray.routing._
import spray.http._
import spray.httpx.SprayJsonSupport._
import spray.httpx.unmarshalling._
import spray.json._
import MediaTypes._
import com.github.chhsiao90.domain._
import com.github.chhsiao90.domain.HistoryJsonProtocol._
import com.github.chhsiao90.dao.HistoryDao._

class HistoryServiceActor extends Actor with HistoryService {
  def actorRefFactory = context
  def receive = runRoute(routes)
}

trait HistoryService extends HttpService {
  val routes = 
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete(index)
        }
      }
    } ~
    path("test-json") {
      get {
        complete {
          History(None, "Jiang", "Hsiao", "2015-08-15", "Hsiao")
        }
      }
    } ~
    path("test-create") {
      get {
        complete {
          create(History(None, "Jiang", "Hsiao", "2015-08-15", "Hsiao"))
          Map("status" -> "success")
        }
      }
    } ~
    pathPrefix("history") {
      pathEnd {
        post {
          entity(as[History]) { history: History =>
            complete {
              create(history)
              Map("status" -> "success")
            }
          }
        }
      } ~
      path("all") {
        get {
          complete {
            getAll().toList
          }
        }
      } ~
      path(LongNumber) { id =>
        complete {
          getById(id)
        }
      }
    } 

  val index =
    <html>
      <body>
        <h1>Check History Page</h1>
      </body>
    </html>
}
