package webshop.domain.entity

abstract class Entity

case class ProductEntity(id: String,
                         price: Double,
                         description: String) extends Entity
