package example

import org.scalajs.jquery.jQuery
import Utils._
import Add._
import AddInstances._

object Calc {

  def main(): Unit = {
  }

  def execute(): Unit = {
    try {

      val c = for {
        a <- jQuery("#a").value().as{_.asInstanceOf[String].toInt}
        b <- jQuery("#b").value().as{_.asInstanceOf[String].toInt}
      } yield {
        add(a, b).toString
      }

      c match {
        case Some(v) => jQuery("#c").text(v)
        case None    => jQuery("#c").text("None")
      }

    } catch {
      case t: Throwable => println(t.getMessage)
    }
  }
}

object Add {
  trait Add[T] {
    def add(a: T, b: T): T
  }

  def add[T](a: T, b: T)(implicit ev: Add[T]): T = ev.add(a, b)
}

object AddInstances {
  import Add._

  implicit lazy val intAdd: Add[Int] = IntAdd

  implicit object IntAdd extends Add[Int] {
    def add(a: Int, b: Int): Int = a + b
  }
}
