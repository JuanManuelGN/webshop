package webshop.application.transformer

import webshop.domain.aggregate._
import webshop.infrastructure.dto._

trait AggregateRootToDto {

  val aggregateToDto: Either[String, Aggregate] => Either[String, Dto] =
    aggregate => {
      aggregate match {
        case Left(e) => Left(e)

        case Right(par: ProductAggregateRoot) =>
          Right(ProductDto(par.getProductId, par.getProductPrice, par.getProductDescription))

        case Right(car: CustomerAggregateRoot) =>
          Right(CustomerDto(car.getName, car.getEmail, car.getAccount))

        case Right(sar: ShoppingAggregateRoot) =>
          Right(ShoppingDto(sar.getProductId, sar.getProductCount))

        case Right(payar: PayAggregateRoot) => Right(PayDto(payar.getEmail))
      }
    }

}
