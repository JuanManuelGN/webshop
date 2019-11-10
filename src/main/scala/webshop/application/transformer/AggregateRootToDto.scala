package webshop.application.transformer

import webshop.domain.aggregate.{Aggregate, CustomerAggregateRoot, ProductAggregateRoot}
import webshop.infrastructure.dto.{CustomerDto, Dto, ProductDto}

trait AggregateRootToDto {

  val aggregateToDto: Either[String, Aggregate] => Either[String, Dto] =
    aggregate => {
      aggregate match {
        case Left(e) => Left(e)
        case Right(par: ProductAggregateRoot) =>
          Right(ProductDto(par.getProductId, par.getProductPrice, par.getProductDescription))
        case Right(car: CustomerAggregateRoot) =>
          Right(CustomerDto(car.getName, car.getEmail, car.getAccount))
      }
    }

}
