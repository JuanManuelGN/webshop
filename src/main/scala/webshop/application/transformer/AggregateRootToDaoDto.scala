package webshop.application.transformer

import webshop.domain.aggregate.{Aggregate, ProductAggregateRoot}
import webshop.infrastructure.dao.dto.{DaoDto, ProductDaoDto}

trait AggregateRootToDaoDto {

  val aggregateToDaoDto: Aggregate => DaoDto = aggregate => {
    aggregate match {
      case par: ProductAggregateRoot => ProductDaoDto(par.getProductId,
                                                      par.getProductPrice,
                                                      par.getProductDescription)
    }
  }
}
