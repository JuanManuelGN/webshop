package webshop.application.command

abstract class Command

case class AddProductToStoreCommand(productId: String,
                                    price: Double,
                                    description: String) extends Command

case class AddCustomerCommand(name: String,
                              email: String,
                              account: String) extends Command