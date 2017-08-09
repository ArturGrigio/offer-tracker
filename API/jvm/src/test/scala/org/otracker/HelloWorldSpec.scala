package org.otracker

import org.junit.Test
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

/**
  * Just a quick example of how to use scalatest
  *
  * http://www.scalatest.org/user_guide/using_matchers
  */
class HelloWorldSpec extends JUnitSuite with Matchers {


  @Test
  def hello(): Unit = {
    1 + 1 should be (2)

    List.empty[String] should have size 0
  }
}
