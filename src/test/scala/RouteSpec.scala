import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server.Directives.complete

class RouteSpec extends WordSpec with Matchers with ScalatestRouteTest {
  class UsersController extends Resource {
    val name = "users"
    override def index = complete { "index" }
    override def show = complete { "show" }
    override def create = complete { "create" }
    override def update = complete { "update" }
    override def delete = complete { "delete" }
  }

  Get("/api/v1/users") ~> Router.resources(new UsersController) ~> check {
    responseAs[String] shouldEqual "index"
  }

  Post("/api/v1/users") ~> Router.resources(new UsersController) ~> check {
    responseAs[String] shouldEqual "create"
  }
  Get("/api/v1/users/1") ~> Router.resources(new UsersController) ~> check {
    responseAs[String] shouldEqual "show"
  }

  Patch("/api/v1/users/1") ~> Router.resources(new UsersController) ~> check {
    responseAs[String] shouldEqual "update"
  }

  Put("/api/v1/users/1") ~> Router.resources(new UsersController) ~> check {
    responseAs[String] shouldEqual "update"
  }

  Delete("/api/v1/users/1") ~> Router.resources(new UsersController) ~> check {
    responseAs[String] shouldEqual "delete"
  }

  Get("/api/v1/users/foo") ~> Router.resources(new UsersController) ~> check {
    handled shouldBe false
  }

  Delete("/api/v1/users") ~> Router.resources(new UsersController) ~> check {
    handled shouldBe false
  }
}
