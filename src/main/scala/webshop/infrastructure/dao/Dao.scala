package webshop.infrastructure.dao

import webshop.infrastructure.Store
import webshop.infrastructure.dao.dto.{CustomerDaoDto, DaoDto, ProductDaoDto, ShoppingDaoDto}

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
      case s: ShoppingDaoDto => {
        val productId = s.product
        val count = s.count
        val customerEmail = s.email

        val productCountInStock = Store.getProductCount(productId)

        val response =
          if (count > productCountInStock) {
            Left("Not enough articles")
          } else {
            Right(Store.addProductToShoppingCard(s))
          }

        response
      }
    }

}
