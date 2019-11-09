package webshop.domain.aggregate

import webshop.domain.entity.ProductEntity

abstract class Aggregate

case class ProductAggregateRoot(private val p: ProductEntity) extends Aggregate {
  def getProductId: String = p.id
  def getProductPrice: Double = p.price
  def getProductDescription: String = p.description
}

object ProductAggregateRoot {
  def apply(productId: String,
            productPrice: Double,
            productDescription: String): ProductAggregateRoot =

    new ProductAggregateRoot(ProductEntity(productId, productPrice, productDescription))
}
