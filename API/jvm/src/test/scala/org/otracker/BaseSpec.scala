package org.otracker

import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

/**
  * Mixes in [[Matchers]].
  *
  * All test classes should extend this base class.
  *
  * http://www.scalatest.org/user_guide/using_matchers
  */
class BaseSpec extends JUnitSuite with Matchers
