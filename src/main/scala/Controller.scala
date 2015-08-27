import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.StandardRoute

trait Controller {
  val name: String

  def index(): StandardRoute = {
    Directives.complete(HttpResponse(404))
  }

  def show(): StandardRoute = {
    Directives.complete(HttpResponse(404))
  }

  def create(): StandardRoute = {
    Directives.complete(HttpResponse(404))
  }

  def update(): StandardRoute = {
    Directives.complete(HttpResponse(404))
  }

  def delete(): StandardRoute = {
    Directives.complete(HttpResponse(404))
  }

  def routes = Directives.path(name) {
    Directives.get { index() } ~
    Directives.post { create() }
  } ~
  Directives.path(name / Directives.IntNumber) { id =>
    Directives.get { show() } ~
    (Directives.put | Directives.patch) { update() } ~
    Directives.delete { delete() }
  }
}
