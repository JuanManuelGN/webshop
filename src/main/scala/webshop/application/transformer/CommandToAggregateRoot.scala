package webshop.application.transformer

import webshop.application.command.{AddCustomerCommand, AddProductToStoreCommand, Command}
import webshop.domain.aggregate.{Aggregate, CustomerAggregateRoot, ProductAggregateRoot}

trait CommandToAggregateRoot {

  val commandToAggregateRoot: Command => Aggregate =
    command => {
      command match {
        case AddProductToStoreCommand(id, price, description) =>
          ProductAggregateRoot(id, price, description)
        case AddCustomerCommand(name, email, account) => CustomerAggregateRoot(name, email, account)
      }
    }
}
