package webshop.application.transformer

import webshop.domain.aggregate.{Aggregate, CustomerAggregateRoot, ProductAggregateRoot}
import webshop.infrastructure.dto.{CustomerDto, Dto, ProductDto}

trait AggregateRootToDto {

  val aggregateToDto: Aggregate => Dto =
    aggregate => {
      aggregate match {
        case par: ProductAggregateRoot =>
          ProductDto(par.getProductId, par.getProductPrice, par.getProductDescription)
        case car: CustomerAggregateRoot =>
          CustomerDto(car.getName, car.getEmail, car.getAccount)
      }
    }

}
