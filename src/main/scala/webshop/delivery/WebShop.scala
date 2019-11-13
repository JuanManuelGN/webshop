package webshop.delivery

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol._
import webshop.application.command.{AddCustomerCommand, AddProductToShoppingCardCommand, AddProductToStoreCommand, PayCommand}
import webshop.application.handler.HandlerImp
import webshop.infrastructure.dto.Dto

object WebShop extends App with HandlerImp {


  implicit val actorSystem = ActorSystem("WebShop")
  implicit val actorMaterializer = ActorMaterializer()

  implicit val format = jsonFormat3(AddProductToStoreCommand)
  implicit val formatCustomer = jsonFormat3(AddCustomerCommand)
  implicit val formatShopping = jsonFormat3(AddProductToShoppingCardCommand)
  implicit val formatPay = jsonFormat1(PayCommand)

  lazy val route =
    pathPrefix("api") {
      path("addCustomer") {
        put {
          entity(as[AddCustomerCommand]) { command =>
            complete {
              val customer: Either[String, Dto] = getDataHandler(command)
              val response = customer.fold(l => l, r => "Customer added")
              response
            }
          }
        }

      } ~
        path("addProductToStore") {
          put {
            entity(as[AddProductToStoreCommand]) { command =>
              complete {
                val product: Either[String, Dto] = getDataHandler(command)
                val response = product.fold(l => l, r => "Product added")
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
                  val shopping: Either[String, Dto] = getDataHandler(command)
                  val response = shopping.fold(l => l, r => "Product added to shopping card")
                  response
                }
              }
          }
        } ~
        path("pay") {
          post {
            entity(as[PayCommand]) { command =>
              complete {
                val shopping: Either[String, Dto] = getDataHandler(command)
                val response = shopping.fold(l => l, r => "Purchase paid successfully")
                response
              }
            }
          }
        }
    }

  Http().bindAndHandle(route, "localhost", 8080)

  println("server started at 8080")
}
