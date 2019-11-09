package webshop.application.transformer

import webshop.domain.aggregate.{Aggregate, ProductAggregateRoot}
import webshop.infrastructure.dao.dto.{DaoDto, ProductDaoDto}

trait DaoDtoToAggregateRoot {

  val daoDtoToAggregateRoot : DaoDto => Aggregate = daoDto => {
    daoDto match {
      case ProductDaoDto(id, price, description) =>
        ProductAggregateRoot(id, price, description)
    }
  }
}
