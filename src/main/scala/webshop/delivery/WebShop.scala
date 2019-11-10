package webshop.delivery

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol._
import webshop.application.command.{AddCustomerCommand, AddProductToShoppingCardCommand, AddProductToStoreCommand}
import webshop.application.handler.HandlerImp
import webshop.infrastructure.dto.Dto

object WebShop extends App with HandlerImp {


  implicit val actorSystem = ActorSystem("WebShop")
  implicit val actorMaterializer = ActorMaterializer()

  implicit val format = jsonFormat3(AddProductToStoreCommand)
  implicit val formatCustomer = jsonFormat3(AddCustomerCommand)
  implicit val formatShopping = jsonFormat3(AddProductToShoppingCardCommand)

  lazy val route =
    pathPrefix("api") {
      path("addCustomer") {
        put {
          entity(as[AddCustomerCommand]) { command =>
            complete {
              println(s"command $command")
              val customer: Either[String, Dto] = getDataHandler(command)
              val response = customer.fold(l => l, r => "Customer added")
              println(s"added $customer")
              response
            }
          }
        }

      } ~
        path("addProductToStore") {
          put {
            entity(as[AddProductToStoreCommand]) { command =>
              complete {
                println(s"command $command")
                val product: Either[String, Dto] = getDataHandler(command)
                val response = product.fold(l => l, r => "Product added")
                println(s"dto $product")
                response
              }
            }
          }
        } ~
        path("addProductToShoppingCard") {
          post {
              // unmarshal with in-scope unmarshaller
              entity(as[AddProductToShoppingCardCommand]) { command =>
                complete {
                  println(command)
                  val shopping: Either[String, Dto] = getDataHandler(command)
                  val response = shopping.fold(l => l, r => "Product added to shopping card")
                  response
                }
              }
          }
        } ~
        path("pay") {
          post {
            complete("This is a POST request.")
          }
        } ~
        path("getProducts") {
          get {
            complete("This is a POST request.")
          }
        }
    }

  Http().bindAndHandle(route, "localhost", 8080)

  println("server started at 8080")
}
