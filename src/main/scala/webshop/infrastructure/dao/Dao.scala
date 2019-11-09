package webshop.infrastructure.dao

import webshop.infrastructure.Store
import webshop.infrastructure.dao.dto.{DaoDto, ProductDaoDto}

trait Dao {

  val getDataDao: DaoDto => DaoDto = dto =>
    dto match {
      case p: ProductDaoDto => Store.addProduct(p)
    }

}
