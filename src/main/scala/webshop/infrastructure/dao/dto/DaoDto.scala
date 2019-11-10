package webshop.infrastructure.dao.dto

abstract class DaoDto

case class ProductDaoDto(id: String,
                         price: Double,
                         description: String) extends DaoDto

case class CustomerDaoDto(name: String,
                          email: String,
                          account: String) extends DaoDto

case class ShoppingDaoDto(product: String,
                          count: Int,
                          email: String) extends DaoDto
