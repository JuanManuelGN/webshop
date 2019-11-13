package webshop.application.transformer

import webshop.domain.aggregate.{Aggregate, CustomerAggregateRoot, ProductAggregateRoot, ShoppingAggregateRoot}
import webshop.infrastructure.dao.dto.{CustomerDaoDto, DaoDto, ProductDaoDto, ShoppingDaoDto}

trait DaoDtoToAggregateRoot {

  val daoDtoToAggregateRoot : Either[String, DaoDto] => Either[String, Aggregate] = daoDto => {
    daoDto match {
      case Left(e) => Left(e)
      case Right(ProductDaoDto(id, price, description)) =>
        Right(ProductAggregateRoot(id, price, description))
      case Right(CustomerDaoDto(name, email, account)) =>
        Right(CustomerAggregateRoot(name, email, account))
      case Right(ShoppingDaoDto(productId: String, count: Int, email: String)) =>
        Right(ShoppingAggregateRoot(productId, count, email))
      case _ => Left("No matching")
    }
  }
}
