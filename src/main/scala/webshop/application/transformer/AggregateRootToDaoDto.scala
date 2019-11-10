package webshop.application.transformer

import webshop.domain.aggregate.{Aggregate, CustomerAggregateRoot, ProductAggregateRoot, ShoppingAggregateRoot}
import webshop.infrastructure.dao.dto.{CustomerDaoDto, DaoDto, ProductDaoDto, ShoppingDaoDto}

trait AggregateRootToDaoDto {

  val aggregateToDaoDto: Aggregate => DaoDto = aggregate => {
    aggregate match {
      case par: ProductAggregateRoot => ProductDaoDto(par.getProductId,
                                                      par.getProductPrice,
                                                      par.getProductDescription)
      case car: CustomerAggregateRoot => CustomerDaoDto(car.getName,
                                                        car.getEmail,
                                                        car.getAccount)
      case sar: ShoppingAggregateRoot => ShoppingDaoDto(sar.getProductId,
                                                        sar.getProductCount,
                                                        sar.getCustomerEmail)
    }
  }
}
