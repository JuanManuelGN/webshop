package webshop.infrastructure.dto

abstract class Dto

case class ProductDto(id: String,
                      price: Double,
                      description: String) extends Dto
