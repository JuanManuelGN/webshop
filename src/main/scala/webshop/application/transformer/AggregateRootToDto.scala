package webshop.application.transformer

import webshop.domain.aggregate.{Aggregate, ProductAggregateRoot}
import webshop.infrastructure.dto.{Dto, ProductDto}

trait AggregateRootToDto {

  val aggregateToDto: Aggregate => Dto =
    aggregate => {
      aggregate match {
        case ProductAggregateRoot(product) =>
          ProductDto(product.id, product.price, product.description)
      }
    }

}
