package webshop.application.handler

import webshop.application.command.Command
import webshop.infrastructure.dto.Dto

trait Handler {
  val getDataHandler: Command => Dto
}
