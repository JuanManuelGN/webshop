package webshop.infrastructure.dao

import webshop.infrastructure.Store
import webshop.infrastructure.dao.dto._

trait Dao {

  val getDataDao: DaoDto => Either[String, DaoDto] = dto =>
    dto match {
      case p: ProductDaoDto => Right(Store.addProductToStore(p))

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

        val customerExists = Store.customerIsExists(customerEmail)

        val response =
          if (customerExists) {
            val productCountInStock = Store.getProductCount(productId)

            productCountInStock match {
              case 0 => Left("This article not exists in the shop")
              case x if x < count => Left("Not enough articles")
              case _ => Right(Store.addProductToShoppingCard(s))
            }
          } else {
            Left("Customer not exists")
          }

        response
      }
      case p: PayDaoDto => {
        val customerExists = Store.customerIsExistsAndHasShoppingCard(p.email)
        val response =
          if (customerExists) {
            Right(Store.pay(p))
          } else {
            Left("Customer not exists or doesn't has a shopping card")
          }
        response
      }
    }

}
