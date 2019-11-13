package webshop.infrastructure.dto

abstract class Dto

case class ProductDto(id: String,
                      price: Double,
                      description: String) extends Dto

case class CustomerDto(name: String,
                       email: String,
                       account: String) extends Dto

case class ShoppingDto(productId: String,
                       count: Int) extends Dto
