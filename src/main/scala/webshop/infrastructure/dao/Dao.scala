package webshop.infrastructure.dao

import webshop.infrastructure.Store
import webshop.infrastructure.dao.dto.{CustomerDaoDto, DaoDto, ProductDaoDto}

trait Dao {

  val getDataDao: DaoDto => Either[String, DaoDto] = dto =>
    dto match {
      case p: ProductDaoDto => Right(Store.addProduct(p))
      case c: CustomerDaoDto => {
        val customerAlredyExistis = Store.getEmailList.contains(c.email)
        if (customerAlredyExistis)
          Left("The customer already exists")
        else
          Right(Store.addCustomer(c))

      }
    }

}
