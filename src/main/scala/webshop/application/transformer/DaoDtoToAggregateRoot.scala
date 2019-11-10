package webshop.application.transformer

import webshop.domain.aggregate.{Aggregate, CustomerAggregateRoot, ProductAggregateRoot}
import webshop.infrastructure.dao.dto.{CustomerDaoDto, DaoDto, ProductDaoDto}

trait DaoDtoToAggregateRoot {

  val daoDtoToAggregateRoot : Either[String, DaoDto] => Either[String, Aggregate] = daoDto => {
    daoDto match {
      case Left(e) => Left(e)
      case Right(ProductDaoDto(id, price, description)) =>
        Right(ProductAggregateRoot(id, price, description))
      case Right(CustomerDaoDto(name, email, account)) =>
        Right(CustomerAggregateRoot(name, email, account))
      case _ => Left("No matching")
    }
  }
}
