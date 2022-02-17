package brown.bag.experiment.examples

import eu.timepit.refined.W
import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.numeric.{Even, Greater, Less}
import eu.timepit.refined.predicates.all.{And, Forall}
import org.scalatest.matchers.should.Matchers._

class Example5_ListOfIntsSpec extends org.scalatest.flatspec.AnyFlatSpec {

  "List[Int] Refined Forall[Greater[W.`3`.T]]" should "recognise a List(4, 8) as valid" in {

    type AListOfInts = List[Int] Refined Forall[Greater[W.`3`.T]]

    object JourneyIds extends RefinedTypeOps[AListOfInts, List[Int]]

    val actualJourneyIds: Either[String, AListOfInts] = JourneyIds.from(List(4, 8))

    //... compilation time validation works only for literal types
    actualJourneyIds should be(Right(JourneyIds.unsafeFrom(List(4, 8))))

  }

  "Forall predicate with Int" should "works" in {

    type AListOfInts = List[Int] Refined Forall[Greater[W.`3`.T] And Even And Less[W.`10`.T]]

    object JourneyIds extends RefinedTypeOps[AListOfInts, List[Int]]

    val actualJourneyIds: Either[String, AListOfInts] = JourneyIds.from(List(4, 8))

    //... compilation time validation works only for literal types
    actualJourneyIds should be(Right(JourneyIds.unsafeFrom(List(4, 8))))

  }

}
