package webshop.infrastructure.dao.dto

abstract class DaoDto

case class ProductDaoDto(id: String,
                         price: Double,
                         description: String) extends DaoDto
