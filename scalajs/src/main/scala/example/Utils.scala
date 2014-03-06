package example

import scalajs.js.Dynamic

object Utils {

  implicit class Caster(val d: Dynamic) {

    def as[A](converter: Dynamic => A): Option[A] = {
      try {
        Some(converter(d))
      }catch {
        case t: Throwable => None
      }
    }
  }
}
