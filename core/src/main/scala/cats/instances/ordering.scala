package cats
package instances

import cats.data.INothing
import cats.instances.either._
import cats.syntax.apply._
import cats.kernel.instances.unit._

trait OrderingInstances {
  implicit val catsDecidableForOrdering: Decidable[Ordering] =
    new Decidable[Ordering] {

      /**
       * Note: resulting instances are law-abiding only when the functions used are injective (represent a one-to-one mapping)
       */
      def unit: Ordering[Unit] = Order[Unit].toOrdering

      def contramap[A, B](fa: Ordering[A])(f: B => A): Ordering[B] = fa.on(f)

      def product[A, B](fa: Ordering[A], fb: Ordering[B]): Ordering[(A, B)] =
        new Ordering[(A, B)] {
          def compare(x: (A, B), y: (A, B)): Int = {
            val z = fa.compare(x._1, y._1)
            if (z == 0) fb.compare(x._2, y._2) else z
          }
        }

      def sum[A, B](fa: Ordering[A], fb: Ordering[B]): Ordering[Either[A, B]] =
        new Ordering[Either[A, B]] {
          def compare(x: Either[A, B], y: Either[A, B]): Int =
            if (x.isRight)
              if (y.isRight)
                (x, y).mapN(fb.compare).toOption.get
              else 1
            else if (y.isLeft)
              (x.swap, y.swap).mapN(fa.compare).toOption.get
            else -1
        }

      override def zero[A]: Ordering[INothing] = Ordering.by(_ => ())
    }
}
private[instances] trait OrderingInstancesBinCompat0 {
  implicit val catsContravariantMonoidalForOrdering: ContravariantMonoidal[Ordering] =
    new ContravariantMonoidal[Ordering] {

      /**
       * Note: resulting instances are law-abiding only when the functions used are injective (represent a one-to-one mapping)
       */
      def unit: Ordering[Unit] = Order[Unit].toOrdering

      def contramap[A, B](fa: Ordering[A])(f: B => A): Ordering[B] = fa.on(f)

      def product[A, B](fa: Ordering[A], fb: Ordering[B]): Ordering[(A, B)] =
        new Ordering[(A, B)] {
          def compare(x: (A, B), y: (A, B)): Int = {
            val z = fa.compare(x._1, y._1)
            if (z == 0) fb.compare(x._2, y._2) else z
          }
        }
    }
}
