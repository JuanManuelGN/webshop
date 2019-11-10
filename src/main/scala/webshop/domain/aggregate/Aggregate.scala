package webshop.domain.aggregate

import webshop.domain.entity.{BankAccount, Customer, Product}

abstract class Aggregate

case class ProductAggregateRoot(private val p: Product) extends Aggregate {
  def getProductId: String = p.id
  def getProductPrice: Double = p.price
  def getProductDescription: String = p.description
}

object ProductAggregateRoot {
  def apply(productId: String,
            productPrice: Double,
            productDescription: String): ProductAggregateRoot =

    new ProductAggregateRoot(Product(productId, productPrice, productDescription))
}

case class CustomerAggregateRoot(private val customer: Customer,
                                 private val account: BankAccount) extends Aggregate {

  def getName: String = customer.name
  def getEmail: String = customer.email
  def getAccount: String = account.account
}

object CustomerAggregateRoot {
  def apply(name: String, email: String, account: String): CustomerAggregateRoot =
    new CustomerAggregateRoot(Customer(name, email), BankAccount(account))
}

case class ShoppingAggregateRoot(private val product: Product,
                                 private val count: Int,
                                 private val customer: Customer) extends Aggregate {
  def getProductId: String = product.id
  def getProductCount: Int = count
  def getCustomerEmail: String = customer.email
}

object ShoppingAggregateRoot {
  def apply(productId: String, count: Int, email: String): ShoppingAggregateRoot =
    new ShoppingAggregateRoot(Product(productId), count, Customer("", email))
}
