package webshop.domain.entity

abstract class Entity

case class Product(id: String,
                   price: Double = 0.0,
                   description: String = "") extends Entity

case class Customer(name: String,
                    email: String) extends Entity

case class BankAccount(account: String) extends Entity
