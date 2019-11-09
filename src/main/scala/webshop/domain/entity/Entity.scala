package webshop.domain.entity

abstract class Entity

case class ProductEntity(id: String,
                         price: Double,
                         description: String) extends Entity

case class Customer(name: String,
                    email: String) extends Entity

case class BankAccount(account: String) extends Entity
