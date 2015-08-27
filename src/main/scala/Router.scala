import akka.http.scaladsl.server.Directives._
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import akka.http.scaladsl.server.StandardRoute

abstract class Resource {
  val name : String

  def index() : StandardRoute  = {
    throw new NotImplementedException
  }

  def show() : StandardRoute = {
    throw new NotImplementedException
  }

  def create() : StandardRoute = {
    throw new NotImplementedException
  }

  def update() : StandardRoute = {
    throw new NotImplementedException
  }

  def delete() : StandardRoute = {
    throw new NotImplementedException
  }
}

object Router {
  def resources(resource: Resource) = {
    pathPrefix("api") {
      pathPrefix("v1") {
        path(resource.name) {
          get { resource.index() } ~
          post { resource.create() }
        } ~
        path(resource.name / IntNumber) { id =>
          get { resource.show() } ~
          (put | patch) { resource.update() } ~
          delete { resource.delete() }
        }
      }
    }
  }
}
