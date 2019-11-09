package webshop.application.handler

import webshop.application.command.Command
import webshop.application.transformer.{AggregateRootToDto, CommandToAggregateRoot}
import webshop.infrastructure.dto.Dto
import webshop.infrastructure.respository.RepositoryImp

trait HandlerImp extends Handler
  with AggregateRootToDto with RepositoryImp with CommandToAggregateRoot {

  val getDataHandler: Command => Dto =
    command =>
      aggregateToDto.compose(getDataRepository).compose(commandToAggregateRoot)(command)
}
