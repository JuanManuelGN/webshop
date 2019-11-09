package webshop.delivery

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol._
import webshop.application.command.{AddCustomerCommand, AddProductToStoreCommand}
import webshop.application.handler.HandlerImp
import webshop.infrastructure.dto.Dto

object WebShop extends App with HandlerImp {


  implicit val actorSystem = ActorSystem("WebShop")
  implicit val actorMaterializer = ActorMaterializer()

  implicit val format = jsonFormat3(AddProductToStoreCommand)
  implicit val formatCustomer = jsonFormat3(AddCustomerCommand)

  lazy val route =
    pathPrefix("api") {
      path("addUser") {
        put {
          entity(as[AddCustomerCommand]) { command =>
            complete {
              println(s"command $command")
              val customer: Dto = getDataHandler(command)
              println(s"added $customer")
              "Added customer"
            }
          }
        }

      } ~
        path("addProductToStore") {
          put {
            entity(as[AddProductToStoreCommand]) { command =>
              complete {
                println(s"command $command")
                val product: Dto = getDataHandler(command)
                println(s"dto $product")
                "Order received"
              }
            }
          }
        } ~
        path("addProductToShoppingCard") {
          post {
              // unmarshal with in-scope unmarshaller
              entity(as[AddProductToStoreCommand]) { p =>
                complete {
                  println(p)
                  "Order received"
                }
              }
          }
        } ~
        path("pay") {
          post {
            complete("This is a POST request.")
          }
        }
    }

  Http().bindAndHandle(route, "localhost", 8080)

  println("server started at 8080")
}
