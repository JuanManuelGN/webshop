package webshop.application.transformer

import webshop.application.command.{Command, ProductCommand}
import webshop.domain.aggregate.{Aggregate, ProductAggregateRoot}

trait CommandToAggregateRoot {

  val commandToAggregateRoot: Command => Aggregate =
    command => {
      command match {
        case ProductCommand(id, price, description) => ProductAggregateRoot(id, price, description)
      }
    }
}
