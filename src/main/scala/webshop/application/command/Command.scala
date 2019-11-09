package webshop.application.command

abstract class Command

case class ProductCommand(productId: String,
                          price: Double,
                          description: String) extends Command